package com.example.sam.minesweeperchess;

/**
 * Created by Sam on 5/8/2016.
 */
public class GameStateManager {
    public Player white;
    public Player black;

    public GameStateManager() {
        white = new Player(Piece.PieceColor.WHITE);
        black = new Player(Piece.PieceColor.BLACK);
        white.isTurn = true;
        black.isTurn = false;
        white.isInCheck = black.isInCheck = false;
    }

    public void nextTurn() {
        if(white.isTurn){
            white.isTurn = false;
            black.isTurn = true;
        }
        else if(black.isTurn){
            black.isTurn = false;
            white.isTurn = true;
        }

    }

    public Piece.PieceColor currentPlayerColor() {
        if (white.isTurn)
            return Piece.PieceColor.WHITE;
        return Piece.PieceColor.BLACK;
    }
}
