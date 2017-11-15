package com.example.message3;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by Dedgoss on 2017/5/13.
 */
public class TitleToolarMain extends Fragment{
    private Toolbar mToolbar;
    private Toolbar.OnMenuItemClickListener onMenuItemClickListener;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.title_toolbar_main, container, false);

        setHasOptionsMenu(true);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolbar.setTitle("主界面");

        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);

        onMenuItemClickListener= new Toolbar.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_more:
                        Toast.makeText(getActivity(), "点击了更多", Toast.LENGTH_SHORT).show();
                        Log.v("1","点击了更多按钮");
                        break;
                    case R.id.action_search:
                        Toast.makeText(getActivity(), "点击了搜索", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        };

        mToolbar.setOnMenuItemClickListener(onMenuItemClickListener);

        return view;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //加载菜单文件
        ((AppCompatActivity)getActivity()).getMenuInflater().inflate(R.menu.menu_main, menu);
    }
}
