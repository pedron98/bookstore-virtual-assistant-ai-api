package com.bookstore.virtual_assistant_ai.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${cors.originPatterns:default}") // Uses a default value if not set
    private String corsOriginPatterns = "";

    // This method defines the CORS configuration as Global.
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        var allowedOrigins = corsOriginPatterns.split(",");
        registry.addMapping("/**")
                .allowedOrigins(allowedOrigins)
                //.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"); // Refers HTTP Methods
                .allowedMethods("*")
                .allowCredentials(true); // Allow credentials to be sent with the request like cookies, Authorization headers, etc.
    }

}
