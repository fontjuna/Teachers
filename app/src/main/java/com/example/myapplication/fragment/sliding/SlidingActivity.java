package com.example.myapplication.fragment.sliding;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.fragment.basketball.BasketScoreFragment;
import com.example.myapplication.fragment.color.ColorFragment;
import com.example.myapplication.fragment.exam.ChatFragment;

public class SlidingActivity extends AppCompatActivity
        implements BasketScoreFragment.OnWarningListener, ChatFragment.OnSendMessageListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding);

        // 뷰페이저
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        //탭달기
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onWarning(String teamName) {
        Toast.makeText(this, teamName, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSendMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private static class MyPagerAdapter extends FragmentPagerAdapter {

        public static final int PAGE_NUM = 10;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new BasketScoreFragment();
                case 1:
                    return ColorFragment.newInstance();
                case 2:
                    return new ChatFragment();
            }
            return ColorFragment.newInstance();
        }

        @Override
        public int getCount() {
            return PAGE_NUM;
        }
    }
}
