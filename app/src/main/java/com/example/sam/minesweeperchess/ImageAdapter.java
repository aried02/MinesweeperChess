package com.example.sam.minesweeperchess;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by Sam on 5/7/2016.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    public Board parentBoard;
    public MainActivity m;

    public ImageAdapter(Context c, Board b, MainActivity m){
        parentBoard = b;
        mContext = c;
        this.m = m;
    }

    public int getCount(){
        return 64;
    }

    public Object getItem(int position){
        return null;
    }

    public long getItemId(int position){
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent){
        ImageView imageButton;
        if(convertView == null){
            //if not recycled, initialize attributes
            imageButton = new ImageButton(mContext);
            imageButton.setLayoutParams(new GridView.LayoutParams(85,85));
            imageButton.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageButton.setPadding(0,0,0,0);
        }else{
            imageButton = (ImageView) convertView;
        }

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m.receivingPiecePress(position);
            }
        });

        imageButton.setImageResource(parentBoard.imagesForBoardState()[position]);
        imageButton.setBackgroundResource(0);
        return imageButton;
    }
}
