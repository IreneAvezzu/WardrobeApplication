package app;

import javafx.scene.control.MenuBar;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class AppMenuBar extends HBox{
    private App application;
    private int index;
    private MenuBar mb;
    private Menu allitems, tops, bottoms, dresses, accessories, occasions, seasons, favorites, outfits;
    private MenuItem all; //all
    private MenuItem alltops,tShirt, sweater, sweatshirtAndHoodie, topAndBody, jacketAndCoat, tankTop; //tops
    private MenuItem allbottoms,skirt, jeans, trouser, shorts; //bottoms
    private MenuItem alldresses,lengthy, little; //dresses
    private MenuItem allaccessories,jewelry, hat, belt, glasses, scarf, gloves, purse, shoes, forTheHair; //accessorise
    private MenuItem fav;
    private MenuItem allOutfits;

    private Button add;
    private ImageView profile, logo;

    private HBox menuBox;

    private int i=-1;

    public AppMenuBar (App application, int index){
        this.application = application;
        this.index = index;

        //Create the menu bar as LOGO + MENU + ADD + PROFILE
        // LOGO-------------------------------------------------------------------------------------
        logo = new ImageView("images/Logo.png");
        //MENU--------------------------------------------------------------------------------------
        // creates a menubar
        mb = new MenuBar();
        // creates all items in the MenuBar and theirs values (All, Tops, Bottoms, Dresses Accessories, Favourites)
        //fist menu -> All items
        allitems = new Menu("All Items");
        all = new MenuItem("All Items"); // create menuitems
        allitems.getItems().add(all);// add menu items to menu
        //second menu -> Top
        tops = new Menu("Tops");
        alltops = new MenuItem("All Tops");
        tShirt = new MenuItem("T-shirts");
        sweater = new MenuItem("Sweaters");
        sweatshirtAndHoodie = new MenuItem("Sweatshirts and hoodies");
        topAndBody = new MenuItem("Tops and bodys");
        jacketAndCoat = new MenuItem("Jackets and coats");
        tankTop = new MenuItem("Tank Tops");
        tops.getItems().addAll(alltops,tShirt, sweater, sweatshirtAndHoodie, topAndBody, jacketAndCoat, tankTop);// add menu items to menu
        // third menu -> Bottoms
        bottoms = new Menu("Bottoms");
        allbottoms = new MenuItem("All Bottoms");
        skirt = new MenuItem("Skirt");
        jeans = new MenuItem("Jeans");
        trouser = new MenuItem("Trouser");
        shorts = new MenuItem("Shorts");
        bottoms.getItems().addAll(allbottoms, skirt, jeans, trouser, shorts);
        // fourth menu -> Dresses
        dresses = new Menu("Dresses");
        alldresses = new MenuItem("All Dresses");
        lengthy= new MenuItem("Long Dresses");
        little= new MenuItem("Short Dresses");
        dresses.getItems().addAll(alldresses, lengthy, little);
        // fifth menu -> Accessories
        accessories = new Menu("Accessories");
        allaccessories = new MenuItem("All Accessories");
        jewelry= new MenuItem("Jewelry");
        hat= new MenuItem("Hats");
        belt= new MenuItem("Belts");
        glasses= new MenuItem("Glasses");
        scarf= new MenuItem("Scarfs");
        gloves= new MenuItem("Gloves");
        purse= new MenuItem("Purses");
        shoes= new MenuItem("Shoes");
        forTheHair= new MenuItem("For the hair");
        accessories.getItems().addAll(allaccessories,jewelry, hat, belt, glasses, scarf, gloves, purse, shoes, forTheHair);
        // sixth menu -> Favorites
        favorites = new Menu("Favorites");
        fav = new MenuItem("Favorites");
        favorites.getItems().add(fav);
        //seventh menu -> All outfits
        outfits = new Menu("Outfits");
        allOutfits = new MenuItem("Outfits"); // create menuitems
        outfits.getItems().add(allOutfits);// add menu items to menu
        //add all menus to the MenuBar
        mb.getMenus().addAll(allitems, tops, bottoms, dresses, accessories, favorites, outfits);// add menus to menubar
        //ADD---------------------------------------------------------------------------------------
        add= new Button("+");
        add.setStyle("-fx-background-color: orange");
        //PROFILE-----------------------------------------------------------------------------------
        //profile image
        ImageView profileImage = new ImageView (application.getWardrobe(index).getPicture());
        profileImage.setFitHeight(50);
        profileImage.setFitWidth(50);
        profile = profileImage;
        profile.setPickOnBounds(true); // allows click on transparent areas



        //join everything together
        menuBox = new HBox(logo, mb, add, profile);



        //Event handlers for menu bar
        //LOGO---------------------------------------------------------------------------------------
        logo.setOnMouseClicked(this::logoEvent);
        //MENU---------------------------------------------------------------------------------------
        //action when clicking on a menu item
        all.setOnAction(this::showItemsAll);
        alltops.setOnAction(this::showItemsAllTops);
        tShirt.setOnAction(this::showItemsTshirt);
        sweater.setOnAction(this::showItemsSweater);
        sweatshirtAndHoodie.setOnAction(this::showItemsSweatshirtAndHoodie);
        topAndBody.setOnAction(this::showItemsTopAndBody);
        jacketAndCoat.setOnAction(this::showItemsJacketAndCoat);
        tankTop.setOnAction(this::showItemsTankTop);
        allbottoms.setOnAction(this::showItemsAllBottoms);
        skirt.setOnAction(this::showItemsSkirt);
        jeans.setOnAction(this::showItemsJeans);
        trouser.setOnAction(this::showItemsTrouser);
        shorts.setOnAction(this::showItemsShorts);
        alldresses.setOnAction(this::showItemsAllDresses);
        lengthy.setOnAction(this::showItemsLengthy);
        little.setOnAction(this::showItemsLittle);
        allaccessories.setOnAction(this::showItemsAllAccessories);
        jewelry.setOnAction(this::showItemsJewelry);
        hat.setOnAction(this::showItemsHat);
        belt.setOnAction(this::showItemsBelt);
        glasses.setOnAction(this::showItemsGlasses);
        scarf.setOnAction(this::showItemsScarf);
        gloves.setOnAction(this::showItemsGloves);
        purse.setOnAction(this::showItemsPurse);
        shoes.setOnAction(this::showItemsShoes);
        forTheHair.setOnAction(this::showItemsForTheHair);
        fav.setOnAction(this::showItemsFav);
        allOutfits.setOnAction(this::showOutfit);
        //ADD---------------------------------------------------------------------------------------
        add.setOnAction(this::addEvent);
        //PROFILE-----------------------------------------------------------------------------------
        profile.setOnMouseClicked(this::profileEvent);
    }

    public HBox getMenuBox(){return menuBox;}

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
     * Event handler for when the logo image is clicked.
     * Return to HomePane
     * @param event
     */
    public void logoEvent (MouseEvent event){
        Stage stage = (Stage) logo.getScene().getWindow();
        stage.close();
        Stage stage2 = new Stage();
        Scene scene = new Scene(new HomePane(getApplication(), getIndex()), 800, 600);
        stage2.setTitle("App");
        stage2.setScene(scene);
        stage2.show();
    }
    /**
     * Event handler for when the add button is clicked.
     * It goes to AddPane
     * @param event
     */
    public void addEvent (ActionEvent event){
        Stage stage = (Stage) add.getScene().getWindow();
        stage.close();
        Stage stage2 = new Stage();
        Scene scene = new Scene(new AddPane(getApplication(), getIndex()), 600, 400);
        stage2.setTitle("Add Element");
        stage2.setScene(scene);
        stage2.show();
    }
    /**
     * Event handler for when the profile Image is clicked.
     * It goes to ProfilePane
     * @param event
     */
    public void profileEvent (MouseEvent event){
        Stage stage = (Stage) profile.getScene().getWindow();
        stage.close();
        Stage stage2 = new Stage();
        Scene scene = new Scene(new ProfilePane(application, index), 800, 600);
        stage2.setTitle("Profile");
        stage2.setScene(scene);
        stage2.show();
    }

    public void show (String iOrO) {
        Stage stage = (Stage) mb.getScene().getWindow();
        stage.close();
        Stage stage2 = new Stage();
        Scene scene = null;
        switch (iOrO){
            case "item":{
                switch (i){
                    case 1:{
                        scene = new Scene(new ItemPane(getApplication(), getIndex(), all.getText()), 800, 600);
                        stage2.setTitle("All item");
                        break;
                    }
                    case 2:{
                        scene = new Scene(new ItemPane(getApplication(), getIndex(), alltops.getText()), 800, 600);
                        stage2.setTitle("All Tops");
                        break;
                    }
                    case 3:{
                        scene = new Scene(new ItemPane(getApplication(), getIndex(), tShirt.getText()), 800, 600);
                        stage2.setTitle("T-shirts");
                        break;
                    }
                    case 4:{
                        scene = new Scene(new ItemPane(getApplication(), getIndex(), sweater.getText()), 800, 600);
                        stage2.setTitle("Sweaters");
                        break;
                    }
                    case 5:{
                        scene = new Scene(new ItemPane(getApplication(), getIndex(), sweatshirtAndHoodie.getText()), 800, 600);
                        stage2.setTitle("Sweatshirt and hoodie");
                        break;
                    }
                    case 6:{
                        scene = new Scene(new ItemPane(getApplication(), getIndex(), topAndBody.getText()), 800, 600);
                        stage2.setTitle("Top and body");
                        break;
                    }case 7:{
                        scene = new Scene(new ItemPane(getApplication(), getIndex(), jacketAndCoat.getText()), 800, 600);
                        stage2.setTitle("Jacket and coat");
                        break;
                    }
                    case 8:{
                        scene = new Scene(new ItemPane(getApplication(), getIndex(), tankTop.getText()), 800, 600);
                        stage2.setTitle("Tank top");
                        break;
                    }
                    case 9:{
                        scene = new Scene(new ItemPane(getApplication(), getIndex(), allbottoms.getText()), 800, 600);
                        stage2.setTitle("All bottoms");
                        break;
                    }
                    case 10:{
                        scene = new Scene(new ItemPane(getApplication(), getIndex(), skirt.getText()), 800, 600);
                        stage2.setTitle("Skirt");
                        break;
                    }
                    case 11:{
                        scene = new Scene(new ItemPane(getApplication(), getIndex(), jeans.getText()), 800, 600);
                        stage2.setTitle("Jeans");
                        break;
                    }
                    case 12:{
                        scene = new Scene(new ItemPane(getApplication(), getIndex(), trouser.getText()), 800, 600);
                        stage2.setTitle("Trouser");
                        break;
                    }
                    case 13:{
                        scene = new Scene(new ItemPane(getApplication(), getIndex(), shorts.getText()), 800, 600);
                        stage2.setTitle("Shorts");
                        break;
                    }
                    case 14:{
                        scene = new Scene(new ItemPane(getApplication(), getIndex(), alldresses.getText()), 800, 600);
                        break;
                    }
                    case 15:{
                        scene = new Scene(new ItemPane(getApplication(), getIndex(), lengthy.getText()), 800, 600);
                        stage2.setTitle("Lengthy dress");
                        break;
                    }
                    case 16:{
                        scene = new Scene(new ItemPane(getApplication(), getIndex(), little.getText()), 800, 600);
                        stage2.setTitle("Little dress");
                        break;
                    }
                    case 17:{
                        scene = new Scene(new ItemPane(getApplication(), getIndex(), allaccessories.getText()), 800, 600);
                        stage2.setTitle("All accessories");
                        break;
                    }
                    case 18:{
                        scene = new Scene(new ItemPane(getApplication(), getIndex(), jewelry.getText()), 800, 600);
                        stage2.setTitle("Jewelry");
                        break;
                    }
                    case 19:{
                        scene = new Scene(new ItemPane(getApplication(), getIndex(), hat.getText()), 800, 600);
                        stage2.setTitle("Hat");
                        break;
                    }
                    case 20:{
                        scene = new Scene(new ItemPane(getApplication(), getIndex(), belt.getText()), 800, 600);
                        stage2.setTitle("Belt");
                        break;
                    }
                    case 21:{
                        scene = new Scene(new ItemPane(getApplication(), getIndex(), glasses.getText()), 800, 600);
                        stage2.setTitle("Glasses");
                        break;
                    }
                    case 22:{
                        scene = new Scene(new ItemPane(getApplication(), getIndex(), scarf.getText()), 800, 600);
                        stage2.setTitle("Scarf");
                        break;
                    }
                    case 23:{
                        scene = new Scene(new ItemPane(getApplication(), getIndex(), gloves.getText()), 800, 600);
                        stage2.setTitle("Gloves");
                        break;
                    }
                    case 24:{
                        scene = new Scene(new ItemPane(getApplication(), getIndex(), purse.getText()), 800, 600);
                        stage2.setTitle("Purse");
                        break;
                    }
                    case 25:{
                        scene = new Scene(new ItemPane(getApplication(), getIndex(), shoes.getText()), 800, 600);
                        stage2.setTitle("Shoes");
                        break;
                    }
                    case 26:{
                        scene = new Scene(new ItemPane(getApplication(), getIndex(), forTheHair.getText()), 800, 600);
                        stage2.setTitle("Accessorise for the hair");
                        //
                        break;
                    }
                    case 27:{
                        scene = new Scene(new ItemPane(getApplication(), getIndex(), fav.getText()), 800, 600);
                        stage2.setTitle("Favourites");
                        break;
                    }
                }
                break;
            }
            case "outfit":{
                scene = new Scene(new OutfitPane(getApplication(), getIndex(), allOutfits.getText()), 800, 600);
                stage2.setTitle("Outfit");
                break;
            }
        }
        stage2.setScene(scene);
        stage2.show();
    }

    /**
     * Event handler for when a menu item is clicked.
     * @param event
     */
    private void showItemsAll(ActionEvent event) {
        i = 1;
        show("item");
    }
    private void showItemsAllTops(ActionEvent event) {
        i = 2;
        show("item");
    }
    private void showItemsTshirt(ActionEvent event) {
        i = 3;
        show("item");
    }
    private void showItemsSweater(ActionEvent event) {
        i = 4;
        show("item");
    }
    private void showItemsSweatshirtAndHoodie(ActionEvent event) {
        i = 5;
        show("item");
    }
    private void showItemsTopAndBody(ActionEvent event) {
        i = 6;
        show("item");
    }
    private void showItemsJacketAndCoat(ActionEvent event) {
        i = 7;
        show("item");
    }
    private void showItemsTankTop(ActionEvent event) {
        i = 8;
        show("item");
    }
    private void showItemsAllBottoms(ActionEvent event) {
        i = 9;
        show("item");
    }
    private void showItemsSkirt(ActionEvent event) {
        i = 10;
        show("item");
    }
    private void showItemsJeans(ActionEvent event) {
        i = 11;
        show("item");
    }
    private void showItemsTrouser(ActionEvent event) {
        i = 12;
        show("item");
    }
    private void showItemsShorts(ActionEvent event) {
        i = 13;
        show("item");
    }
    private void showItemsAllDresses(ActionEvent event) {
        i = 14;
        show("item");
    }
    private void showItemsLengthy(ActionEvent event) {
        i = 15;
        show("item");
    }
    private void showItemsLittle(ActionEvent event) {
        i = 16;
        show("item");
    }
    private void showItemsAllAccessories(ActionEvent event) {
        i = 17;
        show("item");
    }
    private void showItemsJewelry(ActionEvent event) {
        i = 18;
        show("item");
    }
    private void showItemsHat(ActionEvent event) {
        i = 19;
        show("item");
    }
    private void showItemsBelt(ActionEvent event) {
        i = 20;
        show("item");
    }
    private void showItemsGlasses(ActionEvent event) {
        i = 21;
        show("item");
    }
    private void showItemsScarf(ActionEvent event) {
        i = 22;
        show("item");
    }
    private void showItemsGloves(ActionEvent event) {
        i = 23;
        show("item");
    }
    private void showItemsPurse(ActionEvent event) {
        i = 24;
        show("item");
    }
    private void showItemsShoes(ActionEvent event) {
        i = 25;
        show("item");
    }
    private void showItemsForTheHair(ActionEvent event) {
        i = 26;
        show("item");
    }
    private void showItemsFav(ActionEvent event) {
        i = 27;
        show("item");
    }
    private void showOutfit(ActionEvent event) {
        show("outfit");
    }
}
