package com.example.ryanfarrell362.onitama;

public class Card
{
    private String name;
    private int [] xMoves;
    private int [] yMoves;
    private char colour;

    public Card (String name, int [] xMoves, int [] yMoves, char colour)
    {
        this.name = name;
        this.xMoves = xMoves;
        this.yMoves = yMoves;
        this.colour = colour;
    }

    public String getName ()
    {
        return this.name;
    }

    public int [] getXMoves ()
    {
        return this.xMoves;
    }

    public int [] getYMoves ()
    {
        return this.yMoves;
    }

    public char getColour ()
    {
        return this.colour;
    }
}
