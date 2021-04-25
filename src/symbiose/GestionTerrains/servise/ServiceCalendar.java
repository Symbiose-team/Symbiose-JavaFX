package symbiose.GestionTerrains.servise;

import javafx.collections.ObservableList;
import symbiose.GestionTerrains.Iservice.IService;
import symbiose.GestionTerrains.entities.Calendar;
import symbiose.GestionTerrains.utils.MyConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ServiceCalendar implements IService<Calendar> {
    private Connection con;
    private Statement ste;


    public void ServiceCalendar() {
        con= MyConnection.getInstance().getCnx();
    }
    @Override
    public ObservableList<Calendar> fields() throws SQLException {
        return null;
    }


     @Override
    public void add(Calendar calendar) throws SQLException {



        System.out.println("sucées");
    }


    @Override
    public boolean delete(Calendar calendar) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Calendar calendar) throws SQLException {
        return false;
    }

    @Override
    public List<Calendar> readAll() throws SQLException {
        return null;
    }
    private void executeQuery(String query) {
        con = MyConnection.getInstance().getCnx();
        Statement st;
        try {
            st = con.createStatement();
            st.executeUpdate(query);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
