package com.hualala.client.util;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public final class HashUtils {

    public static String sha256(String str) {
        return Hashing.sha256().newHasher().putString(str, StandardCharsets.UTF_8).hash().toString();
    }

    public static String murmur128(String str) {
        return String.valueOf(Hashing.murmur3_128().hashString(str, StandardCharsets.UTF_8).asLong());
    }
}
