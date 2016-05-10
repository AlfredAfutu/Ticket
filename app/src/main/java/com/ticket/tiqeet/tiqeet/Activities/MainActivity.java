package com.ticket.tiqeet.Activities;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
///import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.ticket.tiqeet.Adapters.EventsAdapter;
import com.ticket.tiqeet.R;

import java.util.ArrayList;

/**
 * Created by cted on 4/28/15.
 */
public class MainActivity extends FragmentActivity {

    FragmentManager fragmentManager;
    private DrawerLayout drawerLayout;
    private FrameLayout frameLayout;
    private ViewPager viewPager;
    private PagerTabStrip pagerTitleStrip;
    private ListView listView;
    private ArrayAdapter<CharSequence> listAdapter;
    private ArrayList<CharSequence> listItems = new ArrayList<>();
    int drawerStateCounter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_categories);

        ActionBar actionBar = getActionBar();
        actionBar.show();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        frameLayout = (FrameLayout) findViewById(R.id.content_frame);
        viewPager = (ViewPager) findViewById(R.id.categories_slide_pager);
        pagerTitleStrip = (PagerTabStrip) findViewById(R.id.pagerTitle);
        listView = (ListView) findViewById(R.id.left_drawer);

        pagerTitleStrip.setTabIndicatorColor(Color.parseColor("#D04C11"));

        fragmentManager = getSupportFragmentManager();

        viewPager.setAdapter(new EventsAdapter(fragmentManager, this));
        viewPager.setCurrentItem(1);

        listItems.add("Discover Events");
        listItems.add("Tickets");
        listItems.add("Settings");


        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(listAdapter);

        final int abTitleId = getResources().getIdentifier("action_bar_title", "id", "android");
        findViewById(abTitleId).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                drawerStateCounter ++;

                if(drawerStateCounter == 1) {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }else{
                    drawerStateCounter = 0;
                    drawerLayout.closeDrawer(Gravity.LEFT);
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });







    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.event_categories, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
