package org.example9.config;

import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomerTypeExcludeFilter extends TypeExcludeFilter {

    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        System.out.println(metadataReader.getClassMetadata().getClassName()+"----" + metadataReader.getClassMetadata().getClassName().equals(BeanAutoConfiguration.class.getName()));

        return metadataReader.getClassMetadata().getClassName().equals(BeanAutoConfiguration.class.getName());
//        return super.match(metadataReader, metadataReaderFactory);
    }
}
