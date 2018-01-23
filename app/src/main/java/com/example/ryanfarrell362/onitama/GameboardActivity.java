package com.example.ryanfarrell362.onitama;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

// Bugs
// - There can be duplicate cards in game
// - Can lose turn by clicking on own piece

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
    public static ArrayList<Card> cards = new ArrayList<> (); // For choosing the cards to be in the game
    public static Card [] gameCards = new Card [5]; // For storing the information of those cards
    public static int [] gameCardIDs = new int [5]; // For storing the images of those cards
    public static ImageButton [] gameCardPictures = new ImageButton [5];
    public static ImageButton [][] boardTiles = new ImageButton [5][5]; // Stores tile information

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
        blueStudentID = getResources ().getIdentifier ("bluestudent", "mipmap", getPackageName());
        blueMasterID = getResources ().getIdentifier ("bluemaster", "mipmap", getPackageName());

        redStudentID = getResources ().getIdentifier ("redstudent", "mipmap", getPackageName());
        redMasterID = getResources ().getIdentifier ("redmaster", "mipmap", getPackageName());

        // Game Initialization
        cardInitialization (cards); // Setup cards
        cardDealer (cards, gameCards, gameCardIDs); // Deal out cards
        tileAssigner (boardTiles); // Fills 2D array of board tiles
        turn = firstTurn (gameCards, turn); // Determine who goes first

        // Game Start
        assignTurn (); // Assign who goes
        // Choose card (DONE)
        // Choose piece
        // Move piece
        // Swap cards
        // Check for win

        // Necessary things
        // ArrayLists for blue and red pieces (ResID's)
    }

    //Card Listeners

    public void player1Card1 (View view)
    {
        if (turn)
        {
            cardSelected = gameCards [0];
            cardSelectedInt = 0;
            isTileSelected = false;
            isCardSelected = true;
            tileScan ();
        }
    }

    public void player1Card2 (View view)
    {
        if (turn)
        {
            cardSelected = gameCards [1];
            cardSelectedInt = 1;
            isTileSelected = false;
            isCardSelected = true;
            tileScan ();
        }
    }

    public void player2Card1 (View view)
    {
        if (!turn)
        {
            cardSelected = gameCards [2];
            cardSelectedInt = 2;
            isTileSelected = false;
            isCardSelected = true;
            tileScan ();
        }
    }

    public void player2Card2 (View view)
    {
        if (!turn)
        {
            cardSelected = gameCards [3];
            cardSelectedInt = 3;
            isTileSelected = false;
            isCardSelected = true;
            tileScan ();
        }
    }

    // Tile Listeners

    public void btn1 (View view)
    {
        if (boardTiles [0][0].getBackground().getConstantState() == getResources().getDrawable(R.color.highlight).getConstantState())
        {
            if (isTileSelected == false)
            {
                tileSelectedXOld = 0;
                tileSelectedYOld = 0;
                moveScan ();
            }
            else if (isTileSelected == true)
            {
                tileSelectedXNew = 0;
                tileSelectedYNew = 0;
                movePiece ();
            }
        }
    }

    public void btn2 (View view)
    {
        if (boardTiles [0][1].getBackground().getConstantState() == getResources().getDrawable(R.color.highlight).getConstantState())
        {
            if (isTileSelected == false)
            {
                tileSelectedXOld = 0;
                tileSelectedYOld = 1;
                moveScan ();
            }
            else if (isTileSelected == true)
            {
                tileSelectedXNew = 0;
                tileSelectedYNew = 1;
                movePiece ();
            }
        }
    }

    public void btn3 (View view)
    {
        if (boardTiles [0][2].getBackground().getConstantState() == getResources().getDrawable(R.color.highlight).getConstantState())
        {
            if (isTileSelected == false)
            {
                tileSelectedXOld = 0;
                tileSelectedYOld = 2;
                moveScan ();
            }
            else if (isTileSelected == true)
            {
                tileSelectedXNew = 0;
                tileSelectedYNew = 2;
                movePiece ();
            }
        }
    }

    public void btn4 (View view)
    {
        if (boardTiles [0][3].getBackground().getConstantState() == getResources().getDrawable(R.color.highlight).getConstantState())
        {
            if (isTileSelected == false)
            {
                tileSelectedXOld = 0;
                tileSelectedYOld = 3;
                moveScan ();
            }
            else if (isTileSelected == true)
            {
                tileSelectedXNew = 0;
                tileSelectedYNew = 3;
                movePiece ();
            }
        }
    }

    public void btn5 (View view)
    {
        if (boardTiles [0][4].getBackground().getConstantState() == getResources().getDrawable(R.color.highlight).getConstantState())
        {
            if (isTileSelected == false)
            {
                tileSelectedXOld = 0;
                tileSelectedYOld = 4;
                moveScan ();
            }
            else if (isTileSelected == true)
            {
                tileSelectedXNew = 0;
                tileSelectedYNew = 4;
                movePiece ();
            }
        }
    }

    public void btn6 (View view)
    {
        if (boardTiles [1][0].getBackground().getConstantState() == getResources().getDrawable(R.color.highlight).getConstantState())
        {
            if (isTileSelected == false)
            {
                tileSelectedXOld = 1;
                tileSelectedYOld = 0;
                moveScan ();
            }
            else if (isTileSelected == true)
            {
                tileSelectedXNew = 1;
                tileSelectedYNew = 0;
                movePiece ();
            }
        }
    }

    public void btn7 (View view)
    {
        if (boardTiles [1][1].getBackground().getConstantState() == getResources().getDrawable(R.color.highlight).getConstantState())
        {
            if (isTileSelected == false)
            {
                tileSelectedXOld = 1;
                tileSelectedYOld = 1;
                moveScan ();
            }
            else if (isTileSelected == true)
            {
                tileSelectedXNew = 1;
                tileSelectedYNew = 1;
                movePiece ();
            }
        }
    }

    public void btn8 (View view)
    {
        if (boardTiles [1][2].getBackground().getConstantState() == getResources().getDrawable(R.color.highlight).getConstantState())
        {
            if (isTileSelected == false)
            {
                tileSelectedXOld = 1;
                tileSelectedYOld = 2;
                moveScan ();
            }
            else if (isTileSelected == true)
            {
                tileSelectedXNew = 1;
                tileSelectedYNew = 2;
                movePiece ();
            }
        }
    }

    public void btn9 (View view)
    {
        if (boardTiles [1][3].getBackground().getConstantState() == getResources().getDrawable(R.color.highlight).getConstantState())
        {
            if (isTileSelected == false)
            {
                tileSelectedXOld = 1;
                tileSelectedYOld = 3;
                moveScan ();
            }
            else if (isTileSelected == true)
            {
                tileSelectedXNew = 1;
                tileSelectedYNew = 3;
                movePiece ();
            }
        }
    }

    public void btn10 (View view)
    {
        if (boardTiles [1][4].getBackground().getConstantState() == getResources().getDrawable(R.color.highlight).getConstantState())
        {
            if (isTileSelected == false)
            {
                tileSelectedXOld = 1;
                tileSelectedYOld = 4;
                moveScan ();
            }
            else if (isTileSelected == true)
            {
                tileSelectedXNew = 1;
                tileSelectedYNew = 4;
                movePiece ();
            }
        }
    }

    public void btn11 (View view)
    {
        if (boardTiles [2][0].getBackground().getConstantState() == getResources().getDrawable(R.color.highlight).getConstantState())
        {
            if (isTileSelected == false)
            {
                tileSelectedXOld = 2;
                tileSelectedYOld = 0;
                moveScan ();
            }
            else if (isTileSelected == true)
            {
                tileSelectedXNew = 2;
                tileSelectedYNew = 0;
                movePiece ();
            }
        }
    }

    public void btn12 (View view)
    {
        if (boardTiles [2][1].getBackground().getConstantState() == getResources().getDrawable(R.color.highlight).getConstantState())
        {
            if (isTileSelected == false)
            {
                tileSelectedXOld = 2;
                tileSelectedYOld = 1;
                moveScan ();
            }
            else if (isTileSelected == true)
            {
                tileSelectedXNew = 2;
                tileSelectedYNew = 1;
                movePiece ();
            }
        }
    }

    public void btn13 (View view)
    {
        if (boardTiles [2][2].getBackground().getConstantState() == getResources().getDrawable(R.color.highlight).getConstantState())
        {
            if (isTileSelected == false)
            {
                tileSelectedXOld = 2;
                tileSelectedYOld = 2;
                moveScan ();
            }
            else if (isTileSelected == true)
            {
                tileSelectedXNew = 2;
                tileSelectedYNew = 2;
                movePiece ();
            }
        }
    }

    public void btn14 (View view)
    {
        if (boardTiles [2][3].getBackground().getConstantState() == getResources().getDrawable(R.color.highlight).getConstantState())
        {
            if (isTileSelected == false)
            {
                tileSelectedXOld = 2;
                tileSelectedYOld = 3;
                moveScan ();
            }
            else if (isTileSelected == true)
            {
                tileSelectedXNew = 2;
                tileSelectedYNew = 3;
                movePiece ();
            }
        }
    }

    public void btn15 (View view)
    {
        if (boardTiles [2][4].getBackground().getConstantState() == getResources().getDrawable(R.color.highlight).getConstantState())
        {
            if (isTileSelected == false)
            {
                tileSelectedXOld = 2;
                tileSelectedYOld = 4;
                moveScan ();
            }
            else if (isTileSelected == true)
            {
                tileSelectedXNew = 2;
                tileSelectedYNew = 4;
                movePiece ();
            }
        }
    }

    public void btn16 (View view)
    {
        if (boardTiles [3][0].getBackground().getConstantState() == getResources().getDrawable(R.color.highlight).getConstantState())
        {
            if (isTileSelected == false)
            {
                tileSelectedXOld = 3;
                tileSelectedYOld = 0;
                moveScan ();
            }
            else if (isTileSelected == true)
            {
                tileSelectedXNew = 3;
                tileSelectedYNew = 0;
                movePiece ();
            }
        }
    }

    public void btn17 (View view)
    {
        if (boardTiles [3][1].getBackground().getConstantState() == getResources().getDrawable(R.color.highlight).getConstantState())
        {
            if (isTileSelected == false)
            {
                tileSelectedXOld = 3;
                tileSelectedYOld = 1;
                moveScan ();
            }
            else if (isTileSelected == true)
            {
                tileSelectedXNew = 3;
                tileSelectedYNew = 1;
                movePiece ();
            }
        }
    }

    public void btn18 (View view)
    {
        if (boardTiles [3][2].getBackground().getConstantState() == getResources().getDrawable(R.color.highlight).getConstantState())
        {
            if (isTileSelected == false)
            {
                tileSelectedXOld = 3;
                tileSelectedYOld = 2;
                moveScan ();
            }
            else if (isTileSelected == true)
            {
                tileSelectedXNew = 3;
                tileSelectedYNew = 2;
                movePiece ();
            }
        }
    }

    public void btn19 (View view)
    {
        if (boardTiles [3][3].getBackground().getConstantState() == getResources().getDrawable(R.color.highlight).getConstantState())
        {
            if (isTileSelected == false)
            {
                tileSelectedXOld = 3;
                tileSelectedYOld = 3;
                moveScan ();
            }
            else if (isTileSelected == true)
            {
                tileSelectedXNew = 3;
                tileSelectedYNew = 3;
                movePiece ();
            }
        }
    }

    public void btn20 (View view)
    {
        if (boardTiles [3][4].getBackground().getConstantState() == getResources().getDrawable(R.color.highlight).getConstantState())
        {
            if (isTileSelected == false)
            {
                tileSelectedXOld = 3;
                tileSelectedYOld = 4;
                moveScan ();
            }
            else if (isTileSelected == true)
            {
                tileSelectedXNew = 3;
                tileSelectedYNew = 4;
                movePiece ();
            }
        }
    }

    public void btn21 (View view)
    {
        if (boardTiles [4][0].getBackground().getConstantState() == getResources().getDrawable(R.color.highlight).getConstantState())
        {
            if (isTileSelected == false)
            {
                tileSelectedXOld = 4;
                tileSelectedYOld = 0;
                moveScan ();
            }
            else if (isTileSelected == true)
            {
                tileSelectedXNew = 4;
                tileSelectedYNew = 0;
                movePiece ();
            }
        }
    }

    public void btn22 (View view)
    {
        if (boardTiles [4][1].getBackground().getConstantState() == getResources().getDrawable(R.color.highlight).getConstantState())
        {
            if (isTileSelected == false)
            {
                tileSelectedXOld = 4;
                tileSelectedYOld = 1;
                moveScan ();
            }
            else if (isTileSelected == true)
            {
                tileSelectedXNew = 4;
                tileSelectedYNew = 1;
                movePiece ();
            }
        }
    }

    public void btn23 (View view)
    {
        if (boardTiles [4][2].getBackground().getConstantState() == getResources().getDrawable(R.color.highlight).getConstantState())
        {
            if (isTileSelected == false)
            {
                tileSelectedXOld = 4;
                tileSelectedYOld = 2;
                moveScan ();
            }
            else if (isTileSelected == true)
            {
                tileSelectedXNew = 4;
                tileSelectedYNew = 2;
                movePiece ();
            }
        }
    }

    public void btn24 (View view)
    {
        if (boardTiles [4][3].getBackground().getConstantState() == getResources().getDrawable(R.color.highlight).getConstantState())
        {
            if (isTileSelected == false)
            {
                tileSelectedXOld = 4;
                tileSelectedYOld = 3;
                moveScan ();
            }
            else if (isTileSelected == true)
            {
                tileSelectedXNew = 4;
                tileSelectedYNew = 3;
                movePiece ();
            }
        }
    }

    public void btn25 (View view)
    {
        if (boardTiles [4][4].getBackground().getConstantState() == getResources().getDrawable(R.color.highlight).getConstantState())
        {
            if (isTileSelected == false)
            {
                tileSelectedXOld = 4;
                tileSelectedYOld = 4;
                moveScan ();
            }
            else if (isTileSelected == true)
            {
                tileSelectedXNew = 4;
                tileSelectedYNew = 4;
                movePiece ();
            }
        }
    }

    // Other Methods

    public void assignTurn ()
    {
        ImageView profilePicture1 = (ImageView) findViewById (R.id.profilePicture1);
        ImageView profilePicture2 = (ImageView) findViewById (R.id.profilePicture2);

        if (!turn)
        {
            profilePicture2.setBackgroundResource(R.color.highlight);
            profilePicture1.setBackgroundResource(R.color.background_color);
        }
        else if (turn)
        {
            profilePicture1.setBackgroundResource(R.color.highlight);
            profilePicture2.setBackgroundResource(R.color.background_color);
        }
    }

    public void cardDealer(ArrayList cards, Card [] gameCards, int [] gameCardIDs)
    {
        int arrayValue = 0;

        // Need an array of image buttons

        ImageButton player1Card1 = (ImageButton) findViewById (R.id.player1Card1);
        gameCardPictures [0] = player1Card1;
        cardAssigner (cards, player1Card1, gameCards, gameCardIDs, arrayValue);
        arrayValue ++;

        ImageButton player1Card2 = (ImageButton) findViewById (R.id.player1Card2);
        gameCardPictures [1] = player1Card2;
        cardAssigner (cards, player1Card2, gameCards, gameCardIDs, arrayValue);
        arrayValue ++;

        ImageButton player2Card1 = (ImageButton) findViewById (R.id.player2Card1);
        gameCardPictures [2] = player2Card1;
        cardAssigner (cards, player2Card1, gameCards, gameCardIDs, arrayValue);
        arrayValue ++;

        ImageButton player2Card2 = (ImageButton) findViewById (R.id.player2Card2);
        gameCardPictures [3] = player2Card2;
        cardAssigner (cards, player2Card2, gameCards, gameCardIDs, arrayValue);
        arrayValue ++;

        ImageButton middleCard = (ImageButton) findViewById (R.id.middleCard);
        gameCardPictures [4] = middleCard;
        cardAssigner (cards, middleCard, gameCards, gameCardIDs, arrayValue);
    }

    public ArrayList cardAssigner (ArrayList cards, ImageButton card, Card [] gameCards, int [] gameCardIDs, int arrayValue)
    {
        Random numGen = new Random ();

        int randNum = numGen.nextInt (cards.size ());
        Card chosenCard = (Card) cards.get (randNum);
        gameCards [arrayValue] = chosenCard;
        String name = chosenCard.getName ();
        int resID = getResources ().getIdentifier (name.toLowerCase(), "mipmap", getPackageName());
        gameCardIDs [arrayValue] = resID;

        card.setImageResource(resID);
        cards.remove (randNum);

        return cards;
    }

    public static ArrayList cardInitialization (ArrayList cards)
    {
        int [] tempArray1X = {0, -1, 1};
        int [] tempArray1Y = {-2, -1, 1};
        Card frog = new Card ("Frog", tempArray1X, tempArray1Y, 'r');

        int [] tempArray2X = {-1, 0, 0, 1};
        int [] tempArray2Y = {-1, -1, 1, 1};
        Card goose = new Card ("Goose", tempArray2X, tempArray2Y, 'b');

        int [] tempArray3X = {1, -1, 0};
        int [] tempArray3Y = {-1, 1, 2};
        Card rabbit = new Card ("Rabbit", tempArray3X, tempArray3Y, 'b');

        int [] tempArray4X = {-1, 1, -1};
        int [] tempArray4Y = {-1, 0, 1};
        Card mantis = new Card ("Mantis", tempArray4X, tempArray4Y, 'r');

        int [] tempArray5X = {0, -1, 1};
        int [] tempArray5Y = {-1, 1, 1};
        Card cobra = new Card ("Cobra", tempArray5X, tempArray5Y, 'r');

        int [] tempArray6X = {-1, 0, -1, 0};
        int [] tempArray6Y = {-1, -1, 1, 1};
        Card elephant = new Card ("Elephant", tempArray6X, tempArray6Y, 'r');

        int [] tempArray7X = {1, -1, 1};
        int [] tempArray7Y = {-1, 0, 1};
        Card crane = new Card ("Crane", tempArray7X, tempArray7Y, 'b');

        int [] tempArray8X = {-1, 1, -1, 1};
        int [] tempArray8Y = {-1, -1, 1, 1};
        Card monkey = new Card ("Monkey", tempArray8X, tempArray8Y, 'b');

        int [] tempArray9X = {0, -1, 0};
        int [] tempArray9Y = {-2, 0, 2};
        Card crab = new Card ("Crab", tempArray9X, tempArray9Y, 'b');

        int [] tempArray10X = {0, 1, -1, 0};
        int [] tempArray10Y = {-1, -1, 1, 1};
        Card rooster = new Card ("Rooster", tempArray10X, tempArray10Y, 'r');

        int [] tempArray11X = {-2, 1};
        int [] tempArray11Y = {0, 0};
        Card tiger = new Card ("Tiger", tempArray11X, tempArray11Y, 'b');

        int [] tempArray12X = {-1, 1, 0};
        int [] tempArray12Y = {0, 0, 1};
        Card ox = new Card ("Ox", tempArray12X, tempArray12Y, 'b');

        int [] tempArray13X = {-1, 1, 1, -1};
        int [] tempArray13Y = {-2, -1, 1, 2};
        Card dragon = new Card ("Dragon", tempArray13X, tempArray13Y, 'r');

        int [] tempArray14X = {-1, 1, 0};
        int [] tempArray14Y = {-1, -1, 1};
        Card eel = new Card ("Eel", tempArray14X, tempArray14Y, 'b');

        int [] tempArray15X = {0, -1, 0};
        int [] tempArray15Y = {-1, 0, 1};
        Card boar = new Card ("Boar", tempArray15X, tempArray15Y, 'r');

        int [] tempArray16X = {0, -1, 1};
        int [] tempArray16Y = {-1, 0, 0};
        Card horse = new Card ("Horse", tempArray16X, tempArray16Y, 'r');

        cards.add (frog);
        cards.add (goose);
        cards.add (rabbit);
        cards.add (mantis);
        cards.add (cobra);
        cards.add (elephant);
        cards.add (crane);
        cards.add (monkey);
        cards.add (crab);
        cards.add (rooster);
        cards.add (tiger);
        cards.add (ox);
        cards.add (dragon);
        cards.add (eel);
        cards.add (boar);
        cards.add (horse);

        return cards;
    }

    public boolean firstTurn (Card [] gameCards, boolean turn)
    {
        if (gameCards [4].getColour() == 'b')
        {
            turn = false; // Blue
        }
        else if (gameCards [4].getColour() == 'r')
        {
            turn = true; // Red
        }

        return turn;
    }

    public void tileAssigner (ImageButton [][] boardTiles)
    {
        boardTiles [0][0] = (ImageButton) findViewById (R.id.btn1);
        boardTiles [0][1] = (ImageButton) findViewById (R.id.btn2);
        boardTiles [0][2] = (ImageButton) findViewById (R.id.btn3);
        boardTiles [0][3] = (ImageButton) findViewById (R.id.btn4);
        boardTiles [0][4] = (ImageButton) findViewById (R.id.btn5);

        boardTiles [1][0] = (ImageButton) findViewById (R.id.btn6);
        boardTiles [1][1] = (ImageButton) findViewById (R.id.btn7);
        boardTiles [1][2] = (ImageButton) findViewById (R.id.btn8);
        boardTiles [1][3] = (ImageButton) findViewById (R.id.btn9);
        boardTiles [1][4] = (ImageButton) findViewById (R.id.btn10);

        boardTiles [2][0] = (ImageButton) findViewById (R.id.btn11);
        boardTiles [2][1] = (ImageButton) findViewById (R.id.btn12);
        boardTiles [2][2] = (ImageButton) findViewById (R.id.btn13);
        boardTiles [2][3] = (ImageButton) findViewById (R.id.btn14);
        boardTiles [2][4] = (ImageButton) findViewById (R.id.btn15);

        boardTiles [3][0] = (ImageButton) findViewById (R.id.btn16);
        boardTiles [3][1] = (ImageButton) findViewById (R.id.btn17);
        boardTiles [3][2] = (ImageButton) findViewById (R.id.btn18);
        boardTiles [3][3] = (ImageButton) findViewById (R.id.btn19);
        boardTiles [3][4] = (ImageButton) findViewById (R.id.btn20);

        boardTiles [4][0] = (ImageButton) findViewById (R.id.btn21);
        boardTiles [4][1] = (ImageButton) findViewById (R.id.btn22);
        boardTiles [4][2] = (ImageButton) findViewById (R.id.btn23);
        boardTiles [4][3] = (ImageButton) findViewById (R.id.btn24);
        boardTiles [4][4] = (ImageButton) findViewById (R.id.btn25);

        boardTiles [0][0].setTag ("redstudent");
        boardTiles [0][1].setTag ("redstudent");
        boardTiles [0][2].setTag ("redmaster");
        boardTiles [0][3].setTag ("redstudent");
        boardTiles [0][4].setTag ("redstudent");

        boardTiles [4][0].setTag ("bluestudent");
        boardTiles [4][1].setTag ("bluestudent");
        boardTiles [4][2].setTag ("bluemaster");
        boardTiles [4][3].setTag ("bluestudent");
        boardTiles [4][4].setTag ("bluestudent");
    }

    public void movePiece ()
    {
        if (turn)
        {
            if (boardTiles [tileSelectedXNew][tileSelectedYNew].getBackground().getConstantState() == getResources().getDrawable(R.color.highlight).getConstantState() && boardTiles [tileSelectedXOld][tileSelectedYOld] != boardTiles [tileSelectedXNew][tileSelectedYNew])
            {
                if (boardTiles [tileSelectedXOld][tileSelectedYOld].getTag () == "redstudent")
                {
                    boardTiles [tileSelectedXNew][tileSelectedYNew].setImageResource(R.mipmap.redstudent);
                    boardTiles [tileSelectedXNew][tileSelectedYNew].setTag("redstudent");

                    boardTiles [tileSelectedXOld][tileSelectedYOld].setImageResource(android.R.color.transparent);
                    boardTiles [tileSelectedXOld][tileSelectedYOld].setTag("");
                }
                else if (boardTiles [tileSelectedXOld][tileSelectedYOld].getTag () == "redmaster")
                {
                    boardTiles [tileSelectedXNew][tileSelectedYNew].setImageResource(R.mipmap.redmaster);
                    boardTiles [tileSelectedXNew][tileSelectedYNew].setTag("redmaster");

                    boardTiles [tileSelectedXOld][tileSelectedYOld].setImageResource(android.R.color.transparent);
                    boardTiles [tileSelectedXOld][tileSelectedYOld].setTag("");
                }
            }

            turn = false;
        }
        else if (!turn)
        {
            if (boardTiles [tileSelectedXNew][tileSelectedYNew].getBackground().getConstantState() == getResources().getDrawable(R.color.highlight).getConstantState() && boardTiles [tileSelectedXOld][tileSelectedYOld] != boardTiles [tileSelectedXNew][tileSelectedYNew])
            {
                if (boardTiles [tileSelectedXOld][tileSelectedYOld].getTag () == "bluestudent")
                {
                    boardTiles [tileSelectedXNew][tileSelectedYNew].setImageResource(R.mipmap.bluestudent);
                    boardTiles [tileSelectedXNew][tileSelectedYNew].setTag("bluestudent");

                    boardTiles [tileSelectedXOld][tileSelectedYOld].setImageResource(android.R.color.transparent);
                    boardTiles [tileSelectedXOld][tileSelectedYOld].setTag("");
                }
                else if (boardTiles [tileSelectedXOld][tileSelectedYOld].getTag () == "bluemaster")
                {
                    boardTiles [tileSelectedXNew][tileSelectedYNew].setImageResource(R.mipmap.bluemaster);
                    boardTiles [tileSelectedXNew][tileSelectedYNew].setTag("bluemaster");

                    boardTiles [tileSelectedXOld][tileSelectedYOld].setImageResource(android.R.color.transparent);
                    boardTiles [tileSelectedXOld][tileSelectedYOld].setTag("");
                }
            }

            turn = true;
        }

        for (int x = 0; x < boardTiles.length; x++)
        {
            for (int y = 0; y < boardTiles[x].length; y++)
            {
                boardTiles[x][y].setBackgroundResource(R.color.tile_color);
            }
        }

        isCardSelected = false;
        isTileSelected = false;
        cardSwap();
    }

    public void moveScan ()
    {
        for (int x = 0; x < boardTiles.length; x++)
        {
            for (int y = 0; y < boardTiles[x].length; y++)
            {
                if (boardTiles [x][y] != boardTiles [tileSelectedXOld][tileSelectedYOld])
                {
                    boardTiles[x][y].setBackgroundResource(R.color.tile_color);
                }
            }
        }

        int [] xMoves = cardSelected.getXMoves();
        int [] yMoves = cardSelected.getYMoves();

        for (int x = 0; x < cardSelected.getXMoves ().length; x ++)
        {
            if (turn)
            {
                if (tileSelectedXOld + (xMoves [x] * -1) < 5 && tileSelectedYOld + (yMoves [x] * -1) < 5 && tileSelectedXOld + (xMoves [x] * -1) >= 0 && tileSelectedYOld + (yMoves [x] * -1) >= 0 && boardTiles [tileSelectedXOld + (xMoves [x] * -1)][tileSelectedYOld + (yMoves [x] * -1)].getTag () != "redstudent" && boardTiles [tileSelectedXOld + (xMoves [x] * -1)][tileSelectedYOld + (yMoves [x] * -1)].getTag () != "redmaster")
                {
                    boardTiles [tileSelectedXOld + (xMoves [x] * -1)][tileSelectedYOld + (yMoves [x] * -1)].setBackgroundResource(R.color.highlight);
                    isTileSelected = true;
                }
            }
            else if (!turn)
            {
                if (tileSelectedXOld + xMoves [x] < 5 && tileSelectedYOld + yMoves [x] < 5 && tileSelectedXOld + xMoves [x] >= 0 && tileSelectedYOld + yMoves [x] >= 0 && boardTiles [tileSelectedXOld + xMoves [x]][tileSelectedYOld + yMoves [x]].getTag () != "bluestudent" && boardTiles [tileSelectedXOld + xMoves [x]][tileSelectedYOld + yMoves [x]].getTag () != "bluemaster")
                {
                    boardTiles [tileSelectedXOld + xMoves [x]][tileSelectedYOld + yMoves [x]].setBackgroundResource(R.color.highlight);
                    isTileSelected = true;
                }
            }
        }
    }

    // Need to fix this
    public void cardSwap ()
    {
        Card temp = gameCards [cardSelectedInt];
        gameCards [cardSelectedInt] = gameCards [4];
        gameCards [4] = temp;

        int tempInt = gameCardIDs [cardSelectedInt];
        gameCardIDs [cardSelectedInt] = gameCardIDs [4];
        gameCardIDs [4] = tempInt;

        gameCardPictures [cardSelectedInt].setImageResource(gameCardIDs [cardSelectedInt]);
        gameCardPictures [4].setImageResource(gameCardIDs [4]);

        assignTurn ();
    }

    public void tileScan ()
    {
        if (turn)
        {
            for (int x = 0; x < boardTiles.length; x++)
            {
                for (int y = 0; y < boardTiles[x].length; y++)
                {

                    if (boardTiles [x][y].getTag () == "redstudent" || boardTiles [x][y].getTag () == "redmaster")
                    {
                        boardTiles[x][y].setBackgroundResource(R.color.highlight);
                    }
                    else
                    {
                        boardTiles[x][y].setBackgroundResource(R.color.tile_color);
                    }
                }
            }
        }
        else if (!turn)
        {
            for (int x = 0; x < boardTiles.length; x++)
            {
                for (int y = 0; y < boardTiles[x].length; y++)
                {
                    if (boardTiles [x][y].getTag () == "bluestudent" || boardTiles [x][y].getTag () == "bluemaster")
                    {
                        boardTiles[x][y].setBackgroundResource(R.color.highlight);
                    }
                    else
                    {
                        boardTiles[x][y].setBackgroundResource(R.color.tile_color);
                    }
                }
            }
        }
    }
}
