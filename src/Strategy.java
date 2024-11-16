public class Strategy {
    private static final String[][] hardTotals = {
        // Dealer's upcard: 2 3 4 5 6 7 8 9 10 A
        {"H", "H", "H", "H", "H", "H", "H", "H", "H", "H"}, // <8
        {"H", "DH", "DH", "DH", "DH", "H", "H", "H", "H", "H"}, // 9
        {"DH", "DH", "DH", "DH", "DH", "DH", "DH", "DH", "H", "H"}, // 10
        {"DH", "DH", "DH", "DH", "DH", "DH", "DH", "DH", "DH", "DH"}, // 11
        {"H", "H", "S", "S", "S", "H", "H", "H", "H", "H"}, // 12
        {"S", "S", "S", "S", "S", "H", "H", "H", "H", "H"}, // 13
        {"S", "S", "S", "S", "S", "H", "H", "H", "H", "H"}, // 14
        {"S", "S", "S", "S", "S", "H", "H", "H", "RH", "H"}, // 15
        {"S", "S", "S", "S", "S", "H", "H", "RH", "RH", "RH"}, // 16
        {"S", "S", "S", "S", "S", "S", "S", "S", "S", "S"}  // >17
    };

    private static final String[][] softTotals = {
        // Dealer's upcard: 2 3 4 5 6 7 8 9 10 A
        {"H", "H", "H", "DH", "DH", "H", "H", "H", "H", "H"}, // A2
        {"H", "H", "H", "DH", "DH", "H", "H", "H", "H", "H"}, // A3
        {"H", "H", "DH", "DH", "DH", "H", "H", "H", "H", "H"}, // A4
        {"H", "H", "DH", "DH", "DH", "H", "H", "H", "H", "H"}, // A5
        {"H", "DH", "DH", "DH", "DH", "H", "H", "H", "H", "H"}, // A6
        {"S", "DS", "DS", "DS", "DS", "S", "S", "H", "H", "H"}, // A7
        {"S", "S", "S", "S", "S", "S", "S", "S", "S", "S"}, // >A8
    };

    private static final String[][] pairs = {
        // Dealer's upcard: 2 3 4 5 6 7 8 9 10 A
        {"PH", "PH", "P", "P", "P", "P", "H", "H", "H", "H"}, // 2-2
        {"PH", "PH", "P", "P", "P", "P", "H", "H", "H", "H"}, // 3-3
        {"H", "H", "H", "PH", "PH", "H", "H", "H", "H", "H"}, // 4-4
        {"DH", "DH", "DH", "DH", "DH", "DH", "DH", "DH", "H", "H"}, // 5-5
        {"PH", "P", "P", "P", "P", "H", "H", "H", "H", "H"}, // 6-6
        {"P", "P", "P", "P", "P", "P", "H", "H", "H", "H"}, // 7-7
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
}
