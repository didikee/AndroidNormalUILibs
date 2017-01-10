package com.didikee.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

    private ListView mList;

    private String[] strs=new String[]{
            "1. test",
            "2. ImageTextView",
            "3. XTransparentTitleView",
            "4. ProgressTextActivity",
            "5. (4.1shape 背景不支持透明颜色)"
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
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
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
