package u6pp;

import java.util.ArrayList;
//just a class for players with name and hand as attrubuts and getters for both
//
/////////////////////////////////////////////////////////////
public class Player {
    private String name;
    private ArrayList<Card> hand;

 
    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<Card>();
    }

/////////////////////////////////////////////////////////////
    public String getName() {
        return this.name;
    }

/////////////////////////////////////////////////////////////
    public ArrayList<Card> getHand() {
        return this.hand;
    }
}