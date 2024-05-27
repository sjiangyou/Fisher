public class Strategy {
    public class BlackjackStrategy {
        private static final String[][] hardTotals = {
            // Dealer's upcard:  2      3      4      5      6      7      8      9      10     A
            {"H", "H", "H", "H", "H", "H", "H", "H", "H", "H"}, // 4
            {"H", "H", "H", "H", "H", "H", "H", "H", "H", "H"}, // 5
            {"H", "H", "H", "H", "H", "H", "H", "H", "H", "H"}, // 6
            {"H", "H", "H", "H", "H", "H", "H", "H", "H", "H"}, // 7
            {"H", "H", "H", "H", "H", "H", "H", "H", "H", "H"}, // 8
            {"H", "D", "D", "D", "D", "H", "H", "H", "H", "H"}, // 9
            {"D", "D", "D", "D", "D", "D", "D", "D", "H", "H"}, // 10
            {"D", "D", "D", "D", "D", "D", "D", "D", "D", "H"}, // 11
            {"H", "H", "S", "S", "S", "H", "H", "H", "H", "H"}, // 12
            {"S", "S", "S", "S", "S", "H", "H", "H", "H", "H"}, // 13
            {"S", "S", "S", "S", "S", "H", "H", "H", "H", "H"}, // 14
            {"S", "S", "S", "S", "S", "H", "H", "H", "H", "H"}, // 15
            {"S", "S", "S", "S", "S", "H", "H", "H", "H", "H"}, // 16
            {"S", "S", "S", "S", "S", "S", "S", "S", "S", "S"}  // 17-21
        };
    
        private static final String[][] softTotals = {
            // Dealer's upcard:  2      3      4      5      6      7      8      9      10     A
            {"H", "H", "H", "D", "D", "H", "H", "H", "H", "H"}, // A2
            {"H", "H", "H", "D", "D", "H", "H", "H", "H", "H"}, // A3
            {"H", "H", "D", "D", "D", "H", "H", "H", "H", "H"}, // A4
            {"H", "H", "D", "D", "D", "H", "H", "H", "H", "H"}, // A5
            {"H", "D", "D", "D", "D", "H", "H", "H", "H", "H"}, // A6
            {"S", "D", "D", "D", "D", "S", "S", "H", "H", "H"}, // A7
            {"S", "S", "S", "S", "S", "S", "S", "S", "S", "S"}, // A8
            {"S", "S", "S", "S", "S", "S", "S", "S", "S", "S"}, // A9
            {"S", "S", "S", "S", "S", "S", "S", "S", "S", "S"}, // A10
        };
    
        private static final String[][] pairs = {
            // Dealer's upcard:  2      3      4      5      6      7      8      9      10     A
            {"P", "P", "P", "P", "P", "P", "H", "H", "H", "H"}, // 2-2
            {"P", "P", "P", "P", "P", "P", "H", "H", "H", "H"}, // 3-3
            {"H", "H", "H", "P", "P", "H", "H", "H", "H", "H"}, // 4-4
            {"D", "D", "D", "D", "D", "D", "D", "D", "H", "H"}, // 5-5
            {"P", "P", "P", "P", "P", "P", "H", "H", "H", "H"}, // 6-6
            {"P", "P", "P", "P", "P", "P", "P", "H", "H", "H"}, // 7-7
            {"P", "P", "P", "P", "P", "P", "P", "P", "P", "P"}, // 8-8
            {"P", "P", "P", "P", "P", "S", "P", "P", "S", "S"}, // 9-9
            {"S", "S", "S", "S", "S", "S", "S", "S", "S", "S"}, // 10-10
            {"P", "P", "P", "P", "P", "P", "P", "P", "P", "P"}  // A-A
        };
    
        public static String getAction(int playerTotal, int dealerUpCard, boolean isSoft, boolean canSplit) {
            if (canSplit && playerTotal / 2 - 1 < pairs.length) {
                return pairs[playerTotal / 2 - 1][dealerUpCard - 2];
            }
    
            if (isSoft) {
                if (playerTotal - 13 < softTotals.length) {
                    return softTotals[playerTotal - 13][dealerUpCard - 2];
                }
            } else {
                if (playerTotal - 4 < hardTotals.length) {
                    return hardTotals[playerTotal - 4][dealerUpCard - 2];
                }
            }
    
            return "H"; // Default action if something goes wrong
        }
    
        public static void main(String[] args) {
            // Example usage
            int playerTotal = 16;
            int dealerUpCard = 7;
            boolean isSoft = false;
            boolean canSplit = false;
    
            String action = BlackjackStrategy.getAction(playerTotal, dealerUpCard, isSoft, canSplit);
            System.out.println("Player total: " + playerTotal + ", Dealer up card: " + dealerUpCard);
            System.out.println("Optimal action: " + action);
    
            playerTotal = 13;
            dealerUpCard = 5;
            isSoft = true;
            canSplit = false;
    
            action = BlackjackStrategy.getAction(playerTotal, dealerUpCard, isSoft, canSplit);
            System.out.println("Player total: " + playerTotal + ", Dealer up card: " + dealerUpCard);
            System.out.println("Optimal action: " + action);
    
            playerTotal = 16;
            dealerUpCard = 5;
            isSoft = false;
            canSplit = true;
    
            action = BlackjackStrategy.getAction(playerTotal, dealerUpCard, isSoft, canSplit);
            System.out.println("Player total: " + playerTotal + ", Dealer up card: " + dealerUpCard);
            System.out.println("Optimal action: " + action);
        }
    }
    
}