package app;

import com.google.gson.Gson;

import java.io.*;
import java.util.Scanner;

/**
 * The app.Bottom class represent an accessory item.
 * It extends the app.Item class and its methods  but implement an additional instance data typeOfBottom
 * @author Irene Avezzù
*/

public class Bottom extends Item {
    private bottomType typeOfBottom;

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
    public Bottom (String selectedSeason, String selectedOccasion, String selectedColor, String selectedType, String favourite, String filename, String description, int id) {
        super(selectedSeason, selectedOccasion, selectedColor, favourite, filename, description, "bottom", id, selectedType);
    	setTypeOfBottom(selectedType);
    }

    /**
     * Constructor form file json
     * @param filename
     */
    public Bottom(String filename) {
        super("winter", "formal", "red", "yes", "", "", "bottom", 0, "jeans");
        setTypeOfBottom("jeans");
        createFromFile(filename);
    }

    /**
     * Create a new Bottom object from a json file and assign its value to the current accessorize object
     * @param filename: json file
     */
    public void createFromFile(String filename) {
        String sB, sS, sO, sC, sT, f, pFN, d;
        int id;
        Gson gson;
        Bottom b1;

        //read string from file
        sB=readFile(filename);

        //convert the string into an app.Accessorize object
        gson = new Gson ();
        b1 = gson.fromJson(sB, Bottom.class);

        // once the obj is created from the json file its value are assigned to the current object
        sS=b1.getSeasonOfItem().name();
        sO=b1.getOccasionOfItem().name();
        sC=b1.getColorOfItem().name();
        sT=b1.getTypeOfBottom().name();
        f=((b1.getFavourite())==true ? "yes" : "no");
        pFN=b1.getPicture();
        d=b1.getDescription();
        id= b1.getId();

        setSeasonOfItem(sS);
        setOccasionOfItem(sO);
        setColorOfItem(sC);
        setPicture (pFN);
        setFavourite(f);
        setDescription(d);
        setTypeOfBottom(sT);
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
     * Getter of typeOfBottom
     * @return typeOfBottom
     */
    public bottomType getTypeOfBottom() {
        return typeOfBottom;
    }

    /**
     * Setter of typeOfBottom, a string is passed as parameter and, based on its value, the typeOfBottom is set
     * @param tOB
     */
    public void setTypeOfBottom(String tOB) {
        switch (tOB){
            case "skirt":{
                this.typeOfBottom = bottomType.skirt;
                break;
            }
            case "jeans":{
                this.typeOfBottom = bottomType.jeans;
                break;
            }
            case "trouser":{
                this.typeOfBottom = bottomType.trouser;
                break;
            }
            case "shorts" :{
                this.typeOfBottom = bottomType.shorts;
                break;
            }
        }
    }

    /**
     * Overwritten toString method
     */
    @Override
    public String toString (){
        return ("Type of bottom: " + getTypeOfBottom() +
        "\nDescription: " + getDescription() +
		"\nColor: " + getColorOfItem() +
		"\nSeason: "  + getSeasonOfItem() +
		"\nOccasion: " + getOccasionOfItem() );

    }

}
