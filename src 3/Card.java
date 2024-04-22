/**********************************************************************
 * @file Card.java
 * @brief This is a program about cards
 * @author Alantis Green
 * @date: 3/24/2023
 * @acknowledgement:
 */
public class Card {

    // Suites
    public enum suites {
        NULL, SPADES, CLUBS, DIAMONDS, HEARTS
    }

    // Ranks
    public enum ranks {
        NULL, two, three, four, five, six, seven, eight, nine, ten, jack, king, queen, ace
    }

    private suites suit;
    private ranks rank;

    Card(){
        suit = suites.NULL;
        rank = ranks.NULL;
    }

    Card(suites s, ranks r){
        suit = s;
        rank = r;
    }

    public void print_card(){
        System.out.print(suit + ": " + rank);
    }
    public suites getSuit() {
        return suit;
    }

    public ranks getRank() {
        return rank;
    }
    public int compareTo(Card other) {
        int rankComparison = rank.compareTo(other.rank);
        if (rankComparison != 0) {
            return rankComparison;
        } else {
            return suit.compareTo(other.suit);
        }
    }
}

