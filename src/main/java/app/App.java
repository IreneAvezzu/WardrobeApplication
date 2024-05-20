package app;

import java.util.ArrayList;

/**
 * The class app.App represent the base object used in our desktop app. It has 3 instance data, 3 arrayList:
 * users and password of type string and wardrobes of type app.Wardrobe
 * @author Irene Avezzù
 */
public class App {
    private ArrayList <Wardrobe> wardrobes = new ArrayList<>();
    private ArrayList <String> users = new ArrayList<>();
    private ArrayList <String> passwords = new ArrayList<>();

    /**
     * Constructor: initialize the object
     */
    public App() {
        this.wardrobes = new ArrayList<>();
        this.users = new ArrayList<>();
        this.passwords = new ArrayList<>();
    }

    /**
     * Check if the user of name userName exists
     * @param userName
     * @return boolean -> true: the user exist false: the user dosn't exist
     */
    public boolean userExist (String userName){
        if(users.contains(userName)) {
        	return true;
        }
        else{
            return false;
        }

    }

    /**
     * Creates a new user
     * @param userName
     * @param password
     * @param email
     * @param phone
     */
    public void createNewUser (String userName, String password, String email, String phone) {
        users.add(userName);
        passwords.add(password);
        Wardrobe nW= new Wardrobe(userName, password, email, phone);
        wardrobes.add(nW);
    }

    /**
     * Return the requested wardrobe
     * @param index
     * @return the index-th object of wardrobes
     */
    public Wardrobe getWardrobe (int index) {
        return wardrobes.get(index);
    }

    /**
     * Check if the password and the username exist and are correct
     * @param userName
     * @param password
     * @return a boolean value: true -> the username exists and password is correct
     * fales->the username doesn't exist or the password is incorrect
     */
    public boolean checkUsrAndPsw (String userName, String password){
        if (users.contains(userName)) { //username exists
        	if (password.equals(passwords.get(users.indexOf(userName)))){//password match the users' password
                return true;
            }
        	else {
        		return false;
        	}
        }
        else{
            return false;
        }
    }

    /**
     * Return the index of object of users with value username
     * @param username -> element searched
     * @return the inxed of object of users with value username
     */
    public int getUserIndex (String username) {
    	return users.indexOf(username);
    }

    /**
     * Remove the index-th object of wardrobes, users and passwords
     * @param index
     */
    public void removeUser(int index) {
    	wardrobes.remove(index);
    	users.remove(index);
    	passwords.remove(index);
    }

    /**
     * Add a new wardrobe from a Wardrobe object
     * @param w
     */
    public void addWardrobe(Wardrobe w){
        users.add(w.getUsername());
        passwords.add(w.getPassword());
        wardrobes.add(w);
    }

    public ArrayList<Wardrobe> getWardarobes(){
        return wardrobes;
    }

}
