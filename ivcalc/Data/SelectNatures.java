package ivcalc.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectNatures implements Select {

    private static final String DATABASE = "natures.db";

    @Override
    public double select(String nature, String column) {
        String sqlStatement = "SELECT * FROM natures WHERE nature = ?";

        try {
            Connection conn = connect(DATABASE);
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
