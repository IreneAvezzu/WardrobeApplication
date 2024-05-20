package app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple app.Dress.
 */
class DressTest {
    /**
     * Rigorous app.Test.
     */
    /**
     * Test the constructor from file
     */
    @Test
    void testFromFile() {
        Dress dressFromFile = new Dress("src/test/resources/json/dress.json");
        Dress dress = new Dress("winter",
            "everyDay",
            "Brown",
            "longs",
            "yes",
            "images/longD.png",
            "my fav long dress (it\u0027s yellow)", 8);

        assertEquals(dressFromFile.getSeasonOfItem(), dress.getSeasonOfItem());
        assertEquals(dressFromFile.getOccasionOfItem(), dress.getOccasionOfItem());
        assertEquals(dressFromFile.getColorOfItem(), dress.getColorOfItem());
        assertEquals(dressFromFile.getTypeOfDress(), dress.getTypeOfDress());
        assertEquals(dressFromFile.getFavourite(), dress.getFavourite());
        assertEquals(dressFromFile.getPicture(), dress.getPicture());
        assertEquals(dressFromFile.getDescription(), dress.getDescription());
    }

    /**
     * Test the constructor
     */
    @Test
    void createTop() {
        Dress dress = new Dress("winter",
            "everyDay",
            "Brown",
            "longs",
            "yes",
            "images/longD.png",
            "my fav long dress (it\u0027s brown)", 8);

        boolean tf = dress instanceof Dress;

        assertEquals(tf, true);
    }

    /**
     * Check that season is set correctly
     */
    @Test
    void checkSeasonSet() {
        Dress dress = new Dress("winter",
            "everyDay",
            "Brown",
            "longs",
            "yes",
            "images/longD.png",
            "my fav long dress (it\u0027s brown)", 8);
        String season = (dress.getSeasonOfItem()).name();

        assertEquals(season, "winter");
    }
    /**
     * Check that occasion is set correctly
     */
    @Test
    void checkOccasionSet() {
        Dress dress = new Dress("winter",
            "everyDay",
            "Brown",
            "longs",
            "yes",
            "images/longD.png",
            "my fav long dress (it\u0027s brown)", 8);
        String occasion = (dress.getOccasionOfItem()).name();

        assertEquals(occasion, "everyDay");
    }
    /**
     * Check that color is set correctly
     */
    @Test
    void checkColorSet() {
        Dress dress = new Dress("winter",
            "everyDay",
            "Brown",
            "longs",
            "yes",
            "images/longD.png",
            "my fav long dress (it\u0027s brown)", 8);
        String color = (dress.getColorOfItem()).name();

        assertEquals(color, "Brown");
    }
    /**
     * Check that type is set correctly
     */
    @Test
    void checkTypeSet() {
        Dress dress = new Dress("winter",
            "everyDay",
            "Brown",
            "longs",
            "yes",
            "images/longD.png",
            "my fav long dress (it\u0027s brown)", 8);
        String type = (dress.getTypeOfDress()).name();

        assertEquals(type, "longs");
    }
    /**
     * Check that favourite is set correctly
     */
    @Test
    void checkFavouriteSet() {
        Dress dress = new Dress("winter",
            "everyDay",
            "Brown",
            "longs",
            "yes",
            "images/longD.png",
            "my fav long dress (it\u0027s brown)", 8);
        boolean fav = dress.getFavourite();

        assertEquals(fav, true);
    }/**
     * Check that image is set correctly
     */
    @Test
    void checkImageSet() {
        Dress dress = new Dress("winter",
            "everyDay",
            "Brown",
            "longs",
            "yes",
            "images/longD.png",
            "my fav long dress (it\u0027s brown)", 8);
        String image = dress.getPicture();

        assertEquals(image, "images/longD.png");
    }
    /**
     * Check that description is set correctly
     */
    @Test
    void checkDescriptionSet() {
        Dress dress = new Dress("winter",
            "everyDay",
            "Brown",
            "longs",
            "yes",
            "images/longD.png",
            "my fav long dress (it\u0027s brown)", 8);
        String description = dress.getDescription();

        assertEquals(description, "my fav long dress (it\u0027s brown)");
    }
    /**
     * Check that id is set correctly
     */
    @Test
    void checkIdSet() {
        Dress dress = new Dress("winter",
            "everyDay",
            "Brown",
            "longs",
            "yes",
            "images/longD.png",
            "my fav long dress (it\u0027s brown)", 8);
        int id = dress.getId();

        assertEquals(id, 8);
    }
}
