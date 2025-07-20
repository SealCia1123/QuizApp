package com.sealcia.utils;

import com.sealcia.pojo.User;

public class SessionManager {
    private static SessionManager instance;
    private User user;

    private SessionManager() {}

    public static SessionManager getIntance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
