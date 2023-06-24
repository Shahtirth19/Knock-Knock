package com.example.mygate.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mygate.R;
import com.example.mygate.databinding.FragmentDashboardBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DashboardFragment extends Fragment {
    TextView tv,notice;
   TextInputEditText text;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        tv=root.findViewById(R.id.selection_chat_btn);
        text=root.findViewById(R.id.message_edittiext);
        notice=root.findViewById(R.id.message_list);

        FirebaseDatabase.getInstance().getReference("Notice").child("notice").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                        notice.setText(String.valueOf(task.getResult().getValue()));
//                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                }
            }
        });
//        notice.setText(reference.toString());
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Notice");
                reference.child("notice").setValue(text.getText().toString());
                notice.setText(text.getText().toString().trim());
            }
        });
        return root;
    }

}