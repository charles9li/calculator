package ivcalc.GUI;

import ivcalc.Data.SelectPokemon;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class IVCalcGUI extends Application {

    private static final SelectPokemon sp = new SelectPokemon();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("IV Calculator");
        ComboBox<String> comboBox = createPokemonList();

        HBox hbox = new HBox(comboBox);

        Scene scene = new Scene(hbox, 200, 120);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ComboBox<String> createPokemonList() {
        ComboBox<String> comboBox = new ComboBox<>();
        for (String name : sp.selectPokemonNamesList()) {
            comboBox.getItems().add(name);
        }
        return comboBox;
    }
}
