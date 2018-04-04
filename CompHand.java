import java.util.Iterator;
import java.util.Set;

// Ray Fung

public class CompHand extends Hand {

    public CompHand() {
        super();
    }

    public String revealAll() {
        return super.toString();
    }

    public String toString() {
        String res = "";
        Iterator<DeckCard> itr = this.getHand().iterator();
        itr.next();
        while(itr.hasNext()) {
            DeckCard card = itr.next();
            res = res + ", " + card.toString();
        }
        return "[ {?}" + res + "]";
    }
}