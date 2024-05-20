package app;

/**
 * The app.Item class is an abstract class which represent a generic item of a closet with some instance data of
 * enum types (season, occasion, color), two strings (dexcription, picture(file name of the picture)) and a
 * boolean value (favourite: ture->yes false->no)
 * @author Irene Avezzù
 *
 * @param <T>
 */
public class Item<T> {
    private Seasons seasonOfItem;
    private Occasions occasionOfItem;
    private Colors colorOfItem;
    private boolean favourite;
    private String description, picture, type, typeOf;
    private int id;


    /**
     * This is the constructor that gets six String elements as parameters and assign them to the instance data of
     * the class
     * @param selectedSeason
     * @param selectedOccasion
     * @param selectedColor
     * @param favourite
     * @param filename
     * @param description
     */
    public Item(String selectedSeason, String selectedOccasion, String selectedColor, String favourite, String filename, String description, String selectedType, int id, String typeOf) {
        setSeasonOfItem(selectedSeason);
        setOccasionOfItem(selectedOccasion);
        setColorOfItem(selectedColor);
        setPicture (filename);
        setFavourite(favourite);
        setDescription(description);
        setType(selectedType);
        setId(id);
        setTypeOf(typeOf);
    }

    /**
     * Getter of seasonOfItem
     * @return seasonOfItem
     */
    public Seasons getSeasonOfItem() {
        return seasonOfItem;
    }
    /**
     * Setter of seasonOfItem
     * @param sS
     */
    public void setSeasonOfItem(String sS) {
    	sS = sS.toLowerCase();
    	switch (sS) {
	        case "winter" : {
	        	this.seasonOfItem = Seasons.winter;
				break;
	        }
			case "spring" : {
				this.seasonOfItem = Seasons.spring;
				break;
	        }
			case "summer" : {
				this.seasonOfItem = Seasons.summer;
				break;
			}
			case "fall" : {
				this.seasonOfItem = Seasons.fall;
				break;
			}
        }
    }

    /**
     * Getter of colorOfItem
     * @return colorOfItem
     */
    public Colors getColorOfItem() {
        return colorOfItem;
    }
    /**
     * Setter of colorOfItem
     * @param sC
     */
    public void setColorOfItem(String sC) {
    	sC = sC.toLowerCase();

    	switch (sC) {
	        case "white" : {
	        	this.colorOfItem = Colors.White;
				break;
	        }
			case "black" : {
				this.colorOfItem = Colors.Black;
				break;
	        }
			case "pink" : {
				this.colorOfItem = Colors.Pink;
				break;
			}
			case "blue" : {
				this.colorOfItem = Colors.Blue;
				break;
			}
			case "red" : {
	        	this.colorOfItem = Colors.Red;
				break;
	        }
			case "yellow" : {
				this.colorOfItem = Colors.Yellow;
				break;
	        }
			case "green" : {
				this.colorOfItem = Colors.Green;
				break;
			}
			case "beige" : {
				this.colorOfItem = Colors.Beige;
				break;
			}
			case "brown" : {
	        	this.colorOfItem = Colors.Brown;
				break;
	        }
			case "orange" : {
	        	this.colorOfItem = Colors.Orange;
				break;
	        }
    	}
    }

    /**
     * Getter of occasionOfItem
     * @return occasionOfItem
     */
    public Occasions getOccasionOfItem() {
        return occasionOfItem;
    }
    /**
     * Setter of occasionOfItem
     * @param sO
     */
    public void setOccasionOfItem(String sO) {
    	sO = sO.toLowerCase();
    	switch (sO) {
	        case "formal" : {
	        	this.occasionOfItem = Occasions.formal;
				break;
	        }
			case "everyday" : {
				this.occasionOfItem = Occasions.everyDay;
				break;
	        }
			case "semiformal" : {
				this.occasionOfItem = Occasions.semiformal;
				break;
			}
			case "sporty" : {
				this.occasionOfItem = Occasions.sporty;
				break;
			}
    	}
    }

    /**
     * Getter of favourite
     * @return favourite
     */
    public boolean getFavourite(){
        return(favourite);
    }
    /**
     * Setter of favourite
     * @param f ("yes"-> true, "no"->false)
     */
    public void setFavourite (String f){
    	f = f.toLowerCase();
    	switch (f) {
	        case "yes" : {
	        	this.favourite = true;
				break;
	        }
			case "no" : {
				this.favourite = false;
				break;
	        }
    	}
    }

    /**
     * Getter of picture
     * @return picture
     */
    public String getPicture () {
		return picture;
	}
    /**
     * Setter of picture
     * @param filename
     */
    public void setPicture (String filename){
    	this.picture = filename;
    }

    /**
     * Getter of description
     * @return description
     */
    public String getDescription () {
    	return description;
    }
    /**
     * Setter of description
     * @param description
     */
    public void setDescription(String description) {
    	this.description=description;
    }

    /**
     * Getter of id
     * @return id
     */
    public int getId () {
        return id;
    }
    /**
     * Setter of id
     * @param id
     */
    public void setId(int id) {
        this.id=id;
    }

    /**
     * Getter of type
     * @return type
     */
    public String getType () {
        return type;
    }
    /**
     * Setter of type
     * @param type
     */
    public void setType(String type) {
        this.type=type;
    }

    /**
     * Getter of typeOf
     * @return typeOf
     */
    public String getTypeOf () {
        return typeOf;
    }
    /**
     * Setter of typeOf
     * @param type
     */
    public void setTypeOf(String type) {
        this.typeOf=type;
    }

    /**
     * Overwritten toString method
     */
	@Override
	public String toString (){
		return ("Description: " + getDescription() +
		"\nColor: " + getColorOfItem() +
		"\nSeason: "  + getSeasonOfItem() +
		"\nOccasion: " + getOccasionOfItem() );
	}
}
