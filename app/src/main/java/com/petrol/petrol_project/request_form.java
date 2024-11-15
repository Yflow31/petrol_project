package com.petrol.petrol_project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.petrol.petrol_project.form.Form;
import com.petrol.petrol_project.util.FirebaseConstants;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class request_form extends AppCompatActivity {

    // EditText fields for form input
    EditText name_txt, email_txt, phone_txt, edit_text_symptoms_description, edit_text_street_address,
            edit_text_city, edit_text_state, edit_text_pincode, add_notes_txt;

    // RadioButton fields for gender selection
    RadioButton radio_button_male, radio_button_female;

    // Button for submission
    Button submit_btn;

    // Firebase instances
    FirebaseFirestore db;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_form);

        // Initialize Firebase instances
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        // EditText components for form input
        name_txt = findViewById(R.id.edit_text_full_name);
        email_txt = findViewById(R.id.edit_text_email);
        phone_txt = findViewById(R.id.edit_text_phone);
        edit_text_symptoms_description = findViewById(R.id.edit_text_symptoms_description);
        edit_text_street_address = findViewById(R.id.edit_text_street_address);
        edit_text_city = findViewById(R.id.edit_text_city);
        edit_text_state = findViewById(R.id.edit_text_state);
        edit_text_pincode = findViewById(R.id.edit_text_pincode);
        add_notes_txt = findViewById(R.id.edit_text_special_instructions);

        // RadioButton components for gender selection
        radio_button_male = findViewById(R.id.radio_button_male);
        radio_button_female = findViewById(R.id.radio_button_female);

        // Submission button
        submit_btn = findViewById(R.id.button_submit);

        // Handle submit button click event
        submit_btn.setOnClickListener(v -> {
            FirebaseUser currentUser = auth.getCurrentUser();
            if (currentUser != null) {
                String uid = currentUser.getUid();

                // Retrieve values from form
                String fullName = name_txt.getText().toString();
                String email = email_txt.getText().toString();
                String phone = phone_txt.getText().toString();
                String streetAddress = edit_text_street_address.getText().toString();
                String city = edit_text_city.getText().toString();
                String state = edit_text_state.getText().toString();
                String pincode = edit_text_pincode.getText().toString();
                String additionalNotes = add_notes_txt.getText().toString();

                String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                String time = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

                // Form validation
                if (TextUtils.isEmpty(fullName) || TextUtils.isEmpty(email) || TextUtils.isEmpty(phone) ||
                        TextUtils.isEmpty(streetAddress) || TextUtils.isEmpty(city) || TextUtils.isEmpty(state) ||
                        TextUtils.isEmpty(pincode)) {
                    Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Create form object
                Form form = new Form("Patient Type", fullName, email, phone, streetAddress, city, state, pincode, additionalNotes, date, time);

                // Generate request ID
                String requestId = db.collection(FirebaseConstants.REQUEST.toString()).document().getId();
                form.setRequestId(requestId);

                // Store form data in Firestore
                db.collection(FirebaseConstants.USER_COLLECTION.toString()).document(uid).collection(FirebaseConstants.REQUEST.toString()).document(requestId)
                        .set(form)
                        .addOnSuccessListener(documentReference -> Toast.makeText(this, "Form Submitted Successfully", Toast.LENGTH_SHORT).show())
                        .addOnFailureListener(e -> {
                            Log.d("check", e.getMessage());
                            Toast.makeText(this, "Failed to Submit Form: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            } else {
                // Redirect to MainActivity if user is not logged in
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
