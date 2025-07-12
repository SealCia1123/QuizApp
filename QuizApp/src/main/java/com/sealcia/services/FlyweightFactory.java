package com.sealcia.services;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlyweightFactory {
    private static Map<String, List> cachedData = new HashMap<>();

    public static <E> List<E> getData(BaseServices s, String key) throws SQLException {
        if (cachedData.containsKey(key) == true) {
            return cachedData.get(key);
        } else {
            List result = s.list();
            cachedData.put(key, result);

            System.out.println("Caching: " + key);

            return result;
        }
    }
}
