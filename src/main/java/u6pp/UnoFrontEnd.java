package u6pp;

import java.util.Scanner;



public class UnoFrontEnd {
    private Uno game;
    private Scanner scanner;

    
    public UnoFrontEnd() {
        scanner = new Scanner(System.in);
    }
/////////////////////////////////////////////////////////////
    public void play() {
        System.out.println("How many players?");
        int numPlayers = Integer.parseInt(scanner.nextLine());
        game = new Uno(numPlayers);

        while (game.getWinner() == null) {
            Player current = game.getCurrentPlayer();
            System.out.println("\n------------------------------------");
            System.out.println("Top Card: " + game.getTopDiscard());
            System.out.println(current.getName() + "'s turn. Your hand:");
            
            for (int i = 0; i < current.getHand().size(); i++) {
                System.out.println(i + ": " + current.getHand().get(i));
            }
            
            System.out.println("Enter the number of the card you want to play, or type 'draw' to skip/draw a card:");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("draw")) {
                game.playCard(null, "");
                System.out.println("You drew a card and ended your turn.");
            } else {
                try {
                    int cardIndex = Integer.parseInt(input);
                    Card chosenCard = current.getHand().get(cardIndex);
                    
                    String chosenColor = "";
                    if (chosenCard.getValue().equals(Card.WILD) || chosenCard.getValue().equals(Card.WILD_DRAW_4)) {
                        System.out.println("You played a Wild! Choose a color (Red, Blue, Green, Yellow):");
                        chosenColor = scanner.nextLine().trim();
                    }

                    boolean success = game.playCard(chosenCard, chosenColor);
                    if (!success) {
                        System.out.println("Invalid play. You cannot play that card on " + game.getTopDiscard() + ". Try again.");
                    } else {
                        System.out.println("Played " + chosenCard.getValue() + " successfully.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input. Try again.");
                }
            }
        }

        System.out.println("\n*** Game Over! " + game.getWinner().getName() + " wins! ***");
    }
}
/////////////////////////////////////////////////////////////