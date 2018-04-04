// Ray Fung

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

    public boolean playerTurn() {
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
        hit(player);
        hit(comp);
        hit(player);
        hit(comp);

        if (player.blackjack() != comp.blackjack()) {
            return player.blackjack() ? 1 : -1;
        }
        
        if (player.splitable()) {
            System.out.println("You have two " + player.getHand().remove().getName() + "s.");
            System.out.println("Would you like to split your hand? (Y to split)");
            if (input.next().equalsIgnoreCase("Y")) {
                split();
            }
        }

        System.out.println("The dealer's hand is currently " + comp.toString());
        if (playerTurn()) {
            return -1;
        }
        System.out.println("It's the dealer's turn.");
        System.out.println("The dealer's hand is currently " + comp.toString());
        compTurn();

        return player.compareTo(comp);
    }

    public void split() {
        System.out.println("The split system is working properly right now.");
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