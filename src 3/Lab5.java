/**********************************************************************
 * @file Lab5.java
 * @brief This is a program about linkedlist
 * @author Alantis Green
 * @date: 3/24/2023
 * @acknowledgement:
 */
import java.util.Random;
import java.util.Scanner;

public class Lab5 {

    public static LinkedList initialize_deck() {

        LinkedList deck = new LinkedList();

        // populate linked list with a single deck of cards
        for (Card.suites s : Card.suites.values()) {
            for (Card.ranks r : Card.ranks.values()) {
                if (r != Card.ranks.NULL && s != Card.suites.NULL) {
                    Card newCard = new Card(s, r);
                    deck.add_at_tail(newCard);
                }
            }
        }

        return deck;
    }

    private static void play_blind_mans_bluff(LinkedList player1, LinkedList computer, LinkedList deck) {
        Scanner inputScanner = new Scanner(System.in);

        int player1Wins = 0;
        int computerWins = 0;
        int roundsPlayed = 0;
        boolean playAgain = true;

        while (playAgain) {
            roundsPlayed = 0;
            while (!player1.is_empty() && !computer.is_empty() && roundsPlayed < 5) {
                // Get the next card from player1's hand
                Card player1Card = (Card) player1.remove_from_head();

                // Get the next card from the computer's hand
                Card computerCard = (Card) computer.remove_from_head();

                // Print the cards
                System.out.println("Opponent's card: " + computerCard.getRank() + " of " + computerCard.getSuit());

                // Prompt user for guess
                System.out.println("Is your card higher or lower? Type 'h' for higher or 'l' for lower:");
                String guess = inputScanner.nextLine();

                // Determine winner
                int result = player1Card.compareTo(computerCard);
                if (result == 0) {
                    System.out.println("It's a tie!");
                } else if (result > 0) {
                    if (guess.equals("h")) {
                        System.out.println("You guessed correctly! Your card was " + player1Card.getRank() + " of " + player1Card.getSuit() + ".");
                        player1Wins++;
                    } else {
                        System.out.println("You guessed incorrectly! Your card was " + player1Card.getRank() + " of " + player1Card.getSuit() + ".");
                        computerWins++;
                    }
                } else {
                    if (guess.equals("l")) {
                        System.out.println("You guessed correctly! Your card was " + player1Card.getRank() + " of " + player1Card.getSuit() + ".");
                        player1Wins++;
                    } else {
                        System.out.println("You guessed incorrectly! Your card was " + player1Card.getRank() + " of " + player1Card.getSuit() + ".");
                        computerWins++;
                    }
                }

                // Print current game statistics
                System.out.println("\nPlayer 1 wins: " + player1Wins);
                System.out.println("Computer wins: " + computerWins);
                System.out.println("Cards left in deck: " + deck.size());
                System.out.println("Cards left in player 1's hand: " + player1.size());
                System.out.println("Cards left in computer's hand: " + computer.size());
                System.out.println("-----------------------------");

                roundsPlayed++;

                // If one of the players has no cards left or if we've played 5 rounds, end the game
                if (player1.is_empty() || computer.is_empty() || roundsPlayed == 5) {
                    System.out.println("Game over!");
                    break;
                }

                // If there are less than 3 cards left in the deck, shuffle it again
                if (deck.size() < 3) {
                    System.out.println("Less than 3 cards left in deck. Shuffling again...");
                    deck = initialize_deck();
                }

                // Give each player a new card from the deck
                player1.add_at_tail((Card) deck.remove_from_head());
                computer.add_at_tail((Card) deck.remove_from_head());


                if (roundsPlayed == 3 && computerWins > player1Wins) {
                    if (rage_quit()) {
                        System.out.println("You rage quit! Game over.");
                        playAgain = false;
                        break;
                    }
                }


            }
            // Prompt user to play again
            System.out.println("Would you like to play again? Type 'y' for yes or 'n' for no:");
            String response = inputScanner.nextLine();

            if (response.equals("n")) {
                playAgain = false;
            }
        }

        System.out.println("Thanks for playing!");
        }






    public static boolean rage_quit() {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("You're losing badly. Do you want to rage quit? Type 'y' for yes or 'n' for no:");
        String response = inputScanner.nextLine();
        while (!response.equals("y") && !response.equals("n")) {
            System.out.println("Invalid response. Type 'y' for yes or 'n' for no:");
            response = inputScanner.nextLine();
        }
        if (response.equals("y")) {
            return true;
        } else {
            return false;
        }
    }




                public static void main(String[] args){

                    LinkedList deck = initialize_deck();

                    // Shuffle the deck
                    deck.sanity_check();

                    // Deal the cards
                    LinkedList player1 = new LinkedList();
                    LinkedList computer = new LinkedList();

                    for (int i = 0; i < 5; i++) {
                        player1.add_at_tail((Card) deck.remove_from_head());
                        computer.add_at_tail((Card) deck.remove_from_head());
                    }

                    // Play the game
                    play_blind_mans_bluff(player1, computer, deck);
                }
            }






