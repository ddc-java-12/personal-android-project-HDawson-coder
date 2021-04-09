package edu.cnm.deepdive.gardenbuddy.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import edu.cnm.deepdive.gardenbuddy.R;
import edu.cnm.deepdive.gardenbuddy.service.GoogleSignInService;

/**
 * MainActivityDrawer is the mainActivity class for the application. This holds the required methods
 * for gaining access to different fragments such as the optionsMenu, or the app bar along the top
 * of the screen.
 */
public class MainActivityDrawer extends AppCompatActivity {

  private AppBarConfiguration mAppBarConfiguration;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_navigation_drawer);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    NavigationView navigationView = findViewById(R.id.nav_view);
    // Passing each menu ID as a set of Ids because each
    // menu should be considered as top level destinations.
    mAppBarConfiguration = new AppBarConfiguration.Builder(
        R.id.nav_home, R.id.nav_plant, R.id.nav_notes)
        .setDrawerLayout(drawer)
        .build();
    NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
    NavigationUI.setupWithNavController(navigationView, navController);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.navigation_drawer, menu);
    return true;
  }

  @Override
  public boolean onSupportNavigateUp() {
    NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    return NavigationUI.navigateUp(navController, mAppBarConfiguration)
        || super.onSupportNavigateUp();
  }

  /**
   * To be used to sign out of Google Account if a user would like to. This is not yet implemented.
   * A user would have to uninstall the app in this current state in order to use a different account.
   */
  private void signOut() {
    GoogleSignInService
        .getInstance() // getting the instance of google sign in services
        .signOut() // signing out
        .addOnCompleteListener((ignored) ->  //waits for button to be pushed basically
            startActivity( //once button is pushed this happens
                new Intent(this, LoginActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK)));
  }
}