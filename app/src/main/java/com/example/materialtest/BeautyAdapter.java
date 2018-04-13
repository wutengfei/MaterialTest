package com.example.materialtest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;

import java.util.List;

public class BeautyAdapter extends RecyclerView.Adapter<BeautyAdapter.ViewHolder> {

    private static final String TAG = "BeautyAdapter";

    private Context mContext;

    private List<Beauty> mFruitList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView fruitImage;
        TextView fruitName;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            fruitName = (TextView) view.findViewById(R.id.fruit_name);
        }
    }

    public BeautyAdapter(List<Beauty> fruitList) {
        mFruitList = fruitList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.fruit_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Beauty beauty = mFruitList.get(position);

        holder.fruitName.setText(beauty.getName());
        BitmapUtils bitmapUtils = new BitmapUtils(mContext);
        bitmapUtils.display(holder.fruitImage, beauty.getUrl());

        holder.fruitImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ImageDetail.class);
                intent.putExtra("imageUrl", beauty.getUrl());
                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }

}
