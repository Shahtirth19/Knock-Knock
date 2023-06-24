package com.example.mygate.ui.workers;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mygate.R;
import com.example.mygate.RegisterActivity;
import com.example.mygate.UserHelperClass;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AddWorkers extends Fragment {
    TextInputEditText wRegName,wRegphno,wRegType;
    Button btnAdd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_add_workers, container, false);
        wRegName = root.findViewById(R.id.wName);
        wRegphno = root.findViewById(R.id.wPhno);
        wRegType = root.findViewById(R.id.wType);
        btnAdd = root.findViewById(R.id.btnAddWorker);

        btnAdd.setOnClickListener(view ->{
            createUser();
        });
        return root;
    }

    private void createUser() {
        String name = wRegName.getText().toString();
        String phno = wRegphno.getText().toString();
        String type = wRegType.getText().toString();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Workers");
//        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        WorkerHelperClass workerHelperClass = new WorkerHelperClass(name,phno,type);
        reference.child(phno).setValue(workerHelperClass);
        Toast.makeText(getContext(), "Workers Added successfully", Toast.LENGTH_SHORT).show();
    }
}