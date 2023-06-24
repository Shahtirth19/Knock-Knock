package com.example.mygate.ui.Agoravc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mygate.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Agoramain extends Fragment {
//    String APP_ID = "09609b1eab744eb39888bbe137b643b6";
//    EditText Message;
    Button send;
    String code;
    Spinner staticSpinner;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_agoramain, container, false);
//        Message=root.findViewById(R.id.vCode);
        send=root.findViewById(R.id.call);
        staticSpinner=root.findViewById(R.id.vcID);
        getSpinner();
        URL server;
            try {
                server =new URL("https://meet.jit.si");
                JitsiMeetConferenceOptions defaultOptions=
                        new JitsiMeetConferenceOptions.Builder()
                                .setServerURL(server)
                                .setWelcomePageEnabled(false)
                                .build();
                JitsiMeet.setDefaultConferenceOptions(defaultOptions);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                        .setRoom(code)
                        .setWelcomePageEnabled(false).build();
                JitsiMeetActivity.launch(getActivity(),options);
            }
        });
        return root;
    }

    private void getSpinner() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        ArrayList<String> notification = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, notification);
        Query data = reference.orderByChild("id").equalTo("user");
        data.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String block = snapshot.child("blockno").getValue(String.class);
                notification.add(block);
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
        staticSpinner.setAdapter(adapter);

        staticSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String block = String.valueOf(parent.getItemAtPosition(position));
                Query data = reference.orderByChild("blockno").equalTo(block);
                data.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        code = snapshot.child("videoId").getValue(String.class);
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }

}