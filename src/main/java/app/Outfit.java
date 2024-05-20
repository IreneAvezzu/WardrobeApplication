package app;

import java.util.ArrayList;

/**
 * The app.Outfits class represent an outfit.
 * @author Irene Avezzù
 */
public class Outfit {
    private String  description, picture, description2;
    private int id =0;
    private ArrayList <Item> items = new ArrayList<>();
    private Seasons seasonOfOutfit;
    private Occasions occasionOfOutfit;

    /**
     * Constructor form file json
     * @param id
     * @param description
     * @param picture
     * @param description2
     * @param selectedOccasion
     * @param selectedSeason
     */
    public Outfit(int id, String picture, String description, String selectedSeason, String selectedOccasion, String description2) {
        setId(id);
        this.picture = picture;
        this.description = description;
        setSeasonOfOutfit(selectedSeason);
        setOccasionOfOutfit(selectedOccasion);
        this.description2=description2;
        description2.replace("\nnull", "");
    }

    /**
     * getter of SeasonOfOutfit
     *
     */
    public Seasons getSeasonOfOutfit() {
        return seasonOfOutfit;
    }
    /**
     * setter of SeasonOfOutfit
     *
     */
    public void setSeasonOfOutfit(String selectedSeason) {
    	selectedSeason = selectedSeason.toLowerCase();
    	switch (selectedSeason) {
	        case "winter" : {
	        	this.seasonOfOutfit = Seasons.winter;
				break;
	        }
			case "spring" : {
				this.seasonOfOutfit = Seasons.spring;
				break;
	        }
			case "summer" : {
				this.seasonOfOutfit = Seasons.summer;
				break;
			}
			case "fall" : {
				this.seasonOfOutfit = Seasons.fall;
				break;
			}
        }
    }

    /**
     * getter of OccasionOfOutfit
     *
     */
    public Occasions getOccasionOfOutfit() {
        return occasionOfOutfit;
    }
    /**
     * getter of OccasionOfOutfit
     *
     */
    public void setOccasionOfOutfit(String selectedOccasion) {
    	selectedOccasion = selectedOccasion.toLowerCase();
    	switch (selectedOccasion) {
	        case "formal" : {
	        	this.occasionOfOutfit = Occasions.formal;
				break;
	        }
			case "everyday" : {
				this.occasionOfOutfit = Occasions.everyDay;
				break;
	        }
			case "semiformal" : {
				this.occasionOfOutfit = Occasions.semiformal;
				break;
			}
			case "sporty" : {
				this.occasionOfOutfit = Occasions.sporty;
				break;
			}
    	}
    }

    /**
     * getter of id
     *
     */
    public int getId(){
        return id;
    }
    /**
     * setter of id
     *
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * getter of picture
     *
     */
    public String getPicture(){
        return picture;
    }
    /**
     * setter of picture
     *
     */
    public void setPicture(String picture){
        this.picture = picture;
    }

    /**
     * getter of description
     *
     */
    public String getDescription (){
        return description;
    }
    /**
     * setter of description
     *
     */
    public void setDescription (String description) {
        this.description = description;
    }

    /**
     * Add a new item to the outfit
     * @param newItem
     */
    public void addItem(Item newItem){
        items.add(newItem);
    }

    /**
     * Override to String
     *
     */
    public String toString (){
        String itemsDescriptions = "";
        for (int i=0; i<items.size(); i++){
            itemsDescriptions+="\n" + items.get(i).getDescription();
        }
        return (
            "Description: " + getDescription() +
                "\nSeason: "  + getSeasonOfOutfit() +
                "\nOccasion: " + getOccasionOfOutfit() +
                "\nThe items that compone this outfit are: " + description2
            );
    }
}


