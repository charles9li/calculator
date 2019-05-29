package ivcalc.GUI;

import ivcalc.Data.SelectNatures;
import ivcalc.Data.SelectPokemon;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class IVCalcGUI extends Application {

    private static final SelectNatures SELECT_NATURES = new SelectNatures();
    private static final SelectPokemon SELECT_POKEMON = new SelectPokemon();

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
        TextField textField = createTextField(90);
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                pokemonNumSearch.clear();
                System.out.println("Text changed from " + s + " to " + t1);
            }
        });
        return textField;
    }

    private TextField pokemonNumSearch() {
        TextField textField = createTextField(40);
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                pokemonNameSearch.clear();
                System.out.println("Number change from " + s + " to " + t1);
            }
        });
        return textField;
    }

    private ComboBox<String> pokemonList() {
        ComboBox<String> comboBox = new ComboBox<>();
        for (String name : SELECT_POKEMON.selectPokemonNamesList()) {
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
