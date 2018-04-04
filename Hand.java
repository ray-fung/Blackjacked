// Ray Fung

// Keeps track of the cards in the users hand at any given time

import java.util.*;

public class Hand implements Comparable<Hand> {

    private Set<DeckCard> hand;
    private boolean blackjack;
    private boolean bust;

    public Hand() {
        this.hand = new HashSet();
        this.blackjack = false;
        this.bust = false;
    }

    public void add(DeckCard card) {
        hand.add(card);
    }

    public void clear() {
        hand.clear();
    }
    
    public Set<DeckCard> getHand() {
        return hand;
    }

    public boolean blackjack() {
        return hand.size() == 2 && handValue() == 21;
    }

    public boolean bust() {
        return handValue() > 21;
    }

    public int handValue() {
        boolean ace = false;
        int sum = 0;
        Iterator<DeckCard> itr = hand.iterator();
        while (itr.hasNext()) {
            DeckCard temp = itr.next();
            if (temp.getName().equals("Ace")) {
                ace = true;
            }
            sum += temp.getValue();
        }
        if (ace && sum <= 11) {
            sum += 10;
        }
        return sum;
    }

    public int compareTo(Hand other) {
        if (blackjack() != other.blackjack()) {
            return blackjack() ? 1 : -1;
        } else if (handValue() != other.handValue()) {
            return handValue() - other.handValue();
        } else {
            return 0;
        }
    }

    public String toString() {
        return hand.toString();
    }
}