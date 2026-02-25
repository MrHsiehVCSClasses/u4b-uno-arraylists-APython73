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
package u6pp;

import java.util.ArrayList;
import java.util.Collections;

public class CardStack {
    
    
    private ArrayList<Card> cards;

//class is full of public helper methods to use foe card logic in other files
//
/////////////////////////////////////////////////////////////
    public CardStack() {
        this.cards = new ArrayList<Card>();
    }
//checks is cards are empty
/////////////////////////////////////////////////////////////
    public boolean isEmpty() {
        return this.cards.isEmpty();
    }
//adds card and pushes rest back
/////////////////////////////////////////////////////////////
    public void push(Card card) {
        this.cards.add(card);
    }

//
/////////////////////////////////////////////////////////////
    public Card pop() {
        if (this.isEmpty()) {
            return null;
        }
        return this.cards.remove(this.cards.size() - 1);
    }
//looks at top card
 /////////////////////////////////////////////////////////////
    public Card peek() {
        if (this.isEmpty()) {
            return null;
        }
        return this.cards.get(this.cards.size() - 1);
    }
//returns size
/////////////////////////////////////////////////////////////
    public int getSize() {
        return this.cards.size();
    }
//empties list
 /////////////////////////////////////////////////////////////
    public void clear() {
        this.cards.clear();
    }
//add all cards into one array
/////////////////////////////////////////////////////////////
    public void addAll(CardStack other) {
       
        if (this == other || other.isEmpty()) {
            return;
        }
        
        this.cards.addAll(other.cards);
        other.clear();
    }
//shuffles
/////////////////////////////////////////////////////////////
    public void shuffle() {
        Collections.shuffle(this.cards);
    }

/////////////////////////////////////////////////////////////
/// 

}









