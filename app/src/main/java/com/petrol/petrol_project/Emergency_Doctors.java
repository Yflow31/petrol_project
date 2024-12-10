package com.petrol.petrol_project;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.petrol.petrol_project.alldoctors.Doctor1;
import com.petrol.petrol_project.alldoctors.Doctor2;
import com.petrol.petrol_project.alldoctors.Doctor3;
import com.petrol.petrol_project.alldoctors.Doctor4;
import com.petrol.petrol_project.alldoctors.Doctor5;
import com.petrol.petrol_project.users.DoctorPage;
import com.petrol.petrol_project.users.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class Emergency_Doctors extends AppCompatActivity {

    RecyclerView rv_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_emergency_doctors);

        rv_item = findViewById(R.id.rv_item);

        rv_item.setLayoutManager(new LinearLayoutManager(this));

        List<DoctorPage> itemList = new ArrayList<>();
        itemList.add(new DoctorPage("Dr. Dinesh Rajpal","General Practitioner offering a wide range of services", Doctor1.class, R.drawable.doctor1));
        itemList.add(new DoctorPage("Dr. Pallavi Patekar","General Medicine specialist with 12 years of experience", Doctor2.class, R.drawable.doctor_2));
        itemList.add(new DoctorPage("Dr. Ravindra Mishra","General Physician with 14 years of experience", Doctor3.class, R.drawable.doctor3));
        itemList.add(new DoctorPage("Dr. Hitesh Patel","General Physician with 19 years of experience", Doctor4.class, R.drawable.doctor4));
        itemList.add(new DoctorPage("Dr. Shamim Ahmed Khan","Seasoned General Physician offering comprehensive medical services in Mumbai", Doctor5.class, R.drawable.doctor5));

        ItemAdapter adapter = new ItemAdapter(this, itemList);
        rv_item.setAdapter(adapter);
    }
}