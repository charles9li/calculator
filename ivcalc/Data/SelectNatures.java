package ivcalc.Data;

import ivcalc.Util.StatType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectNatures implements Select {

    private static final String DATABASE = "natures.db";

    public double select(String nature, StatType statType) {
        String sqlStatement = "SELECT * FROM natures WHERE nature = ?";

        try {
            Connection conn = connect(DATABASE);
            PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement);
            preparedStatement.setString(1, nature);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.getDouble(statType.toString().toLowerCase());
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return 0.0;
    }
}
