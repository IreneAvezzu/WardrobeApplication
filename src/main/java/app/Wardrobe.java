package app;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represent a app.Wardrobe object that contains some personal data of the user
 * (username, password, email, phone, description) and a collection of items and outfits
 * @author Irene Avezzù
 *
 */
public class Wardrobe <T extends Item>{
    private ArrayList <Item> items = new ArrayList<>();
    private ArrayList <Item> favourites = new ArrayList<>();
    private ArrayList <Outfit> outfits = new ArrayList<>();
    private String username, password, email, phone, description = "Click Edit to modify your Bio", picture;
    private int itemId, outfitId;


    /**
     * Constructor of the class, gets 4 string as parameters and set the instance data
     * @param username
     * @param password
     * @param email
     * @param phone
     */
    //constructor
    public Wardrobe (String username, String password, String email, String phone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.picture = "images"+ File.separator + "profile.png";
        itemId =0;
    }

    /**
     * Setter of email
     * @param email
     */
    public void setEmail(String email) {
        this.email=email;
    }
    /**
     * Getter of email
     * @return email
     */
    public String getEmail(){
        return email;
    }

    /**
     * Setter of phone
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone=phone;
    }
    /**
     * Getter of phone
     * @return phone
     */
    public String getPhone(){
        return phone;
    }

    /**
     * Setter of picture
     * @param picture
     */
    public void setPicture(String picture) {
        this.picture=picture;
    }
    /**
     * Getter of picture
     * @return picture
     */
    public String getPicture(){
        return picture;
    }

    /**
     * Getter of username
     * @return username
     */
    public String getUsername(){
        return username;
    }
    /**
     * Setter of username
     * @param username
     */
    public void setUsername(String username){
        this.username = username;
    }

    /**
     * Getter of password
     * @return password
     */
    public String getPassword(){
        return password;
    }
    /**
     * Setter of password
     * @param password
     */
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * Getter of favourites (ArrayList of favourites items)
     * @return favourites
     */
    public ArrayList <Item> getFavourites (){
        setFavourties();
        return favourites;
    }
    /**
     * Setter of favourites
     */
    public void setFavourties(){
        ArrayList <Item> newFavourites = new ArrayList<>();
        for (Item it : items){
            if (it.getFavourite()==true)
                newFavourites.add(it);
        }
        favourites = newFavourites;
    }

    /**
     * Getter of items (ArrayList of all items)
     * @return items
     */
    public ArrayList <Item> getItems (){
        return items;
    }

    /**
     * Getter of outfits (ArrayList of all outfits)
     * @return outfits
     */
    public ArrayList <Outfit> getOutfits (){
        return outfits;
    }

    /**
     * Getter of description
     * @return description
     */
    public String getDescription() {
        return description;
    }
    /**
     * Setter of description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter of id
     * @return id
     */
    public int getItemId(){return itemId;}
    /**
     * Setter of id
     */
    public void setItemId(){
        itemId +=1;
    }

    /**
     * Add a new app.Item of type T (extends app.Accessorize, app.Bottom, app.Dress, app.Top) to the ArrayList of items
     * @param newItem
     */
    public void addItem (T newItem){
        items.add(newItem);
        setItemId();
    }

    /**
     * Add a new app.Outfit object to the ArrayList of outfits
     * @param newOutfit
     */
    public void addOutfit (Outfit newOutfit) {
        outfits.add(newOutfit);
        setOutfitId();
    }

    /**
     * search from a list items those who respect all the given filters (AND conditions)
     * @param list
     * @param filter
     * @return
     */
    public ArrayList <Item> search (ArrayList <Item> list, String... filter){
        ArrayList <Item> filteredItems = list;
        for (String f : filter){
            filteredItems = search(filteredItems, f);
        }
        return filteredItems;
    }

    /**
     * search from a list items those who respect the given filter
     * @param list
     * @param filter
     * @return
     */
    public ArrayList <Item> search (ArrayList <Item> list, String filter){
        ArrayList <Item> filteredItems = new ArrayList <> ();

        //list that contains the possible filter
        ArrayList <String> colors = new ArrayList <> ();
        List<String> colorsList = Arrays.asList("White", "Black", "Pink", "Blue", "Red", "Yellow", "Green", "Beige", "Brown", "Orange");
        colors.addAll(colorsList);

        ArrayList <String> seasons = new ArrayList <> ();
        List<String> seasonsList = Arrays.asList("winter", "spring", "summer", "fall");
        seasons.addAll(seasonsList);

        ArrayList <String> occasions = new ArrayList <> ();
        List<String> occasionsList = Arrays.asList("formal", "everyDay","semiformal", "sporty");
        occasions.addAll(occasionsList);

        ArrayList <String> itemTypes = new ArrayList <> ();
        List<String> itemTypesList = Arrays.asList("top", "bottom", "dress", "accessorize");
        itemTypes.addAll(itemTypesList);

        ArrayList <String> topTypes = new ArrayList <> ();
        List<String> topTypesList = Arrays.asList("tShirt", "sweater", "sweatshirtAndHoodie", "topAndBody", "jacketAndCoat", "thankTop");
        topTypes.addAll(topTypesList);

        ArrayList <String> bottomTypes = new ArrayList <> ();
        List<String> bottomTypesList = Arrays.asList("skirt", "jeans", "trouser", "shorts");
        bottomTypes.addAll(bottomTypesList);

        ArrayList <String> dressTypes = new ArrayList <> ();
        List<String> dressTypesList = Arrays.asList("longsD", "shortsD");
        dressTypes.addAll(dressTypesList);

        ArrayList <String> accessorizeTypes = new ArrayList <> ();
        List<String> accessorizeTypesList = Arrays.asList("jewelry", "hat", "belt", "glasses", "scarf", "gloves", "purse", "shoes", "forTheHair");
        accessorizeTypes.addAll(accessorizeTypesList);

        ArrayList <String> favorite = new ArrayList <> ();
        List<String> favoriteList = Arrays.asList("Favorites");
        favorite.addAll(favoriteList);

        //based on in which liste the filter is present the corresponding method  is called to filter the items
        if(favorite.contains(filter)){
            filteredItems = getFavourites();
        }
        else if (colors.contains(filter)) {
            Colors selectedColor = null;
            switch (filter) {
                case "White" : {
                    selectedColor = Colors.White;
                    break;
                }
                case "Black" : {
                    selectedColor = Colors.Black;
                    break;
                }
                case "Pink" : {
                    selectedColor = Colors.Pink;
                    break;
                }
                case "Blue" : {
                    selectedColor = Colors.Blue;
                    break;
                }
                case "Red" : {
                    selectedColor = Colors.Red;
                    break;
                }
                case "Yellow" : {
                    selectedColor = Colors.Yellow;
                    break;
                }
                case "Green" : {
                    selectedColor = Colors.Green;
                    break;
                }
                case "Beige" : {
                    selectedColor = Colors.Beige;
                    break;
                }
                case "Brown" : {
                    selectedColor = Colors.Brown;
                    break;
                }
                case "Orange" : {
                    selectedColor = Colors.Orange;
                    break;
                }
            }

            filteredItems = searchColor (list, selectedColor);
        }
        else if (seasons.contains(filter)){
            Seasons selectedSeason = null;
            switch (filter) {
                case "winter" : {
                    selectedSeason = Seasons.winter;
                    break;
                }
                case "spring" : {
                    selectedSeason = Seasons.spring;
                    break;
                }
                case "summer" : {
                    selectedSeason = Seasons.summer;
                    break;
                }
                case "fall" : {
                    selectedSeason = Seasons.fall;
                    break;
                }
            }
            filteredItems = searchSeason(list, selectedSeason);
        }
        else if (occasions.contains(filter)){
            Occasions selectedOccasion = null;
            switch (filter) {
                case "formal" : {
                    selectedOccasion = Occasions.formal;
                    break;
                }
                case "everyDay" : {
                    selectedOccasion = Occasions.everyDay;
                    break;
                }
                case "semiformal" : {
                    selectedOccasion = Occasions.semiformal;
                    break;
                }
                case "sporty" : {
                    selectedOccasion = Occasions.sporty;
                    break;
                }
            }
            filteredItems = searchOccasion(list, selectedOccasion);
        }
        else if (itemTypes.contains(filter)){
            switch (filter) {
                case "top" : {
                    filteredItems = searchTop(list);
                    break;
                }
                case "bottom" : {
                    filteredItems = searchBottom(list);
                    break;
                }
                case "dress" : {
                    filteredItems = searchDress(list);
                    break;
                }
                case "accessorize" : {
                    filteredItems = searchAccessorize(list);
                    break;
                }
            }
        }
        else if (topTypes.contains(filter)){
            topType selectedType = null;
            switch (filter) {
                case "tShirt" : {
                    selectedType = topType.tShirt;
                    break;
                }
                case "sweater" : {
                    selectedType = topType.sweater;
                    break;
                }
                case "sweatshirtAndHoodie" : {
                    selectedType = topType.sweatshirtAndHoodie;
                    break;
                }
                case "topAndBody" : {
                    selectedType = topType.topAndBody;
                    break;
                }
                case "jacketAndCoat" : {
                    selectedType = topType.jacketAndCoat;
                    break;
                }
                case "thankTop" : {
                    selectedType = topType.tankTop;
                    break;
                }
            }
            filteredItems = searchTopType(searchTop(list), selectedType);
        }
        else if (bottomTypes.contains(filter)){
            bottomType selectedType = null;
            switch (filter) {
                case "skirt" : {
                    selectedType = bottomType.skirt;
                    break;
                }
                case "jeans" : {
                    selectedType = bottomType.jeans;
                    break;
                }
                case "trouser" : {
                    selectedType = bottomType.trouser;
                    break;
                }
                case "shorts" : {
                    selectedType = bottomType.shorts;
                    break;
                }
            }
            filteredItems = searchBottomType(searchBottom(list), selectedType);
        }
        else if (dressTypes.contains(filter)){
            dressType selectedType = null;
            switch (filter) {
                case "longsD" : {
                    selectedType = dressType.longs;
                    break;
                }
                case "shortsD" : {
                    selectedType = dressType.shorts;
                    break;
                }
            }
            filteredItems = searchDressType(searchDress(list), selectedType);
        }
        else if (accessorizeTypes.contains(filter)){
            accessorizeType selectedType = null;
            switch (filter) {
                case "jewelry" : {
                    selectedType = accessorizeType.jewelry;
                    break;
                }
                case "hat" : {
                    selectedType = accessorizeType.hat;
                    break;
                }
                case "belt" : {
                    selectedType = accessorizeType.belt;
                    break;
                }
                case "glasses" : {
                    selectedType = accessorizeType.glasses;
                    break;
                }
                case "scarf" : {
                    selectedType = accessorizeType.scarf;
                    break;
                }
                case "gloves" : {
                    selectedType = accessorizeType.gloves;
                    break;
                }
                case "purse" : {
                    selectedType = accessorizeType.purse;
                    break;
                }
                case "shoes" : {
                    selectedType = accessorizeType.shoes;
                    break;
                }
                case "forTheHair" : {
                    selectedType = accessorizeType.forTheHair;
                    break;
                }
            }
            filteredItems = searchAccessorizeType(searchAccessorize(list), selectedType);
        }


        return filteredItems;
    }

    /**
     * search by feature -> colorOfItem
     * @param list
     * @param filter
     * @return filteredItems: filtered list
     */
    private ArrayList <Item> searchColor(ArrayList<Item> list, Colors filter) {
        ArrayList <Item> filteredItems = new ArrayList <> ();
        for (int i=0; i<list.size(); i++) {
            if (list.get(i).getColorOfItem() == filter) {
                filteredItems.add(list.get(i));
            }
        }
        return filteredItems;
    }
    /**
     * search by feature -> occasionOfItem
     * @param list
     * @param filter
     * @return filteredItems: filtered list
     */
    public ArrayList <Item> searchOccasion (ArrayList <Item> list, Occasions filter){
        ArrayList <Item> filteredItems = new ArrayList <> ();
        for (int i=0; i<list.size(); i++) {
            if (list.get(i).getOccasionOfItem()==filter) {
                filteredItems.add(list.get(i));
            }
        }
        return filteredItems;
    }
    /**
     * search by feature -> seasonOfItem
     * @param list
     * @param filter
     * @return filteredItems: filtered list
     */
    public ArrayList <Item> searchSeason (ArrayList <Item> list, Seasons filter){
        ArrayList <Item> filteredItems = new ArrayList <> ();
        for (int i=0; i<list.size(); i++) {
            if (list.get(i).getSeasonOfItem()==filter) {
                filteredItems.add(list.get(i));
            }
        }
        return filteredItems;
    }

    /**
     * search by type of app.Item -> app.Top
     * @param list
     * @return filteredItems: filtered list
     */
    public ArrayList <Item> searchTop (ArrayList <Item> list){
        ArrayList <Item> filteredItems = new ArrayList <> ();
        for (int i=0; i<list.size(); i++) {
            if(((Item)list.get(i)).getType().equals("top")){
                filteredItems.add(list.get(i));
            }
        }
        return filteredItems;
    }
    /**
     * search by type of app.Item -> app.Bottom
     * @param list
     * @return filteredItems: filtered list
     */
    public ArrayList <Item> searchBottom (ArrayList <Item> list){
        ArrayList <Item> filteredItems = new ArrayList <> ();
        for (int i=0; i<list.size(); i++) {
            if(list.get(i).getType().equals("bottom")){
                filteredItems.add(list.get(i));
            }
        }
        return filteredItems;
    }
    /**
     * search by type of app.Item -> app.Dress
     * @param list
     * @return filteredItems: filtered list
     */
    public ArrayList <Item> searchDress (ArrayList <Item> list){
        ArrayList <Item> filteredItems = new ArrayList <> ();
        for (int i=0; i<list.size(); i++) {
            if(list.get(i).getType().equals("dress")){
                filteredItems.add(list.get(i));
            }
        }
        return filteredItems;
    }
    /**
     * search by type of app.Item -> app.Accessorize
     * @param list
     * @return filteredItems: filtered list
     */
    public ArrayList <Item> searchAccessorize (ArrayList <Item> list){
        ArrayList <Item> filteredItems = new ArrayList <> ();
        for (int i=0; i<list.size(); i++) {
            if(list.get(i).getType().equals("accessorize")){
                filteredItems.add(list.get(i));
            }
        }
        return filteredItems;
    }

    /**
     * search by type of app.Top
     * @param list
     * @param filter
     * @return filteredItems: filtered list
     */
    public ArrayList <Item> searchTopType (ArrayList <Item> list, topType filter){
        ArrayList <Item> filteredItems = new ArrayList <> ();
        Top tempT;
        for (int i=0; i<list.size(); i++) {
            tempT = convertTop(list.get(i));
            if (tempT.getTypeOfTop()==filter) {
                filteredItems.add(list.get(i));
            }
        }
        return filteredItems;
    }
    /**
     * search by type of app.Bottom
     * @param list
     * @param filter
     * @return filteredItems: filtered list
     */
    public ArrayList <Item> searchBottomType (ArrayList <Item> list, bottomType filter){
        ArrayList <Item> filteredItems = new ArrayList <> ();
        Bottom tempB;
        for (int i=0; i<list.size(); i++) {
            tempB = convertBottom(list.get(i));
            if (tempB.getTypeOfBottom()==filter) {
                filteredItems.add(list.get(i));
            }
        }
        return filteredItems;
    }
    /**
     * search by type of app.Dress
     * @param list
     * @param filter
     * @return filteredItems: filtered list
     */
    public ArrayList <Item> searchDressType (ArrayList <Item> list, dressType filter){
        ArrayList <Item> filteredItems = new ArrayList <> ();
        Dress tempD;
        for (int i=0; i<list.size(); i++) {
            tempD = convertDress(list.get(i));
            if (tempD.getTypeOfDress()==filter) {
                filteredItems.add(list.get(i));
            }
        }
        return filteredItems;
    }
    /**
     * search by type of app.Accessorize
     * @param list
     * @param filter
     * @return filteredItems: filtered list
     */
    public ArrayList <Item> searchAccessorizeType (ArrayList <Item> list, accessorizeType filter){
        ArrayList <Item> filteredItems = new ArrayList <> ();
        Accessorize tempA;
        for (int i=0; i<list.size(); i++) {
            tempA = convertAccessorize(list.get(i));
            if (tempA.getTypeOfAccesorize()==filter) {
                filteredItems.add(list.get(i));
            }
        }
        return filteredItems;
    }

    public void removeId(int id){
        for (int i=0; i<getItems().size(); i++){
            if (getItems().get(i).getId()==id){
                items.remove(i);
            }
        }
    }

    public Item searchId(int id){
        for (int i=0; i<getItems().size(); i++){
            if (getItems().get(i).getId()==id){
                return getItems().get(i);
            }
        }
        return null;
    }

    public int getOutfitId(){
        return outfitId;
    }
    /**
     * Setter of id
     */
    public void setOutfitId(){
        outfitId +=1;
    }

    public void updateArray(){
        String type, sS, sO, sC, sT, f, pFN, d;
        int id;
        for (int i=0; i<getItems().size(); i++){ //repeats for all items
            type=items.get(i).getType();
            sS = items.get(i).getSeasonOfItem().name();
            sO = items.get(i).getOccasionOfItem().name();
            sC = items.get(i).getColorOfItem().name();
            sT = items.get(i).getTypeOf();
            f=((items.get(i).getFavourite())==true ? "yes" : "no");
            pFN=items.get(i).getPicture();
            d=items.get(i).getDescription();
            id= items.get(i).getId();

            switch (type){
                case "top":{
                    Top nT = convertTop(items.get(i));
                    items.set(i, nT);
                    break;
                }
                case "bottom":{
                    Bottom nB = convertBottom(items.get(i));
                    items.set(i, nB);
                    break;
                }
                case "dress":{
                    Dress nD = convertDress(items.get(i));
                    items.set(i, nD);
                    break;
                }
                case "accessorize":{
                    Accessorize nA = convertAccessorize(items.get(i));
                    items.set(i, nA);
                    break;
                }
            }
        }
    }

    public Top convertTop (Item item){
        String array [] = readInfo(item);
        int id = Integer.parseInt(array[7]);
        Top nT = new Top (array[0], array[1], array[2], array[3], array[4], array[5], array[6], id);
        return nT;
    }
    public Bottom convertBottom (Item item){
        String array [] = readInfo(item);
        int id = Integer.parseInt(array[7]);
        Bottom nB = new Bottom (array[0], array[1], array[2], array[3], array[4], array[5], array[6], id);
        return nB;
    }
    public Dress convertDress (Item item){
        String array [] = readInfo(item);
        int id = Integer.parseInt(array[7]);
        Dress nD = new Dress (array[0], array[1], array[2], array[3], array[4], array[5], array[6], id);
        return nD;
    }
    public Accessorize convertAccessorize (Item item){
        String array [] = readInfo(item);
        int id = Integer.parseInt(array[7]);
        Accessorize nA = new Accessorize (array[0], array[1], array[2], array[3], array[4], array[5], array[6], id);
        return nA;
    }

    /**
     * Support method for convertTop, convertBottom, convertDress, convertAccessorize
     * @param item
     * @return
     */
    public String [] readInfo(Item item){
        String array [] = new String [8];

        array[0] = item.getSeasonOfItem().name(); //sS
        array[1] = item.getOccasionOfItem().name(); //sO
        array[2] = item.getColorOfItem().name(); //sC
        array[3] = item.getTypeOf(); //sT
        array[4] = ((item.getFavourite())==true ? "yes" : "no"); //f
        array[5] = item.getPicture(); //pFN
        array[6] = item.getDescription(); //d
        array[7] = String.valueOf(item.getId()); //id

        return array;
    }


    /**
     * search from a list of outfit those who respect all the given filters (AND conditions)
     * @param list
     * @param filter
     * @return
     */
    public ArrayList <Outfit> searchOutfit (ArrayList <Outfit> list, String... filter){
        ArrayList <Outfit> filteredItems = list;
        for (String f : filter){
            filteredItems = searchOutfit(filteredItems, f);
        }
        return filteredItems;
    }
    /**
     * search from a list outfit those who respect the given filter
     * @param list
     * @param filter
     * @return
     */
    public ArrayList <Outfit> searchOutfit (ArrayList <Outfit> list, String filter){
        ArrayList <Outfit> filteredItems = new ArrayList <> ();

        ArrayList <String> seasons = new ArrayList <> ();
        List<String> seasonsList = Arrays.asList("winter", "spring", "summer", "fall");
        seasons.addAll(seasonsList);

        ArrayList <String> occasions = new ArrayList <> ();
        List<String> occasionsList = Arrays.asList("formal", "everyDay","semiformal", "sporty");
        occasions.addAll(occasionsList);

        if (seasons.contains(filter)){
            Seasons selectedSeason = null;
            switch (filter) {
                case "winter" : {
                    selectedSeason = Seasons.winter;
                    break;
                }
                case "spring" : {
                    selectedSeason = Seasons.spring;
                    break;
                }
                case "summer" : {
                    selectedSeason = Seasons.summer;
                    break;
                }
                case "fall" : {
                    selectedSeason = Seasons.fall;
                    break;
                }
            }
            filteredItems = searchSeasonOutfit(list, selectedSeason);
        }
        else if (occasions.contains(filter)){
            Occasions selectedOccasion = null;
            switch (filter) {
                case "formal" : {
                    selectedOccasion = Occasions.formal;
                    break;
                }
                case "everyDay" : {
                    selectedOccasion = Occasions.everyDay;
                    break;
                }
                case "semiformal" : {
                    selectedOccasion = Occasions.semiformal;
                    break;
                }
                case "sporty" : {
                    selectedOccasion = Occasions.sporty;
                    break;
                }
            }
            filteredItems = searchOccasionOutfit(list, selectedOccasion);
        }


        return filteredItems;
    }

    /**
     * search by feature -> occasionOfItem
     * @param list
     * @param filter
     * @return filteredItems: filtered list
     */
    public ArrayList <Outfit> searchOccasionOutfit (ArrayList <Outfit> list, Occasions filter){
        ArrayList <Outfit> filteredItems = new ArrayList <> ();
        for (int i=0; i<list.size(); i++) {
            if (list.get(i).getOccasionOfOutfit()==filter) {
                filteredItems.add(list.get(i));
            }
        }
        return filteredItems;
    }
    /**
     * search by feature -> seasonOfItem
     * @param list
     * @param filter
     * @return filteredItems: filtered list
     */
    public ArrayList <Outfit> searchSeasonOutfit (ArrayList <Outfit> list, Seasons filter){
        ArrayList <Outfit> filteredItems = new ArrayList <> ();
        for (int i=0; i<list.size(); i++) {
            if (list.get(i).getSeasonOfOutfit()==filter) {
                filteredItems.add(list.get(i));
            }
        }
        return filteredItems;
    }
}
