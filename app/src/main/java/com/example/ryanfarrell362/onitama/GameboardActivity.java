package com.example.ryanfarrell362.onitama;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class GameboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameboard);

        ArrayList<Card> cards = new ArrayList<Card> ();
        Card [] gameCards = new Card [5];

        boolean turn = false;

        // Game Initialization
        cardInitialization (cards); // Setup cards
        cardDealer (cards, gameCards); // Deal out cards
        firstTurn (gameCards, turn); // Determine who goes first

        // Game Start
        assignTurn (turn); // Assign who goes
        // Choose card
        // Choose piece
        // Move piece
        // Swap cards
        // Check for win
    }

    public void assignTurn (boolean turn)
    {
        ImageView profilePicture1 = (ImageView) findViewById (R.id.profilePicture1);
        ImageView profilePicture2 = (ImageView) findViewById (R.id.profilePicture2);

        if (turn == false)
        {
            profilePicture2.setBackgroundResource(R.color.border);
            profilePicture1.setBackgroundResource(R.color.background_color);
        }
        else if (turn == true)
        {
            profilePicture1.setBackgroundResource(R.color.border);
            profilePicture2.setBackgroundResource(R.color.background_color);
        }
    }

    public void cardDealer(ArrayList cards, Card [] gameCards)
    {
        int arrayValue = 0;

        ImageButton player1Card1 = (ImageButton) findViewById (R.id.player1Card1);
        cardAssigner (cards, player1Card1, gameCards, arrayValue);
        arrayValue ++;

        ImageButton player1Card2 = (ImageButton) findViewById (R.id.player1Card2);
        cardAssigner (cards, player1Card2, gameCards, arrayValue);
        arrayValue ++;

        ImageButton player2Card1 = (ImageButton) findViewById (R.id.player2Card1);
        cardAssigner (cards, player2Card1, gameCards, arrayValue);
        arrayValue ++;

        ImageButton player2Card2 = (ImageButton) findViewById (R.id.player2Card2);
        cardAssigner (cards, player2Card2, gameCards, arrayValue);
        arrayValue ++;

        ImageView middleCard = (ImageView) findViewById (R.id.middleCard);
        cardAssigner (cards, middleCard, gameCards, arrayValue);
    }

    public ArrayList cardAssigner (ArrayList cards, ImageButton card, Card [] gameCards, int arrayValue)
    {
        Random numGen = new Random ();

        int randNum = numGen.nextInt (cards.size ());
        Card chosenCard = (Card) cards.get (randNum);
        gameCards [arrayValue] = chosenCard;
        String name = chosenCard.getName ();
        int resID = getResources ().getIdentifier (name.toLowerCase(), "mipmap", getPackageName());

        card.setImageResource(resID);
        cards.remove (randNum);

        return cards;
    }

    public void cardAssigner (ArrayList cards, ImageView card, Card [] gameCards, int arrayValue)
    {
        Random numGen = new Random ();

        int randNum = numGen.nextInt (cards.size ());
        Card chosenCard = (Card) cards.get (randNum);
        gameCards [arrayValue] = chosenCard;
        String name = chosenCard.getName ();
        int resID = getResources ().getIdentifier (name.toLowerCase(), "mipmap", getPackageName());

        card.setImageResource(resID);
    }

    public static ArrayList cardInitialization (ArrayList cards)
    {
        int [] tempArray1X = {-2, -1, 1};
        int [] tempArray1Y = {0, 1, -1};
        Card frog = new Card ("Frog", tempArray1X, tempArray1Y, 'r');

        int [] tempArray2X = {-1, -1, 1, 1};
        int [] tempArray2Y = {1, 0, 0, -1};
        Card goose = new Card ("Goose", tempArray2X, tempArray2Y, 'b');

        int [] tempArray3X = {-1, 1, 2};
        int [] tempArray3Y = {-1, 1, 0};
        Card rabbit = new Card ("Rabbit", tempArray3X, tempArray3Y, 'b');

        int [] tempArray4X = {-1, 0, 1};
        int [] tempArray4Y = {1, -1, 1};
        Card mantis = new Card ("Mantis", tempArray4X, tempArray4Y, 'r');

        int [] tempArray5X = {-1, 1, 1};
        int [] tempArray5Y = {0, 1, -1};
        Card cobra = new Card ("Cobra", tempArray5X, tempArray5Y, 'r');

        int [] tempArray6X = {-1, -1, 1, 1};
        int [] tempArray6Y = {1, 0, 1, 0};
        Card elephant = new Card ("Elephant", tempArray6X, tempArray6Y, 'r');

        int [] tempArray7X = {-1, 0, 1};
        int [] tempArray7Y = {-1, 1, -1};
        Card crane = new Card ("Crane", tempArray7X, tempArray7Y, 'b');

        int [] tempArray8X = {-1, -1, 1, 1};
        int [] tempArray8Y = {1, -1, 1, -1};
        Card monkey = new Card ("Monkey", tempArray8X, tempArray8Y, 'b');

        int [] tempArray9X = {-2, 0, 2};
        int [] tempArray9Y = {0, 1, 0};
        Card crab = new Card ("Crab", tempArray9X, tempArray9Y, 'b');

        int [] tempArray10X = {-1, -1, 1, 1};
        int [] tempArray10Y = {0, -1, 1, 0};
        Card rooster = new Card ("Rooster", tempArray10X, tempArray10Y, 'r');

        int [] tempArray11X = {0, 0};
        int [] tempArray11Y = {2, -1};
        Card tiger = new Card ("Tiger", tempArray11X, tempArray11Y, 'b');

        int [] tempArray12X = {0, 0, 1};
        int [] tempArray12Y = {1, -1, 0};
        Card ox = new Card ("Ox", tempArray12X, tempArray12Y, 'b');

        int [] tempArray13X = {-2, -1, 1, 2};
        int [] tempArray13Y = {1, -1, -1, 1};
        Card dragon = new Card ("Dragon", tempArray13X, tempArray13Y, 'r');

        int [] tempArray14X = {-1, -1, 1};
        int [] tempArray14Y = {1, -1, 0};
        Card eel = new Card ("Eel", tempArray14X, tempArray14Y, 'b');

        int [] tempArray15X = {-1, 0, 1};
        int [] tempArray15Y = {0, 1, 0};
        Card boar = new Card ("Boar", tempArray15X, tempArray15Y, 'r');

        int [] tempArray16X = {-1, 0, 0};
        int [] tempArray16Y = {0, 1, -1};
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
}