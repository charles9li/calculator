package ivcalc.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectNatures {

    private Connection connect() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:C:./ivcalc/Data/natures.db");
            System.out.println("Opened database successfully");
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

            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        SelectNatures dn = new SelectNatures();
        dn.select();
    }
}
