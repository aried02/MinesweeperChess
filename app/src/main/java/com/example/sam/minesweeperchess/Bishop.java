package com.example.sam.minesweeperchess;

/**
 * Created by Sam on 4/24/2016.
 */
public class Bishop extends Piece {
    public Bishop(Board board, PieceColor color, int xPos, int yPos){
        super(board, PieceType.BISHOP, color, xPos, yPos);
        if(this.color == color.WHITE)
            this.spriteImageId = R.drawable.white_bishop;
        else
            this.spriteImageId = R.drawable.black_bishop;
    }

    public boolean canMoveToLocation(int prospectiveX, int prospectiveY) {
        if(!squareIsCandidateMove(prospectiveX, prospectiveY)){
            return false;
        }

        if(legalMoveForBishop(prospectiveX, prospectiveY)){
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
        if(xDecreasing && yDecreasing){
            xInc = yInc = -1;
        }else if(!xDecreasing && yDecreasing){
            xInc = 1;
            yInc = -1;
        }else if(xDecreasing && !yDecreasing){
            xInc = -1;
            yInc = 1;
        }else{
            xInc = yInc = 1;
        }

        //iterate through every value between here and there, check if square is occupied
        while(xIter != prospectiveX && yIter != prospectiveY){
            xIter+= xInc;
            yIter+= yInc;

            if(board.squareOccupied(xIter,yIter)) {
                if(prospectiveX!=xIter && prospectiveY!=yIter)
                    return true;
            }
        }

        return false;
    }

    //checks if initial square choice is legal for bishop without considering other pieces. just check this at the beginnign, run movesThroughPiece, and should be fine
    private boolean legalMoveForBishop(int prospectiveX, int prospectiveY){
        return Math.abs(xPos - prospectiveX) == Math.abs(yPos - prospectiveY);
    }
}
