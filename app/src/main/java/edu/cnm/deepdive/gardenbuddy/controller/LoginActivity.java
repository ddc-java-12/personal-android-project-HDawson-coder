package edu.cnm.deepdive.gardenbuddy.controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import edu.cnm.deepdive.gardenbuddy.R;
import edu.cnm.deepdive.gardenbuddy.databinding.ActivityLoginBinding;
import edu.cnm.deepdive.gardenbuddy.service.GoogleSignInService;

/**
 * This is the LoginActivity page which uses Google Sign in to log users in with their Google
 * accounts so that the application can remember them regardless of which device a user may be using.
 */
public class LoginActivity extends AppCompatActivity {

  private static final int LOGIN_REQUEST_CODE = 2047;

  private GoogleSignInService service;
  private ActivityLoginBinding binding;

  /**
   * Provides the Google sign activity to be created upon app launch.
   * @param savedInstanceState
   */
  @SuppressLint("CheckResult")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    service = GoogleSignInService.getInstance();
    //noinspection ResultOfMethodCallIgnored
    service
        .refresh()
        .subscribe(
            this::updateAndSwitch,
            (throwable) -> {
              binding = ActivityLoginBinding.inflate(getLayoutInflater());
              binding.signIn.setOnClickListener((v) ->
                  service.startSignIn(this, LOGIN_REQUEST_CODE));
              setContentView(binding.getRoot());
            }
        );
  }

  /**
   *
   * @param requestCode provides a requestCode for Google to recognize the user device signing in.
   * @param resultCode provides the resultCode for Google to recognize the user device signing in.
   * @param data provides the data needed for Google to recognize the user device signing in.
   */
  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    if (requestCode == LOGIN_REQUEST_CODE) {
      service
          .completeSignIn(data)
          .addOnSuccessListener(this::updateAndSwitch)
          .addOnFailureListener((throwable) -> Snackbar.make(binding.getRoot(),
              R.string.sign_in_failed, BaseTransientBottomBar.LENGTH_INDEFINITE).show());
    } else {
      super.onActivityResult(requestCode, resultCode, data);
    }
  }

  /**
   * updateAndSwitch gathers the account information and if correct will sign the user in then
   * display the HomeFragment page which is set through the MainActivityDrawer.class.
   * @param account Needed to gain the account information through GoogleSignInAccount.
   */
  private void updateAndSwitch(GoogleSignInAccount account) {
    startActivity(
        new Intent(this, MainActivityDrawer.class)
        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK)
    );
  }

}
