package edu.cnm.deepdive.gardenbuddy.service;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import io.reactivex.Single;

/**
 * The GoogleSignInService class allows the application to connect to the Google Cloud
 * and recognize the google accounts being used to sign in. The Google Service gives a specific
 * ID to each user that logs in to the application.
 */
public class GoogleSignInService {

  private static final String BEARER_FORMAT = "Bearer %s";

  private static Application context;

  private final GoogleSignInClient client;

  private GoogleSignInAccount account;

  private GoogleSignInService() {
    GoogleSignInOptions options = new GoogleSignInOptions.Builder()
        .requestEmail()
        .requestId()
        .requestProfile()
//        .requestIdToken(BuildConfig.CLIENT_ID)  // use client ID of web application
        .build();
    client = GoogleSignIn.getClient(context, options);
  }

  /**
   * Sets the context of the GoogleSignInService when called upon.
   * @param context
   */
  public static void setContext(Application context) {
    GoogleSignInService.context = context;
  }

  /**
   * Gets the instance of the GoogleSignInService when called upon.
   * @return
   */
  public static GoogleSignInService getInstance() {
    return InstanceHolder.INSTANCE;
  }

  /**
   * When the application is closed and reopened this will refresh the information associated with
   * the signed in account to continue using the application.
   * @return Returns the current GoogleSign in account information.
   */
  public Single<GoogleSignInAccount> refresh() {
    return Single.create(emitter -> client.silentSignIn()
        .addOnSuccessListener(this::setAccount)
        .addOnSuccessListener(emitter::onSuccess)
        .addOnFailureListener(emitter::onError));
  }

  /**
   * Refreshes the BearerToken for the sign in service to prevent the services from timing out.
   * @return
   */
  public Single<String> refreshBearerToken() {
    return refresh()
        .map((account) -> String.format(BEARER_FORMAT, account.getIdToken()));
  }

  /**
   * When a user selects sign in this starts the activity to allow the user to sign in.
   * @param activity Activity starts when the sign service is started.
   * @param requestCode Gets the request code needed for when an account is logging in.
   */
  public void startSignIn(Activity activity, int requestCode) {
    account = null;
    Intent intent = client.getSignInIntent();
    activity.startActivityForResult(intent, requestCode);
  }

  /**
   * Allows the user to sign in when the correct information is input (account and password).
   * @param data The data to be used for the Intent when the service of signing in is complete.
   * @return Returns the following task after the Sign in is complete.
   */
  public Task<GoogleSignInAccount> completeSignIn(Intent data) {
    Task<GoogleSignInAccount> task = null;
    try {
      task = GoogleSignIn.getSignedInAccountFromIntent(data);
      setAccount(task.getResult(ApiException.class));
    } catch (ApiException e) {
      // Ignored: Exception will be passed automatically to onFailureListener.
    }
    return task;
  }

  /**
   * When a user selects to sign out, they will be logged out of their Google Account.
   * @return Completes the sign out.
   */
  public Task<Void> signOut() {
    return client.signOut()
        .addOnCompleteListener((task -> setAccount(null)));
  }

  private void setAccount(GoogleSignInAccount account) {
    this.account = account;
  }

  private static class InstanceHolder {

    private static final GoogleSignInService INSTANCE = new GoogleSignInService();
  }


}
