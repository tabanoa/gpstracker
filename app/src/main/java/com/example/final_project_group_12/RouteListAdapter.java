package com.example.final_project_group_12;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
// This code handles the list shown at the route history page.
public class RouteListAdapter extends RecyclerView.Adapter<RouteListAdapter.RouteViewHolder> {

    class RouteViewHolder extends RecyclerView.ViewHolder {
        private final TextView routeItemView;

        private RouteViewHolder(View itemView) {
            super(itemView);
            // Set id of the list you wish to populate!
            routeItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Routes> mRoutes;

    RouteListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public RouteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new RouteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RouteViewHolder holder, int position) {
        if (mRoutes != null) {
            Routes current = mRoutes.get(position);
            // What text to be set.
            holder.routeItemView.setText(current.getName());
        } else {
            // Covers the case of data not being ready yet.
            holder.routeItemView.setText("No Route");
        }
    }

    void setRoutes(List<Routes> routes){
        mRoutes = routes;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mRoutes has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mRoutes != null)
            return mRoutes.size();
        else return 0;
    }
}
