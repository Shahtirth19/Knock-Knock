package com.example.mygate.ui.PreApprove;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mygate.R;
import com.example.mygate.ui.workers.WorkerHelperClass;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class schedule extends Fragment {
    TextInputEditText vRegName,vRegMessage;
    Button btnAdd;
    TextView btnDate,disp;
    String date,uid,fn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_schedule, container, false);

        vRegName = root.findViewById(R.id.vName);
        vRegMessage = root.findViewById(R.id.vMessage);
        btnAdd = root.findViewById(R.id.btnScheduleVisit);
        btnDate = root.findViewById(R.id.vDate);
        disp = root.findViewById(R.id.dispBlock);
        getdata();
        Calendar mCalendar = Calendar.getInstance();
        final int year = mCalendar.get(Calendar.YEAR);
        final int month = mCalendar.get(Calendar.MONTH);
        final int day = mCalendar.get(Calendar.DAY_OF_MONTH);

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getActivity(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                            i1 = i1+1;
                            date = i2+"/"+i1+"/"+i;
                            btnDate.setText(date);
                        }
                    },year,month,day);
                datePickerDialog.show();
            }
        });
        btnAdd.setOnClickListener(view ->{
            createUser();
        });

        return root;
    }

    private void getdata() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        String id = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        Query data = reference.orderByChild("email").equalTo(id);
        data.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    fn = ds.child("blockno").getValue(String.class);
                    disp.setText(fn);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void createUser() {
        String name = vRegName.getText().toString();
        String message = vRegMessage.getText().toString();
        long unixTime = System.currentTimeMillis() / 1000L;

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("PreApproved");
//        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        PreHelperClass preHelperClass = new PreHelperClass(name,message,date,fn);
        reference.child(String.valueOf(unixTime)).setValue(preHelperClass);
        Toast.makeText(getContext(), "Visit Scheduled successfully", Toast.LENGTH_SHORT).show();
    }
}