package com.example.message3;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Dedgoss on 2017/5/13.
 */
public class Me_setting extends AppCompatActivity{

    private Toolbar mToolbar;
    private RelativeLayout mRelativeLayout_Gender;
    private RelativeLayout mRelativeLayout_Name;
    private RelativeLayout mRelativeLayout_Whatsup;
    private RelativeLayout mRelativeLayout_QRcode;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_setting_activity);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("个人设置");

        mRelativeLayout_Gender = (RelativeLayout)findViewById(R.id.my_setting_relativelayout_gender);
        mRelativeLayout_Gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_gender();

            }
        });

        mRelativeLayout_Name = (RelativeLayout)findViewById(R.id.my_setting_relativelayout_name);
        mRelativeLayout_Name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_Name();
            }
        });

        mRelativeLayout_Whatsup = (RelativeLayout)findViewById(R.id.my_setting_relativelayout_whatsup);
        mRelativeLayout_Whatsup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_Whatsup();
            }
        });

        mRelativeLayout_QRcode = (RelativeLayout)findViewById(R.id.my_setting_relativelayout_qrcode);
        mRelativeLayout_QRcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_QRcode();
            }
        });

    }

    private void dialog_gender(){
        final String items[] = {"男","女"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        //自定义设置Dialog Title的款式
        TextView tv = new TextView(Me_setting.this);
        tv.setText("性别");    //内容
        tv.setTextSize(22);//字体大小
        tv.setPadding(100, 60, 0, 10);//位置(左，上，右，下)
        tv.setTextColor(Color.parseColor("#000000"));//颜色
        mBuilder.setCustomTitle(tv);//引入Dialog
        mBuilder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(Me_setting.this, items[which], Toast.LENGTH_SHORT).show();
            }
        });
        mBuilder.create().show();
    }

    private void Dialog_Name(){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        TextView tv = new TextView(Me_setting.this);
        tv.setText("用户名");
        tv.setTextSize(22);
        tv.setPadding(100, 60, 0, 10);
        tv.setTextColor(Color.parseColor("#000000"));
        mBuilder.setCustomTitle(tv);
        EditText mEdit = new EditText(this);
        mEdit.setTextColor(Color.parseColor("#000000"));
        mEdit.setText(R.string.username);
        mEdit.setSelection(Me_setting.this.getString(R.string.username).length());
        mBuilder.setView(mEdit);
        DialogInterface.OnClickListener mDialogOclickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case Dialog.BUTTON_POSITIVE:
                        Toast.makeText(Me_setting.this, "保存", Toast.LENGTH_SHORT).show();
                        break;
                    case Dialog.BUTTON_NEGATIVE:
                        Toast.makeText(Me_setting.this, "取消", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        mBuilder.setPositiveButton("保存",mDialogOclickListener);
        mBuilder.setNegativeButton("取消",mDialogOclickListener);
        mBuilder.create().show();
    }

    private void Dialog_Whatsup(){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        TextView tv = new TextView(Me_setting.this);
        tv.setText("个性签名");
        tv.setTextSize(22);
        tv.setPadding(100, 60, 0, 10);
        tv.setTextColor(Color.parseColor("#000000"));
        mBuilder.setCustomTitle(tv);
        EditText mEdit = new EditText(this);
        mEdit.setTextColor(Color.parseColor("#000000"));
        mEdit.setText(R.string.whatsup);
        mEdit.setSelection(Me_setting.this.getString(R.string.whatsup).length());
        mBuilder.setView(mEdit);
        DialogInterface.OnClickListener mDialogOclickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case Dialog.BUTTON_POSITIVE:
                        Toast.makeText(Me_setting.this, "保存", Toast.LENGTH_SHORT).show();
                        break;
                    case Dialog.BUTTON_NEGATIVE:
                        Toast.makeText(Me_setting.this, "取消", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        mBuilder.setPositiveButton("保存",mDialogOclickListener);
        mBuilder.setNegativeButton("取消",mDialogOclickListener);
        mBuilder.create().show();
    }

    private void Dialog_QRcode(){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(Me_setting.this);
        View layout = LayoutInflater.from(Me_setting.this).inflate(R.layout.me_fragment_qrcode, null);
        mBuilder.setView(layout);
        Dialog mDialog = mBuilder.create();
        Window mWindow = mDialog.getWindow();
        mDialog.show();
        WindowManager.LayoutParams lp = mWindow.getAttributes();
        lp.width = 900;
        lp.height = 1000;
        mWindow.setAttributes(lp);
    }
}
