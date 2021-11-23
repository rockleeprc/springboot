package com.hualala.client.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

@Component
public class RedisLettuceConfig extends CachingConfigurerSupport {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory, ProtoStuffRedisSerializer protoStuffRedisSerializer) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // key Serializer
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        // value Serializer
        redisTemplate.setValueSerializer(protoStuffRedisSerializer);
        redisTemplate.setHashValueSerializer(protoStuffRedisSerializer);

        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        return redisTemplate;
    }

    /**
     * json 序列化
     *
     * @return
     * @see RedisLettuceConfig#protoStuffRedisSerializer() 推荐使用
     */
    @Bean
    @Deprecated
    public Jackson2JsonRedisSerializer jackson2JsonRedisSerializer() {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.activateDefaultTyping(objectMapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        return jackson2JsonRedisSerializer;
    }

    /**
     * protostuff 序列化
     *
     * @return
     */
    @Bean
    public ProtoStuffRedisSerializer protoStuffRedisSerializer() {
        return new ProtoStuffRedisSerializer();
    }

    @Bean
    public HashOperations hashOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForHash();
    }

    public static class ProtoStuffRedisSerializer implements RedisSerializer<Object> {

        private static final Schema<ObjectWrapper> schema = RuntimeSchema.getSchema(ObjectWrapper.class);

        @Override
        public byte[] serialize(Object t) throws SerializationException {
            LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
            byte[] bytes;
            try {
                bytes = ProtostuffIOUtil.toByteArray(new ObjectWrapper(t), schema, buffer);
            } finally {
                buffer.clear();
            }
            return bytes;
        }

        @Override
        public Object deserialize(byte[] bytes) throws SerializationException {
            if (bytes == null || bytes.length == 0) {
                return null;
            }
            try {
                ObjectWrapper objectWrapper = new ObjectWrapper();
                ProtostuffIOUtil.mergeFrom(bytes, objectWrapper, schema);
                return objectWrapper.getObject();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        protected static class ObjectWrapper {
            private Object object;

            ObjectWrapper() {
            }

            ObjectWrapper(Object object) {
                this.object = object;
            }

            public Object getObject() {
                return object;
            }

            public void setObject(Object object) {
                this.object = object;
            }
        }
    }
}
