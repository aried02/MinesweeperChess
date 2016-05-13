package com.example.sam.minesweeperchess;

/**
 * Created by Sam on 4/24/2016.
 */
public class Queen extends Piece {
    public Queen(Board board, PieceColor color, int xPos, int yPos){
        super(board, PieceType.QUEEN, color, xPos, yPos);
        if(this.color == color.WHITE)
            this.spriteImageId = R.drawable.white_queen;
        else
            this.spriteImageId = R.drawable.black_queen;
    }

    public boolean canMoveToLocation(int prospectiveX, int prospectiveY) {
        if(!squareIsCandidateMove(prospectiveX, prospectiveY))
            return false;

        if(legalMoveForQueen(prospectiveX, prospectiveY)){
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
        }else if(!xDecreasing && !yDecreasing){
            xInc = yInc = 1;
        }else if(xDecreasing && (yPos == prospectiveY)){
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

            if(board.squareOccupied(xIter,yIter)) {
                if(prospectiveX!=xIter && prospectiveY!=yIter)
                    return true;
            }
        }

        return false;
    }

    //checks if initial square choice is legal for queen without considering other pieces. just check this at the beginnign, run movesThroughPiece, and should be fine
    private boolean legalMoveForQueen(int prospectiveX, int prospectiveY){
        return (Math.abs(xPos - prospectiveX) == Math.abs(yPos - prospectiveY)) || (xPos == prospectiveX || yPos == prospectiveY);
    }

}
