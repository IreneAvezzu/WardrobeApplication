package app;

import com.google.gson.Gson;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *This class starts our application by generating a new app.App object and creating and displaying a new app.LoginPane object
 * @author Josephine Sacchetto frontend (GUI)
 * @author Irene Avezzù backend (Upload from file)
 */
public class Start extends javafx.application.Application {
	private App application = new App();

    /**
     * Upload a app from a json file. Simulate a database
     */
    public void uloadApp(){
        Gson gson = new Gson ();
        String stringApp = readFromFile();
        App newApp = gson.fromJson(stringApp, App.class);
        for (int i=0; i<newApp.getWardarobes().size(); i++){ //repeats for all wardrobes
            newApp.getWardrobe(i).updateArray();
        }
        application = newApp;
    }

    /**
     * Support metod for uploadApp, read the context of a file and converts it into a string that is returned
     * @return
     */
    public String readFromFile(){
        String s="";
        //READ from a file using BufferedReader
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src" + File.separator + "main" + File.separator + "resources" + File.separator + "json" + File.separator +"app.json"));
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


    public void start(Stage primaryStage){
        uloadApp();
        Scene scene = new Scene(new LoginPane(application), 500, 400);
        primaryStage.setTitle("Login Page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args)  {
        launch(args);
     }
}
