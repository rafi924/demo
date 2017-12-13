package com.huzaif.admi.demo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {
    int i = R.drawable.google;
    int j = R.drawable.bing;
    WebView tech2;
    Menu m;
    List<String> top;
    ProgressBar progress;
    String data = "https://mobile.twitter.com";
    int t = R.drawable.ic_menu_send;
    ImageView pager;
    ExpandableListView expListView;
    HashMap listDataChild;
    ArrayList<String> listDataHeader;
    int[] icons = {R.drawable.google, R.drawable.bing, R.drawable.fb, R.drawable.inta, R.drawable.twit, R.drawable.engwave,
            R.drawable.indeed, R.drawable.link, R.drawable.naukri
            , R.drawable.stack, R.drawable.quora, R.drawable.toi,
            R.drawable.bbc, R.drawable.ndtv, R.drawable.logo, R.drawable.tpoint, R.drawable.y4u, R.drawable.gfg};
    int k = 0;
    TabLayout tabLayout;
    int list = 4;
    /**
     * int list=4
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    android.support.v4.widget.DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //  setSupportActionBar(toolbar);
        // setStatusBarColor(findViewById(R.id.statusBarBackground),getResources().getColor(R.colors.));
        tech2 = (WebView) findViewById(R.id.tech2);
        pager = (ImageView) findViewById(R.id.pager);
        pager.setImageResource(R.drawable.fb);
        progress = (ProgressBar) findViewById(R.id.progressBar);
        WebSettings setting = tech2.getSettings();
        tech2.setWebViewClient(new myweb());
        setting.setJavaScriptEnabled(true);
        tech2.getSettings().setDomStorageEnabled(true);
        //  Bundle extras = getIntent().getExtras();
        // Intent i = getIntent();
        // String s=i.getStringExtra("http://stackoverflow.com/questions/8017374/how-to-pass-a-uri-to-an-intent");
        tech2.loadUrl(data);


        drawer = (android.support.v4.widget.DrawerLayout) findViewById(R.id.drawer_layout);


        listDataHeader = new ArrayList<String>();

        listDataChild = new HashMap<String, List<String>>();
        expListView = (ExpandableListView) findViewById(R.id.left_drawer);

        prepareListData(listDataHeader, listDataChild);
        final ExpandListAdapter listAdapter = new ExpandListAdapter(this, listDataHeader, listDataChild);
        // setting list adapter
        expListView.setAdapter(listAdapter);

        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                Toast.makeText(getApplicationContext(),
                        "Group Clicked " + listDataHeader.get(groupPosition),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                //top/ Temporary code:
                //top.get(childPosition);
                int cp = (int) listAdapter.getChildId(groupPosition, childPosition);
                if (groupPosition == 1) {
                    // till here
                    //String s=top.get(childPosition);
                    listDataHeader.get(childPosition);
                    if (cp == 1) {
                        //Toast.makeText(getApplicationContext(), "i am done", Toast.LENGTH_SHORT).show();
                        drawer.closeDrawer(android.support.v4.view.GravityCompat.START);
                        pager.setImageResource(R.drawable.twit);

                        WebSettings setting = tech2.getSettings();
                        tech2.setWebViewClient(new myweb());
                        tech2.setWebViewClient(new myweb());
                        data = "https://m.facebook.com";
                        tech2.loadUrl(data);


                    }

                }
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : " + top.get(childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });

        tabLayout = (TabLayout) findViewById(R.id.tab);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        pager = (ImageView) headerView.findViewById(R.id.imageView);
        pager.setImageResource(R.drawable.quora);
        tabLayout.post(new Runnable() {
            @Override

            public void run() {
                int tabLayoutWidth = tabLayout.getWidth();
                tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.twit));
                tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.fb));
                tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.inta));
                tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.toi));
                tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ndtv));
                tabLayout.addTab(tabLayout.newTab().setIcon(t));
                tabLayout.addTab(tabLayout.newTab().setIcon(i));
                tabLayout.addTab(tabLayout.newTab().setIcon(j));
                tabLayout.addTab(tabLayout.newTab().setIcon(t));
                tabLayout.addTab(tabLayout.newTab().setIcon(i));
                tabLayout.addTab(tabLayout.newTab().setIcon(j));
                tabLayout.addTab(tabLayout.newTab().setIcon(t));
                tabLayout.addTab(tabLayout.newTab().setIcon(i));
                tabLayout.addTab(tabLayout.newTab().setIcon(j));
                tabLayout.addTab(tabLayout.newTab().setIcon(t));
                tabLayout.addTab(tabLayout.newTab().setIcon(i));
                tabLayout.addTab(tabLayout.newTab().setIcon(j));

                tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        if (tabLayout.getSelectedTabPosition() == 0) {
                            ////  data="https://m.facebook.com";
                            //  tech2.loadUrl(data);
                        } else if (tabLayout.getSelectedTabPosition() == 1) {
                            data = "https://m.facebook.com";
                            tech2.loadUrl(data);
                        } else if (tabLayout.getSelectedTabPosition() == 2) {
                            Toast.makeText(MainActivity.this, "Tab " + tabLayout.getSelectedTabPosition(), Toast.LENGTH_LONG).show();
                        } else if (tabLayout.getSelectedTabPosition() == 3) {
                            Toast.makeText(MainActivity.this, "Tab " + tabLayout.getSelectedTabPosition(), Toast.LENGTH_LONG).show();
                        } else if (tabLayout.getSelectedTabPosition() == 4) {
                            Toast.makeText(MainActivity.this, "Tab " + tabLayout.getSelectedTabPosition(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });


                DisplayMetrics metrics = new DisplayMetrics();
                MainActivity.this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
                int deviceWidth = metrics.widthPixels;

                if (tabLayoutWidth < deviceWidth) {
                    tabLayout.setTabMode(TabLayout.MODE_FIXED);
                    tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
                } else {
                    tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
                    tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
                }
            }
        });


        android.support.v4.widget.DrawerLayout drawer = (android.support.v4.widget.DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        m = navigationView.getMenu();
        navigationView.setNavigationItemSelectedListener(this);

    }
        @Override
        public void onBackPressed () {
            android.support.v4.widget.DrawerLayout drawer = (android.support.v4.widget.DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(android.support.v4.view.GravityCompat.START)) {
                drawer.closeDrawer(android.support.v4.view.GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }

        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {

                m.findItem(R.id.bing).setVisible(true);
                m.findItem(R.id.google).setVisible(true);

                return true;
            }

            return super.onOptionsItemSelected(item);
        }

        @SuppressWarnings("StatementWithEmptyBody")
        @Override
        public boolean onNavigationItemSelected (MenuItem item){
            // Handle navigation view item clicks here.
            int id = item.getItemId();

   /*    if (id == R.id.nav_camera) {

           final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
           try {
               startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
           } catch (android.content.ActivityNotFoundException anfe) {
               startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
           }
           // Handle the camera action
       } else if (id == R.id.nav_gallery) {


       } else if (id == R.id.nav_slideshow) {
           Intent sendIntent = new Intent();
           sendIntent.setAction(Intent.ACTION_SEND);
           sendIntent.putExtra(Intent.EXTRA_TEXT,
                   "Hey check out my app at: https://play.google.com/store/apps/details?id=package sevenieslab.interviewgo");
           sendIntent.setType("text/plain");
           startActivity(sendIntent);
       } else if (id == R.id.nav_manage) {
          Intent sendIntent = new Intent(Intent.ACTION_VIEW);
           sendIntent.setType("plain/text");
           sendIntent.setData(Uri.parse("rafiuddin924@gmail.com"));
           sendIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
          sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { "huzaifhammad2@gmail.com" });
           sendIntent.putExtra(Intent.EXTRA_SUBJECT, "test");
           sendIntent.putExtra(Intent.EXTRA_TEXT, "hello. this is a message sent from my demo app :-)");
           startActivity(sendIntent);
          Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                   "mailto", "abc@gmail.com", null));
           emailIntent.putExtra(Intent.EXTRA_SUBJECT, "This is my subject text");
           getApplicationContext().startActivity(Intent.createChooser(emailIntent, null));
           i.setType("message/rfc822");
           i.putExtra(Intent.EXTRA_EMAIL, new String[]{"recipient@example.com"});
           i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
           i.putExtra(Intent.EXTRA_TEXT, "body of email");
           try {
               startActivity(Intent.createChooser(i, "Send mail..."));
           } catch (android.content.ActivityNotFoundException ex) {
               Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
         }


       else if (id == R.id.nav_manage1) {
           boolean b=!m.findItem(R.id.bing).isVisible();

           m.findItem(R.id.bing).setVisible(b);
           m.findItem(R.id.google).setVisible(b);

       } else if (id == R.id.nav_manage2) {

   */

            drawer = (android.support.v4.widget.DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(android.support.v4.view.GravityCompat.START);
            return true;

        }

    public class myweb extends WebViewClient {
        public myweb() {
            super();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // return super.shouldOverrideUrlLoading(view, url);
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            MainActivity.this.progress.setProgress(0);
            super.onPageStarted(view, url, favicon);

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
       //     progress.setVisibility(View.INVISIBLE);
     //       pager.setVisibility(View.INVISIBLE);
            // progress.setVisibility(View.GONE);
            MainActivity.this.progress.setProgress(100);
            tech2.setVisibility(View.VISIBLE);

        }

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
        }


        @Override
        public void onPageCommitVisible(WebView view, String url) {
            super.onPageCommitVisible(view, url);
        }

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //return super.onKeyDown(keyCode, event);
        if ((keyCode == KeyEvent.KEYCODE_BACK) && tech2.canGoBack()) {
            tech2.goBack();

        } else {
            MainActivity.super.onBackPressed();
        }
        return true;
    }



    private void prepareListData(List<String> listDataHeader, Map<String,
                    List<String>> listDataChild) {


        // Adding child data
        listDataHeader.add("Social sites");
        listDataHeader.add("News");
        listDataHeader.add("Jobs");
        listDataHeader.add("Education");

        // Adding child data
         top = new ArrayList<String>();
        top.add("Facebook");
        top.add("Instagram");
        top.add("Twitter");


        List<String> mid = new ArrayList<String>();
        mid.add("Times of India");
        mid.add("BBC");
        mid.add("NDTV");

        List<String> bottom = new ArrayList<String>();
        bottom.add("Engg Wave");
        bottom.add("Naukri.com");
        bottom.add("Indeed");
        bottom.add("MOnster");
        bottom.add("z5");
        bottom.add("z6");



        listDataChild.put(listDataHeader.get(0), top); // Header, Child data
        listDataChild.put(listDataHeader.get(1), mid);
        listDataChild.put(listDataHeader.get(2), bottom);
        //listDataChild.put(listDataHeader.get(3), bottom);

    }

    }


