package app;
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

import java.io.File;

/**
 * Generates a scene where the main page of the application is displayed.
 * On top there is our logo and a menu bas is present where the user can select the items to display
 * On the right the user can access its profile,
 * with the "i" buttun ther is a short description of what the application does
 * and with the "+" button you can add a new item
 * @author Joséphine Sacchetto
 */
public class HomePane extends HBox {
    private App application;
    private int index;
    private Button welcome, aboutus, information;
    private ImageView backgroudView;
    private Image backgroud;
    private AppMenuBar amb;

    /**
     * Constructor of the object
     * @param application of type App
     * @param index is the index in the App arrays from where the wardrobe and all its information is loaded
     */
    public HomePane(App application, int index){
        this.application = application;
        this.index = index;

        //get menu bar
        amb = new AppMenuBar(application, index);


        //information button
        double r = 1.5;
        information = new Button("i");
        information.setShape(new Circle(r));
        information.setMinSize(13*r, 13*r);
        information.setMaxSize(13*r, 13*r);

        //information buttons
        welcome = new Button(
                "Hello " + application.getWardrobe(index).getUsername() + " :)" +
                        "\nWelcome to \"My Wardrobe - Organize your closet\" !\n" +
                        "\n" +
                        "We're Josephine and Irene and we developed this app to create a \n" +
                        "digital version of our closet to have it always with us and to solve \n" +
                        "the problem of all people \"I have nothing to wear !\".\n" +
                        "This simple app will help you organize all of the pieces of clothing \n" +
                        "present in your wardrobe selecting also some characteristics that \n" +
                        "will help you sort all your clothes to always find the best match \n" +
                        "for your outfits.\n" +
                        "\n" +
                        "If you're intrested to learn more about us or the application use the \n" +
                        "buttons at the end of this page\n"
        );
        welcome.setStyle("-fx-background-color: #FFE5CC");
        Image img = new Image("images/Logo2.png");
        ImageView view = new ImageView(img);
        view.setFitHeight(100);
        view.setFitWidth(100);
        view.setPreserveRatio(true);
        welcome.setGraphic(view);

        aboutus = new Button("About Us");
        aboutus.setStyle("-fx-background-color: orange");

        information = new Button("Informations");
        information.setStyle("-fx-background-color: orange");

        //background image
        backgroud = new Image("images" + File.separator + "Wardrobe.jpg");
        backgroudView = new ImageView(backgroud);
        backgroudView.setFitHeight(160);
        backgroudView.setFitWidth(160);
        backgroudView.setPreserveRatio(true);

        setStyle("-fx-background-color: #FFE5CC; -fx-background-image:url('images/Wardrobe.jpg');-fx-background-repeat: no-repeat; -fx-background-size: 700 400; -fx-background-position: center center;");
        setAlignment(Pos.BASELINE_CENTER);

        //layout
        HBox menu = amb.getMenuBox();
        menu.setSpacing(20);
        menu.setAlignment(Pos.CENTER);

        HBox body = new HBox(welcome);
        body.setAlignment(Pos.CENTER);

        HBox bottom = new HBox(aboutus,information);
        bottom.setSpacing(100);
        bottom.setAlignment(Pos.CENTER);

        VBox show = new VBox(menu, body, bottom);
        show.setSpacing(120);
        show.setAlignment(Pos.CENTER);

        setSpacing(50);
        getChildren().addAll(show);

        //action when clicking on the "i"
        information.setOnAction(this::informationEvent);

        //action when clicking on  about us
        aboutus.setOnAction(this::aboutusEvent);

    }

    /**
     * Event handler for when the aboutus button is clicked.
     * It shows an information dialog box with information about us
     * @param event
     */
    private void aboutusEvent(ActionEvent event) {
        Alert information = new Alert (Alert.AlertType.INFORMATION);
        information.setHeaderText(null);
        information.setWidth(500);
        information.setHeight(550);
        information.setContentText(
                "Hi, I am Joséphine Sacchetto, and I am one of the two authors of this project. " +
                        "I am 20 years old and was born in Bolzano where I am currently attending the " +
                        "first year of university at the faculty of Computer Science. In addition " +
                        "practice volleyball competitively 4 times a week and I am studying to " +
                        "become a volleyball coach. When I have some free time, I really enjoy " +
                        "hanging out with friends and spending time with my family." +

                        "\n\nHello, I am Irene Avezzù and I am the second author of this project. " +
                        "I am 19 years old, I grew up in Venice but I moved to Bolzano last september" +
                        "to attend University. I was a ballet dancer for many years and still enjoy it but " +
                        "since two years ago I stopped practicing it. I love traveling and every time I get " +
                        "a chance to do it I take my suitcase and go explore the world"

        );

        Image image = new Image("images" + File.separator + "aboutUs.png");
        ImageView view = new ImageView(image);
        view.setFitHeight(250);
        view.setFitWidth(160);
        view.setPreserveRatio(true);
        information.setGraphic(view);

        information.showAndWait();
    }

    /**
     * Event handler for when the information button is clicked.
     * It shows an information dialog box with information about our project
     * @param event
     */
    private void informationEvent(ActionEvent event) {
        Alert information = new Alert (Alert.AlertType.INFORMATION);
        information.setHeaderText(null);
        information.setWidth(500);
        information.setContentText(
                "The menu bar on the top will help you navigate our app. \n" +
                        "Starting from the left the logo button will always bring you back to this homepage, " +
                        "following you'll be able to select which items' page you want to load, inside you will " +
                        "find all the items of that category and you'll be able to see al their details and " +
                        "delete the items that you don't want in your wardrobe. You will also find additional" +
                        "options to sort the items such as colour, season and occasion. " +
                        "Following on the menu bar, the \"+\" button will allow you to create a new item from " +
                        "scratch or to import a new one from its .json file or to create a new outfit. Lastly, " +
                        "the profile icon will lead you to your profile page where you'll be able to modify your" +
                        "data, cancel your account or log out."
        );
        information.showAndWait();
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
