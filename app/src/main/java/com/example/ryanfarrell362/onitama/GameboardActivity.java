package com.example.ryanfarrell362.onitama;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class GameboardActivity extends AppCompatActivity {

    static Card cardSelected;
    static int cardSelectedInt;

    static int tileSelectedXOld;
    static int tileSelectedYOld;

    static int tileSelectedXNew;
    static int tileSelectedYNew;

    static boolean isCardSelected = false;
    static boolean isTileSelected = false;

    // Cards
    public static ArrayList<Card> cards = new ArrayList<>(); // For choosing the cards to be in the game
    public static Card[] gameCards = new Card[5]; // For storing the information of those cards
    public static int[] gameCardIDs = new int[5]; // For storing the images of those cards
    public static ImageButton[] gameCardPictures = new ImageButton[5];
    public static ImageButton[][] boardTiles = new ImageButton[5][5]; // Stores tile information

    // Game Pieces
    public static int blueStudentID;
    public static int blueMasterID;

    public static int redStudentID;
    public static int redMasterID;

    // Game Board
    public static boolean turn = false;
    public static int gameOver = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameboard);

        // Game Pieces (Might not be needed)
        blueStudentID = getResources().getIdentifier("bluestudent", "mipmap", getPackageName());
        blueMasterID = getResources().getIdentifier("bluemaster", "mipmap", getPackageName());

        redStudentID = getResources().getIdentifier("redstudent", "mipmap", getPackageName());
        redMasterID = getResources().getIdentifier("redmaster", "mipmap", getPackageName());

        // Game Initialization
        cardInitialization(cards); // Setup cards
        cardDealer(cards, gameCards, gameCardIDs); // Deal out cards
        tileAssigner(boardTiles); // Fills 2D array of board tiles
        turn = firstTurn(gameCards); // Determine who goes first

        gameOver = 0; // Set variable to default state as the game has just started

        assignTurn();
    }

    // Creates the card objects then shuffles them into the deck
    public static void cardInitialization(ArrayList<Card> cards) {
        // Empty the array if a previous game has been played in the same session
        if (cards.size() > 0) {
            cards.subList(0, cards.size()).clear();
        }

        // Creates each card using the Card class then adds it to the deck
        // X and Y movements are hardcoded in order below
        cards.add(new Card("Frog", new int[]{0, -1, 1}, new int[]{-2, -1, 1}, 'r'));
        cards.add(new Card("Goose", new int[]{-1, 0, 0, 1}, new int[]{-1, -1, 1, 1}, 'b'));
        cards.add(new Card("Rabbit", new int[]{1, -1, 0}, new int[]{-1, 1, 2}, 'b'));
        cards.add(new Card("Mantis", new int[]{-1, 1, -1}, new int[]{-1, 0, 1}, 'r'));
        cards.add(new Card("Cobra", new int[]{0, -1, 1}, new int[]{-1, 1, 1}, 'r'));
        cards.add(new Card("Elephant", new int[]{-1, 0, -1, 0}, new int[]{-1, -1, 1, 1}, 'r'));
        cards.add(new Card("Crane", new int[]{1, -1, 1}, new int[]{-1, 0, 1}, 'b'));
        cards.add(new Card("Monkey", new int[]{-1, 1, -1, 1}, new int[]{-1, -1, 1, 1}, 'b'));
        cards.add(new Card("Crab", new int[]{0, -1, 0}, new int[]{-2, 0, 2}, 'b'));
        cards.add(new Card("Rooster", new int[]{0, 1, -1, 0}, new int[]{-1, -1, 1, 1}, 'r'));
        cards.add(new Card("Tiger", new int[]{-2, 1}, new int[]{0, 0}, 'b'));
        cards.add(new Card("Ox", new int[]{-1, 1, 0}, new int[]{0, 0, 1}, 'b'));
        cards.add(new Card("Dragon", new int[]{-1, 1, 1, -1}, new int[]{-2, -1, 1, 2}, 'r'));
        cards.add(new Card("Eel", new int[]{-1, 1, 0}, new int[]{-1, -1, 1}, 'b'));
        cards.add(new Card("Boar", new int[]{0, -1, 0}, new int[]{-1, 0, 1}, 'r'));
        cards.add(new Card("Horse", new int[]{0, -1, 1}, new int[]{-1, 0, 0}, 'r'));
    }

    // Generates the 5 cards which will be used for a match
    public void cardDealer(ArrayList<Card> cards, Card[] gameCards, int[] gameCardIDs) {
        int arrayValue = 0;

        // Generates each card by calling a function to get a card then assigns the card to each slot
        ImageButton player1Card1 = (ImageButton) findViewById(R.id.player1Card1);
        gameCardPictures[0] = player1Card1;
        cardAssigner(cards, player1Card1, gameCards, gameCardIDs, arrayValue);
        arrayValue++;

        ImageButton player1Card2 = (ImageButton) findViewById(R.id.player1Card2);
        gameCardPictures[1] = player1Card2;
        cardAssigner(cards, player1Card2, gameCards, gameCardIDs, arrayValue);
        arrayValue++;

        ImageButton player2Card1 = (ImageButton) findViewById(R.id.player2Card1);
        gameCardPictures[2] = player2Card1;
        cardAssigner(cards, player2Card1, gameCards, gameCardIDs, arrayValue);
        arrayValue++;

        ImageButton player2Card2 = (ImageButton) findViewById(R.id.player2Card2);
        gameCardPictures[3] = player2Card2;
        cardAssigner(cards, player2Card2, gameCards, gameCardIDs, arrayValue);
        arrayValue++;

        ImageButton middleCard = (ImageButton) findViewById(R.id.middleCard);
        gameCardPictures[4] = middleCard;
        cardAssigner(cards, middleCard, gameCards, gameCardIDs, arrayValue);
    }

    public void cardAssigner(ArrayList<Card> cards, ImageButton card, Card[] gameCards, int[] gameCardIDs, int arrayValue) {
        Random numGen = new Random(); // Initializes the random number generator

        int randNum = numGen.nextInt(cards.size()); // Creates a random number between 0 and the number of cards remaining in the deck
        Card chosenCard = cards.get(randNum); // Assigning the random number to a card in the array of card
        gameCards[arrayValue] = chosenCard; // Then adding that card to the array of chosen cards for the game
        String name = chosenCard.getName(); // Getting the name of the chosen card
        int resID = getResources().getIdentifier(name.toLowerCase(), "mipmap", getPackageName()); // So that the id of the card can be obtained
        gameCardIDs[arrayValue] = resID; // Then added to a similar array of chosen cards

        card.setImageResource(resID);
        cards.remove(randNum); // Finally after the data is obtained, the card is removed from the deck
    }

    public void tileAssigner(ImageButton[][] boardTiles) {
        // Creating a 2d array for the game board with their associated onclick listeners
        boardTiles[0][0] = (ImageButton) findViewById(R.id.btn1);
        boardTiles[0][1] = (ImageButton) findViewById(R.id.btn2);
        boardTiles[0][2] = (ImageButton) findViewById(R.id.btn3);
        boardTiles[0][3] = (ImageButton) findViewById(R.id.btn4);
        boardTiles[0][4] = (ImageButton) findViewById(R.id.btn5);

        boardTiles[1][0] = (ImageButton) findViewById(R.id.btn6);
        boardTiles[1][1] = (ImageButton) findViewById(R.id.btn7);
        boardTiles[1][2] = (ImageButton) findViewById(R.id.btn8);
        boardTiles[1][3] = (ImageButton) findViewById(R.id.btn9);
        boardTiles[1][4] = (ImageButton) findViewById(R.id.btn10);

        boardTiles[2][0] = (ImageButton) findViewById(R.id.btn11);
        boardTiles[2][1] = (ImageButton) findViewById(R.id.btn12);
        boardTiles[2][2] = (ImageButton) findViewById(R.id.btn13);
        boardTiles[2][3] = (ImageButton) findViewById(R.id.btn14);
        boardTiles[2][4] = (ImageButton) findViewById(R.id.btn15);

        boardTiles[3][0] = (ImageButton) findViewById(R.id.btn16);
        boardTiles[3][1] = (ImageButton) findViewById(R.id.btn17);
        boardTiles[3][2] = (ImageButton) findViewById(R.id.btn18);
        boardTiles[3][3] = (ImageButton) findViewById(R.id.btn19);
        boardTiles[3][4] = (ImageButton) findViewById(R.id.btn20);

        boardTiles[4][0] = (ImageButton) findViewById(R.id.btn21);
        boardTiles[4][1] = (ImageButton) findViewById(R.id.btn22);
        boardTiles[4][2] = (ImageButton) findViewById(R.id.btn23);
        boardTiles[4][3] = (ImageButton) findViewById(R.id.btn24);
        boardTiles[4][4] = (ImageButton) findViewById(R.id.btn25);

        // Adding the game pieces to the appropriate tiles
        boardTiles[0][0].setTag("redstudent");
        boardTiles[0][1].setTag("redstudent");
        boardTiles[0][2].setTag("redmaster");
        boardTiles[0][3].setTag("redstudent");
        boardTiles[0][4].setTag("redstudent");

        boardTiles[4][0].setTag("bluestudent");
        boardTiles[4][1].setTag("bluestudent");
        boardTiles[4][2].setTag("bluemaster");
        boardTiles[4][3].setTag("bluestudent");
        boardTiles[4][4].setTag("bluestudent");
    }

    // Sets the first turn based on the colour of the middle card
    public boolean firstTurn(Card[] gameCards) {
        // Returns false for blue, and true for red
        return gameCards[4].getColour() == 'r';
    }

    // Indicate who's turn it is by giving the player's profile picture a yellow background
    public void assignTurn() {
        ImageView profilePicture1 = (ImageView) findViewById(R.id.profilePicture1);
        ImageView profilePicture2 = (ImageView) findViewById(R.id.profilePicture2);

        if (!turn) {
            profilePicture2.setBackgroundResource(R.color.highlight);
            profilePicture1.setBackgroundResource(R.color.background_color);
        } else {
            profilePicture1.setBackgroundResource(R.color.highlight);
            profilePicture2.setBackgroundResource(R.color.background_color);
        }
    }

    // Once a card has been selected, the board is scanned for all of a player's pieces which can be moved and highlights them
    public void tileScan() {
        resetBoard ();

        for (ImageButton[] boardTile : boardTiles) {
            for (ImageButton imageButton : boardTile) {
                if ((turn && (imageButton.getTag() == "redstudent" || imageButton.getTag() == "redmaster")) || (!turn && (imageButton.getTag() == "bluestudent" || imageButton.getTag() == "bluemaster"))) {
                    imageButton.setBackgroundResource(R.color.highlight);
                }
            }
        }
    }


    public void moveScan() {
        // After selecting a piece to move, the board is reset to its original colour and only the selected piece is highlighted
        resetBoard();
        boardTiles [tileSelectedXOld][tileSelectedYOld].setBackgroundResource(R.color.highlight);

        // Retrieve the moves from the card selected and put them into separate x and y axis arrays
        int[] xMoves = cardSelected.getXMoves();
        int[] yMoves = cardSelected.getYMoves();

        // Multiply all values by -1 to flip them if it's red's turn
        if (turn) {
            for (int i = 0; i < xMoves.length; i ++) {
                xMoves [i] *= - 1;
                yMoves [i] *= - 1;
            }
        }

        // Then all valid moves are determined and highlighted
        for (int x = 0; x < cardSelected.getXMoves().length; x++) {
            // Determine the x and y coordinate of the move beforehand to de-clutter the if statement
            int newX = tileSelectedXOld + xMoves [x];
            int newY = tileSelectedYOld + yMoves [x];

            // Finally check which squares are valid to move to and highlight them
            if (turn && (newX < 5 && newY < 5 && newX >= 0 && newY >= 0 && boardTiles[newX][newY].getTag() != "redstudent" && boardTiles[newX][newY].getTag() != "redmaster")) {
                boardTiles[newX][newY].setBackgroundResource(R.color.highlight);
                isTileSelected = true;
            }
            else if (!turn && (newX < 5 && newY < 5 && newX >= 0 && newY >= 0 && boardTiles[newX][newY].getTag() != "bluestudent" && boardTiles[newX][newY].getTag() != "bluemaster")) {
                boardTiles[newX][newY].setBackgroundResource(R.color.highlight);
                isTileSelected = true;
            }
        }
    }

    public void movePiece() {
        if (turn) {
            if (boardTiles[tileSelectedXNew][tileSelectedYNew].getBackground().getConstantState() == Objects.requireNonNull(getDrawable(R.color.highlight)).getConstantState() && boardTiles[tileSelectedXOld][tileSelectedYOld] != boardTiles[tileSelectedXNew][tileSelectedYNew]) {
                if (boardTiles[tileSelectedXOld][tileSelectedYOld].getTag() == "redstudent") {
                    boardTiles[tileSelectedXNew][tileSelectedYNew].setImageResource(R.mipmap.redstudent);
                    boardTiles[tileSelectedXNew][tileSelectedYNew].setTag("redstudent");

                    boardTiles[tileSelectedXOld][tileSelectedYOld].setImageResource(android.R.color.transparent);
                    boardTiles[tileSelectedXOld][tileSelectedYOld].setTag("");
                } else if (boardTiles[tileSelectedXOld][tileSelectedYOld].getTag() == "redmaster") {
                    boardTiles[tileSelectedXNew][tileSelectedYNew].setImageResource(R.mipmap.redmaster);
                    boardTiles[tileSelectedXNew][tileSelectedYNew].setTag("redmaster");

                    boardTiles[tileSelectedXOld][tileSelectedYOld].setImageResource(android.R.color.transparent);
                    boardTiles[tileSelectedXOld][tileSelectedYOld].setTag("");
                }

                turn = false;
            } else {
                isTileSelected = false;
                resetBoard();
            }
        } else {
            if (boardTiles[tileSelectedXNew][tileSelectedYNew].getBackground().getConstantState() == Objects.requireNonNull(getDrawable(R.color.highlight)).getConstantState() && boardTiles[tileSelectedXOld][tileSelectedYOld] != boardTiles[tileSelectedXNew][tileSelectedYNew]) {
                if (boardTiles[tileSelectedXOld][tileSelectedYOld].getTag() == "bluestudent") {
                    boardTiles[tileSelectedXNew][tileSelectedYNew].setImageResource(R.mipmap.bluestudent);
                    boardTiles[tileSelectedXNew][tileSelectedYNew].setTag("bluestudent");

                    boardTiles[tileSelectedXOld][tileSelectedYOld].setImageResource(android.R.color.transparent);
                    boardTiles[tileSelectedXOld][tileSelectedYOld].setTag("");
                } else if (boardTiles[tileSelectedXOld][tileSelectedYOld].getTag() == "bluemaster") {
                    boardTiles[tileSelectedXNew][tileSelectedYNew].setImageResource(R.mipmap.bluemaster);
                    boardTiles[tileSelectedXNew][tileSelectedYNew].setTag("bluemaster");

                    boardTiles[tileSelectedXOld][tileSelectedYOld].setImageResource(android.R.color.transparent);
                    boardTiles[tileSelectedXOld][tileSelectedYOld].setTag("");
                }

                turn = true;
            } else {
                isTileSelected = false;
                resetBoard();
            }
        }

        for (ImageButton[] boardTile : boardTiles) {
            for (ImageButton imageButton : boardTile) {
                imageButton.setBackgroundResource(R.color.tile_color);
            }
        }

        if (isTileSelected && isCardSelected) {
            isCardSelected = false;
            isTileSelected = false;
            cardSwap();
        }
    }

    // After a move is made, sweep through the array of gameboard tiles and reset them back to the original board colour
    public void resetBoard() {
        for (ImageButton[] boardTile : boardTiles) {
            for (ImageButton imageButton : boardTile) {
                imageButton.setBackgroundResource(R.color.tile_color);
            }
        }
    }

    // Swaps the card that was used to move a piece with the middle card as per the game rules
    public void cardSwap() {
        Card temp = gameCards[cardSelectedInt];
        gameCards[cardSelectedInt] = gameCards[4];
        gameCards[4] = temp;

        int tempInt = gameCardIDs[cardSelectedInt];
        gameCardIDs[cardSelectedInt] = gameCardIDs[4];
        gameCardIDs[4] = tempInt;

        gameCardPictures[cardSelectedInt].setImageResource(gameCardIDs[cardSelectedInt]);
        gameCardPictures[4].setImageResource(gameCardIDs[4]);

        // As this signals that a turn is over, a check to see if a winner has been determined is performed
        checkForWin();
    }

    // Checking to see if the game has a victor
    public void checkForWin() {
        // Initializing variables to store the status of the two masters
        boolean isRedMaster = false;
        boolean isBlueMaster = false;

        // Checking the 2d board array for the masters
        for (ImageButton[] boardTile : boardTiles) {
            for (ImageButton imageButton : boardTile) {
                if (imageButton.getTag() == "redmaster" && !isRedMaster) {
                    isRedMaster = true; // Setting variable to true if the master is found
                }

                if (imageButton.getTag() == "bluemaster" && !isBlueMaster) {
                    isBlueMaster = true;
                }
            }
        }

        // If a master is missing, or on the opposing master's starting square, the game is over
        if (!isBlueMaster || boardTiles[4][2].getTag() == "redmaster") {
            gameOver = 2;
            victoryScreen();
        } else if (!isRedMaster || boardTiles[0][2].getTag() == "bluemaster") {
            gameOver = 1;
            victoryScreen();
        } else {
            assignTurn(); // If no victor has been found, continue to the next turn
        }
    }

    // If a winner has been determined, this method is ran
    public void victoryScreen() {
        String winner = "";

        if (gameOver == 2) {
            winner = "Red";
        } else if (gameOver == 1) {
            winner = "Blue";
        }

        // This dialog box prints out a message declaring the winner
        AlertDialog playerWin = new AlertDialog.Builder(GameboardActivity.this).create();
        playerWin.setTitle("Game Over");
        playerWin.setMessage(winner + " wins!");
        playerWin.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        playerWin.show(); // The message is then shown

        // Finally the listeners on the player's cards are disabled so that no further moves can be made
        gameCardPictures[0].setOnClickListener(null);
        gameCardPictures[1].setOnClickListener(null);
        gameCardPictures[2].setOnClickListener(null);
        gameCardPictures[3].setOnClickListener(null);
    }

// Button Listeners

    // Card Listeners

    public void player1Card1(View view) {
        if (turn) {
            cardSelected = gameCards[0];
            cardSelectedInt = 0;
            isTileSelected = false;
            isCardSelected = true;
            tileScan();
        }
    }

    public void player1Card2(View view) {
        if (turn) {
            cardSelected = gameCards[1];
            cardSelectedInt = 1;
            isTileSelected = false;
            isCardSelected = true;
            tileScan();
        }
    }

    public void player2Card1(View view) {
        if (!turn) {
            cardSelected = gameCards[2];
            cardSelectedInt = 2;
            isTileSelected = false;
            isCardSelected = true;
            tileScan();
        }
    }

    public void player2Card2(View view) {
        if (!turn) {
            cardSelected = gameCards[3];
            cardSelectedInt = 3;
            isTileSelected = false;
            isCardSelected = true;
            tileScan();
        }
    }

    // Tile Listeners

    public void btn1(View view) { buttonAction (0, 0); }
    public void btn2(View view) { buttonAction (0, 1); }
    public void btn3(View view) { buttonAction (0, 2); }
    public void btn4(View view) { buttonAction (0, 3); }
    public void btn5(View view) { buttonAction (0, 4); }
    public void btn6(View view) { buttonAction (1, 0); }
    public void btn7(View view) { buttonAction (1, 1); }
    public void btn8(View view) { buttonAction (1, 2); }
    public void btn9(View view) { buttonAction (1, 3); }
    public void btn10(View view) { buttonAction (1, 4); }
    public void btn11(View view) { buttonAction (2, 0); }
    public void btn12(View view) { buttonAction (2, 1); }
    public void btn13(View view) { buttonAction (2, 2); }
    public void btn14(View view) { buttonAction (2, 3); }
    public void btn15(View view) { buttonAction (2, 4); }
    public void btn16(View view) { buttonAction (3, 0); }
    public void btn17(View view) { buttonAction (3, 1); }
    public void btn18(View view) { buttonAction (3, 2); }
    public void btn19(View view) { buttonAction (3, 3); }
    public void btn20(View view) { buttonAction (3, 4); }
    public void btn21(View view) { buttonAction (4, 0); }
    public void btn22(View view) { buttonAction (4, 1); }
    public void btn23(View view) { buttonAction (4, 2); }
    public void btn24(View view) { buttonAction (4, 3); }
    public void btn25(View view) { buttonAction (4, 4); }

    // Performed whenever a button on the gameboard is pressed when valid
    public void buttonAction (int x, int y) {
        if (boardTiles[x][y].getBackground().getConstantState() == Objects.requireNonNull(getDrawable(R.color.highlight)).getConstantState()) {
            if (!isTileSelected) {
                tileSelectedXOld = x;
                tileSelectedYOld = y;
                moveScan();
            } else {
                tileSelectedXNew = x;
                tileSelectedYNew = y;
                movePiece();
            }
        }
    }
}
