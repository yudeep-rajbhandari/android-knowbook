package com.example.user.volleyjson;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by USER on 2/6/2017.
 */

public class TabAdapter extends FragmentPagerAdapter {

    private String[] tabtitle=new String[]{"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday"};

    public String[] getTabtitle() {
        return tabtitle;
    }

    public void setTabtitle(String[] tabtitle) {
        this.tabtitle = tabtitle;
    }

    Context context;
    private int pagenumber=6;
    public TabAdapter(FragmentManager fm, RoutineAdapter routineAdapter, Context context) {
        super(fm);
        this.context=context;
    }

    @Override
    public Fragment getItem(int position) {

switch (position){
    case 0:
        Sunday tab1=new Sunday();
        return tab1;
    case 1:
        Monday tab2=new Monday();
        return tab2;
    case 2:
        Tuesday tab3=new Tuesday();
        return tab3;
    case 3:
        Wednesday tab4=new Wednesday();
        return tab4;
    case 4:
        Thursday tab5=new Thursday();
        return tab5;
    case 5:
        Friday tab6=new Friday();
        return  tab6;
    default:
        Emptyclass tab7=new Emptyclass();
        return  tab7;


}
    }

    @Override
    public int getCount() {
        return pagenumber;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabtitle[position];
    }
}

