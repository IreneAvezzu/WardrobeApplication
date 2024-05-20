package app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple app.Accessoriza.
 */
class AccessorizeTest {
    /**
     * Rigorous app.Test.
     */
    /**
     * Test the constructor from file
     */
    @Test
    void testFromFile() {
        Accessorize accessorizeFromFile = new Accessorize("src/test/resources/json/accessorize.json");
        Accessorize accessorize = new Accessorize("winter",
            "everyDay",
            "Pink",
            "belt",
            "yes",
            "images/belt.png",
            "beige sunglasses", 12);

        assertEquals(accessorizeFromFile.getSeasonOfItem(), accessorize.getSeasonOfItem());
        assertEquals(accessorizeFromFile.getOccasionOfItem(), accessorize.getOccasionOfItem());
        assertEquals(accessorizeFromFile.getColorOfItem(), accessorize.getColorOfItem());
        assertEquals(accessorizeFromFile.getTypeOfAccesorize(), accessorize.getTypeOfAccesorize());
        assertEquals(accessorizeFromFile.getFavourite(), accessorize.getFavourite());
        assertEquals(accessorizeFromFile.getPicture(), accessorize.getPicture());
        assertEquals(accessorizeFromFile.getDescription(), accessorize.getDescription());
    }

    /**
     * Test the constructor
     */
    @Test
    void createTop() {
        Accessorize accessorize = new Accessorize("winter",
            "everyDay",
            "Pink",
            "belt",
            "yes",
            "images/belt.png",
            "My favourite pink belt", 12);

        boolean tf = accessorize instanceof Accessorize;

        assertEquals(tf, true);
    }

    /**
     * Check that season is set correctly
     */
    @Test
    void checkSeasonSet() {
        Accessorize accessorize = new Accessorize("winter",
            "everyDay",
            "Pink",
            "belt",
            "yes",
            "images/belt.png",
            "My favourite pink belt", 12);
        String season = (accessorize.getSeasonOfItem()).name();

        assertEquals(season, "winter");
    }
    /**
     * Check that occasion is set correctly
     */
    @Test
    void checkOccasionSet() {
        Accessorize accessorize = new Accessorize("winter",
            "everyDay",
            "Pink",
            "belt",
            "yes",
            "images/belt.png",
            "My favourite pink belt", 12);
        String occasion = (accessorize.getOccasionOfItem()).name();

        assertEquals(occasion, "everyDay");
    }
    /**
     * Check that color is set correctly
     */
    @Test
    void checkColorSet() {
        Accessorize accessorize = new Accessorize("winter",
            "everyDay",
            "Pink",
            "belt",
            "yes",
            "images/belt.png",
            "My favourite pink belt", 12);
        String color = (accessorize.getColorOfItem()).name();

        assertEquals(color, "Pink");
    }
    /**
     * Check that type is set correctly
     */
    @Test
    void checkTypeSet() {
        Accessorize accessorize = new Accessorize("winter",
            "everyDay",
            "Pink",
            "belt",
            "yes",
            "images/belt.png",
            "My favourite pink belt", 12);
        String type = String.valueOf(accessorize.getTypeOfAccesorize());

        assertEquals(type, "belt");
    }
    /**
     * Check that favourite is set correctly
     */
    @Test
    void checkFavouriteSet() {
        Accessorize accessorize = new Accessorize("winter",
            "everyDay",
            "Pink",
            "belt",
            "yes",
            "images/belt.png",
            "My favourite pink belt", 12);
        boolean fav = accessorize.getFavourite();

        assertEquals(fav, true);
    }/**
     * Check that image is set correctly
     */
    @Test
    void checkImageSet() {
        Accessorize accessorize = new Accessorize("winter",
            "everyDay",
            "Pink",
            "belt",
            "yes",
            "images/belt.png",
            "My favourite pink belt", 12);
        String image = accessorize.getPicture();

        assertEquals(image, "images/belt.png");
    }
    /**
     * Check that description is set correctly
     */
    @Test
    void checkDescriptionSet() {
        Accessorize accessorize = new Accessorize("winter",
            "everyDay",
            "Pink",
            "belt",
            "yes",
            "images/belt.png",
            "My favourite pink belt", 12);
        String description = accessorize.getDescription();

        assertEquals(description, "My favourite pink belt");
    }
    /**
     * Check that id is set correctly
     */
    @Test
    void checkIdSet() {
        Accessorize accessorize = new Accessorize("winter",
            "everyDay",
            "Pink",
            "belt",
            "yes",
            "images/belt.png",
            "My favourite pink belt", 12);
        int id = accessorize.getId();

        assertEquals(id, 12);
    }
}
