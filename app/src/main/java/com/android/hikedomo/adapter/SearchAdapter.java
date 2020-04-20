package com.android.hikedomo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.hikedomo.R;
import com.android.hikedomo.entity.Photo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private Picasso picasso;
    private ArrayList<Photo> photoList;
    Unbinder unbinder;

    public SearchAdapter(ArrayList<Photo> photoList, Picasso picasso) {
        super();
        this.picasso = picasso;
        this.photoList = photoList;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        Photo photo = photoList.get(position);
        holder.setData(photo, position);
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.photo)
        public ImageView photoImageView;
        Photo photo;

        ViewHolder(View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
        }

        void setData(final Photo photo, final int position) {
            this.photo = photo;

            if (this.photo.getURL() != null) {
                picasso.load(this.photo.getURL()).into(photoImageView);
            }
        }
    }
}
