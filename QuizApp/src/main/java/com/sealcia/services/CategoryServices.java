package com.sealcia.services;

import com.sealcia.pojo.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryServices extends BaseService<Category>{

    @Override
    public PreparedStatement getStatement(Connection conn) throws SQLException {
        return conn.prepareCall("SELECT * FROM category");
    }

    @Override
    public List<Category> getResults(ResultSet rs) throws SQLException {
        List<Category> cates = new ArrayList<>();
        
        while(rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            
            Category c = new Category(id, name);
            cates.add(c);
        }
        return cates;
    }
}
