package com.example.sam.minesweeperchess;

/**
 * Created by Sam on 4/24/2016.
 */
public class Rook extends Piece {

    public Rook(Board board, PieceColor color, int xPos, int yPos){
        super(board, PieceType.ROOK, color, xPos, yPos);
        if(this.color == color.WHITE)
            this.spriteImageId = R.drawable.white_rook;
        else
            this.spriteImageId = R.drawable.black_rook;
    }

    public boolean canMoveToLocation(int prospectiveX, int prospectiveY) {
        if(!squareIsCandidateMove(prospectiveX, prospectiveY))
            return false;

        if(legalMoveForRook(prospectiveX, prospectiveY)){
            if(!movesThroughPiece(prospectiveX, prospectiveY)){
                    return true;
            }
        }

        return false;
    }

    private boolean movesThroughPiece(int prospectiveX, int prospectiveY){
        int xInc, yInc, xIter, yIter;

        xIter = xPos;
        yIter = yPos;
        boolean xDecreasing = xPos > prospectiveX;
        boolean yDecreasing = yPos > prospectiveY;

        //check which direction the bishop is moving
        if(xDecreasing && (yPos == prospectiveY)){
            xInc = -1;
            yInc = 0;
        }else if(!xDecreasing && (yPos == prospectiveY)){
            xInc = 1;
            yInc = 0;
        }else if(yDecreasing && (xPos == prospectiveX)){
            xInc = 0;
            yInc = -1;
        }else{
            xInc = 0;
            yInc = 1;
        }

        //iterate through every value between here and there, check if square is occupied
        while(xIter != prospectiveX && yIter != prospectiveY){
            xIter+= xInc;
            yIter+= yInc;

            if(board.squareOccupied(xIter,yIter))
                return true;
        }

        return false;
    }

    //checks if initial square choice is legal for rook without considering other pieces. just check this at the beginnign, run movesThroughPiece, and should be fine
    private boolean legalMoveForRook(int prospectiveX, int prospectiveY){
        return xPos == prospectiveX || yPos == prospectiveY;
    }
}
