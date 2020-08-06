package com.tr.contentManager.dao;

import com.tr.contentManager.model.Tag;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TagMapper implements RowMapper<Tag> {
    @Override
    public Tag mapRow(ResultSet resultSet, int i) throws SQLException {

        Tag tag = new Tag();

        tag.setId(resultSet.getInt("id"));
        tag.setName(resultSet.getString("name"));

        return tag;
    }
}
