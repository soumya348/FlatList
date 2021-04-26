package com.soumya.flatlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FlatListAdapter extends RecyclerView.Adapter<FlatListAdapter.viewHolder> {

    ArrayList<FlatList> lists;
    final Context context;

    public FlatListAdapter( ArrayList<FlatList> lists,Context context) {
        this.lists =lists;
        this.context = context;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.flat_list, parent, false);
        return new FlatListAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        final FlatList list=lists.get(position);
        holder.count.setText(list.getCount());
        holder.name.setText(list.getTitle()+" "+list.getFirst()+" "+list.getLast());
    }

    @Override
    public int getItemCount() {
        return lists.size() ;
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView count;
        TextView name;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            count=itemView.findViewById(R.id.count);
            name=itemView.findViewById(R.id.name);
        }
    }
}
