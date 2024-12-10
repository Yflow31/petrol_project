package com.petrol.petrol_project.output;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.petrol.petrol_project.R;
import com.petrol.petrol_project.alldoctors.Appointment;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    ArrayList<Appointment> arrayList;

    Context context;

    public RecyclerAdapter(ArrayList<Appointment> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_output_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        Appointment output = arrayList.get(position);
        holder.patient_name.setText(output.getDoctorname());
        holder.cdtime.setText(output.getTime());
        holder.cddate.setText(output.getDate());
        holder.patient_address.setText(output.getAddressDetail());
        holder.add_notes_txt.setText("Emergency");
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView patient_name, cdtime, cddate, patient_address, add_notes_txt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            patient_name = itemView.findViewById(R.id.patient_name);
            cdtime = itemView.findViewById(R.id.cdtime);
            cddate = itemView.findViewById(R.id.cddate);
            patient_address = itemView.findViewById(R.id.patient_address);
            add_notes_txt = itemView.findViewById(R.id.add_notes_txt);

        }
    }
}
