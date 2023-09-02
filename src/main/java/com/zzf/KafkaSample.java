package com.zzf;

import org.apache.kafka.clients.producer.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class KafkaSample {

    public static Properties loadConfig(final String configFile) throws IOException {
        if (!Files.exists(Paths.get(configFile))) {
            throw new IOException(configFile + " not found.");
        }
        final Properties cfg = new Properties();
        try (InputStream inputStream = new FileInputStream(configFile)) {
            cfg.load(inputStream);
        }
        return cfg;
    }

    public static void main(String[] args) throws IOException {
        final Properties props = loadConfig("client.properties");
    }
}
