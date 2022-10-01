package com.gdutelc.recruit.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * @author TUFSolareyes
 * @date 22/10/01
 */
@Configuration
public class ResourcesHandler implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static_pics/**")
                .addResourceLocations("file:/rec-system-pics/");
    }
}
