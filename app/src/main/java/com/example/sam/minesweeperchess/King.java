package com.example.sam.minesweeperchess;

import java.util.Map;

/**
 * Created by Sam on 4/24/2016.
 */
public class King extends Piece {
    public boolean inCheck;

    public King(Board board, PieceColor color, int xPos, int yPos){
        super(board, PieceType.KING, color, xPos, yPos);
        if(this.color == color.WHITE)
            this.spriteImageId = R.drawable.white_king;
        else
            this.spriteImageId = R.drawable.black_king;
    }

    public boolean canMoveToLocation(int prospectiveX, int prospectiveY) {
        int xDiff = Math.abs(xPos-prospectiveX);
        int yDiff = Math.abs(yPos-prospectiveY);

        if((xDiff == 1 || yDiff == 1) && !isInCheckAtSquare(prospectiveX,prospectiveY))
            return true;

        return false;
    }

    public boolean isInCheckmate(int prospectiveX, int prospectiveY){
        return true;
    }

    public boolean isInCheckAtSquare(int prospectiveX, int prospectiveY) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board.boardModelData[i][j] != null){
                    //System.out.println("Piece not null.");
                    if(board.boardModelData[i][j].color != this.color){
                        if(board.boardModelData[i][j].canMoveToLocation(prospectiveX, prospectiveY)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
