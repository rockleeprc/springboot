package com.hualala;

import com.alibaba.cloud.commons.io.Charsets;
import com.google.common.hash.Hashing;
import org.junit.Test;
import org.springframework.util.DigestUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;
import java.util.zip.CRC32;

public class KeyCrashTest {

    private static final String LONG_KEY = "sadfasdfasdfdsfadsf阿斯顿发送到发送地方多少发多少粉丝都发多少粉丝到发送发送地方发撒水淀粉撒到发送地方阿斯顿发送方的 发撒到发送地方发阿斯顿发阿斯顿发撒地方撒地方放大地方";
    private static final String SHORT_KEY = "abc";


    @Test
    public void testGetBucketId() throws NoSuchAlgorithmException {

        byte[] bucketId = getBucketId(SHORT_KEY.getBytes(StandardCharsets.UTF_8), 30);
        for (byte b : bucketId) {
            System.out.println(b);
        }

        String bucket = getBucket(SHORT_KEY.getBytes(StandardCharsets.UTF_8), 30);
        System.out.println(bucket);

    }

    /**
     * @param key
     * @param bit bucket 大小 2^30=1073741824
     * @return
     * @throws NoSuchAlgorithmException <p>
     *                                  参考：https://blog.csdn.net/ZopaulCode/article/details/79823032
     */
    public byte[] getBucketId(byte[] key, Integer bit) throws NoSuchAlgorithmException {

        MessageDigest mdInst = MessageDigest.getInstance("MD5");

        mdInst.update(key);

        byte[] md = mdInst.digest();

        byte[] r = new byte[(bit - 1) / 7 + 1];// 因为一个字节中只有7位能够表示成单字符

        int a = (int) Math.pow(2, bit % 7) - 2;

        md[r.length - 1] = (byte) (md[r.length - 1] & a);

        System.arraycopy(md, 0, r, 0, r.length);

        for (int i = 0; i < r.length; i++) {

            if (r[i] < 0) r[i] &= 127;

        }

        return r;

    }

    public String getBucket(byte[] key, Integer bit) throws NoSuchAlgorithmException {

        MessageDigest mdInst = MessageDigest.getInstance("MD5");

        mdInst.update(key);

        byte[] md = mdInst.digest();

        byte[] r = new byte[(bit - 1) / 7 + 1];// 因为一个字节中只有7位能够表示成单字符
        StringBuilder sb = new StringBuilder(r.length);
        int a = (int) Math.pow(2, bit % 7) - 2;

        md[r.length - 1] = (byte) (md[r.length - 1] & a);

        System.arraycopy(md, 0, r, 0, r.length);

        for (int i = 0; i < r.length; i++) {
            if (r[i] < 0) {
                r[i] &= 127;
            }
            sb.append(r[i]);
        }
//        System.out.println(Arrays.toString(r));
        return sb.toString();
    }


    @Test
    public void testString2Key() {
        System.out.println(longHash(LONG_KEY));
        System.out.println(longHash(SHORT_KEY));

    }

    public long longHash(String string) {
        long h = 98764321261L;
        int l = string.length();
        char[] chars = string.toCharArray();

        for (int i = 0; i < l; i++) {
            h = 31 * h + chars[i];
        }
        return h;
    }

    @Test
    public void testMd5() {
        // 太长
        System.out.println(md5(LONG_KEY));
        System.out.println(md5(SHORT_KEY));
    }

    public String md5(String key) {
        return DigestUtils.md5DigestAsHex(key.getBytes(StandardCharsets.UTF_8));
    }


    @Test
    public void testELFHash() {
        System.out.println(ELFHash(LONG_KEY));
        System.out.println(ELFHash(SHORT_KEY));
    }

    public long ELFHash(String strUri) {
        long hash = 0;
        long x = 0;
        for (int i = 0; i < strUri.length(); i++) {
            hash = (hash << 4) + strUri.charAt(i);
            if ((x = hash & 0xF0000000L) != 0) {
                hash ^= (x >> 24);
                hash &= ~x;
            }
        }
        return (hash & 0x7FFFFFFF);
    }

    @Test
    public void testCRC32() {
        System.out.println(getCRC32(LONG_KEY));
        System.out.println(getCRC32(SHORT_KEY));
    }

    public static long getCRC32(String key) {
        long crc32Value = 0L;
        try {
            CRC32 crc32 = new CRC32();

            crc32.update(key.getBytes(StandardCharsets.UTF_8));
            crc32Value = crc32.getValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return crc32Value;
    }

    @Test
    public void testGuavaMurmur() {
        System.out.println(Hashing.murmur3_128().hashString(LONG_KEY, Charsets.UTF_8).asLong());
        System.out.println(Hashing.murmur3_32().hashString(LONG_KEY, Charsets.UTF_8).asInt());

        System.out.println(Hashing.murmur3_128().hashString(SHORT_KEY, Charsets.UTF_8).asLong());
        System.out.println(Hashing.murmur3_32().hashString(SHORT_KEY, Charsets.UTF_8).asInt());
    }


    @Test
    public void testRedisMurmur() {
//        long hash64 = Hashing.murmur3_128().hashBytes(LONG_KEY.getBytes(StandardCharsets.UTF_8)).asLong();
//        int hash1 = (int) hash64;
//        int hash2 = (int) (hash64 >>> 32);
//        int combinedHash = hash1 + (32 * hash2);
//        System.out.println(combinedHash);

        int speed = (int) System.currentTimeMillis();
//        long hash64 = MurmurHash.hash64A(LONG_KEY.getBytes(StandardCharsets.UTF_8), speed);
//        System.out.println(hash64);
    }

    private static final String FILE_PATH = "/Users/admin/uuid_5000W.txt";

    @Test
    public void genericUUIDToFile() {

        System.out.println(UUID.randomUUID());
        int loop = 100_000_0 * 50;

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (int i = 0; i <= loop; i++) {
                bufferedWriter.write(UUID.randomUUID().toString());
                bufferedWriter.newLine();
                if (i % 10000 == 0)
                    System.out.println(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final int BUCKET_SIZE = 18;
    private static final Map<String, Set> CACHE = new HashMap<>();
    private static final Map<String, String> CACHE2 = new HashMap<>();
    private static final AtomicLong INR = new AtomicLong(0);

    @Test
    public void testHashCrash() throws NoSuchAlgorithmException {
        List<String> uuids = readFile("/Users/admin/uuid_200W.txt");

        for (String uuid : uuids) {
            // 获取bucketId
            String bucketId = getBucket(uuid.getBytes(StandardCharsets.UTF_8), 16);

            // 通过bucketId -> field<key,value>
            if (CACHE.containsKey(bucketId)) {
                Set bucketSet = CACHE.get(bucketId);
//                String key = getKey(uuid);
                Long key = ELFHash(uuid);

                if (bucketSet.contains(key)) {
                    INR.incrementAndGet();
//                    System.out.println(uuid);
                } else {
                    bucketSet.add(key);
                }
            } else {
                HashSet<String> values = new HashSet<>();
                values.add(getKey(uuid));
                CACHE.put(bucketId, values);
            }
        }
        System.out.println(CACHE.size());
        System.out.println(INR.get());
        System.out.println("ending");
    }

    @Test
    public void testBucketCrash2() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/admin/uuid_5000W.txt"))) {

            String uuid;
            int loop = 0;
            while ((uuid = bufferedReader.readLine()) != null) {
                String bucketId = getBucket2(uuid);

                // 通过bucketId -> field<key,value>
                if (CACHE2.containsKey(bucketId)) {
                    INR.incrementAndGet();
                } else {
                    CACHE2.put(bucketId, uuid);
                }

                if (loop % 100000 == 0)
                    System.out.println(loop);

                loop++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(CACHE2.size());
        System.out.println(INR.get());
        System.out.println("ending");
    }

    @Test
    public void testBucketCrash3() throws IOException {
        AtomicLong inr = new AtomicLong(0);
        Stream<String> lines = Files.lines(Paths.get("/Users/admin/uuid_5000W.txt"));
        lines.forEach(line -> {
            String bucketId = getBucket2(line);
            // 通过bucketId -> field<key,value>
            if (CACHE2.containsKey(bucketId)) {
                INR.incrementAndGet();
            } else {
                CACHE2.put(bucketId, line);
            }

            if (inr.incrementAndGet() % 10000000 == 0) {
                System.out.println(inr.get());
                System.out.println(CACHE2.size());
                System.out.println(INR.get());
                System.out.println("ending");
            }
        });

    }

    public String getBucket2(String key) {
        long res = Hashing.murmur3_128().hashString(key, StandardCharsets.UTF_8).asLong();
//        int res = Hashing.murmur3_32().hashString(key, StandardCharsets.UTF_8).asInt();
        return String.valueOf(res);
    }

    public String getKey(String key) {
        int res = Hashing.murmur3_32().hashString(SHORT_KEY, StandardCharsets.UTF_8).asInt();
//        int res = Hashing.murmur3_128().hashString(SHORT_KEY, StandardCharsets.UTF_8).asInt();
        return String.valueOf(res);
    }

    @Test
    public void test() throws NoSuchAlgorithmException {
        String uuid1 = "dc296d71-d156-4a20-8e5b-963e0308abe5";
        String uuid2 = "1";

        Long key1 = Hashing.murmur3_128().hashString(uuid1, StandardCharsets.UTF_8).asLong();
        Long key2 = Hashing.murmur3_128().hashString(uuid2, StandardCharsets.UTF_8).asLong();

        System.out.println(Hashing.murmur3_32().hashString(uuid1, StandardCharsets.UTF_8).asInt());
        System.out.println(Hashing.murmur3_32().hashString(uuid1, StandardCharsets.UTF_8).asInt());
        System.out.println(key1);
        System.out.println(key2);
    }


    private List<String> readFile(String filePath) {
        List<String> list = new ArrayList();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Test
    public void t() {
        System.out.println(this.getClass().getSimpleName());
    }
}
