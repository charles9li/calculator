package ivcalc.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface Select {

    double select(String col1, String col2);

    default Connection connect(String database) {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:C:./ivcalc/Data/" + database);
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("sqlite-JDBC driver not downloaded");
        }
        return conn;
    }
}
