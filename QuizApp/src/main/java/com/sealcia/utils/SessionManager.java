package com.sealcia.utils;

import com.sealcia.pojo.User;

public class SessionManager {
    private static SessionManager instance = new SessionManager();
    private static User user;

    private SessionManager() {}

    public static SessionManager getIntance() {
        return instance;
    }

    public void setUser(User u) {
        user = u;
    }

    public User getUser() {
        return user;
    }
}
