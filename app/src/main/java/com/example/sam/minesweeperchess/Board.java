package com.example.sam.minesweeperchess;

/**
 * Created by Sam on 4/24/2016.
 */
public class Board {
    Piece[][] boardModelData = new Piece[8][8];

    public King whiteKing = new King(this, Piece.PieceColor.WHITE, 4, 0);
    public King blackKing = new King(this, Piece.PieceColor.BLACK, 4, 7);

    public Board() {
        initializeBoardState();
    }

    public void initializeBoardState() {

        //initialize each square to null
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boardModelData[i][j] = null;
            }
        }

        //instantiate pawns
        for (int i = 0; i < 8; i++) {
            boardModelData[i][1] = new Pawn(this, Piece.PieceColor.WHITE, i, 1);
            boardModelData[i][6] = new Pawn(this, Piece.PieceColor.BLACK, i, 6);
        }

        //instantiate all the rest of the pieces
        boardModelData[0][0] = new Rook(this, Piece.PieceColor.WHITE, 0, 0);
        boardModelData[7][0] = new Rook(this, Piece.PieceColor.WHITE, 7, 0);
        boardModelData[0][7] = new Rook(this, Piece.PieceColor.BLACK, 0, 7);
        boardModelData[7][7] = new Rook(this, Piece.PieceColor.BLACK, 7, 7);

        boardModelData[1][0] = new Knight(this, Piece.PieceColor.WHITE, 1, 0);
        boardModelData[6][0] = new Knight(this, Piece.PieceColor.WHITE, 6, 0);
        boardModelData[1][7] = new Knight(this, Piece.PieceColor.BLACK, 1, 7);
        boardModelData[6][7] = new Knight(this, Piece.PieceColor.BLACK, 6, 7);

        boardModelData[2][0] = new Bishop(this, Piece.PieceColor.WHITE, 2, 0);
        boardModelData[5][0] = new Bishop(this, Piece.PieceColor.WHITE, 5, 0);
        boardModelData[2][7] = new Bishop(this, Piece.PieceColor.BLACK, 2, 7);
        boardModelData[5][7] = new Bishop(this, Piece.PieceColor.BLACK, 5, 7);

        boardModelData[3][0] = new Queen(this, Piece.PieceColor.WHITE, 3, 0);
        boardModelData[3][7] = new Queen(this, Piece.PieceColor.BLACK, 3, 7);

        boardModelData[4][0] = whiteKing;
        boardModelData[4][7] = blackKing;
    }

    //set previous square to null, change piece position information, and update board
    public boolean movePieceToPosition(Piece piece, int xPos, int yPos) {
        if (piece.canMoveToLocation(xPos, yPos)) {
            boardModelData[piece.xPos][piece.yPos] = null;
            piece.xPos = xPos;
            piece.yPos = yPos;
            boardModelData[xPos][yPos] = piece;
            return true;
        }

        return false;
    }

    public boolean squareOccupied(int xPos, int yPos){
        if(boardModelData[xPos][yPos]!= null)
            return true;
        return false;
    }

    public boolean squareIsOnBoard(int xPos, int yPos){
        if((xPos < 8 && xPos >= 0)
                &&(yPos < 8) && (yPos >= 0))
            return true;
        return false;
    }

    public Piece.PieceColor pieceColorForSquare(int xPos, int yPos){
        return boardModelData[xPos][yPos].color;
    }

    public int[] imagesForBoardState(){
        int[] images = new int[64];

        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                if(boardModelData[j][i]!=null)
                    images[(8*(7-i))+j] = boardModelData[j][i].spriteImageId;
                else
                    images[(8*(7-i))+j] = 0;
            }
        }

        return images;
    }

    private int[] reverseBoard(int[] array){
        for(int i = 0; i < array.length / 2; i++){
            int temp = array[i];
            array[i] = array[array.length - 1 -i];
            array[array.length - 1 - i] = temp;
        }
        return array;
    }

}
