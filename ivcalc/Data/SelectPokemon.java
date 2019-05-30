package ivcalc.Data;

import ivcalc.Util.StatType;
import ivcalc.Util.TrieMap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectPokemon implements Select {

    private static final String DATABASE = "pokemon.db";

    public int selectDexNum(String pokemon) {
        String sqlStatement = "SELECT * FROM pokemon WHERE name = ?";
        try {
            Connection conn = connect(DATABASE);
            PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement);
            preparedStatement.setString(1, pokemon);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.getInt("number");
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return 0;
    }

    public int selectBaseStat(String pokemon, StatType statType) {
        String sqlStatement = "SELECT * FROM pokemon WHERE name = ?";

        try {
            Connection conn = connect(DATABASE);
            PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement);
            preparedStatement.setString(1, pokemon);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.getInt(statType.toString().toLowerCase());
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return 0;
    }

    public List<String> selectPokemonNamesList() {
        String sqlStatement = "SELECT * FROM pokemon ORDER BY number ASC";

        List<String> pokemonNames = new ArrayList<>();

        try{
            Connection conn = connect(DATABASE);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStatement);
            while (rs.next()) {
                pokemonNames.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return pokemonNames;
    }

    public TrieMap<Integer> selectPokemonNamesTrie() {
        String sqlStatement = "SELECT * FROM pokemon ORDER BY number ASC";

        TrieMap<Integer> pokemonNames = new TrieMap<>();

        try{
            Connection conn = connect(DATABASE);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStatement);
            int i = 0;
            while (rs.next()) {
                pokemonNames.put(rs.getString("name").toLowerCase(), i);
                i++;
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return pokemonNames;
    }

    public Map<Integer, List<String>> selectPokemonNum() {
        String sqlStatement = "SELECT * FROM pokemon ORDER BY number ASC, name ASC";

        Map<Integer, List<String>> pokemonNum = new HashMap<>();

        try{
            Connection conn = connect(DATABASE);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStatement);
            while (rs.next()) {
                int dexNum = rs.getInt("number");
                if (!pokemonNum.containsKey(dexNum)) {
                    List<String> nameList = new ArrayList<>();
                    nameList.add(rs.getString("name"));
                    pokemonNum.put(dexNum, nameList);
                } else {
                    pokemonNum.get(dexNum).add(rs.getString("name"));
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return pokemonNum;
    }

    public String[] selectPokemonNames() {
        String sqlStatement = "SELECT * FROM pokemon ORDER BY number ASC";

        String[] pokemonNames = new String[selectNumRows()];

        try {
            Connection conn = connect(DATABASE);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStatement);
            int i = 0;
            while (rs.next()) {
                pokemonNames[i] = rs.getString("name");
                i++;
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return pokemonNames;
    }

    private int selectNumRows() {
        String sqlStatement = "SELECT COUNT(*) AS numrows FROM pokemon";

        try {
            Connection conn = connect(DATABASE);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStatement);
            return rs.getInt("numrows");
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return 0;
    }
}
