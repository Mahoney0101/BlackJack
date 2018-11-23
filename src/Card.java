import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Card implements Game {
    private String suit;
    private String value;
    private int sum;
    private String path;

    /**
     * No argument constructer
     */
    public Card(){this("NO SUIT","NO VALUE",0, "No Path");}

    /**
     *
     * @param suit String type of card
     * @param value String number on card
     * @param sum   int the value of the card in game
     * @param path String a path to the image associated with the card
     */
    public Card(String suit, String value, int sum, String path) {
        setSuit(suit);
        setValue( value);
        setPath(path);

    }
    //Mutator methods

    /**
     *
     * @param value String number on card
     *              method sets the value of a card
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     *
     * @param suit String type of card
     *             Method sets the suit of a card
     */
    public void setSuit(String suit) {
        this.suit = suit;
    }

    /**
     *
     * @param sum Integer value of the card in game
     *            Method sets the value of a card
     */
    public void setSum(int sum) { this.sum = sum; }

    /**
     *
     * @param path String path to image associated with a card
     *             Method sets the path of the card
     */
    public void setPath(String path){this.path = path;}

    //Accessor methods

    /**
     *
     * @return The path of the card
     */
    public String getPath() {return path;}

    /**
     *
     * @return The suit of the card
     */
    public String getSuit() {
        return suit;
    }

    /**
     *
     * @return The value of the card
     */
    public String getValue() {
        return value;
    }

    /**
     *
     * @return The sum of the card
     */
    public int getSum() { return sum; }


    @Override
    /**
     * Method returns the card to String i.e value,suit
     */
    public String toString() {
        return value + " of " +
                suit+"\n";
    }

    /**
     *
     * @param deck is an array of Card objects.
     * @return a JtextArea containing printed deck.
     */
    public static JTextArea deckToString(ArrayList<Card> deck){
        JTextArea window = new JTextArea("Deck\n");
        for(int i=0;i<deck.size();i++){
            window.append(deck.get(i).toString());}
        return window;
    }
    // A sorting algoryithm that uses random number generater

    /**
     *
     * @param deck an ArrayList of Card objects
     *             Method shuffles a deck of cards
     */
    public void shuffle(ArrayList<Card> deck){
        int index;
        Card temperary;
        Random random = new Random();
        for(int i=0;i<deck.size();i++) {
            index = random.nextInt(i + 1);
            //shuffling algorythm
            temperary = deck.get(index);
            deck.set(index,deck.get(i));
            deck.set(i, temperary);
        }
    }

    /**
     *
     * @return a deck of cards. An array of Card objects
     * Method populates an ArrayList with a deck of cards
     */
    public static ArrayList<Card> getDeck() {
        //creating an array of card objects
        ArrayList<Card> deck = new ArrayList<Card>();
        //images from http://acbl.mybigcommerce.com/52-playing-cards/

        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < 13; j++) {
                Card newCard = new Card();
                if (i == 0) {
                    newCard.setSuit("Clubs");
                }
                if (i == 1) {
                    newCard.setSuit("Diamonds");
                }
                if (i == 2) {
                    newCard.setSuit("Hearts");
                }
                if (i == 3) {
                    newCard.setSuit("Spades");
                }
                if (j == 0) {
                    newCard.setValue("Ace");
                    newCard.setSum(11);
                    if(i==0){
                        newCard.setPath("resources\\AC.png");
                    }
                    else if(i==1){
                        newCard.setPath("resources\\AD.png");
                    }
                    else if(i==2){
                        newCard.setPath("resources\\AH.png");
                    }
                    else if(i==3){
                        newCard.setPath("resources\\AS.png");
                    }
                }
                if (j == 1) {
                    newCard.setValue("2");
                    newCard.setSum(2);
                    if(i==0){
                        newCard.setPath("resources\\2C.png");
                    }
                    else if(i==1){
                        newCard.setPath("resources\\2D.png");
                    }
                    else if(i==2){
                        newCard.setPath("resources\\2H.png");
                    }
                    else if(i==3){
                        newCard.setPath("resources\\2S.png");
                    }

                }
                if (j == 2) {
                    newCard.setValue("3");
                    newCard.setSum(3);
                    if(i==0){
                        newCard.setPath("resources\\3C.png");
                    }
                    else if(i==1){
                        newCard.setPath("resources\\3D.png");
                    }
                    else if(i==2){
                        newCard.setPath("resources\\3H.png");
                    }
                    else if(i==3){
                        newCard.setPath("resources\\3S.png");
                    }
                }
                if (j == 3) {
                    newCard.setValue("4");
                    newCard.setSum(4);
                    if(i==0){
                        newCard.setPath("resources\\4C.png");
                    }
                    else if(i==1){
                        newCard.setPath("resources\\4D.png");
                    }
                    else if(i==2){
                        newCard.setPath("resources\\4H.png");
                    }
                    else if(i==3){
                        newCard.setPath("resources\\4S.png");
                    }
                }
                if (j == 4) {
                    newCard.setValue("5");
                    newCard.setSum(5);
                    if(i==0){
                        newCard.setPath("resources\\5C.png");
                    }
                    else if(i==1){
                        newCard.setPath("resources\\5D.png");
                    }
                    else if(i==2){
                        newCard.setPath("resources\\5H.png");
                    }
                    else if(i==3){
                        newCard.setPath("resources\\5S.png");
                    }
                }
                if (j == 5) {
                    newCard.setValue("6");
                    newCard.setSum(6);
                    if(i==0){
                        newCard.setPath("resources\\6C.png");
                    }
                    else if(i==1){
                        newCard.setPath("resources\\6D.png");
                    }
                    else if(i==2){
                        newCard.setPath("resources\\6H.png");
                    }
                    else if(i==3){
                        newCard.setPath("resources\\6S.png");
                    }
                }
                if (j == 6) {
                    newCard.setValue("7");
                    newCard.setSum(7);
                    if(i==0){
                        newCard.setPath("resources\\7C.png");
                    }
                    else if(i==1){
                        newCard.setPath("resources\\7D.png");
                    }
                    else if(i==2){
                        newCard.setPath("resources\\7H.png");
                    }
                    else if(i==3){
                        newCard.setPath("resources\\7S.png");
                    }
                }
                if (j == 7) {
                    newCard.setValue("8");
                    newCard.setSum(8);
                    if(i==0){
                        newCard.setPath("resources\\8C.png");
                    }
                    else if(i==1){
                        newCard.setPath("resources\\8D.png");
                    }
                    else if(i==2){
                        newCard.setPath("resources\\8H.png");
                    }
                    else if(i==3){
                        newCard.setPath("resources\\8S.png");
                    }
                }
                if (j == 8) {
                    newCard.setValue("9");
                    newCard.setSum(9);
                    if(i==0){
                        newCard.setPath("resources\\9C.png");
                    }
                    else if(i==1){
                        newCard.setPath("resources\\9D.png");
                    }
                    else if(i==2){
                        newCard.setPath("resources\\9H.png");
                    }
                    else if(i==3){
                        newCard.setPath("resources\\9S.png");
                    }
                }
                if (j == 9) {
                    newCard.setValue("10");
                    newCard.setSum(10);
                    if(i==0){
                        newCard.setPath("resources\\10C.png");
                    }
                    else if(i==1){
                        newCard.setPath("resources\\10D.png");
                    }
                    else if(i==2){
                        newCard.setPath("resources\\10H.png");
                    }
                    else if(i==3){
                        newCard.setPath("resources\\10S.png");
                    }
                }
                if (j == 10) {
                    newCard.setValue("Jack");
                    newCard.setSum(10);
                    if(i==0){
                        newCard.setPath("resources\\JC.png");
                    }
                    else if(i==1){
                        newCard.setPath("resources\\JD.png");
                    }
                    else if(i==2){
                        newCard.setPath("resources\\JH.png");
                    }
                    else if(i==3){
                        newCard.setPath("resources\\JS.png");
                    }
                }
                if (j == 11) {
                    newCard.setValue("Queen");
                    newCard.setSum(10);
                    if(i==0){
                        newCard.setPath("resources\\QC.png");
                    }
                    else if(i==1){
                        newCard.setPath("resources\\QD.png");
                    }
                    else if(i==2){
                        newCard.setPath("resources\\QH.png");
                    }
                    else if(i==3){
                        newCard.setPath("resources\\QS.png");
                    }

                }
                if (j == 12) {
                    newCard.setValue("King");
                    newCard.setSum(10);
                    if(i==0){
                        newCard.setPath("resources\\KC.png");
                    }
                    else if(i==1){
                        newCard.setPath("resources\\KD.png");
                    }
                    else if(i==2){
                        newCard.setPath("resources\\KH.png");
                    }
                    else if(i==3){
                        newCard.setPath("resources\\KS.png");
                    }
                }

                deck.add(newCard);
                // counter to create each card lineally
                // count++;
            }//end of inner for loop
        }//end of outer for loop
        return deck;
    }

    /**
     *
     * @param deck an ArrayList of Card objects
     * @return A random Card for the deck of cards
     */
    public static Card hitMe(ArrayList<Card> deck){
        Card card;
        Random random = new Random();
        int  hit = random.nextInt(deck.size());
        card=deck.get(hit);
        deck.remove(hit);
//return a random card from deck and skip the card in any future hits
        return card;
    }
}
