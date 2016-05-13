package com.example.sam.minesweeperchess;

/**
 * Created by Sam on 4/24/2016.
 */
public class Knight extends Piece {
    public Knight(Board board, PieceColor color, int xPos, int yPos){
        super(board, PieceType.KNIGHT, color, xPos, yPos);
        if(this.color == color.WHITE)
            this.spriteImageId = R.drawable.white_knight;
        else
            this.spriteImageId = R.drawable.black_knight;
    }

    public boolean canMoveToLocation(int prospectiveX, int prospectiveY) {

        if(!squareIsCandidateMove(prospectiveX, prospectiveY))
            return false;

        if((Math.abs(xPos - prospectiveX) == 1
            && Math.abs(yPos - prospectiveY) == 2)
            || (Math.abs(xPos - prospectiveX) == 2
            && Math.abs(yPos - prospectiveY) == 1)) {
                return true;
        }
        return false;
    }
}
