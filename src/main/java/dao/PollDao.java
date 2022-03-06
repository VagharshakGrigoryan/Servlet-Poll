package dao;

import java.sql.SQLException;
import java.util.List;

public interface PollDao<T> {

    List<T> findAll() throws SQLException;

    T findById(long id) throws SQLException;
}
