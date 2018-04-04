// Ray Fung
// 4/2/2018

// BlackJacked is a version of BlackJack where the AI will always make the most
// advantageous through having knowledge of the cards in the deck.

import java.util.*;

public class BlackjackedMain {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to play a game of Blackjack? (Y to play) ");
        String play = input.next();

        if (play.equalsIgnoreCase("Y")) {
            while (play.equalsIgnoreCase("Y")) {
                Manager playGame = new Manager(input);
                int score = playGame.newRound();
                playGame.win(score);
                System.out.println("Would you like to play another game? (Y/N) ");
                play = input.next();
            }
        }
    }
}