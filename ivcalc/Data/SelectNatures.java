package ivcalc.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    public void select() {
        String sql = "SELECT * FROM natures";

        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getString("nature"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public double select(String nature, String column) {
        try {
            ResultSet rs = result(nature);
            return rs.getDouble(column);
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return 0.0;
    }

    private ResultSet result(String nature) {
        String sqlStatement = "SELECT * FROM natures WHERE nature = ?";

        try {
            Connection conn = this.connect();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement);
            preparedStatement.setString(1, nature);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        SelectNatures dn = new SelectNatures();
        System.out.println(dn.select("Bold", "hp"));
    }
}
