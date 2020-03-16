package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.drawer.WearableActionDrawer;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ActionMenuView;
import android.widget.TextView;
import android.graphics.drawable.Drawable;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.wear.ambient.AmbientModeSupport;
import androidx.wear.widget.drawer.WearableActionDrawerView;
import androidx.wear.widget.drawer.WearableDrawerLayout;
import androidx.wear.widget.drawer.WearableNavigationDrawerView;
import androidx.wear.widget.drawer.WearableNavigationDrawerView.WearableNavigationDrawerAdapter;

import java.util.ArrayList;

public class MainActivity extends Activity implements
        MenuItem.OnMenuItemClickListener {
    private WearableDrawerLayout wearableDrawerLayout;
    private WearableNavigationDrawerView wearableNavigationDrawer;
    private WearableActionDrawerView wearableActionDrawer;

    private TextView mTextView;

    private ArrayList<Navigation> mNavigation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNavigation = initialiseMenu();
        // Top navigation drawer
        wearableNavigationDrawer = (WearableNavigationDrawerView) findViewById(R.id.top_drawer);
        wearableNavigationDrawer.setAdapter(new NavigationAdapter(this));
        // Peeks navigation drawer on the top.
        wearableNavigationDrawer.getController().peekDrawer();
        // Bottom action drawer
        wearableActionDrawer = (WearableActionDrawerView) findViewById(R.id.bottom_drawer);
        // Peeks action drawer on the bottom.
        wearableActionDrawer.getController().peekDrawer();
        wearableActionDrawer.setOnMenuItemClickListener(this);
    }

    private ArrayList<Navigation> initialiseMenu() {
        ArrayList<Navigation> navigation = new ArrayList<>();
        String[] navigationNames = getResources().getStringArray(R.array.navigation_menu);

        for(String item: navigationNames) {
            int value = getResources().getIdentifier(item, "array", getPackageName());
            String[] navigationInfo = getResources().getStringArray(value);

            navigation.add(new Navigation(navigationInfo[0],
                    navigationInfo[1]));
        }
        return navigation;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    private class NavigationAdapter extends WearableNavigationDrawerAdapter {

        private final MainActivity activity;

        public NavigationAdapter(MainActivity mainActivity) {
            activity = mainActivity;
        }

        @Override
        public CharSequence getItemText(int pos) {
            return mNavigation.get(pos).getNavigationName();
        }

        @Override
        public Drawable getItemDrawable(int pos) {
            String navigationIcon = mNavigation.get(pos).getNavigationIcon();
            int iconID = getResources().getIdentifier(navigationIcon, "drawable", getPackageName());
            return activity.getDrawable(iconID);
        }

        @Override
        public int getCount() {
            return mNavigation.size();
        }
    }
}