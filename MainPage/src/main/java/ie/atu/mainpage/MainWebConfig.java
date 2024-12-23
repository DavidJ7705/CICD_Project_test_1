package ie.atu.mainpage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MainWebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Allow CORS for all endpoints and methods
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost:8083", // Existing front-end domain
                        "http://localhost:3000" // React app domain
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true); // Allow credentials if needed
    }

    // Optional: If you want to declare it as a bean explicitly
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(
                                "http://localhost:8083", // Existing front-end domain
                                "http://localhost:3000" // React app domain
                        )
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*")
                        .allowCredentials(true); // Allow credentials if needed
            }
        };
    }
}