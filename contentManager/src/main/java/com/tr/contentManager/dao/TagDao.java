package com.tr.contentManager.dao;

import com.tr.contentManager.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TagDao<T> implements Dao<Tag> {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    @Transactional
    public Tag create(Tag model) {
        final String INSERT_TAG = "INSERT INTO tag (name) VALUES (?)";
        jdbc.update(INSERT_TAG, model.getName());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        model.setId(newId);

        return model;
    }

    @Override
    public List<Tag> readAll() {
        final String SELECT_ALL_TAG = "SELECT * FROM tag";
        return jdbc.query(SELECT_ALL_TAG, new TagMapper());
    }

    @Override
    public Tag readById(int id) {
        try {
            final String SELECT_TAG_BY_ID = "SELECT * FROM tag WHERE id = ?";
            return jdbc.queryForObject(SELECT_TAG_BY_ID, new TagMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public void update(Tag model) {
        final String UPDATE_TAG = "UPDATE tag SET name = ? WHERE id = ?";
        jdbc.update(UPDATE_TAG, model.getName(), model.getId());
    }

    @Override
    public void delete(int id) {
        final String DELETE_CONTENT_TAG = "DELETE FROM content_tag WHERE tagId = ?";
        jdbc.update(DELETE_CONTENT_TAG, id);

        final String DELETE_TAG = "DELETE FROM tag WHERE id = ?";
        jdbc.update(DELETE_TAG, id);
    }


}
