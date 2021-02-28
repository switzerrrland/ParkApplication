package org.example;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface Dao<T> {
    Optional<T> findById(int id);

    List<T> findAll() throws SQLException;

    void create(T t);

    void update(int id, Map<String, String> params);

    Plant deleteById(int id);
}
