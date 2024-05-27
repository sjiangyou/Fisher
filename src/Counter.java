public class Counter {
    public class Main {
        public static void main(String[] args) {
            int numberOfDecks = 6;
            Deck deck = new Deck();
            deck.shuffle();
            CardCounter cardCounter = new CardCounter(numberOfDecks);
    
            for (int i = 0; i < 52 * numberOfDecks; i++) {
                Card card = deck.dealCard();
                if (card == null) {
                    break;
                }
                cardCounter.updateCount(card);
                System.out.println("Dealt: " + card + ", Running Count: " + cardCounter.getRunningCount());
            }
    
            System.out.println("Final Running Count: " + cardCounter.getRunningCount());
            System.out.println("True Count: " + cardCounter.getTrueCount());
        }
    }
    
}