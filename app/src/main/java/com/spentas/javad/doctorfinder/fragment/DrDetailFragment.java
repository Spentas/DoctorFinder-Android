package com.spentas.javad.doctorfinder.fragment;

import android.app.Activity;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spentas.javad.doctorfinder.DrDetailActivity;
import com.spentas.javad.doctorfinder.DrListActivity;
import com.spentas.javad.doctorfinder.R;
import com.spentas.javad.doctorfinder.app.App;
import com.spentas.javad.doctorfinder.dummy.DummyContent;
import com.spentas.javad.doctorfinder.model.DrProfile;
import com.spentas.javad.doctorfinder.model.Location;
import com.spentas.javad.doctorfinder.network.HttpCallback;
import com.spentas.javad.doctorfinder.network.HttpHelper;
import com.spentas.javad.doctorfinder.network.NetworkConfig;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * A fragment representing a single Person detail screen.
 * This fragment is either contained in a {@link DrListActivity}
 * in two-pane mode (on tablets) or a {@link DrDetailActivity}
 * on handsets.
 */
public class DrDetailFragment extends Fragment  {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */



    public static final String ARG_DR_ID = "item_id";

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DrDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((App) App.getContext()).getComponent().inject(this);

        if (getArguments().containsKey(ARG_DR_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.person_detail, container, false);

        // Show the dummy content as text in a TextView.


        return rootView;
    }


}
