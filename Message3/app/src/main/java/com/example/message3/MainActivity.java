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

    //向服务器发送请求
    private class RequestThread implements Runnable{

        @Override
        public void run() {
            //因为选择POST方法，所以new HttpPost对象，构造方法传入处理请求php文件的url
            HttpPost httpRequest = new HttpPost("http://47.94.201.24");
            //POST方法的参数列表
            ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
            //添加名为userName的参数，值为giantpoplar
            params.add(new BasicNameValuePair("userName", "haoxingxing"));

            try {
                //设置请求实体，设定了参数列表
                httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                //执行请求,等待服务器返回结果
                HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);
                //log出http返回报文头
                Log.e("status",httpResponse.getStatusLine().toString());
                //判断返回码是否为200，200表示请求成功
                if (httpResponse.getStatusLine().getStatusCode() == 200) {
                    //获取返回字符串
                    strResult = EntityUtils.toString(httpResponse.getEntity(), HTTP.UTF_8);
                    Log.d("线程","启动线程");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //恢复outState的状态
        mBottomBar.onSaveInstanceState(outState);
    }



}


