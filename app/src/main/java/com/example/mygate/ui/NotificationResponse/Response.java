package com.example.mygate.ui.NotificationResponse;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.mygate.R;
import com.example.mygate.ui.home.HomeFragment;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class Response extends Fragment {

    Button btn;
    private ListView listView;
    private ArrayList<String> notification = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_response, container, false);
        listView = root.findViewById(R.id.list);
        btn = root.findViewById(R.id.btnVisit);
        initializeListView();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(Response.this)
                        .navigate(R.id.visit);
            }
        });
        return root;
    }
    private void initializeListView() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,notification);
        listView.setAdapter(adapter);
        Query data = reference.orderByChild("id").equalTo("user");
        data.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String name = snapshot.child("name").getValue(String.class);
                if (snapshot.child("response").getValue().equals("yes")){
                    notification.add("Name:  "+name+"\nStatus: Visitor Approved.");
                    adapter.notifyDataSetChanged();
                }else if (snapshot.child("response").getValue().equals("no")){
                    notification.add("Name:  "+name+"\nStatus: Visitor Not Approved.");
                    adapter.notifyDataSetChanged();
                }
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
    }
}