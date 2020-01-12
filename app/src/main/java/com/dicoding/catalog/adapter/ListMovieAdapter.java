package com.dicoding.catalog.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dicoding.catalog.DetailActivity;
import com.dicoding.catalog.R;

import java.util.ArrayList;

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.ListViewHolder> {
    private ArrayList<Movie> listMovie;
    private Activity activity;

    public ListMovieAdapter(Activity activity) {
        this.listMovie = new ArrayList<>();
        this.activity = activity;
    }

    public void setMovieData(ArrayList<Movie> items) {
        if (getItemCount() > 0) {
            this.listMovie.clear();
        }
        this.listMovie.addAll(items);
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie,parent,false);
        return view;
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Movie movie = listMovie.get(position);
        Glide.with(holder.itemView.getContex())
                .load(movie.getPhoto)
                .into(holder.imgPhoto);

        holder.tvTitle.setText(movie.getTitle());
        holder.tvDescription.setText(movie.getDescription());
        holder.tvDate.setText(movie.getDate());

        holder.itemView.setOnClickListener(new CustomOnClickListener(position, new CustomOnClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(activity, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_POSITION, position);
                intent.putExtra(DetailActivity.EXTRA_MOVIE, listMovie.get(position));
                activity.startActivity(intent);
            }
        }));
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle,tvDate, tvDescription;
        ImageView imgPhoto;

        ListViewHolder(@NonNull View itemView){
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvDescription = itemView.findViewById(R.id.tv_description);
            imgPhoto = itemView.findViewById(R.id.img_photo);
        }
    }
}
