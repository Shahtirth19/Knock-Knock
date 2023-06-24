package com.example.mygate;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import com.example.mygate.ui.SendNotificationPack.Token;
import com.example.mygate.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.mygate.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {

//    FloatingActionButton floatingActionButton;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (FirebaseAuth.getInstance().getCurrentUser()== null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else {
            Toast.makeText(MainActivity.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
            binding.fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NavController navController = Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment_activity_main);
                    navController.navigateUp();
                    navController.navigate(R.id.sucess);
//                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
                }
            });
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                   BottomNavigationView navView = findViewById(R.id.nav_view);
            if(uid.equals("bV5t91MWz4dSbgh8xq3FUmlAYg13")){
                navView.inflateMenu(R.menu.admin_menu);
            }else{
                navView.inflateMenu(R.menu.bottom_nav_menu);
            }
                   // Passing each menu ID as a set of Ids because each
                   // menu should be considered as top level destinations.
                   AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                           R.id.navigation_home,R.id.navigation_dashboard,R.id.navigation_notifications, R.id.navigation_response)
                           .build();

                   NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
                   NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
                   NavigationUI.setupWithNavController(binding.navView, navController);

                FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
                String refreshToken= FirebaseInstanceId.getInstance().getToken();
                if(firebaseUser!=null){
                    updateToken(refreshToken);
                }

        }
    }

    private void updateToken(String refreshToken) {
//        private void updateToken(String refreshToken){
            FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
            Token token1= new Token(refreshToken);
            FirebaseDatabase.getInstance().getReference("Tokens").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(token1);
//        }
    }

}