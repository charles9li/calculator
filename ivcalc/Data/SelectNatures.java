package ivcalc.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectNatures {

    private Connection connect() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:C:./ivcalc/Data/natures.db");
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("sqlite-JDBC driver not downloaded");
        }
        return conn;
    }

    public double select(String nature, String column) {
        String sqlStatement = "SELECT * FROM natures WHERE nature = ?";

        try {
            Connection conn = this.connect();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement);
            preparedStatement.setString(1, nature);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.getDouble(column);
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return 0.0;
    }
}
