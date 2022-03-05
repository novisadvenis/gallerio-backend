package ch.bzzz.galleriobackend;

import ch.bzzz.galleriobackend.storage.StorageProperties;
import ch.bzzz.galleriobackend.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class GallerioBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(GallerioBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }
}
