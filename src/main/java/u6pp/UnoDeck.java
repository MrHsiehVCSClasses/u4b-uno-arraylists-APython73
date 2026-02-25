/** 
 * Uno cards
 *
 * Colors: red, blue, green, yellow
 * Values: 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
 * Special Values: Reverse, Skip, Draw 2
 * Wilds: Wild, Wild Draw 4
 *
 * There are two of each value (and special value), in each color.
 * exception: there is only 1 of each 0.
 * There are 4 of each wild. Wilds do not have colors. 
 *
 */
import java.util.ArrayList;
import java.util.Collections;

public static CardStack createUnoDeck() {
    CardStack stack = new CardStack();
    for (String color : Card.COLORS) {
        if (color.equalsIgnoreCase(Card.WILD)) {
            continue;
        }

        for (String value : Card.VALUES) {
            if (value.equals(Card.WILD) || value.equals(Card.WILD_DRAW_4)) {
                continue;
            }
            stack.push(new Card(color, value));
            if (!value.equalsIgnoreCase(Card.ZERO)) {
                stack.push(new Card(color, value));
            }
        }
    }

    for (int i = 0; i < 4; i++) {
        stack.push(new Card(Card.WILD, Card.WILD));
        stack.push(new Card(Card.WILD, Card.WILD_DRAW_4));
    }

    return stack;
}











//import arraylists and a shuffle command


public class Deck{

  // this is an array list, it basically means that if there is empty data it gets deleted
  // automatically like if i take a card from the middle of the deck there will be 53
  // slots left instead of 54 with 1 empty

  //  < > are needed to signify for object data types like cards
  // also now "rejects" any ther data type (Compiler error)
  private ArrayList <Card> cards;

////////////////////////////////////////////////////////////
///  constructor defaults to a 52 card deck (arraylist), shuffles at the end
  public Deck() {
    //first loop takes care of suit and second loop creates all numbers while having the same suit
    //basically it creates 13 cards of one suit into the array and then moves to next suit
    //after its dione it shuffles deck with shuffle from collections.java
    this.cards = new ArrayList<Card>();
    for(String suit : Card.SUITS){
       for (String value : Card.VALUES){
        this.cards.add(new Card( suit , value ));

       }
    }
    Collections.shuffle(this.cards);
  }
////////////////////////////////////////////////////////////
 public int numLeft() {
    return cards.size(); 
  }
////////////////////////////////////////////////////////////
  public Card deal() {
    //gives cards from top of deck and removes it at the same time
    
    if(cards.size() > 0){
    return cards.remove(0);
    }
    return null;
  }
////////////////////////////////////////////////////////////
  public void shuffle() {
    this.cards.clear();
    for(String suit : Card.SUITS){
       for (String value : Card.VALUES){
        this.cards.add(new Card( suit , value ));

       }
    }
    Collections.shuffle(this.cards);


}
///////////////////////////////////////////////////////////////////////////////
public void setCards(Card[] newCards) {
        this.cards.clear(); 
        for (Card c : newCards) {
            this.cards.add(c);
}

}

}


