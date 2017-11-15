package com.example.message3;

import android.app.Dialog;
import android.app.Fragment;
import android.app.WallpaperManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by Dedgoss on 2017/5/8.
 */
public class MeFragment extends Fragment {

    private LinearLayout mLinearLayout;
    private ImageView mImage_user_QRCode;


    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.me_fragment,container,false);

        mLinearLayout = (LinearLayout) view.findViewById(R.id.usersetting);
        mLinearLayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Log.v("1","usering");
                Intent intent=new Intent(getActivity(),Me_setting.class);
                startActivity(intent);
            }
        });

        mImage_user_QRCode = (ImageView)view.findViewById(R.id.user_QRCode);
        mImage_user_QRCode.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Dialog_QRcode();
            }

        });

        return view;
    }

    private void Dialog_QRcode(){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
        View layout = LayoutInflater.from(getActivity()).inflate(R.layout.me_fragment_qrcode, null);
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
