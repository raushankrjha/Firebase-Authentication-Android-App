package in.fullstackcube.firebaseauthentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    EditText edtemail,edtpassword;
    Button blogin,bsignup;
    TextView forgetpassword;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        firebaseAuth= FirebaseAuth.getInstance();

        //find id of view
        edtemail=(EditText)findViewById(R.id.editText_emailAddress);
        edtpassword=(EditText)findViewById(R.id.editText_password);
        bsignup=(Button)findViewById(R.id.bttnsignup);
        blogin=(Button)findViewById(R.id.blogin);
        forgetpassword=(TextView)findViewById(R.id.forget);

        //check user already login or not
        if(firebaseAuth.getCurrentUser()!=null)
        {
            finish();
            startActivity(new Intent(getApplicationContext(),HomePageActivity.class));
        }


        //Perform Action On Button

             blogin.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onClick(View view) {
                // call method for user login
                userlogin();
            }
             });

            forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Forget.class));
            }
            });

            bsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
            }
            });
    }


    //login method
    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public void userlogin() {

        //get input text from edittext and store in string
        String email = edtemail.getText().toString();
        String password = edtpassword.getText().toString();


        //check textfield is filled or not
            if (email.isEmpty()) {
            Toasty.warning(getApplicationContext(), "plzz Enter Your Email", Toast.LENGTH_SHORT).show();

            }
             else if (password.isEmpty()) {
            Toasty.warning(getApplicationContext(), "plzz Enter Your password", Toast.LENGTH_SHORT).show();

             }
             else
                 {
                     progressDialog.setMessage("Login User....");
                     progressDialog.show();
                     firebaseAuth.signInWithEmailAndPassword(email, password)
                             .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                              @Override
                              public void onComplete(@NonNull Task<AuthResult> task) {
                                      if (task.isSuccessful()) {
                                        // Sign in success
                                         Toasty.success(LoginActivity.this, "Login Success.",
                                            Toast.LENGTH_SHORT).show();
                                            finish();
                                             startActivity(new Intent(getApplicationContext(), HomePageActivity.class));

                                             }
                                      else
                                          {
                                              // If sign in fails, display a message to the user.

                                                Toasty.error(LoginActivity.this, "Login failed.",
                                                 Toast.LENGTH_SHORT).show();
                                                //dismiss progress dialog
                                                    progressDialog.dismiss();
                                           }

                            // ...
                        }
                    });
        }
    }


}
