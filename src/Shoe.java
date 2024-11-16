import java.util.ArrayList;
import java.util.Collections;

public class Shoe{
    int numDecks;
    ArrayList<Card> shoe = new ArrayList<Card>();
    public Shoe(int numberOfDecks){ 
        this.numDecks = numberOfDecks;
        initializeShoe();
        shuffle();
    }
    private void initializeShoe() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        for(int i = 0; i < this.numDecks; i++){
            for (String suit : suits) {
                for (String value : values) {
                    shoe.add(new Card(value, suit));
                }
            }
        }
    }
    public void shuffle() {
        Collections.shuffle(this.shoe);
    }
    public Card drawCard() {
        if (this.shoe.isEmpty()) {
            return null;
        }
        return this.shoe.remove(0);
    }
}
