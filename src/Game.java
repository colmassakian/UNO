import java.util.Scanner;

public class Game {
    private static final int FIRST_HAND = 7;
    private static final int SKIP = 10;
    private static final int REVERSE = 11;
    private static final int DRAW_TWO = 12;
    private static final int WILD = 13;
    private static final int WILD_PLUS_FOUR = 14;

    Deck deck;
    Player[] players;
    int numPlayers;
    int currPlayerIndex;
    Card activeCard;
    boolean isReverse;
    boolean alreadyDrew;

    public Game(Deck deck) {
        this.deck = deck;
        currPlayerIndex = 0;
        isReverse = false;

        numPlayers = getNumPlayers();
        players = new Player[numPlayers];

        for(int i = 0; i < numPlayers; i ++)
            players[i] = new Player();
    }

    public void play() {
        // Deal 7 cards
        for(int i = 0; i < numPlayers; i ++)
        {
            for(int j = 0; j < FIRST_HAND; j ++)
            {
                players[i].addCard(deck.deal());
            }
        }

        // Draw for the first card to match
        // TODO: Fix behavior for non-number card dealt first
        activeCard = deck.deal();

        // Start player turns
        int option;
        int currNumCards;
        boolean gameOver = false;
        while(true)
        {
            currNumCards = showCards(currPlayerIndex);
            option = getOption(currNumCards);
            if(option < currNumCards) // Chose card to play
                gameOver = play(option);
            else if(option == currNumCards) // Draw
                draw();
            else // Pass
                pass();

            if(gameOver)
                break;
            //break;
        }

        System.out.println("Player " + currPlayerIndex + " won!");
    }

    private void pass() {
        alreadyDrew = false;
        adjustIndex(1);
    }

    private void draw() {
        players[currPlayerIndex].addCard(deck.deal());
        alreadyDrew = true;
    }

    private boolean play(int option) {
        alreadyDrew = false;

        Card playedCard = players[currPlayerIndex].removeCard(option);
        int val = playedCard.getValue();

        if(players[currPlayerIndex].getNumCards() == 0) // Empty hand, won game
            return true;

        activeCard = playedCard;

        switch (val) {
            case SKIP:
                adjustIndex(2);
                break;
            case REVERSE:
                isReverse = true;
                adjustIndex(1);
                break;
            case DRAW_TWO:
                nextPlayerDraws(2);
                adjustIndex(2);
                break;
            case WILD:
                changeColor();
                adjustIndex(1);
                break;
            case WILD_PLUS_FOUR:
                nextPlayerDraws(4);
                changeColor();
                adjustIndex(2);
                break;
            default: // Plays a card between 0 and 9
                adjustIndex(1);
                break;
        }

        return false;
    }

    private void nextPlayerDraws(int numCards) {
        adjustIndex(1);

        for(int i = 0; i < numCards; i ++)
            players[currPlayerIndex].addCard(deck.deal());

        adjustIndex(-1);
    }

    private void changeColor() {
        int option;
        System.out.println("0: Blue");
        System.out.println("1: Green");
        System.out.println("2: Red");
        System.out.println("3: Yellow");

        do {
            System.out.println("Choose the new color: ");
            Scanner in = new Scanner(System.in);
            option = in.nextInt();
        }
        while (option < 0 || option > 3);

        activeCard.setColor(Deck.Color.values()[option]);
    }

    private void adjustIndex(int offset) {
        if(isReverse)
            currPlayerIndex  = currPlayerIndex - offset;
        else
            currPlayerIndex = currPlayerIndex + offset;

        // Adjust index to make sure it doesn't go out of bounds
        if (currPlayerIndex < 0)
            currPlayerIndex = numPlayers + (currPlayerIndex % numPlayers);
        else
            currPlayerIndex %= numPlayers;
    }

    private boolean checkIfValid(int option, int currNumCards) {
        if(option == currNumCards + 1) // Pass is always a legal move
            return true;

        if(option == currNumCards) // Return true if player has not drawn yet this turn and false if they have
            return !alreadyDrew;

        Card played = players[currPlayerIndex].getCard(option);

        if(activeCard.getValue() == played.getValue()) // Matching number
            return true;

        if(activeCard.getColor() == played.getColor()) // Matching color
            return true;

        if(played.getValue() == WILD || played.getValue() == WILD_PLUS_FOUR) // Wild card
            return true;

        return false;
    }

    private int getOption(int currNumCards) {
        int option;
        do {
            System.out.println("What would you like to do?");
            Scanner in = new Scanner(System.in);
            option = in.nextInt();
        } // Ask again if is option is not one of the cards, draw, or pass
          // or if player has already drawn this turn
          // or if card cannot be placed this turn
        while((option < 0 || option >= currNumCards + 2) ||
                (!checkIfValid(option, currNumCards)));

        return option;
    }

    private int getNumPlayers() {
        int numPlayers = 3;
//        int numPlayers;
//
//        do {
//            System.out.println("Enter the number of players (between 2 and 10)");
//            Scanner in = new Scanner(System.in);
//            numPlayers = in.nextInt();
//        }
//        while(numPlayers < 2 || numPlayers > 10);
        return numPlayers;
    }

    private int showCards(int currPlayerIndex) {
        Player currPlayer = players[currPlayerIndex];
        System.out.println("Player: " + currPlayerIndex);
        System.out.println("Active Card: " + activeCard);
        int i;
        for(i = 0; i < currPlayer.getNumCards(); i ++)
        {
            System.out.println(i + ": " + currPlayer.getCard(i));
        }

        System.out.println(i ++  + ": Draw");
        System.out.println(i + ": Pass");

        return currPlayer.getNumCards();
    }
}
