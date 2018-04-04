// Ray Fung

import java.util.*;

public class Manager {
    private Deck cards;
    private Hand player;
    private CompHand comp;
    private Scanner input;

    public Manager() {
        cards = new Deck();
        player = new Hand();
        comp = new CompHand();
        input = new Scanner(System.in);
    }

    public void hit(Hand player) {
        player.add(cards.draw());
    }

    public int newRound() {
        String choice = "";
        boolean compTurn = false;
        hit(player);
        hit(comp);
        hit(player);
        hit(comp);

        if (player.blackjack() != comp.blackjack()) {
            return player.blackjack() ? 1 : -1;
        }
        
        System.out.println("The computer's hand is currently " + comp.toString());

        while (!compTurn) {
            if (comp.handValue() + cards.peek() <= 21) {
                System.out.println("The computer decided to hit.");
                hit(comp);
                System.out.println("The computer's hand is currently " + comp.toString());
            } else {
                compTurn = true;
                System.out.println("The computer decided to stay.");
            }
        }

        do {
            System.out.println("Your hand is currently " + player.toString());
            System.out.println("Would you like to hit? (Y / N) ");
            choice = input.next();
            if (choice.equalsIgnoreCase("Y")) {
                hit(player);
            }
            if (player.bust()) {
                return -1;
            }
        }
        while (choice.equalsIgnoreCase("Y"));

        return player.compareTo(comp);
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
        System.out.println("The computer's hand was " + comp.revealAll() + " for " + comp.handValue() + ".");
    }
}