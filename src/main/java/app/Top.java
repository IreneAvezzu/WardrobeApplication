package app;

import com.google.gson.Gson;

import java.io.*;
import java.util.Scanner;

/**
 * The app.Top class represent a app.Top item.
 * It extends the app.Item class and its methods but implement an additional instance data typeofTop
 * @author Irene Avezzù
*/

public class Top extends Item{
    private topType typeOfTop;

    /**
     * Constructor form file json
     * @param filename
     */
    public Top(String filename) {
        super("winter", "formal", "red", "yes", "", "", "top", 0, "tshirt");
        setTypeOfTop("tshirt");
        createFromFile(filename);
    }

    /**
     * This is the constructor that gets seven String elements as parameters and assign them to the instance data of the class
     * @param selectedSeason
     * @param selectedOccasion
     * @param selectedColor
     * @param selectedType
     * @param favourite
     * @param filename
     * @param description
     */
    public Top(String selectedSeason, String selectedOccasion, String selectedColor, String selectedType, String favourite, String filename, String description, int id) {
        super(selectedSeason, selectedOccasion, selectedColor, favourite, filename, description, "top", id, selectedType);
        setTypeOfTop(selectedType);
    }

    /**
     * Create a new Top object from a json file and assign its value to the current accessorize object
     * @param filename: json file
     */
    public void createFromFile(String filename) {
        String strT, sS, sO, sC, sT, f, pFN, d;
        int id;
        Gson gson;
        Top t1;

        //read string from file
        strT = readFile(filename);

        //convert the string into an app.Accessorize object
        gson = new Gson ();
        t1 = gson.fromJson(strT, Top.class);

        // once the obj is created from the json file its value are assigned to the current object
        sS=t1.getSeasonOfItem().name();
        sO=t1.getOccasionOfItem().name();
        sC=t1.getColorOfItem().name();
        sT=t1.getTypeOfTop().name();
        f=((t1.getFavourite())==true ? "yes" : "no");
        pFN=t1.getPicture();
        d=t1.getDescription();
        id= t1.getId();

        setSeasonOfItem(sS);
        setOccasionOfItem(sO);
        setColorOfItem(sC);
        setPicture (pFN);
        setFavourite(f);
        setDescription(d);
        setTypeOfTop(sT);
        setId(id);
        setTypeOf(sT);
    }
    /**
     * Support method for createFromFile, convert the context of a file into a string
     */
    public String readFile (String path){
        String s="";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
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
     * Getter of typeOfTop
     * @return typeOfTop
     */
    public topType getTypeOfTop() {
        return typeOfTop;
    }
     /**
     * Setter of typeOfTop, a string is passed as parameter and, based on its value, the typeOfTop is set
     * @param tOT
     */
    public void setTypeOfTop(String tOT) {
        switch (tOT){
            case "tShirt":{
                this.typeOfTop = topType.tShirt;
                break;
            }
            case "sweater":{
                this.typeOfTop = topType.sweater;
                break;
            }
            case "sweatshirtAndHoodie":{
                this.typeOfTop = topType.sweatshirtAndHoodie;
                break;
            }
            case "topAndBody" :{
                this.typeOfTop = topType.topAndBody;
                break;
            }
            case "jacketAndCoat":{
                this.typeOfTop = topType.jacketAndCoat;
                break;
            }
            case "tankTop":{
                this.typeOfTop = topType.tankTop;
                break;
            }
        }
    }

    /**
     * Overwritten toString method
     */
    @Override
    public String toString (){
        return ("Type of top: " + getTypeOfTop() +
        "\nDescription: " + getDescription() +
		"\nColor: " + getColorOfItem() +
		"\nSeason: "  + getSeasonOfItem() +
		"\nOccasion: " + getOccasionOfItem() );

    }

}
