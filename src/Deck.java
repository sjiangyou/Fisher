public class Deck {
    public Deck() {
        card = new ArrayList<>();
        initializeDeck();
    }

    private void initializeDeck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        for (String suit : suits) {
            for (String value : values) {
                cards.add(new Card(value, suit));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            return null; // Return null if the deck is empty
        }
        return cards.remove(0);
    }

    public static void main(String[] args) {
        DeckOfCards deck = new DeckOfCards();
        deck.shuffle();

        // Drawing and printing a few cards from the deck
        for (int i = 0; i < 5; i++) {
            Card drawnCard = deck.drawCard();
            if (drawnCard != null) {
                System.out.println("Drawn Card: " + drawnCard);
            } else {
                System.out.println("The deck is empty!");
                break;
            }
        }
    }
}

}