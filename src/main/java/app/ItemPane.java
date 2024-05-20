package app;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;

public class ItemPane extends HBox {
    private App application;
    private int index, internIndex=0;
    private String filterString;
    private boolean tf = false;
    private ArrayList <Item> itemToDisplay = new ArrayList<Item>(); //all items that can be displayed in this page
    private ArrayList <Item> filteredItemToDisplay = new ArrayList<Item>(); //filterd items
    private ArrayList <Item> displayedItems; //items that are currently being displayed
    private ArrayList <Item> filteredDisplayedItems = new ArrayList<Item>(); //filtered items that are currently being displayed

    private ComboBox occasionsFilter, colorsFilter, seasonsFilter;
    private Font font;
    private Label title, occasionLabel, colorLabel, seasonLabel, filterLabel;
    private AppMenuBar amb;
    private Button apply, next, previous;
    private ButtonType ok;  //Dialoge box
    private ButtonType delete;  //Dialoge box
    private HBox menu, label, filters, cardsr1, cardsr2;
    private VBox filter1, filter2, filter3, show;
    private int popUpIndex=-1;

    /**
     * Constructor of the object
     * @param application of type App
     * @param index is the index in the App arrays from where the wardrobe and all its information is loaded
     */
    public ItemPane(App application, int index, String labelItem){
        this.application = application;
        this.index = index;

        font = new Font("Times New Roman",24);
        title = new Label (labelItem);  //Set based on which menu option has been clicked
        title.setFont(font);

        switch (labelItem){
            case "All Items" : {
                filterString="all";
                break;
            }
            case "All Tops" : {
                filterString="top";
                break;
            }
            case "T-shirts" : {
                filterString="tShirt";
                break;
            }
            case "Sweaters" : {
                filterString="sweater";
                break;
            }
            case "Sweatshirts and hoodies" : {
                filterString="sweatshirtAndHoodie";
                break;
            }
            case "Tops and bodys" : {
                filterString="topAndBody";
                break;
            }
            case "Jackets and coats" : {
                filterString="jacketAndCoat";
                break;
            }
            case "Tank Tops" : {
                filterString="thankTop";
                break;
            }
            case "All Bottoms" : {
                filterString="bottom";
                break;
            }
            case "Skirt" : {
                filterString="skirt";
                break;
            }
            case "Jeans" : {
                filterString="jeans";
                break;
            }
            case "Trouser" : {
                filterString="trouser";
                break;
            }
            case "Shorts" : {
                filterString="shorts";
                break;
            }
            case "All Dresses" : {
                filterString="dress";
                break;
            }
            case "Long Dresses" : {
                filterString="longsD";
                break;
            }
            case "Short Dresses" : {
                filterString="shortsD";
                break;
            }
            case "All Accessories" : {
                filterString="accessorize";
                break;
            }
            case "Jewelry" : {
                filterString="hat";
                break;
            }
            case "Hats" : {
                filterString="hat";
                break;
            }
            case "Belts" : {
                filterString="belt";
                break;
            }
            case "Glasses" : {
                filterString="glasses";
                break;
            }
            case "Scarfs" : {
                filterString="scarf";
                break;
            }
            case "Gloves" : {
                filterString="gloves";
                break;
            }
            case "Purses" : {
                filterString="purse";
                break;
            }
            case "Shoes" : {
                filterString="shoes";
                break;
            }
            case "For the hair" : {
                filterString="forTheHair";
                break;
            }
            case "Favorites" : {
                filterString="Favorites";
                break;
            }
        }


        if (filterString=="all"){
            itemToDisplay = getApplication().getWardrobe(getIndex()).getItems();
        }
        else {
            if(filterString=="Favorites"){
                getApplication().getWardrobe(getIndex()).setFavourties();
                itemToDisplay = getApplication().getWardrobe(getIndex()).getFavourites();
            }
            else{
                itemToDisplay = getApplication().getWardrobe(getIndex()).search(getApplication().getWardrobe(getIndex()).getItems(), filterString);
            }
        }

        amb = new AppMenuBar(application, index);

        //filters
        //first filter -> occasions
        filterLabel = new Label("Filter : ");
        filterLabel.setFont(font);

        occasionLabel = new Label("Occasion:");
        occasionsFilter = new ComboBox();
        occasionsFilter.getItems().addAll("formal", "everyDay", "semiformal", "sporty");

        //second filter -> colors
        colorLabel = new Label("Color:");
        colorsFilter  = new ComboBox();
        colorsFilter.getItems().addAll( "White", "Black", "Pink", "Blue", "Red", "Yellow", "Green", "Beige", "Brown", "Orange");

        //third filter -> seasons
        seasonLabel = new Label("Season:");
        seasonsFilter  = new ComboBox();
        seasonsFilter.getItems().addAll( "winter", "spring", "summer", "fall");

        //dialoge box buttons
        delete = new ButtonType("Delete");
        ok = new ButtonType("Close");

        apply = new Button("Apply");
        apply.setStyle("-fx-background-color: orange");
        next = new Button(" Next ");
        next.setStyle("-fx-background-color: orange");
        previous = new Button(" Previous ");
        previous.setStyle("-fx-background-color: orange");
        previous.setVisible(false);
        next.setVisible(false);

        setStyle("-fx-background-color: #FFE5CC");
        setAlignment(Pos.BASELINE_CENTER);

        menu = amb.getMenuBox();
        menu.setSpacing(20);
        menu.setAlignment(Pos.CENTER);

        label = new HBox(title);
        label.setSpacing(20);
        label.setAlignment(Pos.CENTER);

        filter1 = new VBox(occasionLabel, occasionsFilter);
        filter2 = new VBox(colorLabel, colorsFilter);
        filter3 = new VBox(seasonLabel, seasonsFilter);

        Button buttons [] = createCard(itemToDisplay, internIndex);
        if (itemToDisplay.size()==0){
            filters = new HBox ();
            Label nL = new Label("There are no items to be displayed");
            nL.setFont(new Font(20));
            cardsr1 = new HBox (nL);
            cardsr2 = new HBox ();
        }
        else{
            filters = new HBox(filterLabel,filter1,filter2,filter3,apply);
            filters.setSpacing(20);
            filters.setAlignment(Pos.BOTTOM_LEFT);

            cardsr1 = new HBox(buttons[0],buttons[1],buttons[2]);
            cardsr1.setSpacing(30);

            cardsr2 = new HBox(buttons[3],buttons[4],buttons[5], previous, next);
            cardsr2.setSpacing(30);
            cardsr2.setAlignment(Pos.BOTTOM_CENTER);
        }

        show = new VBox (menu,label,filters,cardsr1,cardsr2);
        show.setSpacing(20);

        setSpacing(50);
        getChildren().addAll(show);

        //action when clicking on the apply button
        apply.setOnAction(this::applyEvent);
        //action when clicking on the nest button
        next.setOnAction(this::nextEvent);
        //action when clicking on the previous button
        previous.setOnAction(this::previousEvent);
    }


    /**
     * Generates the six buttons to be displayed
     * @param items
     * @param i
     * @return
     */
    public Button[] createCard (ArrayList <Item> items , int i){
        Button buttons [] = new Button[6];
        if (tf==true){
            buttons = createCardFilterd(items, i);
        }
        else{
            buttons = createCardAll(items, i);
        }

        return buttons;
    }
    /**
     * Generates the six buttons to be displayed
     * @param items
     * @param i
     * @return
     */
    public Button[] createCardFilterd (ArrayList <Item> items , int i){
        Button buttons [] = new Button[6];
        filteredDisplayedItems = selectItemFilterd(items, i);

        if (filteredDisplayedItems.size()!=0){
            if (filteredDisplayedItems.size()==6){//if there are six items to display
                int x = items.size()-((getInternIndex()/6)+1);
                if(x>=6){
                    next.setVisible(true);
                }
                for(int g=0; g<6; g++){
                    Image img = new Image(filteredDisplayedItems.get(g).getPicture());
                    ImageView view = new ImageView(img);
                    view.setFitHeight(160);
                    view.setFitWidth(160);
                    view.setPreserveRatio(true);

                    Button card = new Button();
                    card.setPrefSize(180, 180);
                    card.setGraphic(view);

                    buttons[g]=card;
                }
                buttons[0].setOnAction(this::popUpEvent1);
                buttons[1].setOnAction(this::popUpEvent2);
                buttons[2].setOnAction(this::popUpEvent3);
                buttons[3].setOnAction(this::popUpEvent4);
                buttons[4].setOnAction(this::popUpEvent5);
                buttons[5].setOnAction(this::popUpEvent6);
            }else{//if there are less than six items to display
                next.setVisible(false);
                for(int g=0; g<(filteredDisplayedItems.size()); g++){ //create enough buttons for the available item
                    Image img = new Image(filteredDisplayedItems.get(g).getPicture());
                    ImageView view = new ImageView(img);
                    view.setFitHeight(160);
                    view.setFitWidth(160);
                    view.setPreserveRatio(true);

                    Button card = new Button();
                    card.setPrefSize(180, 180);
                    card.setGraphic(view);

                    buttons[g]=card;
                }

                switch(filteredDisplayedItems.size()){
                    case 1:{
                        buttons[0].setOnAction(this::popUpEvent1);
                        break;
                    }
                    case 2:{
                        buttons[0].setOnAction(this::popUpEvent1);
                        buttons[1].setOnAction(this::popUpEvent2);
                        break;
                    }
                    case 3:{
                        buttons[0].setOnAction(this::popUpEvent1);
                        buttons[1].setOnAction(this::popUpEvent2);
                        buttons[2].setOnAction(this::popUpEvent3);
                        break;
                    }
                    case 4:{
                        buttons[0].setOnAction(this::popUpEvent1);
                        buttons[1].setOnAction(this::popUpEvent2);
                        buttons[2].setOnAction(this::popUpEvent3);
                        buttons[3].setOnAction(this::popUpEvent4);
                        break;
                    }
                    case 5:{
                        buttons[0].setOnAction(this::popUpEvent1);
                        buttons[1].setOnAction(this::popUpEvent2);
                        buttons[2].setOnAction(this::popUpEvent3);
                        buttons[3].setOnAction(this::popUpEvent4);
                        buttons[4].setOnAction(this::popUpEvent5);
                        break;
                    }
                }
                for(int h=(filteredDisplayedItems.size()); h<6; h++){ //creates empty cards necessary to have a total of 6 cards
                    Button card = new Button();
                    card.setPrefSize(180, 180);

                    buttons[h]=card;
                }
            }
        }

        if (i==0){
            previous.setVisible(false);
        }
        return buttons;
    }

    /**
     * Support method for createCard , Selects  6 or fewer items to be displayed
     * @param items
     * @param i
     * @return six or less selected items to be displayed
     * @Author Irene
     */
    public ArrayList <Item> selectItemFilterd (ArrayList <Item> items, int i){
        ArrayList <Item> listToReturn = new ArrayList<Item>();
        if ((items.size()-i)>=6){
            for (int g=0; g<6; g++){
                listToReturn.add(filteredItemToDisplay.get(i+g));
            }
        }else{
            for (int g=0; g<(items.size()-i); g++){
                listToReturn.add(filteredItemToDisplay.get(i+g));
            }
        }
        return listToReturn;
    }

    /**
     * Generates the six buttons to be displayed
     * @param items
     * @param i
     * @return
     */
    public Button[] createCardAll (ArrayList <Item> items , int i){
        Button buttons [] = new Button[6];
        displayedItems = selectItemAll(items, i);

        if (displayedItems.size()!=0){
            if (displayedItems.size()==6){//if there are six items to display
                int x = items.size()-((getInternIndex()/6)+1);
                if(x>=6){
                    next.setVisible(true);
                }
                for(int g=0; g<6; g++){
                    Image img = new Image(displayedItems.get(g).getPicture());
                    ImageView view = new ImageView(img);
                    view.setFitHeight(160);
                    view.setFitWidth(160);
                    view.setPreserveRatio(true);

                    Button card = new Button();
                    card.setPrefSize(180, 180);
                    card.setGraphic(view);

                    buttons[g]=card;
                }
                buttons[0].setOnAction(this::popUpEvent1);
                buttons[1].setOnAction(this::popUpEvent2);
                buttons[2].setOnAction(this::popUpEvent3);
                buttons[3].setOnAction(this::popUpEvent4);
                buttons[4].setOnAction(this::popUpEvent5);
                buttons[5].setOnAction(this::popUpEvent6);
            }else{//if there are less than six items to display
                next.setVisible(false);
                for(int g=0; g<(displayedItems.size()); g++){ //create enough buttons for the available item
                    Image img = new Image(displayedItems.get(g).getPicture());
                    ImageView view = new ImageView(img);
                    view.setFitHeight(160);
                    view.setFitWidth(160);
                    view.setPreserveRatio(true);

                    Button card = new Button();
                    card.setPrefSize(180, 180);
                    card.setGraphic(view);

                    buttons[g]=card;
                }

                switch(displayedItems.size()){
                    case 1:{
                        buttons[0].setOnAction(this::popUpEvent1);
                        break;
                    }
                    case 2:{
                        buttons[0].setOnAction(this::popUpEvent1);
                        buttons[1].setOnAction(this::popUpEvent2);
                        break;
                    }
                    case 3:{
                        buttons[0].setOnAction(this::popUpEvent1);
                        buttons[1].setOnAction(this::popUpEvent2);
                        buttons[2].setOnAction(this::popUpEvent3);
                        break;
                    }
                    case 4:{
                        buttons[0].setOnAction(this::popUpEvent1);
                        buttons[1].setOnAction(this::popUpEvent2);
                        buttons[2].setOnAction(this::popUpEvent3);
                        buttons[3].setOnAction(this::popUpEvent4);
                        break;
                    }
                    case 5:{
                        buttons[0].setOnAction(this::popUpEvent1);
                        buttons[1].setOnAction(this::popUpEvent2);
                        buttons[2].setOnAction(this::popUpEvent3);
                        buttons[3].setOnAction(this::popUpEvent4);
                        buttons[4].setOnAction(this::popUpEvent5);
                        break;
                    }
                }
                for(int h=(displayedItems.size()); h<6; h++){ //creates empty cards necessary to have a total of 6 cards
                    Button card = new Button();
                    card.setPrefSize(180, 180);

                    buttons[h]=card;
                }
            }
        }

        if (i==0){
            previous.setVisible(false);
        }
        return buttons;
    }

    /**
     * Support method for createCard , Selects  6 or fewer items to be displayed
     * @param items
     * @param i
     * @return six or less selected items to be displayed
     * @Author Irene
     */
    public ArrayList <Item> selectItemAll (ArrayList <Item> items, int i){
        ArrayList <Item> listToReturn = new ArrayList<Item>();
        if ((items.size()-i)>=6){
            for (int g=0; g<6; g++){
                listToReturn.add(itemToDisplay.get(i+g));
            }
        }else{
            for (int g=0; g<(items.size()-i); g++){
                listToReturn.add(itemToDisplay.get(i+g));
            }
        }
        return listToReturn;
    }
    /**
     * Event handler for when the next button is clicked, displays the next 6 elements
     * @param event
     */
    private void nextEvent(ActionEvent event) {
        setInternIndex (6);
        previous.setVisible(true);

        //Reload th correct cards
        Button buttons [] = createCard(itemToDisplay, getInternIndex());
        cardsr1 = new HBox(buttons[0],buttons[1],buttons[2]);
        cardsr1.setSpacing(30);

        cardsr2 = new HBox(buttons[3],buttons[4],buttons[5], previous, next);
        cardsr2.setSpacing(30);
        cardsr2.setAlignment(Pos.BOTTOM_CENTER);

        show = new VBox (menu,label,filters,cardsr1,cardsr2);
        show.setSpacing(20);

        getChildren().clear();
        setSpacing(50);
        getChildren().addAll(show);
    }

    /**
     * Event handler for when the previous button is clicked, displays the previous 6 elements
     * @param event
     */
    private void previousEvent(ActionEvent event) {
        setInternIndex (-6);
        next.setVisible(true);

        //Reload th correct cards
        Button buttons [] = createCard(itemToDisplay, getInternIndex());
        cardsr1 = new HBox(buttons[0],buttons[1],buttons[2]);
        cardsr1.setSpacing(30);

        cardsr2 = new HBox(buttons[3],buttons[4],buttons[5], previous, next);
        cardsr2.setSpacing(30);
        cardsr2.setAlignment(Pos.BOTTOM_CENTER);

        show = new VBox (menu,label,filters,cardsr1,cardsr2);
        show.setSpacing(20);

        getChildren().clear();
        setSpacing(50);
        getChildren().addAll(show);
    }

    /**
     * Event handler for when the apply button is clicked
     * Shows the items based on which filters were chose
     * @param actionEvent
     */
    private void applyEvent(ActionEvent actionEvent) {
        int x = 0;
        tf=true;
        //if the filter selector have been selected the items are filtered
        if (occasionsFilter.getValue()!=null){
            if (colorsFilter.getValue()!=null){
                if (seasonsFilter.getValue()!=null){
                    //All filters are != null
                    filteredItemToDisplay = application.getWardrobe(index).search(itemToDisplay,
                            (String) occasionsFilter.getValue(),
                            (String) colorsFilter.getValue(),
                            (String) seasonsFilter.getValue()
                    );
                }
                else{
                    //occasion and color filters are != null
                    filteredItemToDisplay = application.getWardrobe(index).search(itemToDisplay,
                            (String) occasionsFilter.getValue(),
                            (String) colorsFilter.getValue()
                    );
                }
            }
            else{
                if ((String) seasonsFilter.getValue()!=null){
                    //occasion and season filters are != null
                    filteredItemToDisplay = application.getWardrobe(index).search(itemToDisplay,
                            (String) occasionsFilter.getValue(),
                            (String) seasonsFilter.getValue()
                    );
                }
                else{
                    //occasion filter is != null
                    filteredItemToDisplay = application.getWardrobe(index).search(itemToDisplay,
                            (String) occasionsFilter.getValue()
                    );
                }
            }
        }
        else{
            if (colorsFilter.getValue()!=null){
                if (seasonsFilter.getValue()!=null){
                    //color and season filters are != null
                    filteredItemToDisplay = application.getWardrobe(index).search(itemToDisplay,
                            (String) colorsFilter.getValue(),
                            (String) seasonsFilter.getValue()
                    );
                }
                else{
                    //color filters is != null
                    filteredItemToDisplay = application.getWardrobe(index).search(itemToDisplay,
                            (String) colorsFilter.getValue()
                    );
                }
            }
            else{
                if (seasonsFilter.getValue()!=null){
                    //season filter is != null
                    filteredItemToDisplay = application.getWardrobe(index).search(itemToDisplay,
                            (String) seasonsFilter.getValue()
                    );
                    tf=false;
                }
                else{
                    //none of the filter has been selected
                    x=1;
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Select filters before clicking the apply button");
                    alert.showAndWait();
                }
            }
        }


        if (x==0){//the items have been filtered
            //Reload the correct cards
            Button buttons [] = createCard(filteredItemToDisplay, getInternIndex());

            if (filteredItemToDisplay.size()==0){
                this.filters = new HBox ();
                Label nL = new Label("There are no items to be displayed");
                nL.setFont(new Font(20));
                cardsr1 = new HBox (nL);
                cardsr2 = new HBox ();
            }
            else{
                this.filters = new HBox(filterLabel,filter1,filter2,filter3,apply);
                this.filters.setSpacing(20);
                this.filters.setAlignment(Pos.BOTTOM_LEFT);

                cardsr1 = new HBox(buttons[0],buttons[1],buttons[2]);
                cardsr1.setSpacing(30);

                cardsr2 = new HBox(buttons[3],buttons[4],buttons[5], previous, next);
                cardsr2.setSpacing(30);
                cardsr2.setAlignment(Pos.BOTTOM_CENTER);
            }

            show = new VBox (menu,label,this.filters,cardsr1,cardsr2);
            show.setSpacing(20);

            getChildren().clear();
            setSpacing(50);
            getChildren().addAll(show);
        }



    }

    private void popUpEvent(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        Image image;
        if (tf==true) {
            alert.setContentText(filteredDisplayedItems.get(popUpIndex).toString());
            image = new Image(filteredDisplayedItems.get(popUpIndex).getPicture());
        }
        else{
            alert.setContentText(displayedItems.get(popUpIndex).toString());
            image = new Image(displayedItems.get(popUpIndex).getPicture());
        }
        ImageView view = new ImageView(image);
        view.setFitHeight(160);
        view.setFitWidth(160);
        view.setPreserveRatio(true);
        alert.setGraphic(view);
        alert.getButtonTypes().setAll(delete, ok);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == delete) {
            int id = -1;
            if (tf==true) {
                id = filteredDisplayedItems.get(popUpIndex).getId();}
            else{
                id = displayedItems.get(popUpIndex).getId();
            }

            application.getWardrobe(index).removeId(id);
            updateApp();
            Alert alert1 = new Alert(AlertType.INFORMATION);
            alert1.setTitle("Information Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("The item has been deleted from your closet");
            alert1.showAndWait();
        }
    }

    /**
     * Event handler for when the item card button is clicked.
     * Shows the item in a Dialog Box with the description and allows to delete that item
     * @param event
     */
    private void popUpEvent1(ActionEvent event) {
        popUpIndex = 0;
        popUpEvent();
    }
    private void popUpEvent2(ActionEvent event) {
        popUpIndex = 1;
        popUpEvent();
    }
    private void popUpEvent3(ActionEvent event) {
        popUpIndex = 2;
        popUpEvent();
    }
    private void popUpEvent4(ActionEvent event) {
        popUpIndex = 3;
        popUpEvent();
    }
    private void popUpEvent5(ActionEvent event) {
        popUpIndex = 4;
        popUpEvent();
    }
    private void popUpEvent6(ActionEvent event) {
        popUpIndex = 5;
        popUpEvent();
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
    /**
     * Getter of internIndex
     * @return internIndex
     */
    public int getInternIndex(){
        return internIndex;
    }
    /**
     * Setter of internIndex
     */
    public void setInternIndex(int increment){
        internIndex+=increment;
    }

    /**
     * Updates the json file that keeps a backup of the application that is used as "database"
     */
    public void updateApp (){
        //obj->json
        Gson gson = new Gson ();
        String jsonString = gson.toJson(getApplication());

        String path = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "json" + File.separator + "app.json";

        //write into the file
        FileWriter fw = null;
        String stringToWrite = jsonString;
        try {
            fw = new FileWriter(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fw.write(stringToWrite);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //json->obj
        jsonString = readFromFile();
        App nA = gson.fromJson(jsonString, App.class);

        setApplication(nA);
    }

    /**
     * Support metod for uploadApp, read the context of a file and converts it into a string that is returned
     * @return
     */
    public String readFromFile(){
        String s="";
        //READ from a file using BufferedReader
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src" + File.separator + "main" + File.separator + "resources" + File.separator + "json" + File.separator + "app.json"));
            String line = reader.readLine();
            while (line!=null){
                s= s + line;
                line=reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return s;
    }
    /**
     * Setter of application
     * @param nA
     */
    public void setApplication(App nA){
        application=nA;
    }
}
