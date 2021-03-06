public class Card {

    int value;
    Deck.Color color;

    public Card(int value, Deck.Color color) {
        this.value = value;
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public Deck.Color getColor() {
        return color;
    }

    public void setColor(Deck.Color newColor) {
        color = newColor;
    }

    @Override
    public String toString() {
        return "Card{" +
                "value=" + value +
                ", color=" + color +
                '}';
    }
}
