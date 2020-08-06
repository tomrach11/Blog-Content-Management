package com.tr.contentManager.dao;

import com.tr.contentManager.model.Content;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContentMapper implements RowMapper<Content> {
    @Override
    public Content mapRow(ResultSet resultSet, int i) throws SQLException {
        Content content = new Content();

        content.setId(resultSet.getInt("id"));
        content.setCreateDate(resultSet.getTimestamp("createDate").toLocalDateTime());
        content.setTitle(resultSet.getString("title"));
        content.setType(resultSet.getString("type"));
        content.setStatus(resultSet.getString("status"));
        content.setContent(resultSet.getString("content"));
        content.setTitlePicture("titlePicture");
        if (resultSet.getTimestamp("scheduleDate") != null) {
            content.setScheduleDate(resultSet.getTimestamp("scheduleDate").toLocalDateTime());
        }
        if (resultSet.getTimestamp("expiredDate") != null) {
            content.setExpiredDate(resultSet.getTimestamp("expiredDate").toLocalDateTime());
        }
        content.setTitlePicture(resultSet.getString("titlePicture"));

        return content;
    }
}
