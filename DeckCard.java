// Ray Fung

// Class creates different cards each with their own suite and value.

public class DeckCard {
    private String name;
    private String suite;
    private int value;

    public DeckCard (String name, String suite, int value) {
        this.name = name;
        this.suite = suite;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getSuite() {
        return suite;
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        return name + " of " + suite;
    }
}