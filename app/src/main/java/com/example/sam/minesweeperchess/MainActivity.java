package com.example.sam.minesweeperchess;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RelativeLayout relativeLayout;
    ImageView boardContainerView;
    GridView boardGrid;
    ImageAdapter boardImageAdapter;
    Board board;
    GameStateManager manager;
    TextView gameStatusText;
    Button resetButton;

    int[] boardBackgrounds;
    int currentBackgroundIndex = 0;
    int selectedRank;
    int selectedFile;
    boolean pieceSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = new GameStateManager();
        board = new Board();

        resetButton = (Button) findViewById(R.id.resetButton);
        gameStatusText = (TextView) findViewById(R.id.gameStatusLabel);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        boardGrid = (GridView) findViewById(R.id.boardGrid);
        boardContainerView = (ImageView) findViewById(R.id.boardImage);
        boardImageAdapter = new ImageAdapter(this,board,this);
        boardGrid.setAdapter(boardImageAdapter);

        initializeBoards();
        //runs slow when background image set, for some reason
        relativeLayout.setBackgroundResource(R.drawable.black_bg);

        relativeLayout.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()) {
            public void onSwipeLeft(){
                int nextBg = nextBoardBackground(currentBackgroundIndex);
                boardContainerView.setImageResource(nextBg);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGameState();
                System.out.println("Game state resetting?");
            }
        });
    }

    public void initializeBoards(){
        boardBackgrounds = new int[]{R.drawable.blue_board,
                R.drawable.pink_board,
                R.drawable.brown_board,
                R.drawable.purple_board};
    }

    public int nextBoardBackground(int currentIndex){
        currentBackgroundIndex++;
        if(currentIndex>boardBackgrounds.length-1){
            currentBackgroundIndex = 0;
            return boardBackgrounds[0];
        }
        return boardBackgrounds[currentIndex];
    }

    public void receivingPiecePress(int gridValue){

        int piecePosition = 63-gridValue;

        int rank = (int)Math.floor(piecePosition/8);
        int file = 7-(piecePosition)%8;

        if(!pieceSelected && board.boardModelData[file][rank]!= null){
            if(board.boardModelData[file][rank].color == manager.currentPlayerColor()) {
                System.out.println("Piece selected.");
                selectedRank = rank;
                selectedFile = file;
                pieceSelected = true;
            }
        }
        else if(pieceSelected){
            if(board.movePieceToPosition(board.boardModelData[selectedFile][selectedRank],file,rank)){
                System.out.println("Piece moved.");

                if(manager.currentPlayerColor() == Piece.PieceColor.WHITE){
                    if(board.blackKing.isInCheckAtSquare(board.blackKing.xPos,board.blackKing.yPos)){
                        manager.black.isInCheck = true;
                        gameStatusText.setText("Black is in check!");
                    }
                }
                else if(manager.currentPlayerColor() == Piece.PieceColor.BLACK){
                    if(board.whiteKing.isInCheckAtSquare(board.whiteKing.xPos,board.whiteKing.yPos)){
                        manager.white.isInCheck = true;
                        gameStatusText.setText("White is in check!");
                    }
                }

                pieceSelected = false;
                boardImageAdapter.notifyDataSetChanged();
                manager.nextTurn();
            }
            else{
                System.out.println("Not a valid move.");
                pieceSelected=false;
            }
        }
    }

    public void resetGameState(){
        manager = new GameStateManager();
        board.initializeBoardState();
        boardImageAdapter.notifyDataSetChanged();
        gameStatusText.setText("Game Status");
    }
}