package com.petrol.petrol_project.users;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.petrol.petrol_project.R;

import java.util.List;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private Context context;
    private List<DoctorPage> itemList;

    // Constructor
    public ItemAdapter(Context context, List<DoctorPage> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    // ViewHolder class
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView,textView2;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.main_page_item_text);
            imageView = itemView.findViewById(R.id.main_page_item_image);
            textView2 = itemView.findViewById(R.id.designation);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_of_each_doctor, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DoctorPage currentItem = itemList.get(position);
        holder.textView.setText(currentItem.getText());
        holder.imageView.setImageResource(currentItem.getImageResourceId());
        holder.textView2.setText(currentItem.getDesignation());

        // Set click listener for the item to start a new activity
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, currentItem.getActivity());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
