package com.example.mygate.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.mygate.LoginActivity;
import com.example.mygate.MainActivity;
import com.example.mygate.R;
import com.example.mygate.databinding.FragmentHomeBinding;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class HomeFragment extends Fragment {
    TextInputLayout fullname,phno,eid,address;
    Button btnLogOut,update,view;
    String fn,email,add,ph,dt,uid;
    DatabaseReference reference;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        fullname= root.findViewById(R.id.fullname);
        phno= root.findViewById(R.id.phno);
        eid= root.findViewById(R.id.eid);
        address= root.findViewById(R.id.address);
        btnLogOut = root.findViewById(R.id.btnLogout);
//        view = root.findViewById(R.id.btnView);
        update = root.findViewById(R.id.update);
        reference = FirebaseDatabase.getInstance().getReference("User");
        getdata();
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNameChanged() || isAddChanged() || isPhnoChanged()){
                    Toast.makeText(getContext(),"Data Has been Updated.", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getContext(),"Email-ID Can't be changed.", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnLogOut.setOnClickListener(view ->{
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(getContext(), "User logged out successfully", Toast.LENGTH_LONG).show();
            startActivity(new Intent(getActivity(), LoginActivity.class));
            getActivity().finish();
        });
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(HomeFragment.this)
//                        .navigate(R.id.sucess);
//            }
//        });
        return root;
    }
    private void getdata() {
        String id = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        Query data = reference.orderByChild("email").equalTo(id);
        data.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    fn = ds.child("name").getValue(String.class);
                    fullname.getEditText().setText(fn);

                    ph = ds.child("phno").getValue(String.class);
                    phno.getEditText().setText(String.valueOf(ph));

                    email = ds.child("email").getValue(String.class);
                    eid.getEditText().setText(email);

                    add = ds.child("address").getValue(String.class);
                    address.getEditText().setText(add);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    private boolean isNameChanged() {
        if(!fn.equals(fullname.getEditText().getText().toString())){
            reference.child(uid).child("name").setValue(fullname.getEditText().getText().toString());
            fn = fullname.getEditText().getText().toString();
            return true;
        }else{
            return false;
        }

    }
    private boolean isPhnoChanged() {
        String id = ph;
        if(!ph.equals(phno.getEditText().getText().toString())){
            reference.child(uid).child("phno").setValue(phno.getEditText().getText().toString());
            ph = phno.getEditText().getText().toString();
            return true;
        }else{
            return false;
        }

    }

    private boolean isAddChanged() {
        if(!add.equals(address.getEditText().getText().toString())){
            reference.child(uid).child("address").setValue(address.getEditText().getText().toString());
            add = address.getEditText().getText().toString();
            return true;
        }else{
            return false;
        }
    }
}