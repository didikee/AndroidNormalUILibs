package com.didikee.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.didikee.example.act.ImageTextActivity;
import com.didikee.example.act.ItemViewActivity;
import com.didikee.example.act.XTransparentTitleViewActivity;

public class MainActivity extends Activity {

    private ListView mList;

    private String[] strs=new String[]{
            "test",
            "ImageTextView",
            "XTransparentTitleView",
            "test",
            "test"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mList = ((ListView) findViewById(R.id.listview));
        mList.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,strs));

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i+1){
                    case 1:
                        gotoActivity(ItemViewActivity.class);
                        break;
                    case 2:
                        gotoActivity(ImageTextActivity.class);
                        break;
                    case 3:
                        gotoActivity(XTransparentTitleViewActivity.class);
                        break;
                    case 4:
                        break;
                }
            }
        });
    }

    /**
     * test
     * @param clazz
     */
    public void gotoActivity(Class clazz){
        startActivity(new Intent(this,clazz));
    }
}
