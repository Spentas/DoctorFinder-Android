package com.spentas.javad.doctorfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spentas.javad.doctorfinder.app.App;
import com.spentas.javad.doctorfinder.model.Location;
import com.spentas.javad.doctorfinder.network.HttpCallback;
import com.spentas.javad.doctorfinder.network.HttpHelper;
import com.spentas.javad.doctorfinder.network.NetworkConfig;
import com.spentas.javad.doctorfinder.util.Utils;

import org.xml.sax.Locator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.autoCompleteTextView)
    AutoCompleteTextView textView;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    private ArrayList<String> mSearchData = new ArrayList<>();
    private ArrayAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        mAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,mSearchData);

        textView.setAdapter(mAdapter);
        textView.setThreshold(1);

        try {
            mHttpHelper.getData(NetworkConfig.LOCATION_ENDPOINT,this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        textView.setOnEditorActionListener(new AutoCompleteTextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Intent intent = new Intent(MainActivity.this,DrListActivity.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
        com.spentas.javad.doctorfinder.util.Logger.i(String.valueOf(Utils.isConnected()));
        if(!Utils.isConnected()) {
            Utils.showDialog(this, "Please turn on your wifi!");
        }
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void httpCallback(String response) {
        Gson gson = new Gson();
        List<Location> list = gson.fromJson(response, new TypeToken<List<Location>>(){}.getType());
        for (Location location: list) {
            mSearchData.add(location.getArea());
        }
        if(mAdapter != null)
        mAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.fab)
    public void search(View view) {
        if (textView.getText().toString().equalsIgnoreCase(""))
            Snackbar.make(view, "No area to search!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        else {
            Intent intent = new Intent(MainActivity.this,DrListActivity.class);
            startActivity(intent);
        }
    }

}
