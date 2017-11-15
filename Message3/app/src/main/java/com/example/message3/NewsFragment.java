package com.example.message3;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 欧阳 on 2017/5/6.
 */
public class NewsFragment extends Fragment {
    private RecyclerView mRecyclerView;

    private RecyclerAdapter mAdapter;
    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm");
    private String date;
    private String strResult="";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_fragment, container, false);
        mAdapter = new RecyclerAdapter();
        mAdapter.initData();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        return view;
    }


    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
        private List<ListItem> mDatas;
        private List<Integer> mHeight;

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.item, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            ListItem listItem=mDatas.get(position);
            holder.bindMyView(listItem);
            /*
            holder.tv.setText(mDatas.get(position).getNewsTitle());
            holder.imageview.setImageResource(mDatas.get(position).getNewsImageId());
            */
            //绑定数据的同时，修改每个ItemView的高度
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            lp.height = 400;
            holder.itemView.setLayoutParams(lp);
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        protected void initData() {
            mDatas = new ArrayList<ListItem>();
            mHeight = new ArrayList<Integer>();
            String data=new String("在老挝的崇山峻岭中，成群结队的中国工程人员在开挖隧道、修建桥梁，用以支持一条260英里长的铁路。这是个耗资60亿美元的工程，最终将贯穿8个亚洲国家。在巴基斯坦，中国资金被用来建发电厂，解决当地长期的缺电问题。这是一个460亿美元投资计划的一部分。中国规划者在绘制从布达佩斯到贝尔格莱德的铁路线，这是中国商品经过在希腊的一个中资港口进入欧洲的另一条重要通道。在肯尼亚，中国正升级一条从蒙巴萨港口到内罗毕的铁路。肯尼亚政府游说别国未果，而中国在非洲改造破旧基础设施已逾10年…\n" +
                    "　　\n" +
                    "　　被称为“一带一路”的这个倡议，其范围和规模均是现代历史上几无先例的。其基础设施投资有望超过1万亿美元，涵盖60多个国家。这几天，北京聚集了数十位国家的领导人。");

            for (int i = 0; i <= 5; i++) {
                ListItem listItem = new ListItem("文章"+Integer.toString(i+1), R.drawable.data, "华电学生会", new Date(), "部门通知类");
                listItem.setNewsData(data);
                mDatas.add(listItem);
                mHeight.add(new Integer(300));
                saveData(listItem.getNewsTitle(),data);
            }


        }
        class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            TextView tv_title,tv_source,tv_date,tv_label;
            ImageView imageview;

            private ListItem mListItem;

            public MyViewHolder(View view) {
                super(view);
                tv_title = (TextView) view.findViewById(R.id.item_title);
                imageview = (ImageView) view.findViewById(R.id.item_image);
                tv_source=(TextView)view.findViewById(R.id.item_source);
                tv_date=(TextView)view.findViewById(R.id.item_date);
                tv_label=(TextView)view.findViewById(R.id.item_label);
                view.setOnClickListener(this);
            }
            public void bindMyView(ListItem listItem){
                mListItem=listItem;
                date=sdf.format(mListItem.getNewsDate());
                tv_title.setText(mListItem.getNewsTitle());
                tv_source.setText("来源："+mListItem.getNewsSource());
                tv_date.setText(date);
                tv_label.setText("标签："+mListItem.getNewsLabel());
                imageview.setImageResource(mListItem.getNewsImageId());
            }
            @Override
            public void onClick(View v){
                Intent intent=new Intent(getActivity(),ReadActivity.class);
                intent.putExtra("NewsArticleTitle",mListItem.getNewsTitle());
                intent.putExtra("NewsArticleSource",mListItem.getNewsSource());
                startActivity(intent);
            }
        }

    }
    public boolean saveData(String name,String data){
        if(data!=null) {
            SharedPreferences.Editor editor = getActivity().getSharedPreferences("data", getActivity().MODE_PRIVATE).edit();
            editor.putString(name, data);
            editor.commit();
            return true;
        }
        else {
            Log.v("save","要存储的数据为空");
            return false;
        }
    }



}
