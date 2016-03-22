package zeal.nilesh.zcoer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private WebView myWebView ;
    private ProgressBar progressBar;
//    WebSettings webSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        myWebView = (WebView)findViewById(R.id.webView);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl("http://www.zcoer.in/");

        myWebView.setBackgroundColor(0x00000000);
        myWebView.setHorizontalScrollBarEnabled(false);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setUseWideViewPort(true);
        myWebView.getSettings().setDomStorageEnabled(true);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setMax(100);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

        if (id == R.id.nav_about) {
            // Handle the camera action
            myWebView.loadUrl("http://www.zcoer.in/about-us");
            MainActivity.this.progressBar.setProgress(0);
            myWebView.setBackgroundColor(0x00000000);
            myWebView.setHorizontalScrollBarEnabled(false);
            myWebView.getSettings().setJavaScriptEnabled(true);
            myWebView.getSettings().setUseWideViewPort(true);
            myWebView.getSettings().setDomStorageEnabled(true);

        } else if (id == R.id.nav_academic) {

            myWebView.loadUrl("http://www.zcoer.in/from-hod-desk-computer");
            MainActivity.this.progressBar.setProgress(0);
            myWebView.setBackgroundColor(0x00000000);
            myWebView.setHorizontalScrollBarEnabled(false);
            myWebView.getSettings().setJavaScriptEnabled(true);
            myWebView.getSettings().setUseWideViewPort(true);
            myWebView.getSettings().setDomStorageEnabled(true);

        } else if (id == R.id.nav_infra) {

            myWebView.loadUrl("http://www.zcoer.in/computer-center");
            MainActivity.this.progressBar.setProgress(0);
            myWebView.setBackgroundColor(0x00000000);
            myWebView.setHorizontalScrollBarEnabled(false);
            myWebView.getSettings().setJavaScriptEnabled(true);
            myWebView.getSettings().setUseWideViewPort(true);
            myWebView.getSettings().setDomStorageEnabled(true);

        } else if (id == R.id.nav_tnp) {

            myWebView.loadUrl("http://www.zcoer.in/from-tpo-desk");
            MainActivity.this.progressBar.setProgress(0);
            myWebView.setBackgroundColor(0x00000000);
            myWebView.setHorizontalScrollBarEnabled(false);
            myWebView.getSettings().setJavaScriptEnabled(true);
            myWebView.getSettings().setUseWideViewPort(true);
            myWebView.getSettings().setDomStorageEnabled(true);

        } else if (id == R.id.nav_news) {

            myWebView.loadUrl("http://www.zcoer.in/news-events");
            MainActivity.this.progressBar.setProgress(0);
            myWebView.setBackgroundColor(0x00000000);
            myWebView.setHorizontalScrollBarEnabled(false);
            myWebView.getSettings().setJavaScriptEnabled(true);
            myWebView.getSettings().setUseWideViewPort(true);
            myWebView.getSettings().setDomStorageEnabled(true);

        } else if (id == R.id.nav_contact) {

            myWebView.loadUrl("http://www.zcoer.in/contact-us");
            MainActivity.this.progressBar.setProgress(0);
            myWebView.setBackgroundColor(0x00000000);
            myWebView.setHorizontalScrollBarEnabled(false);
            myWebView.getSettings().setJavaScriptEnabled(true);
            myWebView.getSettings().setUseWideViewPort(true);
            myWebView.getSettings().setDomStorageEnabled(true);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getHost().equals("www.example.com")) {
                // This is my web site, so do not override; let my WebView load the page
                return false;
            }
            // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }
    }
}
