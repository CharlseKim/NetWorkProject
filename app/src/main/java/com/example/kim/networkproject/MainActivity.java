package com.example.kim.networkproject;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{

    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    ViewPager viewPager;
    private final long FINISH_INTERVAL_TIME = 2000;     // 2초 안에 Back 버튼 한번 누르면 종료
    private long backgPressTime = 0; //위 변수의 2초를 측정하는 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);**/

        collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.collapsingToolbarLayout);    //CollapsingToolbarLayout
        collapsingToolbarLayout.setTitle(" ");      //

        viewPager = (ViewPager)findViewById(R.id.viewpager);            //ViewPager
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));      // 어댑터 설정

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);       //TabLayout
        tabLayout.addOnTabSelectedListener(this);       // 리스너
        tabLayout.setupWithViewPager(viewPager);        //setupWithViewPager()를 사용하면 손쉽게 TabLayout와 ViewPager를 연동




    }

    @Override
    public void onBackPressed() {
        //뒤로가기 눌렀을때 처리
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backgPressTime;

        if(0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime){
            super.onBackPressed();
        } else {
            backgPressTime = tempTime;
            Toast.makeText(getApplicationContext(),"뒤로 가기 버튼을 한번 누르면 앱이 종료됩니다.",Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }
    //TabSelect 되었을때,viewPager 에서 특정 페이지로 이동하고 싶은 경우 사용하는 setCurrentItem
    //select 되면 해당 select된 tab 으로 이동할 수 있다.

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {


    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        List<Fragment> fragments = new ArrayList<>();
        String titles[] = new String[]{"Tab1","Tab2","Tab3"};
        //이름1,이름2,이름3

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            fragments.add(new ParsingFragment());   //구현한 Fragment 를 add
            fragments.add(new MyIntroFragment());
            fragments.add(new ProgramIntroFragment());
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }   //Fragment 리턴

        @Override
        public int getCount() {
            return fragments.size();
        }       //Fragment 개수를 리턴

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
            //위의 Tab1,Tab2,Tab3 이 각각 ParsingFragment ~ ProgramIntroFragment 의 제목이 된다
        }
    }
}
