package com.example.config;

import com.example.filter.AuthenticationFilter;
import org.glassfish.jersey.server.ResourceConfig;

public class JerseyApplication extends ResourceConfig {
    public JerseyApplication(){

        packages("com.example");

        register(AuthenticationFilter.class);
    }
}
