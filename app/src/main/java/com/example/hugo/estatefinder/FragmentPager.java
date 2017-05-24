package com.example.hugo.estatefinder;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Hugo on 2017-02-19.
 */

public class FragmentPager extends FragmentStatePagerAdapter {
    MainMenu.currentFrags selectedFrag;
    ArrayList<categories> tabHeadings = new ArrayList<categories> ();
    int index = 0 ;
    public FragmentPager(FragmentManager fm,MainMenu.currentFrags type , ArrayList<categories> categories) {
        super(fm);
        this.selectedFrag=type;
        tabHeadings =categories;
    }

    @Override
    // Toggles which fragment to load up , in this case
    // case 0 is always all fragment
    // case 1 to n will be dependent on the type of menu
    public Fragment getItem(int position) {

            this.index = position;
            return ServicesListFragment.newInstance(this.tabHeadings.get(position));
    }

    @Override
    // total size of the pager
    public int getCount() {

        return this.tabHeadings.size();
    }

    @Override
    public void startUpdate(ViewGroup container) {
        super.startUpdate(container);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        super.finishUpdate(container);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return super.isViewFromObject(view, object);
    }

    @Override
    public Parcelable saveState() {
        return super.saveState();
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        super.restoreState(state, loader);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title;

        return this.tabHeadings.get(position).label;
    }
}
