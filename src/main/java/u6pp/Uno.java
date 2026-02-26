package u6pp;

import java.util.ArrayList;



public class Uno {

    private ArrayList<Player> players;
    private CardStack drawPile;
    private CardStack discardPile;
    private int currentPlayerIndex;
    private boolean isReversed;

    /**
     * Main constructor for actual games
     */
    //makes players , input is number of player
/////////////////////////////////////////////////////////////
    public Uno(int numPlayers) {
        this.players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            this.players.add(new Player("Player " + (i + 1)));
        }
        
        this.drawPile = createUnoDeck();
        this.drawPile.shuffle();
        this.discardPile = new CardStack();
        this.currentPlayerIndex = 0;
        this.isReversed = false;

        // Deal 7 cards to each player
        for (Player p : players) {
            for (int i = 0; i < 7; i++) {
                p.getHand().add(drawCard());
            }
        }
        // Start the discard pile with one card from the deck
        this.discardPile.push(drawCard());
    }

   // overloaded constructor to put all neede aspects into a class and make a custom game if needed
/////////////////////////////////////////////////////////////
    public Uno(ArrayList<Player> players, CardStack drawPile, CardStack discardPile, int currentPlayerIndex, boolean isReversed) {
        this.players = players;
        this.drawPile = drawPile;
        this.discardPile = discardPile;
        this.currentPlayerIndex = currentPlayerIndex;
        this.isReversed = isReversed;
    }
  /////////////////////////////////////////////////////////////


//helper methods
    /////////////////////////////////////////////////////////////
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /////////////////////////////////////////////////////////////
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    /////////////////////////////////////////////////////////////
    public Player getNextPlayer() {
        return players.get(getNextIndex(1));
    }

    /////////////////////////////////////////////////////////////
    public Card getTopDiscard() {
        return discardPile.peek();
    }

    // returns player type o who won by checking all players
    /////////////////////////////////////////////////////////////
    public Player getWinner() {
        for (Player p : players) {
            if (p.getHand().isEmpty()) {
                return p;
            }
        }
        return null;
    }

    /**
     * Plays a card from the current player's hand. 
     * If cardToPlay is null, the player draws a card and ends their turn.
     */
    /////////////////////////////////////////////////////////////
    public boolean playCard(Card cardToPlay, String color) {
        Player current = getCurrentPlayer();

        // If player chooses to skip and draw
        if (cardToPlay == null) {
            current.getHand().add(drawCard());
            advanceTurn(1);
            return true;
        }


        
        if (!current.getHand().contains(cardToPlay) || !cardToPlay.canPlayOn(getTopDiscard())) {
            return false;
        }

        current.getHand().remove(cardToPlay);
        
        // Handle Wild logic
        if (cardToPlay.getValue().equals(Card.WILD) || cardToPlay.getValue().equals(Card.WILD_DRAW_4)) {
            cardToPlay.setColor(color);
        }
        
        discardPile.push(cardToPlay);

        // Handle special card effects
        int stepsToAdvance = 1;
        String val = cardToPlay.getValue();

        if (val.equals(Card.REVERSE)) {
            

            isReversed = !isReversed;
        } else if (val.equals(Card.SKIP)) {
            stepsToAdvance = 2;
        } else if (val.equals(Card.DRAW_2)) {
            Player next = getNextPlayer();
            next.getHand().add(drawCard());
            next.getHand().add(drawCard());
            stepsToAdvance = 2; // skip the player who just drew
        } else if (val.equals(Card.WILD_DRAW_4)) {
            Player next = getNextPlayer();
            for (int i = 0; i < 4; i++) {
                next.getHand().add(drawCard());
            }
            stepsToAdvance = 2; // skip the player who just drew
        }

        advanceTurn(stepsToAdvance);
        return true;
    }

   
    //draws card from drawpile
/////////////////////////////////////////////////////////////
    private Card drawCard() {
        if (drawPile.isEmpty()) {
            if (discardPile.isEmpty()) return null;
            
            Card top = discardPile.pop();
            drawPile.addAll(discardPile);
            drawPile.shuffle();
            discardPile.push(top);
        }
        return drawPile.pop();
    }
    
//
/////////////////////////////////////////////////////////////
    private int getNextIndex(int steps) {
        int size = players.size();
        int direction = isReversed ? -1 : 1;
        int nextIndex = (currentPlayerIndex + (steps * direction)) % size;
        
        if (nextIndex < 0) {
            nextIndex += size;
        }
        return nextIndex;
    }

    private void advanceTurn(int steps) {
        currentPlayerIndex = getNextIndex(steps);
    }

// this makes the deck
/////////////////////////////////////////////////////////////
    public static CardStack createUnoDeck() {
        CardStack stack = new CardStack();
        for (String color : Card.COLORS) { // color loop
            // Skip the "Wild" 
            if (color.equalsIgnoreCase(Card.WILD)) continue;

            for (String value : Card.VALUES) {  //value loop
                // Wilds are added at the end
                if (value.equals(Card.WILD) || value.equals(Card.WILD_DRAW_4)) continue;
                
                stack.push(new Card(color, value));
                // There is only one 0 card per color. Everything else has two.
                if (!value.equalsIgnoreCase(Card.ZERO)) {
                    stack.push(new Card(color, value));
                }
            }
        }
        // Add 4 of each Wild type
        for (int i = 0; i < 4; i++) {
            stack.push(new Card(Card.WILD, Card.WILD));
            stack.push(new Card(Card.WILD, Card.WILD_DRAW_4));
        }
        return stack;
    }
/////////////////////////////////////////////////////////////
}