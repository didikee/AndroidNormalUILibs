package com.didikee.example.act;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.didikee.example.R;
import com.didikee.uilibs.views.WaitFinishTextView;

public class ProgressTextActivity extends AppCompatActivity {

    private WaitFinishTextView mWait;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_text);

        mWait = (WaitFinishTextView) findViewById(R.id.tv_wait);
        mWait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProgressTextActivity.this, "宝宝不开心!", Toast.LENGTH_SHORT).show();
                Log.e("test","--------------");
            }
        });

        findViewById(R.id.tv_wait_finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWait.onFinish();
            }
        });
    }
}
