package ivcalc.GUI;

import ivcalc.Data.SelectNatures;
import ivcalc.Data.SelectPokemon;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class IVCalcGUI extends Application {

    private static final SelectNatures sn = new SelectNatures();
    private static final SelectPokemon sp = new SelectPokemon();

    private TextField pokemonNameSearch = pokemonNameSearch();
    private TextField pokemonNumSearch = pokemonNumSearch();
    private ComboBox<String> pokemonList = pokemonList();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("IV Calculator");
        Scene scene = new Scene(pokemonVBox(), 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Methods for creating Pokemon selection area.

    private VBox pokemonVBox() {
        Label numLabel = new Label("   # ");
        HBox hBox = new HBox(pokemonNameSearch, numLabel, pokemonNumSearch);
        Label selectPokemon = new Label("Select Pokemon");
        selectPokemon.setPrefWidth(150);
        selectPokemon.setAlignment(Pos.CENTER);
        return new VBox(selectPokemon, hBox, pokemonList);
    }

    private TextField pokemonNameSearch() {
        return createTextField(100);
    }

    private TextField pokemonNumSearch() {
        return createTextField(30);
    }

    private ComboBox<String> pokemonList() {
        ComboBox<String> comboBox = new ComboBox<>();
        for (String name : sp.selectPokemonNamesList()) {
            comboBox.getItems().add(name);
        }
        comboBox.setPrefWidth(150);
        comboBox.getSelectionModel().selectFirst();
        return comboBox;
    }

    // Utility methods.

    private TextField createTextField(double width) {
        TextField textField = new TextField();
        textField.setPrefWidth(width);
        return textField;
    }
}
