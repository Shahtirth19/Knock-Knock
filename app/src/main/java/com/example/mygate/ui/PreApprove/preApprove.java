package com.example.mygate.ui.PreApprove;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mygate.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Date;

public class preApprove extends Fragment {
    private ListView listView;
    private ArrayList<String> preApprove = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_pre_approve, container, false);
        listView = root.findViewById(R.id.preApproved_list);
        initializeListView();
//        long unixTime = System.currentTimeMillis() / 1000L;
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        String currentDateTime = dateFormat.format(new Date());
//        Toast.makeText(getContext(), currentDateTime, Toast.LENGTH_LONG).show();
        return root;
    }

    private void initializeListView() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("PreApproved");
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,preApprove);
        listView.setAdapter(adapter);
//        Query data = reference.orderByChild("id").equalTo("user");
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String name = snapshot.child("name").getValue(String.class);
                String number = snapshot.child("message").getValue(String.class);
                String type = snapshot.child("date").getValue(String.class);
                String blockno = snapshot.child("blockno").getValue(String.class);
                preApprove.add("Date: "+type+"\nFlat No: "+blockno+"\nName: "+name+"\nMessage: "+number);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                adapter.notifyDataSetChanged();
            }
        });
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String block = String.valueOf(parent.getItemAtPosition(position));
//                Query data = reference.orderByChild("blockno").equalTo(block) && ref;
//                String block = String.valueOf(adapterView.getItemAtPosition(i));
//                Toast.makeText(getContext(), block.substring(23,30), Toast.LENGTH_LONG).show();
            }
        });
    }
}

