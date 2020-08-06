package com.tr.contentManager.dao;

import com.tr.contentManager.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class RoleDao<T> implements Dao<Role> {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    @Transactional
    public Role create(Role model) {
        final String INSERT_ROLE = "INSERT INTO role (name) values (?)";
        jdbc.update(INSERT_ROLE, model.getName());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        model.setId(newId);

        return model;
    }

    @Override
    public List<Role> readAll() {
        final String SELECT_ALL_ROLES = "SELECT * FROM role";
        return jdbc.query(SELECT_ALL_ROLES, new RoleMapper());
    }

    @Override
    public Role readById(int id) {
        try {
            final String SELECT_ROLE_BY_ID = "SELECT * FROM role WHERE id = ?";
            return jdbc.queryForObject(SELECT_ROLE_BY_ID, new RoleMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public void update(Role model) {
        final String UPDATE_ROLE = "UPDATE role SET name = ? WHERE id = ?";
        jdbc.update(UPDATE_ROLE, model.getName(), model.getId());
    }

    @Override
    @Transactional
    public void delete(int id) {
        final String DELETE_USER_ROLE = "DELETE FROM user_role WHERE roleId = ?";
        jdbc.update(DELETE_USER_ROLE, id);

        final String DELETE_ROLE = "DELETE FROM role WHERE id = ?";
        jdbc.update(DELETE_ROLE, id);
    }

}
