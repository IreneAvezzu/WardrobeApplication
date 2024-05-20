package app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple app.Top.
 */
class TopTest {
    /**
     * Rigorous app.Test.
     */
    /**
     * Test the constructor from file
     */
    @Test
    void testFromFile() {
        Top topFromFile = new Top("src/test/resources/json/top.json");
        Top top = new Top("winter",
            "everyDay",
            "White",
            "tShirt",
            "yes",
            "images/tShirt.png",
            "white sweater", 0);

        assertEquals(topFromFile.getSeasonOfItem(), top.getSeasonOfItem());
        assertEquals(topFromFile.getOccasionOfItem(), top.getOccasionOfItem());
        assertEquals(topFromFile.getColorOfItem(), top.getColorOfItem());
        assertEquals(topFromFile.getTypeOfTop(), top.getTypeOfTop());
        assertEquals(topFromFile.getFavourite(), top.getFavourite());
        assertEquals(topFromFile.getPicture(), top.getPicture());
        assertEquals(topFromFile.getDescription(), top.getDescription());
    }


    /**
     * Test the constructor
     */
    @Test
    void createTop() {
        Top top = new Top("winter",
            "everyDay",
            "White",
            "tShirt",
            "yes",
            "images/tShirt.png",
            "white tShirt", 0);
        boolean tf = top instanceof Top;

        assertEquals(tf, true);
    }

    /**
     * Check that season is set correctly
     */
    @Test
    void checkSeasonSet() {
        Top top = new Top("winter",
            "everyDay",
            "White",
            "tShirt",
            "yes",
            "images/tShirt.png",
            "white tShirt", 0);
        String season = (top.getSeasonOfItem()).name();

        assertEquals(season, "winter");
    }
    /**
     * Check that occasion is set correctly
     */
    @Test
    void checkOccasionSet() {
        Top top = new Top("winter",
            "everyDay",
            "White",
            "tShirt",
            "yes",
            "images/tShirt.png",
            "white tShirt", 0);
        String occasion = (top.getOccasionOfItem()).name();

        assertEquals(occasion, "everyDay");
    }
    /**
     * Check that color is set correctly
     */
    @Test
    void checkColorSet() {
        Top top = new Top("winter",
            "everyDay",
            "White",
            "tShirt",
            "yes",
            "images/tShirt.png",
            "white tShirt", 0);
        String color = (top.getColorOfItem()).name();

        assertEquals(color, "White");
    }
    /**
     * Check that type is set correctly
     */
    @Test
    void checkTypeSet() {
        Top top = new Top("winter",
            "everyDay",
            "White",
            "tShirt",
            "yes",
            "images/tShirt.png",
            "white tShirt", 0);
        String type = (top.getTypeOfTop()).name();

        assertEquals(type, "tShirt");
    }
    /**
     * Check that favourite is set correctly
     */
    @Test
    void checkFavouriteSet() {
        Top top = new Top("winter",
            "everyDay",
            "White",
            "tShirt",
            "yes",
            "images/tShirt.png",
            "white tShirt", 0);
        boolean fav = top.getFavourite();

        assertEquals(fav, true);
    }/**
     * Check that image is set correctly
     */
    @Test
    void checkImageSet() {
        Top top = new Top("winter",
            "everyDay",
            "White",
            "tShirt",
            "yes",
            "images/tShirt.png",
            "white tShirt", 0);
        String image = top.getPicture();

        assertEquals(image, "images/tShirt.png");
    }
    /**
     * Check that description is set correctly
     */
    @Test
    void checkDescriptionSet() {
        Top top = new Top("winter",
            "everyDay",
            "White",
            "tShirt",
            "yes",
            "images/tShirt.png",
            "white tShirt", 0);
        String description = top.getDescription();

        assertEquals(description, "white tShirt");
    }
    /**
     * Check that id is set correctly
     */
    @Test
    void checkIdSet() {
        Top top = new Top("winter",
            "everyDay",
            "White",
            "tShirt",
            "yes",
            "images/tShirt.png",
            "white tShirt", 0);
        int id = top.getId();

        assertEquals(id, 0);
    }

}
