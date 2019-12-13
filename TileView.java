package com.example.colortiles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Random;

public class TileView extends View {
    int[] tiles= new int[20];
    int darkColor = Color.GRAY;
    int brightColor = Color.YELLOW;
    int color = darkColor;
    int width, height;
    Toast toast;


    public TileView(Context context) {
        super(context);
        int k = 0;
        for(int i = 0 ; i < 4; i+=1) {
            for (int j = 0; j < 4; j += 1) {
                int randick=0+(int)(Math.random()*10);
                Log.d("kotick", "randick " + randick);
                if(randick % 2==0){
                    tiles[k] = 0;
                }
                else {
                    tiles[k] = 1;
                }
                k++;
            }
        }
    }

    public TileView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        width = canvas.getWidth();
        height = canvas.getHeight();
        super.onDraw(canvas);
        Paint p = new Paint();
        p.setColor(Color.BLUE);
        int k = 0;
        for(int i = 0 ; i < height; i+=(height/4)){
            for(int j = 0 ; j < width; j+=(width/4)) {
                if ( tiles[k]== 0){
                    p.setColor(Color.GREEN);
                }
                else{
                    p.setColor(Color.RED);
                }
                k++;
                canvas.drawRect(j+5, i+5, j+(width/4-5), i+(height/4-5), p);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int)event.getX();
            int x1 = x / (width/4);
            int y = (int)event.getY();
            int y1 = y / (height/4);
            int xy= y1*4+x1;
            tiles[xy] = tiles[xy]==1?0:1;
            for(int i =0 ; i<4 ; i++){
                xy= y1*4+i;
                tiles[xy] = tiles[xy]==1?0:1;
                xy= i*4+x1;
                tiles[xy] = tiles[xy]==1?0:1;
            }
            int flag=1;
            for(int j = 1 ; j < 16 ; j++){
                if(tiles[j]!=tiles[j-1]){
                    flag=0;
                    break;
                }
            }
            if(flag==1){
//                toast=Toast.makeText(this, "Victory!", Toast.LENGTH_LONG);
//                toast.show();
                Toast.makeText(getContext(), "Victory!", Toast.LENGTH_SHORT).show();
            }

            invalidate();
        }
            return true;
    }

}

