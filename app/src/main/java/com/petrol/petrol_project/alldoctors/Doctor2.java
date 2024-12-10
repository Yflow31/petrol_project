package com.petrol.petrol_project.alldoctors;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.petrol.petrol_project.R;
import com.petrol.petrol_project.util.FirebaseConstants;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Doctor2 extends AppCompatActivity {

    ImageView recipeImage;
    TextView tv_doctorName, tv_designation, tv_DescriptionDetail;
    EditText patient_address;

    Button book;
    FirebaseFirestore db;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doctor2);

        // Initialize UI components
        tv_doctorName = findViewById(R.id.tv_doctorName);
        tv_designation = findViewById(R.id.tv_designation);
        patient_address = findViewById(R.id.patient_address);

        tv_DescriptionDetail = findViewById(R.id.tv_DescriptionDetail);
        book = findViewById(R.id.book);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if user is logged in
                if (auth.getCurrentUser() == null) {
                    Toast.makeText(Doctor2.this, "User not logged in", Toast.LENGTH_SHORT).show();
                    return;
                }

                String doctorName = tv_doctorName.getText().toString();
                String designation = tv_designation.getText().toString();
                String addressDetail = patient_address.getText().toString();
                String DescriptionDetail = tv_DescriptionDetail.getText().toString();

                String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                String time = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

                Appointment appointment = new Appointment(doctorName, designation, addressDetail, DescriptionDetail, "",date,time);

                String requestId = db.collection(FirebaseConstants.REQUEST.toString()).document().getId();
                appointment.setRequestid(requestId);

                db.collection(FirebaseConstants.USER_COLLECTION.toString())
                        .document(auth.getCurrentUser().getUid())
                        .collection(FirebaseConstants.REQUEST.toString())
                        .document(requestId)
                        .set(appointment)
                        .addOnSuccessListener(aVoid -> {
                            Toast.makeText(Doctor2.this, "Appointment Booked Successfully", Toast.LENGTH_SHORT).show();
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(Doctor2.this, "Failed to book appointment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            }
        });
    }
}