package org.example.testing.config;

import org.example.testing.model.Game;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CacheConfig {
    @Bean
    public LRUCache<String, List<Game>> myCustomCache() {
        return new LRUCache<>(10, 5 * 60 * 1000);  // Thread-safe version
    }
}
