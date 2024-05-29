import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Fisher extends JFrame {
    private Deck deck;
    private CardCounter cardCounter;
    private List<Card> playerHand;
    private List<Card> dealerHand;
    private JTextArea gameTextArea;
    private JButton dealButton;
    private JButton hitButton;
    private JButton standButton;

    public BlackjackGUI() {
        setTitle("Blackjack Game");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        deck = new Deck();
        deck.shuffle();
        cardCounter = new CardCounter(6);
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();

        gameTextArea = new JTextArea();
        gameTextArea.setEditable(false);
        add(new JScrollPane(gameTextArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        dealButton = new JButton("Deal");
        hitButton = new JButton("Hit");
        standButton = new JButton("Stand");

        buttonPanel.add(dealButton);
        buttonPanel.add(hitButton);
        buttonPanel.add(standButton);
        add(buttonPanel, BorderLayout.SOUTH);

        dealButton.addActionListener(new DealButtonListener());
        hitButton.addActionListener(new HitButtonListener());
        standButton.addActionListener(new StandButtonListener());

        hitButton.setEnabled(false);
        standButton.setEnabled(false);
    }

    private class DealButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            playerHand.clear();
            dealerHand.clear();
            deck = new Deck();
            deck.shuffle();
            cardCounter = new CardCounter(6);

            playerHand.add(deck.dealCard());
            playerHand.add(deck.dealCard());
            dealerHand.add(deck.dealCard());
            dealerHand.add(deck.dealCard());

            cardCounter.updateCount(playerHand.get(0));
            cardCounter.updateCount(playerHand.get(1));
            cardCounter.updateCount(dealerHand.get(0));
            cardCounter.updateCount(dealerHand.get(1));

            updateGameTextArea();

            dealButton.setEnabled(false);
            hitButton.setEnabled(true);
            standButton.setEnabled(true);
        }
    }

    private class HitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Card newCard = deck.dealCard();
            playerHand.add(newCard);
            cardCounter.updateCount(newCard);
            updateGameTextArea();

            if (calculateHandValue(playerHand) > 21) {
                gameTextArea.append("\nPlayer busts! Dealer wins.");
                endRound();
            }
        }
    }

    private class StandButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            while (calculateHandValue(dealerHand) < 17) {
                Card newCard = deck.dealCard();
                dealerHand.add(newCard);
                cardCounter.updateCount(newCard);
            }
            updateGameTextArea();
            determineWinner();
            endRound();
        }
    }

    private void updateGameTextArea() {
        gameTextArea.setText("Player's Hand:\n");
        for (Card card : playerHand) {
            gameTextArea.append(card.toString() + "\n");
        }
        gameTextArea.append("Total: " + calculateHandValue(playerHand) + "\n\n");

        gameTextArea.append("Dealer's Hand:\n");
        for (Card card : dealerHand) {
            gameTextArea.append(card.toString() + "\n");
        }
        gameTextArea.append("Total: " + calculateHandValue(dealerHand) + "\n");

        gameTextArea.append("\nRunning Count: " + cardCounter.getRunningCount());
        gameTextArea.append("\nTrue Count: " + cardCounter.getTrueCount());
    }

    private int calculateHandValue(List<Card> hand) {
        int value = 0;
        int numAces = 0;
        for (Card card : hand) {
            String rank = card.getRank();
            if (rank.equals("A")) {
                numAces++;
                value += 11;
            } else if (rank.equals("K") || rank.equals("Q") || rank.equals("J") || rank.equals("10")) {
                value += 10;
            } else {
                value += Integer.parseInt(rank);
            }
        }
        while (value > 21 && numAces > 0) {
            value -= 10;
            numAces--;
        }
        return value;
    }

    private void determineWinner() {
        int playerTotal = calculateHandValue(playerHand);
        int dealerTotal = calculateHandValue(dealerHand);

        if (dealerTotal > 21 || playerTotal > dealerTotal) {
            gameTextArea.append("\nPlayer wins!");
        } else if (playerTotal < dealerTotal) {
            gameTextArea.append("\nDealer wins!");
        } else {
            gameTextArea.append("\nPush!");
        }
    }

    private void endRound() {
        dealButton.setEnabled(true);
        hitButton.setEnabled(false);
        standButton.setEnabled(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BlackjackGUI gui = new BlackjackGUI();
            gui.setVisible(true);
        });
    }
}