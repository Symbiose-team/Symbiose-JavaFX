package symbiose.GestionTerrains.Iservice;

import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public interface IService<T> {
    public ObservableList<T > fields() throws SQLException;
    void add(T t) throws SQLException;
    boolean delete(T t) throws SQLException;
    boolean update(T t) throws SQLException;
    List<T> readAll() throws SQLException;

}
