package app;

import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * This class generates a scene where the user can login into his/hers account
 * @author Josephine Sacchetto
 */
public class LoginPane extends GridPane{
    private Label username, password, title, register ;
    private TextField inputusername;
    private Font font, font2;
    private Button singup,login;
    private PasswordField inputpassword;
    private ImageView img;
    private App application;
    private String usn, psw;

    /**
     * Constructor of the object
     * @param application of type App
     */
    public LoginPane(App application){
        this.application = application;
        title= new Label("Login");
        img = new ImageView("images/Logo2.png");
        font = new Font("Times New Roman",24);
        font2 = Font.font("Times New Roman", FontWeight.BOLD, 40);
        username = new Label("Username:");
        inputusername = new TextField();
        password = new Label("Password:");
        inputpassword = new PasswordField();
        login= new Button("Login");
        login.setStyle("-fx-background-color: orange");
        register=new Label("Still no account? ");
        singup= new Button("Sing up");
        singup.setStyle("-fx-background-color: orange");

        title.setFont(font2);
        title.setTextFill(Color.ORANGE);

        GridPane.setHalignment(username, HPos.RIGHT); //alignment in the cell
        username.setFont(font);

        GridPane.setHalignment(password, HPos.RIGHT);
        password.setFont(font);

        login.setOnAction(this::loginEvent);
        singup.setOnAction(this::signUpEvent);

        setAlignment(Pos.CENTER); //alignment of the grid in the scene
        setHgap(20); // horizontal gap between cells
        setVgap(10);
        setStyle("-fx-background-color: white");

        //set the layout (element, column, row)
        add(img,0,0);
        add(title, 1, 0);
        add(username, 0, 1);
        add(inputusername, 1, 1);
        add(password, 0, 2);
        add(inputpassword, 1, 2);
        add(login, 2, 2);
        add(register, 0, 3);
        add(singup, 1, 3);
    }

    /**
     * Event handler for when the button login is clicked
     * goes to HomePane if the username and password are correct otherwise an Alert is generated
     * @param event
     */
    public void loginEvent(ActionEvent event){
        usn = inputusername.getText();
        psw = inputpassword.getText();
        if(getApplication().checkUsrAndPsw(usn, psw)==true){
            Stage stage = (Stage) login.getScene().getWindow();
            stage.close();
            Stage stage2 = new Stage();
            Scene scene = new Scene(new HomePane(getApplication(), getApplication().getUserIndex(usn)), 800, 600);
            stage2.setTitle("Home");
            stage2.setScene(scene);
            stage2.show();
        }
        else{
            /*
             * if the username of password are not correct a warning dialog is open, at the same time the
             * input on the textfield is canceled so the user can type again its credentials
             */
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please insert the correct credentials");
            alert.showAndWait();
            inputpassword.clear();
            inputusername.clear();
            throw new loginException();
        }
    }

    /**
     * Event handler for when the button signIn is clicked
     * goes to RegisterPane
     * @param event
     */
    public void signUpEvent(ActionEvent event){
        Stage stage = (Stage) singup.getScene().getWindow();
        stage.close();
        Stage stage2 = new Stage();
        Scene scene = new Scene(new RegisterPane(getApplication()), 500, 450);
        stage2.setTitle("Register");
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
}
