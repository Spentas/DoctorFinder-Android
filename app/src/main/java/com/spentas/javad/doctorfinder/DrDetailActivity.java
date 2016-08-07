package com.spentas.javad.doctorfinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.spentas.javad.doctorfinder.model.DrProfile;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * An activity representing a single Person detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link DrListActivity}.
 */
public class DrDetailActivity extends BaseActivity {


    @BindView(R.id.recommendation)
    TextView recommendation;

    @BindView(R.id.schedule)
    TextView schedule;

    @BindView(R.id.rate)
    TextView rate;

    @BindView(R.id.specialist)
    TextView specialist;

    @BindView(R.id.area)
    TextView area;

    @BindView(R.id.experience)
    TextView experience;

    @BindView(R.id.desc)
    TextView desc;

    @BindView(R.id.profile_img)
    ImageView img;

    private CollapsingToolbarLayout appBarLayout;

    private DrProfile mDrProfile;

    private GoogleMap googleMap;

    public static final String ARG_DR_ID = "item_id";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "We will contact you soon!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().
                    findFragmentById(R.id.map)).getMap();
        }

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        appBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        appBarLayout.setExpandedTitleTextAppearance(R.style.CollapsedAppBar);



        // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.

            try {
                mHttpHelper.getDataById(getIntent().getIntExtra(ARG_DR_ID,1),this);
            } catch (Exception e) {
                e.printStackTrace();
            }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, new Intent(this, DrListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setLocation(double lat, double lon, String title){
        LatLng location = new LatLng(lat , lon);
        System.out.println(lon);
        System.out.println(lat);

        try {

            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,15));
            googleMap.animateCamera(CameraUpdateFactory.zoomIn());
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void httpCallback(String response) {
        try {
            Gson gson = new Gson();
            mDrProfile = gson.fromJson(response, DrProfile.class);
            if(mDrProfile !=null)
            if (appBarLayout != null) {
                System.out.println(mDrProfile.getRecommendation());
                appBarLayout.setTitle(mDrProfile.getName());
                recommendation.setText(recommendation.getText().toString() + String.valueOf(mDrProfile.getRecommendation()));
                schedule.setText(schedule.getText() + mDrProfile.getSchedule());
                area.setText(mDrProfile.getArea());
                specialist.setText(mDrProfile.getSpeciality());
                rate.setText(rate.getText() + String.valueOf(mDrProfile.getRate()));
                experience.setText(experience.getText() + String.valueOf(mDrProfile.getExperience()) + " years");
                desc.setText(mDrProfile.getDescription());
                //setLocation(mDrProfile.getmLatitude(), mDrProfile.getmLongitude(), mDrProfile.getName());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Picasso.with(getBaseContext()).load(mDrProfile.getmPhoto()).into(img);
                        googleMap.addMarker(new MarkerOptions().
                                position(new LatLng(mDrProfile.getmLatitude(), mDrProfile.getmLongitude())).title(mDrProfile.getName()));
                        CameraPosition cameraPosition = new CameraPosition.Builder()
                                .target(new LatLng(mDrProfile.getmLatitude(), mDrProfile.getmLongitude()))
                                .zoom(17).build();
                        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                    }
                });

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
