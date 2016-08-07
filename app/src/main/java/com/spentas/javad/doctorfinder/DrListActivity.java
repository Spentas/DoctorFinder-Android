package com.spentas.javad.doctorfinder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spentas.javad.doctorfinder.fragment.DrDetailFragment;
import com.spentas.javad.doctorfinder.model.Person;
import com.spentas.javad.doctorfinder.network.NetworkConfig;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * An activity representing a list of Persons. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link DrDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class DrListActivity extends BaseActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */

    @BindView(R.id.person_list)
    View recyclerView;

    @BindView(R.id.spinner)
    Spinner spinner ;

    private static final int ROW_NO = 10;
    private SimpleItemRecyclerViewAdapter mAdapter;
    private ArrayAdapter<Integer> mSpinnerAdapter;
    private boolean mTwoPane;
    private ArrayList<Person> mData = new ArrayList<>();
    private ArrayList<Integer> mPageNo = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_list);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
        mSpinnerAdapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item,mPageNo);
        mSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());


        try {
            mHttpHelper.getData(NetworkConfig.DATA_ENDPOINT,this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (findViewById(R.id.person_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        mAdapter =  new SimpleItemRecyclerViewAdapter(mData,this);
        recyclerView.setAdapter(mAdapter);
        spinner.setAdapter(mSpinnerAdapter);

    }

    @OnClick(R.id.spinner_arrow)
    public void showDropdown(View view) {
        spinner.performClick();
    }
    @Override
    public void httpCallback(String response) {
        Gson gson = new Gson();
        mData = gson.fromJson(response, new TypeToken<List<Person>>(){}.getType());
        for (int i = 1; i <= mData.size()/10 + 1 ; i++) {
            mPageNo.add(i);
        }
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private int lastPosition = -1;
        private List<Person> mValues;
        private Context mContext;
        public SimpleItemRecyclerViewAdapter(List<Person> items, Activity activity) {
            mValues = items;
            mContext = activity.getBaseContext();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.person_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {

            if(mValues.size()>0) {
                holder.mItem = mValues.get(position);
                holder.mAreaView.setText(mValues.get(position).getArea());
                holder.mContentView.setText(mValues.get(position).getName());
                holder.mSpecialistView.setText(mValues.get(position).getSpeciality());
                holder.mIdrView.setText(String.valueOf(mValues.get(position).getRate()));

                Picasso.with(mContext).load(mValues.get(position).getmPhoto()).resize(650, 650).into(holder.mImageView);
            }

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putInt(DrDetailFragment.ARG_DR_ID, holder.mItem.getId());
                        DrDetailFragment fragment = new DrDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.person_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, DrDetailActivity.class);
                        intent.putExtra(DrDetailFragment.ARG_DR_ID, holder.mItem.getId());

                        context.startActivity(intent);
                    }
                }
            });
            //setAnimation(holder.mContainer, position);

        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public void setmValues(List<Person> persons)
        {
            mValues = persons;
        }

        private void setAnimation(View viewToAnimate, int position)
        {
            // If the bound view wasn't previously displayed on screen, it's animated
            if (position > lastPosition)
            {
                Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
                viewToAnimate.startAnimation(animation);
                lastPosition = position;
            }
        }
        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final ImageView mImageView;
            public final TextView mAreaView;
            public final TextView mContentView;
            public final TextView mSpecialistView;
            public final TextView mIdrView;
            public Person mItem;
            LinearLayout mContainer;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mContainer = (LinearLayout) itemView.findViewById(R.id.item_layout_container);
                mImageView = (ImageView) view.findViewById(R.id.image);
                mAreaView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
                mSpecialistView = (TextView) view.findViewById(R.id.specialist);
                mIdrView = (TextView) view.findViewById(R.id.Idr);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
    public  class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent,
                                   View view, int pos, long id) {


            try {

                List<Person> persons;
                int position = pos + 1;
                persons = position == mPageNo.size() ? mData.subList(position * ROW_NO - ROW_NO - 1, mData.size() ) :
                    mData.subList( position == 1 ? position * ROW_NO - ROW_NO : position * ROW_NO - ROW_NO - 1, position * ROW_NO - 1);


                mAdapter.setmValues(persons);
                mAdapter.notifyDataSetChanged();
                for (Person p : persons) {
                    System.out.println(String.format( "Dr Id : %s",p.getId()));
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        public void onNothingSelected(AdapterView parent) {
            // Do nothing.
        }
    }
}
