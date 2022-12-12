package com.mycompany.secondmicroservice.context;

public class CustomUserContextFactory {

    private static ThreadLocal<CustomUserContext> userContext = new ThreadLocal<>();

    private CustomUserContextFactory() {}

    public static ThreadLocal<CustomUserContext> getUserContext() {
        if (userContext == null) {
            userContext = new ThreadLocal<>();
        }

        return userContext;
    }

    public static void setUserContext(ThreadLocal<CustomUserContext> userContext) {
        CustomUserContextFactory.userContext = userContext;
    }

    public static void unload() {
        userContext.remove();
    }
}
