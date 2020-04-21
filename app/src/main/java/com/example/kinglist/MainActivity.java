package com.example.kinglist;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView mTitle;
    private Toolbar mToolbar;
    private TabLayout mTab;
    private FrameLayout mFragment;
    private ArrayList<Fragment> fragments;
    private HomeFragment homeFragment;
    private VideoFragment videoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        String a="111";
        mTitle = (TextView) findViewById(R.id.title);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTab = (TabLayout) findViewById(R.id.tab);
        mFragment = (FrameLayout) findViewById(R.id.fragment);

        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mToolbar.setLogo(R.drawable.browse_back);

        mTitle.setText("我的收藏");

        mTab.addTab(mTab.newTab().setText("全部"));
        mTab.addTab(mTab.newTab().setText("收藏"));

        LinearLayout childAt = (LinearLayout) mTab.getChildAt(0);
        childAt.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        childAt.setDividerDrawable(ContextCompat.getDrawable(this,R.drawable.dividy_vertical));

        //
        fragments = new ArrayList<>();
        homeFragment = new HomeFragment();
        videoFragment = new VideoFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment,homeFragment)
                .add(R.id.fragment,videoFragment)
                .show(homeFragment)
                .hide(videoFragment)
                .commit();

        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position){
                    case 0:
                        getSupportFragmentManager().beginTransaction()
                                .show(homeFragment)
                                .hide(videoFragment)
                                .commit();
                        break;

                    case 1:
                        getSupportFragmentManager().beginTransaction()
                                .show(videoFragment)
                                .hide(homeFragment)
                                .commit();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void sendData(VideoBean videoBean) {
        //给Fragment传值
        videoFragment.setData(videoBean);
    }
}
