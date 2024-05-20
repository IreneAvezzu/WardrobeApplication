package app;

import com.google.gson.Gson;

import java.io.*;
import java.util.Scanner;

/**
 * The app.Dress class represent an accessory item.
 * It extends the app.Item class and its methods but implement an additional instance data typeOfAccessorize
 * @author Irene Avezzù
 */

public class Dress extends Item{
    private dressType typeOfDress;

    /**
     * This is the constructor that gets seven String elements as parameters and assign them to the instance data of the class
     * @param selectedSeason
     * @param selectedOccasion
     * @param selectedColor
     * @param selectedType
     * @param filename
     * @param description
     */
    public Dress (String selectedSeason, String selectedOccasion, String selectedColor, String selectedType, String favourite, String filename, String description, int id) {
        super(selectedSeason, selectedOccasion, selectedColor, favourite, filename, description, "dress", id, selectedType);
    	setTypeOfDress(selectedType);
    }

    /**
     * Constructor form file json
     * @param filename
     */
    public Dress(String filename) {
        super("winter", "formal", "red", "yes", "", "", "dress", 0, "longs");
        setTypeOfDress("longs");
        createFromFile(filename);
    }

    /**
     * Create a new Dress object from a json file and assign its value to the current accessorize object
     * @param filename: json file
     */
    public void createFromFile(String filename) {
        String sD, sS, sO, sC, sT, f, pFN, d;
        int id;
        Scanner file;
        Gson gson;
        Dress d1;

        //read string from file
        sD = readFile(filename);

        //convert the string into an app.Accessorize object
        gson = new Gson ();
        d1 = gson.fromJson(sD, Dress.class);

        // once the obj is created from the json file its value are assigned to the current object
        sS=d1.getSeasonOfItem().name();
        sO=d1.getOccasionOfItem().name();
        sC=d1.getColorOfItem().name();
        sT=d1.getTypeOfDress().name();
        f=((d1.getFavourite())==true ? "yes" : "no");
        pFN=d1.getPicture();
        d=d1.getDescription();
        id= d1.getId();

        setSeasonOfItem(sS);
        setOccasionOfItem(sO);
        setColorOfItem(sC);
        setPicture (pFN);
        setFavourite(f);
        setDescription(d);
        setTypeOfDress(sT);
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
     * Getter of typeOfDress
     * @return typeOfDress
     */
    public dressType getTypeOfDress() {
        return typeOfDress;
    }

    /**
     * Setter of typeOfDress, a string is passed as parameter and, based on its value, the typeOfDress is set
     * @param tOD
     */
    public void setTypeOfDress(String tOD) {
        switch (tOD){
            case "longs":{
                this.typeOfDress = dressType.longs;
                break;
            }
            case "shorts":{
                this.typeOfDress = dressType.shorts;
                break;
            }
        }
    }
    /**
     * Overwritten toString method
     */
    @Override
    public String toString (){
        return ("Type of dress: " + getTypeOfDress() +
        "\nDescription: " + getDescription() +
		"\nColor: " + getColorOfItem() +
		"\nSeason: "  + getSeasonOfItem() +
		"\nOccasion: " + getOccasionOfItem() );

    }
}
