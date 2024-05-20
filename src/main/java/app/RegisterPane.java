package app;

import com.google.gson.Gson;
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
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.apache.commons.lang.math.NumberUtils;

import java.io.*;

/**
 * This class generates a register scene where the user can create a new account (unless its username already exist)
 * @author Josephine Sacchetto
 */
public class RegisterPane extends GridPane{
    private Label username, password, email, phone, title, login ;
    private TextField inputusername, inputemail, inputphone;
    private PasswordField inputpassword;
    private Font font, font2;
    private Button signIn, register;
    private ImageView img;
    private String set_username, set_password, set_email, set_phone;
    private App application;

    /**
     * Constructor of the object
     * @param application of type App
     */
    public RegisterPane(App application){

        this.application = application;
        title= new Label("Register");
        font = new Font("Times New Roman",24);
        font2 = Font.font("Times New Roman", FontWeight.BOLD, 40);
        img = new ImageView("images" + File.separator + "Profile.png");
        username = new Label("Username: *");
        inputusername = new TextField();
        password = new Label("Password: *");
        inputpassword = new PasswordField();
        email = new Label("E-mail:");
        inputemail = new TextField();
        phone = new Label("Phone:");
        inputphone = new TextField();
        register= new Button("Register");
        register.setStyle("-fx-background-color: orange");
        login=new Label("Still have an account? ");
        signIn = new Button("Sign in");
        signIn.setStyle("-fx-background-color: orange");

        title.setFont(font2);
        title.setTextFill(Color.ORANGE);

        GridPane.setHalignment(username, HPos.RIGHT); //alignment in the cell
        username.setFont(font);

        GridPane.setHalignment(password, HPos.RIGHT);
        password.setFont(font);

        GridPane.setHalignment(email, HPos.RIGHT);
        email.setFont(font);

        GridPane.setHalignment(phone, HPos.RIGHT);
        phone.setFont(font);

        setStyle("-fx-background-color: white");
        setAlignment(Pos.CENTER); //alignment of the grid in the scene
        setHgap(20); // horizontal gap between cells
        setVgap(10);

        //set the layout (element, column, row)
        add(title, 1, 0);
        add(img,0,0);
        add(username, 0, 1);
        add(inputusername, 1, 1);
        add(password, 0, 2);
        add(inputpassword, 1, 2);
        add(email, 0, 3);
        add(inputemail, 1, 3);
        add(phone, 0, 4);
        add(inputphone, 1, 4);
        add(register,2,4);
        add(login, 0, 5);
        add(signIn, 1, 5);

        //event
        register.setOnAction(this::registerEvent);
        signIn.setOnAction(this::signInEvent);
    }

    /**
     * Event handler for when the button register is clicked
     * creates the new account if all the necessary info are present otherwise generates an alert
     * @author Joséphine Sacchetto
     * @param event
     */
    public void registerEvent(ActionEvent event){
        String alertText = "";
        set_username=inputusername.getText();
        set_password=inputpassword.getText();
        set_email=inputemail.getText();
        set_phone=inputphone.getText();

        if(!isUserValid(set_username) || !isPswValid(set_password)){
            alertText+="\nPlease insert a username (max. 10 character min. 5 character)\nand a password (max. 10 charactermin. 5 character containing minimum 1 letter 1 digit 1 special character (\"@\"\"#\"\"%\"\"$\"\"!\"\"?\"))";
        }
        if (!isEmailValid(set_email)){
            alertText+=("\nE-mail is not valid");
        }
        if (!isPhoneValid(set_phone)){
            alertText+=("\nPhone number is not valid");
        }


        if (isUserValid(set_username) && isPswValid(set_password) && isEmailValid(set_email) && isPhoneValid(set_phone)){
            if (accountExist(set_username)){ //if the username already exists the user an alert is generated
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("This username already exist use a new one");
                alert.showAndWait();

            }
            else{ //a new user is created
                getApplication().createNewUser(set_username, set_password, set_email, set_phone);
                updateApp();
                Stage stage = (Stage) register.getScene().getWindow();
                stage.close();
                Stage stage2 = new Stage();
                Scene scene = new Scene(new LoginPane(getApplication()), 500, 400);
                stage2.setTitle("Login");
                stage2.setScene(scene);
                stage2.show();
            }
        }
        else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText(alertText);
            alert.showAndWait();
            throw new registerException(alertText);
        }
    }

    /**
     * Event handler for when the button signIn is clicked
     * return to LoginPane
     * @param event
     */
    public void signInEvent(ActionEvent event){
        Stage stage = (Stage) signIn.getScene().getWindow();
        stage.close();
        Stage stage2 = new Stage();
        Scene scene = new Scene(new LoginPane(getApplication()), 500, 400);
        stage2.setTitle("Login");
        stage2.setScene(scene);
        stage2.show();
    }

    /**
     * Check if the username already exist
     * @param username
     * @return
     */
    public boolean accountExist(String username){
        if (getApplication().userExist(username)){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * This method is used to check whether the user entered is valid.
     * The user must contain minimum 5 maximum 10 characters
     * @author Josephine Sacchetto
     * @param user entered user to be validated
     * @return true if the user is valid, false if the user is empty or does not correspond to the requirements
     */
    public static boolean isUserValid(String user) {
        if (user.isEmpty()){
            return false;
        }
        else if (user.length()<=10 && user.length()>=5){
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * This method is used to check whether the password entered is valid.
     * The password must contain at least:
     * one letter, one number, a special character and is minimum 5 maximum 10 characters long
     * @author Josephine Sacchetto
     * @param psw entered password to be validated
     * @return true if the password is valid, false if the password is empty or does not correspond to the requirements
     */
    public static boolean isPswValid(String psw) {
        if (psw.isEmpty()){
            return false;
        }
        else if (psw.contains("1") || psw.contains("2") || psw.contains("3") || psw.contains("4") || psw.contains("5") || psw.contains("6") || psw.contains("7") || psw.contains("8") || psw.contains("9")  ){
            if (psw.contains("a") || psw.contains("b") || psw.contains("c") || psw.contains("d") || psw.contains("e") || psw.contains("f") || psw.contains("g") || psw.contains("h") || psw.contains("i") || psw.contains("j") || psw.contains("k") || psw.contains("l") || psw.contains("m") || psw.contains("n") || psw.contains("o") || psw.contains("p") || psw.contains("q") || psw.contains("r") || psw.contains("s") || psw.contains("t") || psw.contains("u") || psw.contains("v") || psw.contains("w") || psw.contains("x") || psw.contains("y") || psw.contains("z") ){
                if (psw.contains("@") || psw.contains("#") || psw.contains("%") || psw.contains("$") || psw.contains("!") || psw.contains("?")) {
                    if (psw.length() <= 10 && psw.length() >= 5){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    /**
     * This method is used to check whether the email entered is valid.
     * The email must respect the model:
     * text (letters, digits, ., _, -) @ text (letters, digits, ., _, -) . text (letters)
     * @author Irene Avezzù
     * @param email entered email to be validated
     * @return true if the email is valid or empty, false if the email does not correspond to the requirements
     */
    public static boolean isEmailValid(String email) {
        if (email.isEmpty()){
            return true;
        }
        else {
            String emailRegex = "^[a-zA-Z0-9_.-]+" + //string begins with a string that contains one or more upper/lower cases, digits and symbols as _, - and .
                    "@" + //there must be the @ symbol
                    "[a-zA-Z0-9_.-]+" + // after the @ there is another string similar to before
                    "[.]" + //there must be a .
                    "[a-zA-Z]+$"; //the string ends with a domain made of letters
            return email.matches(emailRegex);
        }
    }
    /**
     * This method is used to check whether the phone number entered is valid.
     * The email must respect the model:
     * optional prefix ('+' + 1 to 3 digits) area code (1 to 3 digits) number (4 or more digits)
     * @author Irene Avezzù
     * @param phone entered phone number to be validated
     * @return true if the phone number is valid or empty, false if the phone number does not correspond to the requirements
     */
    public static boolean isPhoneValid(String phone) {
        if (phone.isEmpty()){
            return true;
        }
        else {
            String phoneRegex = "^(\\+[0-9]{1,3})?" + // number may begin with a prefix of 1 to 3 digits
                    "-?" + //optional separator
                    "[0-9]{1,3}" + //one to three digits, representing the area code
                    "-?" + //optional separator
                    "[0-9]{4,}$"; //four or more digits, representing the main part of the phone number

            return phone.matches(phoneRegex);
        }
    }

    /**
     * Getter of application
     * @return application
     */
    public App getApplication(){
        return application;
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
