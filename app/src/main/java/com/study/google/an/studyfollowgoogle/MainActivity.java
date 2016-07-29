package com.study.google.an.studyfollowgoogle;

import android.content.Context;
import android.content.Intent;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.study.google.an.studyfollowgoogle.FragmentBasics.LargerFragmentsActivity;


public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private myAdapter mAdapter;
    private ArrayMap mArrayMaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new myAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        getArrayMaps();
        mAdapter.setData(mArrayMaps);
        mAdapter.notifyDataSetChanged();
    }

    private void getArrayMaps() {
        mArrayMaps = new ArrayMap();
        mArrayMaps.put(R.drawable.ic_3d_rotation_blue_a400_18dp, R.string.large_fragments);
    }

    public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {
        private static final String TAG = "myAdapter";
        private Context mContext;
        private ArrayMap mData;


        public void setData(ArrayMap data) {
            mData = data;
        }


        public myAdapter(Context context) {
            mContext = context;
        }


        @Override
        public int getItemCount() {
            return mData == null ? 0 : mData.size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_recycler, parent, false));
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            try {
                Log.i(TAG, mData.keyAt(position) + "");
                holder.mImageView.setImageResource((int) mData.keyAt(position));
                holder.mTextView.setText((int) mData.valueAt(position));

                holder.mImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        switch ((int) mData.valueAt(position)) {
                            case R.string.large_fragments:
                                intent.setClass(mContext, LargerFragmentsActivity.class);
                                break;
                            default:
                                break;
                        }

                        startActivity(intent);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView mImageView;
            TextView mTextView;

            public ViewHolder(View itemView) {
                super(itemView);
                mImageView = (ImageView) itemView.findViewById(R.id.image_view);
                mTextView = (TextView) itemView.findViewById(R.id.text_view);
            }
        }
    }
}
