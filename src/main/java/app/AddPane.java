package app;

import com.google.gson.Gson;
import javafx.event.Event;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;

/**
 * This class creates a scene where the user can add an item to its wardrobe by creating it from scratch or by
 * loading it from a file (json)
 * @author Josephine Sacchetto
 */
public class AddPane extends HBox {
    private int index;
    private App application;
    private Label title, outfit, item;
    private Font font;
    private Button addOutfit, addItem;
    private ImageView logo;
    private VBox outfitVB, itemVB, show;
    private HBox head, row1;

    /**
     * Constructor of the object
     * @param application of type App
     * @param index is the index in the App arrays from where the wardrobe and all its information is loaded
     */
    public AddPane(App application, int index){
        this.application = application;
        this.index = index;

        title = new Label("Add a new element to your wardrobe");
        font = new Font("Times New Roman",24);
        title.setFont(font);

        logo = new ImageView("images" + File.separator + "Logo.png");
        logo.setPickOnBounds(true); // allows click on transparent areas
        logo.setOnMouseClicked(this::logoEvent);

        outfit = new Label("Add new outfit");
        font = new Font("Times New Roman",18);
        outfit.setFont(font);
        item = new Label("Add new item");
        item.setFont(font);

        addOutfit = new Button("Outfit");
        addOutfit.setMinSize(100, 50);
        addOutfit.setStyle("-fx-background-color: orange");

        addItem = new Button("Item");
        addItem.setMinSize(100, 50);
        addItem.setStyle("-fx-background-color: orange");

        //VBox
        outfitVB = new VBox(outfit, addOutfit);
        itemVB = new VBox(item, addItem);

        //HBox for pane fromOptions
        head = new HBox(logo,title);
        head.setAlignment(Pos.CENTER);
        head.setSpacing(30);

        //button to choose between add outfit or add item
        row1 = new HBox(outfitVB, itemVB);
        row1.setSpacing(30);
        row1.setAlignment(Pos.CENTER);

        show = new VBox(head, row1);
        show.setSpacing(110);

        setStyle("-fx-background-color: #FFE5CC");
        setAlignment(Pos.CENTER); //alignment of the grid in the scene
        setSpacing(50);
        getChildren().addAll(show);

        addOutfit.setOnAction(this::addOutfitEvent);
        addItem.setOnAction(this::addItemEvent);
    }

    /**
     * Event handler for when the doneOptions button is clicked
     * if all fields are corrects it creates a new item and add it to the wardrobe returning to the HomePane otherwise an alert is generated
     * @param actionEvent
     */
    private void addOutfitEvent(ActionEvent actionEvent) {
        Stage stage = (Stage) addOutfit.getScene().getWindow();
        stage.close();
        Stage stage2 = new Stage();
        Scene scene = new Scene(new AddOutfitPane(getApplication(), getIndex()), 800, 600);
        stage2.setTitle("Add Outfit");
        stage2.setScene(scene);
        stage2.show();
    }

    /**
     * Event handler for when the doneFromFile button is clicked
     * if the user inserted a valid JSON file the item is created and added to the wardrobe and then the app returns to the HomePane otherwise an alert is generated
     * @param actionEvent
     */
    private void addItemEvent(ActionEvent actionEvent) {
        Stage stage = (Stage) addItem.getScene().getWindow();
        stage.close();
        Stage stage2 = new Stage();
        Scene scene = new Scene(new AddItemPane(getApplication(), getIndex()), 800, 600);
        stage2.setTitle("Add Items");
        stage2.setScene(scene);
        stage2.show();
    }

    /**
     * Event handler for when the logo image is clicked
     * return to HomePane
     * @param event
     */
    public void logoEvent (MouseEvent event){
        Stage stage = (Stage) logo.getScene().getWindow();
        stage.close();
        Stage stage2 = new Stage();
        Scene scene = new Scene(new HomePane(getApplication(), getIndex()), 800, 600);
        stage2.setTitle("app.Seasons");
        stage2.setScene(scene);
        stage2.show();
    }

    /**
     * Getter of application
     * @return application
     */
    public App getApplication(){
        return application;
    }
    /**
     * Getter of index
     * @return index
     */
    public int getIndex(){
        return index;
    }
}
