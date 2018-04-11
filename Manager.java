// Ray Fung

import java.time.Year;
import java.util.*;

public class Manager {
    private Deck cards;
    private Hand player;
    private CompHand comp;
    private Scanner input;

    public Manager(Scanner input) {
        cards = new Deck();
        player = new Hand();
        comp = new CompHand();
        this.input = input;
    }

    public void hit(Hand player) {
        player.add(cards.draw());
    }

    public boolean playerTurn(Hand player) {
        String choice;
        do {
            System.out.println("Your hand is currently " + player.toString());
            System.out.println("Would you like to hit? (Y / N) ");
            choice = input.next();
            if (choice.equalsIgnoreCase("Y")) {
                hit(player);
            }
            if (player.bust()) {
                return true;
            }
        }
        while (choice.equalsIgnoreCase("Y"));
        return false;
    }

    public void compTurn() {
        boolean turnOver = false;

        while (!turnOver) {
            if (comp.handValue() + cards.peek() <= 21) {
                System.out.println("The dealer decided to hit.");
                hit(comp);
                System.out.println("The dealer's hand is currently " + comp.toString());
            } else {
                turnOver = true;
                System.out.println("The dealer decided to stay.");
            }
        }
    }

    public int newRound() {
        Hand secondHand = new Hand();
        boolean toSplit = false;

        hit(player);
        hit(comp);
        hit(player);
        hit(comp);

        if (player.blackjack() != comp.blackjack()) {
            return player.blackjack() ? 1 : -1;
        }
        
        if (player.splitable()) {
            DeckCard splitted = player.getHand().remove();
            System.out.println("You have two " + splitted.getName() + "s.");
            System.out.println("Would you like to split your hand? (Y to split)");
            if (input.next().equalsIgnoreCase("Y")) {
                toSplit = true;
                secondHand.add(splitted);
            } else {
                player.add(splitted);
            }
        }

        System.out.println("The dealer's hand is currently " + comp.toString());
        if (playerTurn(player) && !toSplit) {
            return -1;
        } 
        if (toSplit) {
            if (playerTurn(secondHand)) {
                System.out.println("Oh no! Your second hand busted.");
            }
        }
        System.out.println("It's the dealer's turn.");
        System.out.println("The dealer's hand is currently " + comp.toString());
        compTurn();

        if (!secondHand.getHand().isEmpty()) {
            win(secondHand.compareTo(comp), secondHand);
        }
        return player.compareTo(comp);
    }

    public void win(int value, Hand player) {
        if (value >= 1) {
            if (player.blackjack()) {
                System.out.println("Congratulations! You got a Blackjack!");
            } else {
                System.out.println("Congratulations! You won!"); 
            }
        } else if (value <= -1) {
            if (player.bust()) {
                System.out.println("Oh no! You busted.");
            } else {
                System.out.println("Whoops! You lost.");
            }
        } else {
            System.out.println("Almost! You tied.");
        }
        System.out.println("Your hand was " + player.toString() + " for " + player.handValue() + ".");
        System.out.println("The dealer's hand was " + comp.revealAll() + " for " + comp.handValue() + ".");
    }

    public void win(int value) {
        if (value >= 1) {
            if (player.blackjack()) {
                System.out.println("Congratulations! You got a Blackjack!");
            } else {
                System.out.println("Congratulations! You won!"); 
            }
        } else if (value <= -1) {
            if (player.bust()) {
                System.out.println("Oh no! You busted.");
            } else {
                System.out.println("Whoops! You lost.");
            }
        } else {
            System.out.println("Almost! You tied.");
        }
        System.out.println("Your hand was " + player.toString() + " for " + player.handValue() + ".");
        System.out.println("The dealer's hand was " + comp.revealAll() + " for " + comp.handValue() + ".");
    }
}