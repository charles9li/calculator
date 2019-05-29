package ivcalc.GUI;

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

    private static final SelectPokemon sp = new SelectPokemon();

    private ComboBox<String> pokemonList = createPokemonList();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("IV Calculator");
        Scene scene = new Scene(pokemonVBox(), 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox pokemonVBox() {
        TextField nameSearch = new TextField();
        nameSearch.setPrefWidth(100);

        Label numLabel = new Label("   # ");

        TextField numSearch = new TextField();
        numSearch.setPrefWidth(30);

        HBox hBox = new HBox(nameSearch, numLabel, numSearch);

        Label selectPokemon = new Label("Select Pokemon");
        selectPokemon.setPrefWidth(150);
        selectPokemon.setAlignment(Pos.CENTER);

        return new VBox(selectPokemon, hBox, pokemonList);
    }

    private ComboBox<String> createPokemonList() {
        ComboBox<String> comboBox = new ComboBox<>();
        for (String name : sp.selectPokemonNamesList()) {
            comboBox.getItems().add(name);
        }
        comboBox.setPrefWidth(150);
        comboBox.getSelectionModel().selectFirst();
        return comboBox;
    }
}
