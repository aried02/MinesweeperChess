package com.example.sam.minesweeperchess;

/**
 * Created by Sam on 5/8/2016.
 */
public class Player {
    public Piece.PieceColor color;
    public boolean isInCheck = false;
    public boolean isTurn;

    public Player(Piece.PieceColor c){
        this.color = c;
    }
}
