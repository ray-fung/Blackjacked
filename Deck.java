// Ray Fung
// 4/2/2018

// BlackJacked is a version of BlackJack where the AI will always make the most
// advantageous through having knowledge of the cards in the deck.

import java.util.*;

public class Deck {
    
    private Hand player = new Hand();
    private CompHand comp = new CompHand();

    private ArrayList<DeckCard> deck;
    private final int size = 52;
    private final String[] suites = {"Spades", "Clubs", "Hearts", "Diamonds"};
    private final String[] values = {"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};

    public Deck() {
        deck = new ArrayList<DeckCard>();
        for (String suite : suites) {
            for (String value : values) {
                int converted;
                if (!value.equals("Ace") && !value.equals("Jack") && !value.equals("Queen") && !value.equals("King")) {
                    converted = Integer.parseInt(value);
                } else if (value.equals("Ace")) {
                    converted = 1;
                } else {
                    converted = 10;
                }
                deck.add(new DeckCard(value, suite, converted));
            }
        }
        Collections.shuffle(deck);
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public int peek() {
        return deck.get(0).getValue();
    }

    public DeckCard draw() {
        return deck.remove(0);
    }
}