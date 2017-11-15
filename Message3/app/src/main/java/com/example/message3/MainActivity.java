package com.example.message3;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    BottomBar mBottomBar;
    String strResult="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        new Thread(new RequestThread()).start();
//        Toast.makeText(MainActivity.this,"调用线程", Toast.LENGTH_SHORT  ).show();
        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.setItemsFromMenu(R.menu.menu_bottombar   , new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(int menuItemId) {
                if(menuItemId == R.id.menu_news){
//                    Toast.makeText(MainActivity.this,"消息",Toast.LENGTH_SHORT  ).show();
                    NewsFragment newsFragment = new NewsFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.mainfragment,newsFragment);
                    transaction.commit();

                }
                else if(menuItemId == R.id.menu_recent) {
//                    Toast.makeText(MainActivity.this,"最近消息",Toast.LENGTH_SHORT  ).show();
                    ReNewsFragment reNewsFragment = new ReNewsFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.mainfragment,reNewsFragment);
                    transaction.commit();

                }
                else if(menuItemId == R.id.menu_me  ){
                    MeFragment meFragment = new MeFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.mainfragment,meFragment);
                    transaction.commit();

                }

            }

            @Override
            public void onMenuTabReSelected(int menuItemId) {

            }
        });



    }

    

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //恢复outState的状态
        mBottomBar.onSaveInstanceState(outState);
    }



}


