package ivcalc.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectPokemon implements Select {

    private static final String DATABASE = "pokemon.db";

    public int select(String pokemon, String column) {
        String sqlStatement = "SELECT * FROM pokemon WHERE name = ?";

        try {
            Connection conn = connect(DATABASE);
            PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement);
            preparedStatement.setString(1, pokemon);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.getInt(column);
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return 0;
    }
}
