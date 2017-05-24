package com.example.hugo.estatefinder;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import java.util.List;

public class MainMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    FrameLayout currentFrame;
    Menu menuOptions;


    public static enum currentFrags {
            MAIN_MENU,
            FAVS,
            ESTATE,
            RENOVATION,
            BROWSE
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        this.currentFrame = (FrameLayout) findViewById(R.id.current_fragment);
        this.startFragment(null);

    }



     private void startFragment ( String tag) {
         String fragmentTag ;
         FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
         if(tag == null ) {
             fragmentTag = getResources().getString(R.string.main_menu_fragment);
             ft.replace(this.currentFrame.getId(),new MainMenuFragment(),tag);
             ft.addToBackStack(tag);
             ft.commit();
             return;
         }else{
             fragmentTag = tag;
         }

         Fragment nextFrag ;
         switch(currentFrags.valueOf(tag)){

             case MAIN_MENU:
                 nextFrag = new MainMenuFragment();
                 ft.replace(this.currentFrame.getId(),new MainMenuFragment(),tag);
                 ft.addToBackStack(tag);
                 ft.commit();

                 break;
             case ESTATE:
                 System.out.println("Going to estate page");
                 ft.replace(this.currentFrame.getId(),ServicesFragment.newInstance(currentFrags.valueOf(tag)),tag);
                 ft.addToBackStack(tag);
                 ft.commit();
                 break;
             case RENOVATION:
                 System.out.println("Going to Renovation page");
                 ft.replace(this.currentFrame.getId(),ServicesFragment.newInstance(currentFrags.valueOf(tag)),tag);
                 ft.addToBackStack(tag);
                 ft.commit();
                 break;
             case FAVS:
                 System.out.println("Going to Favoritse page");
                 ft.replace(this.currentFrame.getId(), DataListFragment.newInstance("Favorite"));
                 ft.addToBackStack(tag);
                 ft.commit();
                 break;
             case BROWSE:
                 System.out.println("Going to Browse ALl page");
                 ft.replace(this.currentFrame.getId(),DataListFragment.newInstance("BROWSE"));
                 ft.addToBackStack(tag);
                 ft.commit();
                 break;


         }



     }
     // Accessed by oither fragments
     public  void LoadFragment (String tag){
         this.startFragment(tag);
     }


    @Override
    public void onBackPressed() {


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            FragmentManager fmpop = getSupportFragmentManager();
            List<Fragment> listOfFragments = fmpop.getFragments();
            int currentFragmentIndex = fmpop.getBackStackEntryCount() -1;
            Fragment currentFragment = listOfFragments.get(currentFragmentIndex);
            if (currentFragment != null && currentFragment.getClass() == ServicesFragment.class){
                ServicesFragment serviceTabs = (ServicesFragment)currentFragment;
                 if (serviceTabs.jumpToTab()) {
                     return;
                 }
            }
           // if currentFraggmentName is that of ServicesFragment, call its jumpToTab
            //System.out.println(currentFragmentName);


            super.onBackPressed();}



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        String targetFragment = "";
        if (id == R.id.nav_home) {
            targetFragment=getResources().getString(R.string.main_menu_fragment);
            // Handle the camera action
        } else if (id == R.id.nav_Estate) {
            targetFragment=getResources().getString(R.string.estate_menu_fragment);

        } else if (id == R.id.nav_Renovations) {
            targetFragment=getResources().getString(R.string.renovation_menu_fragment);

        } else if (id == R.id.nav_favorites) {
            targetFragment=getResources().getString(R.string.favs_menu_fragment);

        } else if (id == R.id.nav_All) {
            targetFragment=getResources().getString(R.string.browse_menu_fragment);

        }
        this.LoadFragment(targetFragment);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
