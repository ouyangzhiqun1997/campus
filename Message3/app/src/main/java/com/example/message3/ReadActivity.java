package com.example.message3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;

/**
 * Created by 欧阳 on 2017/5/11.
 */
public class ReadActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView mNewsArticle_tv;
    private TextView mNewsTitle_tv;
    private String mNewsTitle;
    private Intent intent;
    private String mNewsArticleData;
    private Spanned spanned;

    private String strResult="";
    private static int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        new Thread(new RequestThread()).start();

        //获取intent中的数据（文章标题）
        intent=getIntent();
        mNewsTitle=intent.getStringExtra("NewsArticleTitle");
        //获取标题栏对象
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        mToolbar.setTitle(intent.getStringExtra("NewsArticleSource"));
        //得到显示文章内容的TextView对象
        mNewsArticle_tv=(TextView)findViewById(R.id.article_tv);
        mNewsTitle_tv=(TextView)findViewById(R.id.article_title_tv);
        //通过文章标题，从sharedpreference中读取文章内容
//        mNewsArticleData=reStoreData(mNewsTitle);
        mNewsTitle_tv.setText("想不到！纽约时报竟然这么评价“一带一路”");
        TextPaint tp = mNewsTitle_tv.getPaint();
        tp.setFakeBoldText(true);
        while (flag!=1)
        {

        }
        flag=0;
        mNewsArticleData=new String(strResult);
        mNewsArticle_tv.setText("      "+mNewsArticleData);
        mNewsArticle_tv.setMovementMethod(ScrollingMovementMethod.getInstance());
    }
    public String reStoreData(String name){
        SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);
        String data=pref.getString(name,"");
        return data;
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
                    flag=1;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}

