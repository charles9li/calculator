package ivcalc.Data;

import ivcalc.Util.StatType;
import ivcalc.Util.TrieMap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    public List<String> selectNatureList() {
        String sqlStatement = "SELECT * FROM natures ORDER BY nature ASC";

        List<String> natureList = new ArrayList<>();

        try {
            Connection conn = connect(DATABASE);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStatement);
            while (rs.next()) {
                natureList.add(rs.getString("nature"));
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return natureList;
    }

    public TrieMap<Integer> selectNatureTrie() {
        String sqlStatement = "SELECT * FROM natures ORDER BY nature ASC";

        TrieMap<Integer> natureTrie = new TrieMap<>();

        try {
            Connection conn = connect(DATABASE);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStatement);
            int i = 0;
            while (rs.next()) {
                natureTrie.put(rs.getString("nature").toLowerCase(), i);
                i++;
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return natureTrie;
    }
}
