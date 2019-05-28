package ivcalc.GUI;

import ivcalc.Data.SelectPokemon;

import javax.swing.*;

public class IVCalcGUI extends JFrame {

    private static final SelectPokemon sp = new SelectPokemon();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pokemon S/M IV Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocation(430, 100);

        JPanel panel = new JPanel();

        frame.add(panel);

        JLabel label = new JLabel("Pokemon");
        label.setVisible(true);

        panel.add(label);


//        JComboBox<String> pokemonSearch = new JComboBox<>(sp.selectPokemonNames());
        JComboBox<String> pokemonSearch = new JComboBox<>(new String[] {"", "Bulbasaur"});
        pokemonSearch.setEditable(true);
        panel.add(pokemonSearch);


        panel.add(pokemonSearch);
        frame.setVisible(true);
    }
}
