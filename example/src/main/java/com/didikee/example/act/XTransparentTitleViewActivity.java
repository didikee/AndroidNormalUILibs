package com.didikee.example.act;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.didikee.example.R;
import com.didikee.uilibs.utils.DisplayUtil;
import com.didikee.uilibs.viewgroups.XTransparentTitleView;

public class XTransparentTitleViewActivity extends AppCompatActivity {

    private XTransparentTitleView mXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_xtransparent_title_view);
        setBarStyle();

        mXT = ((XTransparentTitleView) findViewById(R.id.xt));
        mXT.initParams(DisplayUtil.dp2px(this,500),Color.BLUE,Color.BLUE);

    }
    public void setBarStyle() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置状态栏透明
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
