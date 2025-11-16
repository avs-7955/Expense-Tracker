package com.kyra.Expense.Tracker.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.UUID;

public class UUIDGenerator {

    /**
     * Generates a deterministic UUIDv5 using a namespace and one or more name components.
     */
    public static UUID generate(NamespaceUUID namespace, String... names) {

        // Combine all name components into one string
        StringBuilder builder = new StringBuilder();
        for (String n : names) {
            if (n != null) {
                builder.append(n);
            }
        }

        return generateUUIDv5(namespace.getNamespace(), builder.toString());
    }

    /**
     * Real UUIDv5 generation using SHA-1 hashing.
     */
    private static UUID generateUUIDv5(UUID namespace, String name) {
        try {
            // Prepare SHA-1
            MessageDigest sha1 = MessageDigest.getInstance("SHA-1");

            // Feed namespace bytes
            sha1.update(toBytes(namespace));

            // Feed name bytes
            sha1.update(name.getBytes(StandardCharsets.UTF_8));

            byte[] hash = sha1.digest();

            // Adjust to UUIDv5 format
            hash[6] &= 0x0f;
            hash[6] |= 0x50; // Version 5

            hash[8] &= 0x3f;
            hash[8] |= 0x80; // IETF variant

            // Convert to UUID
            return fromBytes(hash);

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate UUIDv5", e);
        }
    }

    private static byte[] toBytes(UUID uuid) {
        byte[] b = new byte[16];
        long msb = uuid.getMostSignificantBits();
        long lsb = uuid.getLeastSignificantBits();

        for (int i = 0; i < 8; i++) {
            b[i] = (byte) (msb >>> ((7 - i) * 8));
            b[8 + i] = (byte) (lsb >>> ((7 - i) * 8));
        }
        return b;
    }

    private static UUID fromBytes(byte[] data) {
        long msb = 0, lsb = 0;
        for (int i = 0; i < 8; i++) {
            msb = (msb << 8) | (data[i] & 0xff);
        }
        for (int i = 8; i < 16; i++) {
            lsb = (lsb << 8) | (data[i] & 0xff);
        }
        return new UUID(msb, lsb);
    }
}

