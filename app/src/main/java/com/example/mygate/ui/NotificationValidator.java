package com.example.mygate.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mygate.LoginActivity;
import com.example.mygate.MainActivity;
import com.example.mygate.R;
import com.example.mygate.RegisterActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NotificationValidator extends AppCompatActivity {
    DatabaseReference reference;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_validator);
        alertDialogue();
    }
    private void alertDialogue() {
        reference = FirebaseDatabase.getInstance().getReference("User");
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        AlertDialog.Builder dialog=new AlertDialog.Builder(NotificationValidator.this);
        dialog.setTitle("Visitor Alert");
        dialog.setIcon(R.drawable.alert);
        String text = "You have a Visitor";
        dialog.setMessage(text);
        dialog.setPositiveButton("Approve",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which) {
                        reference.child(uid).child("response").setValue("yes");
                        Toast.makeText(NotificationValidator.this,"Visitor Approved.",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(NotificationValidator.this, MainActivity.class));
                        finish();
                    }
                });
        dialog.setNegativeButton("Disapprove",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                reference.child(uid).child("response").setValue("no");
                Toast.makeText(NotificationValidator.this,"Visitor Rejected.",Toast.LENGTH_LONG).show();
                startActivity(new Intent(NotificationValidator.this, MainActivity.class));
                finish();
            }
        });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }
}