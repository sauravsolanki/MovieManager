package com.example.hp.moviemanager.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.moviemanager.R;
import com.example.hp.moviemanager.activities.MovieDetailActivity;
import com.example.hp.moviemanager.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hp on 24-02-2018.
 */

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder> {
    List<Movie> movies;
    Context context;


    public MovieRecyclerViewAdapter(List<Movie> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies,parent,false);
        return new ViewHolder(view);
    }

    public Context getContext() {
        return context;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       Movie movie=movies.get(position);
       holder.tvoverview.setText(movie.getOverview());
       holder.tvtitle.setText(movie.getTitle());

        Picasso.with(getContext())
                .load(movie.getPosterPath())
                .into(holder.ivmovieimage);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

          @BindView(R.id.ivMovieImage)ImageView ivmovieimage;
          @BindView(R.id.tvTitle)TextView tvtitle;
          @BindView(R.id.tvOverViw)TextView tvoverview;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Movie movie =movies.get(getAdapterPosition());
            Intent intent = new Intent(getContext(), MovieDetailActivity.class);
            intent.putExtra("Movie", movie);
            getContext().startActivity(intent);
        }
    }
}
