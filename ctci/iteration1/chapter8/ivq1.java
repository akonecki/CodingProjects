import java.util.Comparator;
import java.util.List;
import java.util.LinkedList;

public class ivq1 {

    private class Game {
        private Deck mDeck = new Deck();
        private List<Player> mPlayers;
        private int numberOfPlayers;
        private static final int STARTING_HAND_SIZE = 2;

        public void startNewGame(int playerCount) {
            this.numberOfPlayers = playerCount;
            this.shuffle();
            this.dealCards();
        }

        public void shuffle() {
            // perform shuffling of the deck according to the game.
        }

        public void dealCards() {
            // Deal cards to the players
        }

        private class Player {
            private List<Card> mHand = new LinkedList<Card>();
            private int playerID = 0;

            public Player(int id) {
                this.playerID = id;
            }

            public int addCardToHand(Card card) {
                if (card != null) {
                    this.mHand.add(card);
                }

                return this.mHand.size();
            }

            public int getScore() {
                int value = 0;

                for (Card card : this.mHand) {
                    value += card.mValue;
                }

                return value;
            }
        }

        private class Deck {
            public static final int SUITE_COUNT = 4;
            public static final int VALUE_COUNT = 13;
            public static final int SUITE_CLUB = 0;
            public static final int SUITE_SPADE = 1;
            public static final int SUITE_DIAMOND = 2;
            public static final int SUITE_HEART = 3;
            private Card [] mCards = new Card [Deck.SUITE_COUNT * Deck.VALUE_COUNT];
    
            public Deck() {
                for (int suite = 0; suite < Deck.SUITE_COUNT; suite++) {
                    for (int value = 0; value < Deck.VALUE_COUNT; value++) {
                        Card card = new Card(suite, value + 1, (suite * 4) + value + 1);
                        this.mCards[(suite * 4) + value] = card;
                    }
                }
            }
    
            public Card drawCard() {
                // draw from the top of the deck.
                return null;
            }
        }
    
        private class Card {
            private int mSuite;
            private int mValue;
            private int mPosition;
    
            public Card(int suite, int value, int position) {
                this.mSuite = suite;
                this.mValue = value;
                this.mPosition = position;
            }
    
            public int compareTo(Card card) {
                if (card == null) {
                    return 1;
                }
    
                if (this.mValue > card.mValue) {
                    return 1;
                }
                else if (this.mValue < card.mValue) {
                    return -1;
                }
                else if (this.mSuite > card.mSuite) {
                    return 1;
                }
                else {
                    // assuming no duplicates in the cards.
                    return -1;
                }
            }
        }
    }
}