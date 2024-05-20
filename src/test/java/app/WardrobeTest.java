package app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple app.Wardrobe.
 */
class WardrobeTest {
    /**
     * Rigorous app.Test.
     */
    /**
     * Test the constructor from file
     */


    /**
     * Test the constructor
     */
    @Test
    void createwardrobe() {
        Wardrobe wardrobe = new Wardrobe("user", "psw", "email", "phone");
        boolean tf = wardrobe instanceof Wardrobe;

        assertEquals(tf, true);
    }

    /**
     * Check that UserName is set correctly
     */
    @Test
    void checkUserNameSet() {
        Wardrobe wardrobe = new Wardrobe("user", "psw", "email", "phone");

        String username = wardrobe.getUsername();

        assertEquals(username, "user");
    }
    /**
     * Check that password is set correctly
     */
    @Test
    void checkPswSet() {
        Wardrobe wardrobe = new Wardrobe("user", "psw", "email", "phone");

        String password = wardrobe.getPassword();

        assertEquals(password, "psw");
    }
    /**
     * Check that email is set correctly
     */
    @Test
    void checkEmailSet() {
        Wardrobe wardrobe = new Wardrobe("user", "psw", "email", "phone");

        String email = wardrobe.getEmail();

        assertEquals(email, "email");
    }
    /**
     * Check that phone is set correctly
     */
    @Test
    void checkPhoneSet() {
        Wardrobe wardrobe = new Wardrobe("user", "psw", "email", "phone");

        String phone = wardrobe.getPhone();

        assertEquals(phone, "phone");
    }
}
