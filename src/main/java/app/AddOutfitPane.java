package app;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import java.util.ArrayList;

/**
 * This class creates a scene where the user can add an item to its wardrobe by creating it from scratch or by
 * loading it from a file (json)
 * @author Josephine Sacchetto
 */
public class AddOutfitPane extends HBox {
    private String inputSeason, inputOccasion, inputTop, inputBottom, inputDress, inputAccessorize, inputPath, inputDescription;
    private int index, tIndex, bIndex, dIndex, aIndex;
    private App application;
    private Label title, season, occasion, top,bottom, dress, accessorize, path, description;

    private Font font;
    private TextField chooseImagePath, chooseDescription;
    private Button done;
    private ComboBox  selectSeason, selectOccasion, selectTop, selectBottom, selectDress, selectAccessorize;
    private ImageView logo;
    private VBox imagePathVB, seasonVB, occasionVB, topVB, bottomVB, dressVB, accessorizeVB, descriptionVB, options, showAll, showFromFile, filePathVB;
    private HBox head, row1, row2, row3, row4, row5;

    /**
     * Constructor of the object
     * @param application of type App
     * @param index is the index in the App arrays from where the wardrobe and all its information is loaded
     */
    public AddOutfitPane(App application, int index) {
    	this.application = application;
    	this.index = index;

        title = new Label("Add your outfit");
        font = new Font("Times New Roman",24);
        title.setFont(font);

        logo = new ImageView("images" + File.separator + "Logo.png");
        logo.setPickOnBounds(true); // allows click on transparent areas
        logo.setOnMouseClicked(this::logoEvent);

        season = new Label("Season:");
        selectSeason = new ComboBox();

        occasion = new Label("Occasion:");
        selectOccasion = new ComboBox();

        top = new Label("Select top: ");
        selectTop = new ComboBox();
        selectTop.setMinWidth(160);

        bottom = new Label("Select bottom: ");
        selectBottom = new ComboBox();
        selectBottom.setMinWidth(160);

        dress = new Label("Select dress: ");
        selectDress = new ComboBox();
        selectDress.setMinWidth(160);

        accessorize = new Label("Select accessory: ");
        selectAccessorize = new ComboBox();
        selectAccessorize.setMinWidth(160);

        path = new Label("Insert the path of the item's photo");
        chooseImagePath = new TextField();

        description = new Label("Add description: ");
        chooseDescription = new TextField();

        done = new Button("Done");
        done.setStyle("-fx-background-color: orange");

        addSeasonCategory();
        addOccasionCategory();
        addTop();
        addBottom();
        addDress();
        addAccessorize();

        //VBox
        seasonVB = new VBox(season, selectSeason);
        occasionVB = new VBox(occasion, selectOccasion);

        topVB = new VBox(top, selectTop);
        bottomVB = new VBox(bottom, selectBottom);
        dressVB = new VBox(dress, selectDress);
        accessorizeVB = new VBox(accessorize, selectAccessorize);
        imagePathVB = new VBox(path, chooseImagePath);
        descriptionVB = new VBox(description, chooseDescription);

        //HBox for header
        head = new HBox(logo,title);
        head.setAlignment(Pos.CENTER);
        head.setSpacing(30);

        //selectors and labels for top and bottom
        row1 = new HBox(topVB, bottomVB);
        row1.setSpacing(30);
        row1.setAlignment(Pos.CENTER);
        //selectors and labels for dress and accessorize
        row2 = new HBox(dressVB, accessorizeVB);
        row2.setSpacing(30);
        row2.setAlignment(Pos.CENTER);
        //selectors and labels for season and occasion
        row3 = new HBox(seasonVB, occasionVB);
        row3.setSpacing(30);
        row3.setAlignment(Pos.CENTER);
        //selectors and labels for image path and description
        row4 = new HBox(imagePathVB, descriptionVB);
        row4.setSpacing(30);
        row4.setAlignment(Pos.CENTER);
        //button to create the outfit
        row5 = new HBox(done);
        row5.setAlignment(Pos.CENTER);

        options = new VBox(row1 ,row2, row3, row4, row5);
        options.setSpacing(50);

        showAll = new VBox(head,options);
        showAll.setSpacing(110);

        setStyle("-fx-background-color: #FFE5CC");
        setAlignment(Pos.CENTER); //alignment of the grid in the scene
        setSpacing(50);
        getChildren().addAll(showAll);

        done.setOnAction(this::doneEvent);
    }
    public void addSeasonCategory() {
        selectSeason.getItems().add("winter");
        selectSeason.getItems().add("spring");
        selectSeason.getItems().add("summer");
        selectSeason.getItems().add("fall");
    }
    public void addOccasionCategory() {
        selectOccasion.getItems().add("formal");
        selectOccasion.getItems().add("everyDay");
        selectOccasion.getItems().add("semiformal");
        selectOccasion.getItems().add("sporty");
    }
    public void addTop() {
        ArrayList<Item> topsItem = application.getWardrobe(getIndex()).searchTop(application.getWardrobe(getIndex()).getItems());
        ArrayList<Top> tops = new ArrayList<>();
        Top tempT;
        for (int i = 0; i<topsItem.size(); i++){
            tempT = convertTop(topsItem.get(i));
            tops.add(tempT);
        }
        String s;
        for (int i=0; i<tops.size(); i++){
            s=(tops.get(i).getColorOfItem().name() + " "
                + tops.get(i).getTypeOfTop().name()
                + ", "
                + tops.get(i).getOccasionOfItem().name()
                + " for the "
                + tops.get(i).getSeasonOfItem().name()
            );
            selectTop.getItems().add(s);
            selectTop.setOnAction(this::topEvent);
            selectBottom.setOnAction(this::bottomEvent);
            selectDress.setOnAction(this::dressEvent);
            selectAccessorize.setOnAction(this::accessorizeEvent);
        }
    }

    /**
     * Event handler for when the selectBottom is clicked
     * @param event
     */
    private void topEvent(Event event) {
        ArrayList<Item> topsItem = application.getWardrobe(getIndex()).searchTop(application.getWardrobe(getIndex()).getItems());
        ArrayList<Top> tops = new ArrayList<>();
        Top tempT;
        for (int i = 0; i<topsItem.size(); i++){
            tempT = convertTop(topsItem.get(i));
            tops.add(tempT);
        }
        String s;
        for (int i=0; i<tops.size(); i++){
            s=(tops.get(i).getColorOfItem().name() + " "
                + tops.get(i).getTypeOfTop().name()
                + ", "
                + tops.get(i).getOccasionOfItem().name()
                + " for the "
                + tops.get(i).getSeasonOfItem().name()
            );
            selectBottom.getItems().add(s);
        }
    }
    public void addBottom() {
        ArrayList<Item> bottomsItem = application.getWardrobe(getIndex()).searchBottom(application.getWardrobe(getIndex()).getItems());
        ArrayList<Bottom> bottoms = new ArrayList<>();
        Bottom tempT;
        for (int i = 0; i<bottomsItem.size(); i++){
            tempT = convertBottom(bottomsItem.get(i));
            bottoms.add(tempT);
        }
        String s;
        for (int i=0; i<bottoms.size(); i++){
            s=(bottoms.get(i).getColorOfItem().name() + " "
                + bottoms.get(i).getTypeOfBottom().name()
                + ", "
                + bottoms.get(i).getOccasionOfItem().name()
                + " for the "
                + bottoms.get(i).getSeasonOfItem().name()
            );
            selectBottom.getItems().add(s);
        }
    }
    /**
     * Event handler for when the selectBottom is clicked
     * @param event
     */
    private void bottomEvent(Event event) {
        String value = (String) selectBottom.getValue();
        String s;
        ArrayList<Bottom> bottoms = application.getWardrobe(getIndex()).searchBottom(application.getWardrobe(getIndex()).getItems());
        for (int i=0; i<bottoms.size(); i++){
            s=(bottoms.get(i).getColorOfItem().name() + " "
                + bottoms.get(i).getTypeOfBottom().name()
                + ", "
                + bottoms.get(i).getOccasionOfItem().name()
                + " for the "
                + bottoms.get(i).getSeasonOfItem().name()
            );
            if (s.equals(value)){
                bIndex=i;
            }
        }
    }
    public void addDress() {
        ArrayList<Item> dressItem = application.getWardrobe(getIndex()).searchDress(application.getWardrobe(getIndex()).getItems());
        ArrayList<Dress> dresses = new ArrayList<>();
        Dress tempT;
        for (int i = 0; i<dressItem.size(); i++){
            tempT = convertDress(dressItem.get(i));
            dresses.add(tempT);
        }
        String s;
        for (int i=0; i<dresses.size(); i++){
            s=(dresses.get(i).getColorOfItem().name() + " "
                + dresses.get(i).getTypeOfDress().name() + " dress"
                + ", "
                + dresses.get(i).getOccasionOfItem().name()
                + " for the "
                + dresses.get(i).getSeasonOfItem().name()
            );
            selectDress.getItems().add(s);
        }
    }
    /**
     * Event handler for when the selectDress is clicked
     * @param event
     */
    private void dressEvent(Event event) {
        String value = (String) selectDress.getValue();
        String s;
        ArrayList<Dress> dresses = application.getWardrobe(getIndex()).searchDress(application.getWardrobe(getIndex()).getItems());
        for (int i=0; i<dresses.size(); i++){
            s=(dresses.get(i).getColorOfItem().name() + " "
                + dresses.get(i).getTypeOfDress().name() + " dress"
                + ", "
                + dresses.get(i).getOccasionOfItem().name()
                + " for the "
                + dresses.get(i).getSeasonOfItem().name()
            );
            if (s.equals(value)){
                dIndex=i;
            }
        }
    }
    public void addAccessorize() {
        ArrayList<Item> dressItem = application.getWardrobe(getIndex()).searchAccessorize(application.getWardrobe(getIndex()).getItems());
        ArrayList<Accessorize> accessorizes = new ArrayList<>();
        Accessorize tempT;
        for (int i = 0; i<dressItem.size(); i++){
            tempT = convertAccessorize(dressItem.get(i));
            accessorizes.add(tempT);
        }
        String s;
        for (int i=0; i<accessorizes.size(); i++){
            s=(accessorizes.get(i).getColorOfItem().name() + " "
                + accessorizes.get(i).getTypeOfAccesorize().name()
                + ", "
                + accessorizes.get(i).getOccasionOfItem().name() + " "
                + "for the "
                + accessorizes.get(i).getSeasonOfItem().name()
            );
            selectAccessorize.getItems().add(s);
        }
    }
    /**
     * Event handler for when the selectAccessorize is clicked
     * @param event
     */
    private void accessorizeEvent(Event event) {
        String value = (String) selectAccessorize.getValue();
        String s;
        ArrayList<Accessorize> accessorizes = application.getWardrobe(getIndex()).searchAccessorize(application.getWardrobe(getIndex()).getItems());
        for (int i=0; i<accessorizes.size(); i++){
            s=(accessorizes.get(i).getColorOfItem().name() + " "
                + accessorizes.get(i).getTypeOfAccesorize().name()
                + ", "
                + accessorizes.get(i).getOccasionOfItem().name() + " "
                + "for the "
                + accessorizes.get(i).getSeasonOfItem().name()
            );
            if (s.equals(value)){
                aIndex=i;
            }
        }
    }

    /**
     * Event handler for when the doneOptions button is clicked
     * if all fields are corrects it creates a new item and add it to the wardrobe returning to the HomePane otherwise an alert is generated
     * @param actionEvent
     */
    private void doneEvent(ActionEvent actionEvent) {
        inputSeason = (String) selectSeason.getValue();
        inputOccasion = (String) selectOccasion.getValue();
        inputTop = (String) selectTop.getValue();
        inputBottom = (String) selectBottom.getValue();
        inputDress = (String) selectDress.getValue();
        inputAccessorize = (String) selectAccessorize.getValue();
        inputDescription = chooseDescription.getText();
        inputPath = chooseImagePath.getText();


        //if the picture path is not valid generates an alert
        if (!isFileValid(inputPath)){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("The picture path is not correct");
            alert.showAndWait();
        }
        //Otherwise, the new outfit is created and the user is redirected to the HomePane
        else{
            //if there is no given description the default is an empty string
            if (inputDescription == null) {
                inputDescription = "";
            }
            //if there is no given picture path is set a default picture
            if (inputPath.isEmpty()) {
                inputPath = "images" + File.separator + "outfit.png";
            }

            //creates the new outfit and add the items selected
            int id = application.getWardrobe(getIndex()).getOutfitId();
            String s = "" + inputTop + "\n" + inputBottom + "\n" + inputDress + "\n" + inputAccessorize;
            Outfit nO = new Outfit(id, inputPath, inputDescription, inputSeason, inputOccasion, s);
            Item nI;

            for (int i=0; i<4; i++){
                switch (i){
                    case 0 : {
                        if (inputTop != null){
                            nI = application.getWardrobe(getIndex()).searchId(tIndex);
                            nO.addItem(nI);
                        }
                    }
                    case 2 : {
                        if (inputBottom !=null){
                            nI = application.getWardrobe(getIndex()).searchId(bIndex);
                            nO.addItem(nI);
                        }
                    }
                    case 3 : {
                        if (inputDress !=null){
                            nI = application.getWardrobe(getIndex()).searchId(dIndex);
                            nO.addItem(nI);
                        }
                    }
                    case 4 : {
                        if (inputAccessorize != null){
                            nI = application.getWardrobe(getIndex()).searchId(aIndex);
                            nO.addItem(nI);
                        }
                    }
                }
            }

            application.getWardrobe(getIndex()).addOutfit(nO);

            updateApp();

            Alert information = new Alert (AlertType.INFORMATION);
            information.setHeaderText(null);
            information.setWidth(400);
            information.setContentText("New outfit was added");
            information.showAndWait();

            Stage stage = (Stage) done.getScene().getWindow();
            stage.close();
            Stage stage2 = new Stage();
            Scene scene = new Scene(new HomePane(getApplication(), getIndex()), 800, 600);
            stage2.setTitle("Home");
            stage2.setScene(scene);
            stage2.show();
        }
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
     * Check if the path refers to a valid file
     * @param path
     * @return
     */
    private boolean isFileValid(String path) {
        if (path.isEmpty()){
            return true;
        }
        else {
            path = "src" + File.separator + "main" + File.separator + "resources" + File.separator + path;
            File f = new File(path);
            if (f.exists() && !f.isDirectory()){
                return true;
            }
            else{
                return false;
            }
        }
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
     * Getter of application
     * @return application
     */
    public App getApplication(){
        return application;
    }

    /**
     * Setter of application
     * @param nA
     */
    public void setApplication(App nA){
        application=nA;
    }
    /**
     * Getter of index
     * @return index
     */
    public int getIndex(){
        return index;
    }

    public Top convertTop (Item item){
        String array [] = readInfo(item);
        int id = Integer.parseInt(array[7]);
        Top nT = new Top (array[0], array[1], array[2], array[3], array[4], array[5], array[6], id);
        return nT;
    }
    public Bottom convertBottom (Item item){
        String array [] = readInfo(item);
        int id = Integer.parseInt(array[7]);
        Bottom nB = new Bottom (array[0], array[1], array[2], array[3], array[4], array[5], array[6], id);
        return nB;
    }
    public Dress convertDress (Item item){
        String array [] = readInfo(item);
        int id = Integer.parseInt(array[7]);
        Dress nD = new Dress (array[0], array[1], array[2], array[3], array[4], array[5], array[6], id);
        return nD;
    }
    public Accessorize convertAccessorize (Item item){
        String array [] = readInfo(item);
        int id = Integer.parseInt(array[7]);
        Accessorize nA = new Accessorize (array[0], array[1], array[2], array[3], array[4], array[5], array[6], id);
        return nA;
    }

    /**
     * Support method for convertTop, convertBottom, convertDress, convertAccessorize
     * @param item
     * @return
     */
    public String [] readInfo(Item item){
        String array [] = new String [8];

        array[0] = item.getSeasonOfItem().name(); //sS
        array[1] = item.getOccasionOfItem().name(); //sO
        array[2] = item.getColorOfItem().name(); //sC
        array[3] = item.getTypeOf(); //sT
        array[4] = ((item.getFavourite())==true ? "yes" : "no"); //f
        array[5] = item.getPicture(); //pFN
        array[6] = item.getDescription(); //d
        array[7] = String.valueOf(item.getId()); //id

        return array;
    }
}
