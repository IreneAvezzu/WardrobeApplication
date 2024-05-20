package app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple app.Bottom.
 */
class BottomTest {
    /**
     * Rigorous app.Test.
     */
    /**
     * Test the constructor from file
     */
    @Test
    void testFromFile() {
        Bottom bottomFromFile = new Bottom("src/test/resources/json/bottom.json");
        Bottom bottom = new Bottom("winter",
            "everyDay",
            "Red",
            "skirt",
            "yes",
            "images/skirt.png",
            "blue trouser", 4);

        assertEquals(bottomFromFile.getSeasonOfItem(), bottom.getSeasonOfItem());
        assertEquals(bottomFromFile.getOccasionOfItem(), bottom.getOccasionOfItem());
        assertEquals(bottomFromFile.getColorOfItem(), bottom.getColorOfItem());
        assertEquals(bottomFromFile.getTypeOfBottom(), bottom.getTypeOfBottom());
        assertEquals(bottomFromFile.getFavourite(), bottom.getFavourite());
        assertEquals(bottomFromFile.getPicture(), bottom.getPicture());
        assertEquals(bottomFromFile.getDescription(), bottom.getDescription());
    }

    /**
     * Test the constructor
     */
    @Test
    void createTop() {
        Bottom bottom = new Bottom("winter",
            "everyDay",
            "Red",
            "skirt",
            "yes",
            "images/skirt.png",
            "Red skirt", 4);

        boolean tf = bottom instanceof Bottom;

        assertEquals(tf, true);
    }

    /**
     * Check that season is set correctly
     */
    @Test
    void checkSeasonSet() {
        Bottom bottom = new Bottom("winter",
            "everyDay",
            "Red",
            "skirt",
            "yes",
            "images/skirt.png",
            "Red skirt", 4);
        String season = (bottom.getSeasonOfItem()).name();

        assertEquals(season, "winter");
    }
    /**
     * Check that occasion is set correctly
     */
    @Test
    void checkOccasionSet() {
        Bottom bottom = new Bottom("winter",
            "everyDay",
            "Red",
            "skirt",
            "yes",
            "images/skirt.png",
            "Red skirt", 4);
        String occasion = (bottom.getOccasionOfItem()).name();

        assertEquals(occasion, "everyDay");
    }
    /**
     * Check that color is set correctly
     */
    @Test
    void checkColorSet() {
        Bottom bottom = new Bottom("winter",
            "everyDay",
            "Red",
            "skirt",
            "yes",
            "images/skirt.png",
            "Red skirt", 4);
        String color = (bottom.getColorOfItem()).name();

        assertEquals(color, "Red");
    }
    /**
     * Check that type is set correctly
     */
    @Test
    void checkTypeSet() {
        Bottom bottom = new Bottom("winter",
            "everyDay",
            "Red",
            "skirt",
            "yes",
            "images/skirt.png",
            "Red skirt", 4);
        String type = (bottom.getTypeOfBottom()).name();

        assertEquals(type, "skirt");
    }
    /**
     * Check that favourite is set correctly
     */
    @Test
    void checkFavouriteSet() {
        Bottom bottom = new Bottom("winter",
            "everyDay",
            "Red",
            "skirt",
            "yes",
            "images/skirt.png",
            "Red skirt", 4);
        boolean fav = bottom.getFavourite();

        assertEquals(fav, true);
    }/**
     * Check that image is set correctly
     */
    @Test
    void checkImageSet() {
        Bottom bottom = new Bottom("winter",
            "everyDay",
            "Red",
            "skirt",
            "yes",
            "images/skirt.png",
            "Red skirt", 4);
        String image = bottom.getPicture();

        assertEquals(image, "images/skirt.png");
    }
    /**
     * Check that description is set correctly
     */
    @Test
    void checkDescriptionSet() {
        Bottom bottom = new Bottom("winter",
            "everyDay",
            "Red",
            "skirt",
            "yes",
            "images/skirt.png",
            "Red skirt", 4);
        String description = bottom.getDescription();

        assertEquals(description, "Red skirt");
    }
    /**
     * Check that id is set correctly
     */
    @Test
    void checkIdSet() {
        Bottom bottom = new Bottom("winter",
            "everyDay",
            "Red",
            "skirt",
            "yes",
            "images/skirt.png",
            "Red skirt", 4);
        int id = bottom.getId();

        assertEquals(id, 4);
    }
}
