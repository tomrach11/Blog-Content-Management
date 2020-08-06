package com.tr.contentManager.dao;

import com.tr.contentManager.model.Comment;
import com.tr.contentManager.model.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CommentMapper implements RowMapper<Comment> {
    @Override
    public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
        Comment comment = new Comment();

        comment.setId(resultSet.getInt("id"));
        comment.setCreateDate(resultSet.getTimestamp("createDate").toLocalDateTime());
        comment.setComment(resultSet.getString("comment"));

        return comment;
    }

}
