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
public class AddItemPane extends HBox {
    private String inputColor, inputSeason, inputOccasion, inputFavorite, inputCategory, inputType, inputPath, inputDescription, inputFilePath;
    private int index;
    private App application;
    private Label path, title, color, season, occasion, favorite, category, type, description, filePath;
    private Font font;
    private TextField chooseImagePath, chooseDescription, chooseFilePath;
    private Button doneOptions, doneFromFile, optionFromScratch, optionFromFile;
    private ComboBox selectColor, selectSeason, selectOccasion, selectCategory, selectType;
    private ImageView logo;
    private VBox imagePathVB, colorVB, seasonVB, occasionVB, favoriteVB, categoryVB, typeVB, descriptionVB, options, showAll, showFromFile, filePathVB;
    private HBox head, row1, row2, row3, row4, line1,line2, favouriteHB;
    private RadioButton yes, no;
    private ToggleGroup tg;

    /**
     * Constructor of the object
     * @param application of type App
     * @param index is the index in the App arrays from where the wardrobe and all its information is loaded
     */
    public AddItemPane(App application, int index){
    	this.application = application;
    	this.index = index;

        title = new Label("Add your items");
        font = new Font("Times New Roman",24);
        title.setFont(font);

        logo = new ImageView("images/Logo.png");
        logo.setPickOnBounds(true); // allows click on transparent areas
        logo.setOnMouseClicked(this::logoEvent);

        optionFromScratch = new Button("Frome scratch");
        optionFromScratch.setStyle("-fx-background-color: orange");
        optionFromFile = new Button("From file");
        optionFromFile.setStyle("-fx-background-color: orange");

        color = new Label("Color:");
        selectColor = new ComboBox();

        season = new Label("Season:");
        selectSeason = new ComboBox();

        occasion = new Label("Occasion:");
        selectOccasion = new ComboBox();

        favorite = new Label("Favorite:");
        yes = new RadioButton("Yes");
        yes.setSelected(false);
        no = new RadioButton("No");
        no.setSelected(true);
        tg = new ToggleGroup();
        yes.setToggleGroup(tg);
        no.setToggleGroup(tg);

        category = new Label("Category:");
        selectCategory = new ComboBox();

        type = new Label("Choose witch type of ... :");
        selectType = new ComboBox();
        selectType.setMinWidth(160);

        path = new Label("Insert the path of the item's photo");
        chooseImagePath = new TextField();

        description = new Label("Add description: ");
        chooseDescription = new TextField();

        doneOptions = new Button("Done");
        doneOptions.setStyle("-fx-background-color: orange");

        doneFromFile = new Button("Done");
        doneFromFile.setStyle("-fx-background-color: orange");
        filePath = new Label("Insert name of JSON file:");
        chooseFilePath = new TextField();//?

        addColorCategory();
        addSeasonCategory();
        addOccasionCategory();
        addTypeCategory();


        //VBox for pane fromOptions
        colorVB = new VBox(color, selectColor);
        seasonVB = new VBox(season, selectSeason);
        occasionVB = new VBox(occasion, selectOccasion);
        favouriteHB = new HBox (yes, no);
        favouriteHB.setSpacing(10);
        favoriteVB = new VBox(favorite, favouriteHB);
        categoryVB = new VBox(category, selectCategory);
        typeVB = new VBox(type, selectType);
        imagePathVB = new VBox(path, chooseImagePath);
        descriptionVB = new VBox(description, chooseDescription);

        //HBox for pane fromOptions
        head = new HBox(logo,title);
        head.setAlignment(Pos.CENTER);
        head.setSpacing(30);

        //button to choose between from options or from file
        row1 = new HBox(optionFromFile);
        row1.setSpacing(30);
        row1.setAlignment(Pos.CENTER);
        //selectors and labels for color, season, occasion and favourite
        row2 = new HBox(colorVB, seasonVB, occasionVB, favoriteVB);
        row2.setSpacing(30);
        row2.setAlignment(Pos.CENTER);
        //selectors and labels for category(Top, Bottom, ...), type of Item (Tshirt, long dress, jeans,...), image path and description
        row3 = new HBox(categoryVB, typeVB, imagePathVB, descriptionVB);
        row3.setSpacing(30);
        row3.setAlignment(Pos.CENTER);
        //button to create the item from options
        row4 = new HBox(doneOptions);
        row4.setAlignment(Pos.CENTER);

        options = new VBox(row1 ,row2, row3, row4);
        options.setSpacing(50);

        showAll = new VBox(head,options);
        showAll.setSpacing(110);

        setStyle("-fx-background-color: #FFE5CC");
        setAlignment(Pos.CENTER); //alignment of the grid in the scene
        setSpacing(50);
        getChildren().addAll(showAll);

        optionFromScratch.setOnAction(this::optionFromScratchEvent);
        optionFromFile.setOnAction(this::optionFromFileEvent);
        selectCategory.setOnAction(this::categoryEvent);
        doneOptions.setOnAction(this::doneOptionsEvent);
        doneFromFile.setOnAction(this::doneFromFileEvent);
    }


    public void addColorCategory() {
        selectColor.getItems().add("White");
        selectColor.getItems().add("Black");
        selectColor.getItems().add("Pink");
        selectColor.getItems().add("Blue");
        selectColor.getItems().add("Red");
        selectColor.getItems().add("Yellow");
        selectColor.getItems().add("Green");
        selectColor.getItems().add("Beige");
        selectColor.getItems().add("Brown");
        selectColor.getItems().add("Orange");
    }
    public void addSeasonCategory() {
        selectSeason.getItems().add("Winter");
        selectSeason.getItems().add("Spring");
        selectSeason.getItems().add("Summer");
        selectSeason.getItems().add("Fall");
    }
    public void addOccasionCategory() {
        selectOccasion.getItems().add("Formal");
        selectOccasion.getItems().add("EveryDay");
        selectOccasion.getItems().add("Semiformal");
        selectOccasion.getItems().add("Sporty");
    }
    public void addTypeCategory() {
        selectCategory.getItems().add("Tops");
        selectCategory.getItems().add("Bottoms");
        selectCategory.getItems().add("Dresses");
        selectCategory.getItems().add("Accessories");
    }
    public void addTopCategory() {
        selectType.getItems().clear();
        selectType.getItems().add("T-shirt");
        selectType.getItems().add("Sweater");
        selectType.getItems().add("Sweatshirt and hoodie");
        selectType.getItems().add("Top and body");
        selectType.getItems().add("Jacket and coat");
        selectType.getItems().add("Tank top");
    }
    public void addBottomCategory() {
        selectType.getItems().clear();
        selectType.getItems().add("Skirt");
        selectType.getItems().add("Jeans");
        selectType.getItems().add("Trouser");
        selectType.getItems().add("Shorts");
    }
    public void addDressCategory() {
        selectType.getItems().clear();
        selectType.getItems().add("Long");
        selectType.getItems().add("Short");
    }
    public void addAccessorizeCategory() {
        selectType.getItems().clear();
        selectType.getItems().add("Jewelry");
        selectType.getItems().add("Hat");
        selectType.getItems().add("Belt");
        selectType.getItems().add("Glasses");
        selectType.getItems().add("Scarf");
        selectType.getItems().add("Gloves");
        selectType.getItems().add("Purse");
        selectType.getItems().add("Shoes");
        selectType.getItems().add("For the hair");
    }

    /**
     * Event handler for when the optionFromScratch button is clicked.
     * Return to the
     * @param event
     */
    private void optionFromScratchEvent(ActionEvent event) {
        Stage stage = (Stage) optionFromScratch.getScene().getWindow();
        stage.close();
        Stage stage2 = new Stage();
        Scene scene = new Scene(new AddItemPane(getApplication(), getIndex()), 800, 600);
        stage2.setTitle("Add Items");
        stage2.setScene(scene);
        stage2.show();
    }

    /**
     * Event handler for when the category selector change
     * @param event
     */
    private void categoryEvent(Event event) {
        inputCategory = (String) selectCategory.getValue();
        if(inputCategory =="Tops"){
            type.setText("Choose witch type of "+ inputCategory +":");
            addTopCategory();
        }
        else if(inputCategory =="Bottoms"){
            type.setText("Choose witch type of "+ inputCategory +":");
            addBottomCategory();
        }
        else if(inputCategory =="Dresses"){
            type.setText("Choose witch type of "+ inputCategory +":");
            addDressCategory();
        }
        else if(inputCategory =="Accessories"){
            type.setText("Choose witch type of "+ inputCategory +":");
            addAccessorizeCategory();
        }
    }

    /**
     * Event handler for when the doneOptions button is clicked
     * if all fields are corrects it creates a new item and add it to the wardrobe returning to the HomePane otherwise an alert is generated
     * @param actionEvent
     */
    private void doneOptionsEvent(ActionEvent actionEvent) {
        inputColor = (String) selectColor.getValue();
        inputSeason = (String) selectSeason.getValue();
        inputOccasion = (String) selectOccasion.getValue();
        inputFavorite = yes.isSelected() ? "yes" : "no";
        inputType = (String) selectType.getValue(); //type of ...
        inputCategory = (String) selectCategory.getValue(); //top/bottom/dress/accessorize
        inputDescription = (String) chooseDescription.getText();
        inputPath = chooseImagePath.getText();

        //if any of the combobox is not completed it does not proceed in the creation of the item and an alert is generated
        if(inputColor ==null || inputSeason ==null || inputOccasion ==null || inputCategory ==null || inputType ==null){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please choose from every combobox a field!");
            alert.showAndWait();
        }
        else{
            if (!isFileValid(inputPath)){ //if the picture path is not valid generates an alert
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("The picture path is not correct");
                alert.showAndWait();
            }
            else{//Otherwise, the new item is created and the user is redirected to the HomePane
                //if there is no given description the default is an empty string
                if (inputDescription == null) {
                    inputDescription = "";
                }
                //if there is no given picture path ti set a default picture
                switch (inputType) {
                    case ("T-shirt"): {
                        inputType = "tShirt";
                        break;
                    }
                    case ("Sweater"): {
                        inputType = "sweater";
                        break;
                    }
                    case ("Sweatshirt and hoodie"): {
                        inputType = "sweatshirtAndHoodie";
                        break;
                    }
                    case ("Top and body"): {
                        inputType = "topAndBody";
                        break;
                    }
                    case ("Jacket and coat"): {
                        inputType = "jacketAndCoat";
                        break;
                    }
                    case ("Tank top"): {
                        inputType = "tankTop";
                        break;
                    }
                    case ("Skirt"): {
                        inputType = "skirt";
                        break;
                    }
                    case ("Jeans"): {
                        inputType = "jeans";
                        break;
                    }
                    case ("Trouser"): {
                        inputType = "trouser";
                        break;
                    }
                    case ("Shorts"): {
                        inputType = "shorts";
                        break;
                    }
                    case ("Long"): {
                        inputType = "longs";
                        break;
                    }
                    case ("Short"): {
                        inputType = "shorts";
                        break;
                    }
                    case ("Jewelry"): {
                        inputType = "jewelry";
                        break;
                    }
                    case ("Hat"): {
                        inputType = "hat";
                        break;
                    }
                    case ("Belt"): {
                        inputType = "belt";
                        break;
                    }
                    case ("Glasses"): {
                        inputType = "glasses";
                        break;
                    }
                    case ("Scarf"): {
                        inputType = "scarf";
                        break;
                    }
                    case ("Gloves"): {
                        inputType = "gloves";
                        break;
                    }
                    case ("Purse"): {
                        inputType = "purse";
                        break;
                    }
                    case ("Shoes"): {
                        inputType = "shoes";
                        break;
                    }
                    case ("For the hair"): {
                        inputType = "forTheHair";
                        break;
                    }
                }
                //set default pictures
                if (inputPath.isEmpty()) {
                    switch (inputCategory) {
                        case "Tops": {
                            switch (inputType) {
                                case "tShirt": {
                                    inputPath = "images/tshirt.png";
                                    break;
                                }
                                case "sweater": {
                                    inputPath = "images/sweater.png";
                                    break;
                                }
                                case "sweatshirtAndHoodie": {
                                    inputPath = "images/sweatshirtAndHoodie.png";
                                    break;
                                }
                                case "topAndBody": {
                                    inputPath = "images/topAndBody.png";
                                    break;
                                }
                                case "jacketAndCoat": {
                                    inputPath = "images/jacketAndCoat.png";
                                    break;
                                }
                                case "tankTop": {
                                    inputPath = "images/tankTop.png";
                                    break;
                                }
                            }
                            break;
                        }
                        case "Bottoms": {
                            switch (inputType) {
                                case "skirt": {
                                    inputPath = "images/skirt.png";
                                    break;
                                }
                                case "jeans": {
                                    inputPath = "images/jeans.png";
                                    break;
                                }
                                case "trouser": {
                                    inputPath = "images/trouser.png";
                                    break;
                                }
                                case "shorts": {
                                    inputPath = "images/shorts.png";
                                    break;
                                }
                            }
                            break;
                        }
                        case "Dresses": {
                            switch (inputType) {
                                case "long": {
                                    inputPath = "images/longD.jpg";
                                    break;
                                }
                                case "short": {
                                    inputPath = "images/shortD.jpg";
                                    break;
                                }
                            }
                            break;
                        }
                        case "Accessories": {
                            switch (inputType) {
                                case "jewelry": {
                                    inputPath = "images/jewelry.jpg";
                                    break;
                                }
                                case "hat": {
                                    inputPath = "images/hat.jpg";
                                    break;
                                }
                                case "belt": {
                                    inputPath = "images/belt.jpg";
                                    break;
                                }
                                case "glasses": {
                                    inputPath = "images/glasses.jpg";
                                    break;
                                }
                                case "scarf": {
                                    inputPath = "images/scarf.jpg";
                                    break;
                                }
                                case "gloves": {
                                    inputPath = "images/gloves.jpg";
                                    break;
                                }
                                case "purse": {
                                    inputPath = "images/purse.jpg";
                                    break;
                                }
                                case "shoes": {
                                    inputPath = "images/shoes.jpg";
                                    break;
                                }
                                case "forTheHair": {
                                    inputPath = "images/forTheHair.jpg";
                                    break;
                                }
                            }
                            break;
                        }
                    }
                }

                //creates the correct item based on the user's selection and add it into the closet
                int id = application.getWardrobe(index).getItemId();
                Item i = new Top (inputSeason, inputOccasion, inputColor, inputType, inputFavorite, inputPath, inputDescription, id);
                switch (inputCategory) {
                    case ("Tops"): {
                        Top nT = new Top(inputSeason, inputOccasion, inputColor, inputType, inputFavorite, inputPath, inputDescription, id);
                        getApplication().getWardrobe(getIndex()).addItem(nT);
                        i=nT;
                        break;
                    }
                    case ("Bottoms"): {
                        Bottom nB = new Bottom(inputSeason, inputOccasion, inputColor, inputType, inputFavorite, inputPath, inputDescription, id);
                        getApplication().getWardrobe(getIndex()).addItem(nB);
                        i=nB;
                        break;
                    }
                    case ("Dresses"): {
                        Dress nD = new Dress(inputSeason, inputOccasion, inputColor, inputType, inputFavorite, inputPath, inputDescription, id);
                        getApplication().getWardrobe(getIndex()).addItem(nD);
                        i=nD;
                        break;
                    }
                    case ("Accessories"): {
                        Accessorize nA = new Accessorize(inputSeason, inputOccasion, inputColor, inputType, inputFavorite, inputPath, inputDescription, id);
                        getApplication().getWardrobe(getIndex()).addItem(nA);
                        i=nA;
                        break;
                    }
                }
                updateApp();

                Alert information = new Alert (AlertType.INFORMATION);
                information.setHeaderText(null);
                information.setWidth(400);
                information.setContentText("New item was added");
                information.showAndWait();

                Stage stage = (Stage) doneOptions.getScene().getWindow();
                stage.close();
                Stage stage2 = new Stage();
                Scene scene = new Scene(new HomePane(getApplication(), getIndex()), 800, 600);
                stage2.setTitle("Home");
                stage2.setScene(scene);
                stage2.show();
            }
        }
    }

    /**
     * Event handler for when the doneFromFile button is clicked
     * if the user inserted a valid JSON file the item is created and added to the wardrobe and then the app returns to the HomePane otherwise an alert is generated
     * @param actionEvent
     */
    private void doneFromFileEvent(ActionEvent actionEvent) {
        inputCategory = (String) selectCategory.getValue();
        inputFilePath = chooseFilePath.getText();

        //if the ComboBox or the TextField is not completed it does not proceed in the creation of the item and an alert is generated
        if(inputCategory == null || inputFilePath.isEmpty() == true){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please fill both the ComboBox and the TextField!");
            alert.showAndWait();
        }
        //Otherwise, the new item is created and the user is redirected to the HomePane
        else{
            if (isFileValid2(inputFilePath)){
                //creates the correct item based on the user's selection and add it to the closet
                int id = application.getWardrobe(index).getItemId();
                Item i = new Top ("Winter", "formal", "red", "sweater", "yes", "images/sweater.png", "", 0);
                switch(inputCategory){
                    case ("Tops"):{
                        Top nT = new Top("src/main/resources/"+inputFilePath);
                        getApplication().getWardrobe(getIndex()).addItem(nT);
                        i=nT;
                        break;
                    }
                    case ("Bottoms"):{
                        Bottom nB = new Bottom(inputFilePath);
                        getApplication().getWardrobe(getIndex()).addItem(nB);
                        i=nB;
                        break;
                    }
                    case ("Dresses"):{
                        Dress nD = new Dress(inputFilePath);
                        getApplication().getWardrobe(getIndex()).addItem(nD);
                        i=nD;
                        break;
                    }
                    case ("Accessories"):{
                        Accessorize nA = new Accessorize(inputFilePath);
                        getApplication().getWardrobe(getIndex()).addItem(nA);
                        i=nA;
                        break;
                    }
                }

                if (getApplication().getWardrobe(getIndex()).getItems().indexOf(i)>0){
                    Alert information = new Alert (AlertType.INFORMATION);
                    information.setHeaderText(null);
                    information.setWidth(400);
                    information.setContentText("New item was added");
                    information.showAndWait();

                    Stage stage = (Stage) doneFromFile.getScene().getWindow();
                    stage.close();
                    Stage stage2 = new Stage();
                    Scene scene = new Scene(new HomePane(getApplication(), getIndex()), 800, 600);
                    stage2.setTitle("Home");
                    stage2.setScene(scene);
                    stage2.show();
                }
                else{
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("A problem occurred during the creation of the item");
                    alert.showAndWait();
                }
            }
            else{
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("The file path is not valid");
                alert.showAndWait();
            }

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
     * Check if the path refers to a valid image file
     * @param path
     * @return
     */
    private boolean isFileValid(String path) {
        if (path.isEmpty()){
            return true;
        }
        else {
            path = "src/main/resources/json"+path;
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
     * Check if the path refers to a valid file
     * @param path
     * @return
     */
    private boolean isFileValid2(String path) {
        path = "src/main/resources/"+path;
        File f = new File(path);
        if (f.exists() && !f.isDirectory()){
            return true;
        }
        else {
            return false;
        }
    }


    /**
     * Display the items necessary for the user to create its item from a JSON file
     * @param event
     */
    public void optionFromFileEvent(ActionEvent event){
        getChildren().clear();

        //VBox and HBox for pane fromFile
        //selectors and labels for fileName
        filePathVB = new VBox(filePath, chooseFilePath);

        HBox line0 = new HBox(optionFromScratch);
        line0.setSpacing(30);
        line0.setAlignment(Pos.CENTER);

        line1 = new HBox(categoryVB, filePathVB);
        line1.setSpacing(30);
        line1.setAlignment(Pos.CENTER);

        line2 = new HBox(doneFromFile);
        line2.setSpacing(30);
        line2.setAlignment(Pos.CENTER);

        showFromFile = new VBox(head, line0, line1,line2);
        showFromFile.setSpacing(90);
        showFromFile.setAlignment(Pos.CENTER);

        setStyle("-fx-background-color: #FFE5CC");
        setAlignment(Pos.CENTER); //alignment of the grid in the scene
        setSpacing(50);
        getChildren().addAll(showFromFile);
    }

    /**
     * Updates the json file that keeps a backup of the application that is used as "database"
     */
    public void updateApp (){
        //obj->json
        Gson gson = new Gson ();
        String jsonString = gson.toJson(getApplication());

        String path = "src\\main\\resources\\json\\app.json";

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
            BufferedReader reader = new BufferedReader(new FileReader("src\\main\\resources\\json\\app.json"));
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
}
