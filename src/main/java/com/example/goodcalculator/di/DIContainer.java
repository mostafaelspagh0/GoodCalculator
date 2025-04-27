package com.example.goodcalculator.di;

import java.util.HashMap;
import java.util.Map;


public class DIContainer {
    private final Map<Class<?>, Object> services = new HashMap<>();

    public <T> void register(Class<T> serviceType, T implementation) {
        services.put(serviceType, implementation);
    }

    @SuppressWarnings("unchecked")
    public <T> T resolve(Class<T> serviceType) {
        T service = (T) services.get(serviceType);
        if (service == null) {
            throw new RuntimeException("Service not registered: " + serviceType.getName());
        }
        return service;
    }
}