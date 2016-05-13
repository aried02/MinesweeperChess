package com.example.sam.minesweeperchess;

import android.media.Image;

/**
 * Created by Sam on 4/24/2016.
 */

public abstract class Piece {
    public enum PieceType {PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING};
    public enum PieceColor {BLACK, WHITE};

    public Board board;
    public PieceType type;
    public PieceColor color;
    public int xPos;
    public int yPos;

    //each piece will have image member that represents it
    public int spriteImageId;

    public Piece(Board board, PieceType type, PieceColor color, int xPos, int yPos) {
        this.type = type;
        this.xPos = xPos;
        this.yPos = yPos;
        this.board = board;
        this.color = color;
    }

    //can this square even be considered?
    private boolean friendlyPieceOnSquare(int xPos, int yPos){
        if(board.boardModelData[xPos][yPos] == null) {
            return false;
        }else if(board.boardModelData[xPos][yPos].color != this.color) {
            return false;
        }else
            return true;
    }

    public boolean squareIsCandidateMove(int prospectiveX, int prospectiveY){
        return !friendlyPieceOnSquare(prospectiveX, prospectiveY) && board.squareIsOnBoard(prospectiveX, prospectiveY) && (xPos != prospectiveX || yPos != prospectiveY);
    }

    public void onMove(){

    }

    public boolean movingToSquareWouldPutKingInCheck(int prospectiveX, int prospectiveY){
        Board hypotheticalBoard = this.board;
        final boolean b = hypotheticalBoard.movePieceToPosition(this,prospectiveX, prospectiveY);
        if(this.color == PieceColor.BLACK){
            if(board.whiteKing.isInCheckAtSquare(board.whiteKing.xPos,board.whiteKing.yPos)) {
                System.out.println("detecting check");
                return true;
            }
        }else{
            if(board.blackKing.isInCheckAtSquare(board.blackKing.xPos,board.blackKing.yPos)) {
                System.out.println("detecting check");
                return true;
            }
        }
        return false;
    }

    public abstract boolean canMoveToLocation(int prospectiveX, int prospectiveY);

}
