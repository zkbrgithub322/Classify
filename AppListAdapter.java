// AppListAdapter.java
package com.example.appverify;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AppListAdapter extends RecyclerView.Adapter<AppListAdapter.ViewHolder> {
    private final List<AppInfo> apps;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView appName;
        public TextView status;

        public ViewHolder(View view) {
            super(view);
            appName = view.findViewById(R.id.app_name);
            status = view.findViewById(R.id.app_status);
        }
    }

    public AppListAdapter(List<AppInfo> apps) {
        this.apps = apps;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_app, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AppInfo app = apps.get(position);
        holder.appName.setText(app.getAppName());
        holder.status.setText(app.isOfficial() ? "Official" : "Suspicious");
        holder.status.setTextColor(app.isOfficial() ? 0xFF00FF00 : 0xFFFF0000);
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }
}