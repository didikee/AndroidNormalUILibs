package com.didikee.example.act;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;

import com.didikee.example.R;
import com.didikee.uilibs.viewgroups.HorizontalItemView;

public class ItemViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);

        HorizontalItemView itemView = (HorizontalItemView) findViewById(R.id.itemview);
        itemView.setLeftImageView(50,50,R.mipmap.ic_launcher,20);
        itemView.setRightImageView(70,70,R.mipmap.ic_launcher,30);

        itemView.setLeftTextView("我的新增哈哈", Color.BLUE,18,90);
        itemView.setRightTextView("我是右边", Color.RED,25,100);

        itemView.setLine(Gravity.BOTTOM,Color.GREEN,10,5,5);

        itemView.setTextLeft("哈H");
    }
}
