package com.example.villacarlos.movielibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    Context context;
    Activity activity;
    ArrayList movie_id, movie_title, movie_director, movie_year, movie_summary;
    int position;


    CustomAdapter(Activity activity, Context context, ArrayList movie_id, ArrayList movie_title, ArrayList movie_year, ArrayList movie_director, ArrayList movie_summary){
        this.activity = activity;
        this.context = context;
        this.movie_id = movie_id;
        this.movie_title = movie_title;
        this.movie_year = movie_year;
        this.movie_director = movie_director;
        this.movie_summary = movie_summary;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        this.position = position;
        holder.txId.setText(String.valueOf(movie_id.get(position)));
        holder.txTitle.setText(String.valueOf(movie_title.get(position)));
        holder.txYear.setText(String.valueOf(movie_year.get(position)));
        holder.txDirector.setText(String.valueOf(movie_director.get(position)));
        holder.txSummary.setText(String.valueOf(movie_summary.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,UpdateActivity.class);
                intent.putExtra("id",String.valueOf(movie_id.get(position)));
                intent.putExtra("title",String.valueOf(movie_title.get(position)));
                intent.putExtra("year",String.valueOf(movie_year.get(position)));
                intent.putExtra("director",String.valueOf(movie_director.get(position)));
                intent.putExtra("summary",String.valueOf(movie_summary.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movie_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txId, txTitle, txDirector, txYear, txSummary;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txId = itemView.findViewById(R.id.tvMovieID);
            txTitle = itemView.findViewById(R.id.tvMovieTitle);
            txYear = itemView.findViewById(R.id.tvYear);
            txDirector = itemView.findViewById(R.id.tvDirector);
            txSummary = itemView.findViewById(R.id.tvSummary);
            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }
}
