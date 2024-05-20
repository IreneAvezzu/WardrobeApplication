package app;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
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

public class OutfitPane extends HBox {
    private App application;
    private boolean tf = false;
    private int index, internIndex=0;
    private String filterString;
    private ArrayList <Outfit> outfitToDisplay = new ArrayList<Outfit>(); //all outfits that can be displayed
    private ArrayList <Outfit> displayedOutfits; //outfits that are currently being displayed
    private ArrayList <Outfit> filteredOutfitToDisplay = new ArrayList<Outfit>(); //filterd outfits

    private ArrayList <Outfit> filteredDisplayedOutfit = new ArrayList<Outfit>(); //filtered outfits that are currently being displayed

    private ComboBox occasionsFilter, seasonsFilter;
    private Font font;
    private Label title, occasionLabel, seasonLabel, filterLabel;
    private Button apply, next, previous;
    private HBox menu, label, filters, cardsr1, cardsr2;
    private VBox filter1, filter2, show;

    private AppMenuBar amb;
    private int popUpIndex;

    /**
     * Constructor of the object
     * @param application of type App
     * @param index is the index in the App arrays from where the wardrobe and all its information is loaded
     */
    public OutfitPane(App application, int index, String labelItem){
        this.application = application;
        this.index = index;

        font = new Font("Times New Roman",24);
        title = new Label (labelItem);  //Set based on which menu option has been clicked
        title.setFont(font);

        outfitToDisplay = application.getWardrobe(getIndex()).getOutfits();

        amb = new AppMenuBar(application, index);

        //filters
        //first filter -> occasions
        filterLabel = new Label("Filter : ");
        filterLabel.setFont(font);

        occasionLabel = new Label("Occasion:");
        occasionsFilter = new ComboBox();
        occasionsFilter.getItems().addAll("formal", "everyDay", "semiformal", "sporty");

        //second filter -> seasons
        seasonLabel = new Label("Season:");
        seasonsFilter  = new ComboBox();
        seasonsFilter.getItems().addAll( "winter", "spring", "summer", "fall");

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
        filter2 = new VBox(seasonLabel, seasonsFilter);

        Button buttons [] = createCard(outfitToDisplay, internIndex);
        if (outfitToDisplay.size()==0){
            filters = new HBox ();
            Label nL = new Label("There are no items to be displayed");
            nL.setFont(new Font(20));
            cardsr1 = new HBox (nL);
            cardsr2 = new HBox ();
        }
        else{
            filters = new HBox(filterLabel,filter1,filter2,apply);
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
    public Button[] createCard (ArrayList <Outfit> items , int i){
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
    public Button[] createCardFilterd (ArrayList <Outfit> items , int i){
        Button buttons [] = new Button[6];
        filteredDisplayedOutfit = selectItemFilterd(items, i);

        if (filteredDisplayedOutfit.size()!=0){
            if (filteredDisplayedOutfit.size()==6){//if there are six items to display
                int x = items.size()-((getInternIndex()/6)+1);
                if(x>=6){
                    next.setVisible(true);
                }
                for(int g=0; g<6; g++){
                    Image img = new Image(filteredDisplayedOutfit.get(g).getPicture());
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
                for(int g=0; g<(filteredDisplayedOutfit.size()); g++){ //create enough buttons for the available item
                    Image img = new Image(filteredDisplayedOutfit.get(g).getPicture());
                    ImageView view = new ImageView(img);
                    view.setFitHeight(160);
                    view.setFitWidth(160);
                    view.setPreserveRatio(true);

                    Button card = new Button();
                    card.setPrefSize(180, 180);
                    card.setGraphic(view);

                    buttons[g]=card;
                }

                switch(filteredDisplayedOutfit.size()){
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
                for(int h=(filteredDisplayedOutfit.size()); h<6; h++){ //creates empty cards necessary to have a total of 6 cards
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
    public ArrayList <Outfit> selectItemFilterd (ArrayList <Outfit> items, int i){
        ArrayList <Outfit> listToReturn = new ArrayList<Outfit>();
        if ((items.size()-i)>=6){
            for (int g=0; g<6; g++){
                listToReturn.add(filteredOutfitToDisplay.get(i+g));
            }
        }else{
            for (int g=0; g<(items.size()-i); g++){
                listToReturn.add(filteredOutfitToDisplay.get(i+g));
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
    public Button[] createCardAll (ArrayList <Outfit> items , int i){
        Button buttons [] = new Button[6];
        displayedOutfits = selectItemAll(items, i);

        if (displayedOutfits.size()!=0){
            if (displayedOutfits.size()==6){//if there are six items to display
                int x = items.size()-((getInternIndex()/6)+1);
                if(x>=6){
                    next.setVisible(true);
                }
                for(int g=0; g<6; g++){
                    Image img = new Image(displayedOutfits.get(g).getPicture());
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
                for(int g=0; g<(displayedOutfits.size()); g++){ //create enough buttons for the available item
                    Image img = new Image(displayedOutfits.get(g).getPicture());
                    ImageView view = new ImageView(img);
                    view.setFitHeight(160);
                    view.setFitWidth(160);
                    view.setPreserveRatio(true);

                    Button card = new Button();
                    card.setPrefSize(180, 180);
                    card.setGraphic(view);

                    buttons[g]=card;
                }

                switch(displayedOutfits.size()){
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
                for(int h=(displayedOutfits.size()); h<6; h++){ //creates empty cards necessary to have a total of 6 cards
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
    public ArrayList <Outfit> selectItemAll (ArrayList <Outfit> items, int i){
        ArrayList <Outfit> listToReturn = new ArrayList<Outfit>();
        if ((items.size()-i)>=6){
            for (int g=0; g<6; g++){
                listToReturn.add(outfitToDisplay.get(i+g));
            }
        }else{
            for (int g=0; g<(items.size()-i); g++){
                listToReturn.add(outfitToDisplay.get(i+g));
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
        Button buttons [] = createCard(outfitToDisplay, getInternIndex());
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
        Button buttons [] = createCard(outfitToDisplay, getInternIndex());
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
        ArrayList <String> filters = new ArrayList<>();
        if ((String) occasionsFilter.getValue()!=null){
            if ((String) seasonsFilter.getValue()!=null){
                //Both filters are != null
                filteredOutfitToDisplay = application.getWardrobe(index).searchOutfit(application.getWardrobe(index).getOutfits(), (String) occasionsFilter.getValue(),
                    (String) seasonsFilter.getValue()
                );
            }
            else{
                //occasion filter is != null
                filteredOutfitToDisplay = application.getWardrobe(index).searchOutfit(application.getWardrobe(index).getOutfits(),
                    (String) occasionsFilter.getValue()
                );
            }
        }
        else{
            if ((String) seasonsFilter.getValue()!=null){
                //Season filter is != null
                filteredOutfitToDisplay = application.getWardrobe(index).searchOutfit(application.getWardrobe(index).getOutfits(),
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
        if (x==0){//the items have been filtered
            //Reload the correct cards
            Button buttons [] = createCard(filteredOutfitToDisplay, getInternIndex());

            if (filteredOutfitToDisplay.size()==0){
                this.filters = new HBox ();
                Label nL = new Label("There are no items to be displayed");
                nL.setFont(new Font(20));
                cardsr1 = new HBox (nL);
                cardsr2 = new HBox ();
            }
            else{
                this.filters = new HBox(filterLabel,filter1,filter2,apply);
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
            alert.setContentText(filteredDisplayedOutfit.get(popUpIndex).toString());
            image = new Image(filteredDisplayedOutfit.get(popUpIndex).getPicture());
        }
        else{
            alert.setContentText(displayedOutfits.get(popUpIndex).toString());
            image = new Image(displayedOutfits.get(popUpIndex).getPicture());
        }
        ImageView view = new ImageView(image);
        view.setFitHeight(160);
        view.setFitWidth(160);
        view.setPreserveRatio(true);
        alert.setGraphic(view);
        Optional<ButtonType> result = alert.showAndWait();
    }

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
