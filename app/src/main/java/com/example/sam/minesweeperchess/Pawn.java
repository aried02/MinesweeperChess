package com.example.sam.minesweeperchess;

/**
 * Created by Sam on 4/24/2016.
 */
public class Pawn extends Piece {
    public boolean hasMoved = false;

    public Pawn(Board board, PieceColor color, int xPos, int yPos){
        super(board, PieceType.PAWN, color, xPos, yPos);
        if(this.color == color.WHITE)
            this.spriteImageId = R.drawable.white_pawn;
        else
            this.spriteImageId = R.drawable.black_pawn;
    }

    public boolean canMoveToLocation(int prospectiveX, int prospectiveY) {
        if(this.color == PieceColor.WHITE){
            if(this.hasMoved == false && prospectiveY - yPos == 2 && prospectiveX == xPos)
                return true;
            if(prospectiveY - yPos == 1 && prospectiveX == xPos)
                return true;
            else if((prospectiveX == xPos + 1 || prospectiveX == xPos - 1) && prospectiveY==yPos+1){
                if(board.boardModelData[prospectiveX][prospectiveY]!=null) {
                    if (board.squareOccupied(prospectiveX, prospectiveY) && board.pieceColorForSquare(prospectiveX, prospectiveY) != this.color)
                        return true;
                }
            }
        }
        else if(this.color == PieceColor.BLACK){
            if(this.hasMoved == false && yPos - prospectiveY == 2 && prospectiveX == xPos)
                return true;
            if(yPos - prospectiveY == 1 && prospectiveX == xPos)
                return true;
            else if((prospectiveX == xPos + 1 || prospectiveX == xPos - 1) && prospectiveY==yPos-1){
                if(board.boardModelData[prospectiveX][prospectiveY]!=null) {
                    if (board.squareOccupied(prospectiveX, prospectiveY) && board.pieceColorForSquare(prospectiveX, prospectiveY) != this.color)
                        return true;
                }
            }
        }
        return false;
    }

    @Override
    public void onMove() {
        this.hasMoved = true;
    }
}
