package com.didikee.uilibs.views.viewhelper;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Toast;

import com.didikee.uilibs.views.customviews.XEditText;


/**
 * Created by didik on 2016/8/15.
 *
 */
public class AddMinusHelper {

    private Context mContext;
    private XEditText sEditText;
    private long max= Long.MAX_VALUE;
    private long min= Long.MIN_VALUE;
    private long num;

    public AddMinusHelper(XEditText sEditText, Context context) {
        this.sEditText = sEditText;
        this.mContext=context;
        init();
    }

    private void init() {
        String s = sEditText.getText().toString();
        if (TextUtils.isEmpty(s)){
            num=0;
        }else {
            try {
                num= Long.valueOf(s);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                num=0;
                Toast.makeText(mContext, "NumberFormatError", Toast.LENGTH_SHORT).show();
            }
        }

        sEditText.setOnDrawableClickListener(new XEditText.OnDrawableClickListener() {
            @Override
            public void onDrawableClick(XEditText view, int who) {
                switch (who){
                    case XEditText.DRAWABLE_LEFT:
                        minus();
                        break;
                    case XEditText.DRAWABLE_RIGHT:
                        add();
                        break;
                    case XEditText.DRAWABLE_TOP:
                        break;
                    case XEditText.DRAWABLE_BOTTOM:
                        break;

                }
            }
        });

        sEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String st = editable.toString();
                int length = st.length();
                if (TextUtils.isEmpty(st)){
                    return;
                }else if (length>1 && st.startsWith("0")){
                    editable.delete(length-1,length);
                    num=0;
                }else {
                    try {
                        num= Long.valueOf(st);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        num=0;
                        Toast.makeText(mContext, "NumberFormatError", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    /**
     * add
     */
    public void add(){
       num++;
        if (num<=max){
            setText(num);
        }else {
            Toast.makeText(mContext, "MaxMax!!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * minus
     */
    public void minus(){
        num--;
        if (num>=min){
            setText(num);
        }else {
            Toast.makeText(mContext, "MinMin!!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     *
     * @param num num
     */
    public void setText(long num){
        String result=num+"";
        sEditText.setText(result);
        sEditText.setSelection(result.length());
    }

    /**
     *
     * @param max limit max
     */
    public void setMax(long max){
        this.max=max;
    }

    /**
     *
     * @param min limit min
     */
    public void setMin(long min){
        this.min=min;
    }
}
