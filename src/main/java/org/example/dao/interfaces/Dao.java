package org.example.dao.interfaces;

import org.example.models.Plant;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface Dao<T> {
    Optional<T> findById(int id);

    List<T> findAll() throws SQLException;

    void create(int id, T t);

    void update(int id1, int id2, Map<String, String> params);

    T delete(int id1, int id2);
}
