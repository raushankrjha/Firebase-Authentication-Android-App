package in.fullstackcube.firebaseauthentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import es.dmoral.toasty.Toasty;

public class SignUpActivity extends AppCompatActivity {

    EditText edtemail,edtname,edtmobile,edtpassword;
    String email,name,mobile ;
    String password;
    Button bsignup;
    private FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        bsignup=(Button)findViewById(R.id.bttnsignup);
        edtemail=(EditText)findViewById(R.id.editText_emailAddress);
        edtname=(EditText)findViewById(R.id.editText_name);
        edtmobile=(EditText)findViewById(R.id.editText_mobile);
        edtpassword=(EditText)findViewById(R.id.editText_password);
        progressDialog=new ProgressDialog(this);


        firebaseAuth= FirebaseAuth.getInstance();
        bsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Register();

            }
        });
    }

    public void Register()
    {
        email = edtemail.getText().toString();
        name = edtname.getText().toString();
        mobile = edtmobile.getText().toString();
        password = edtpassword.getText().toString();
        if (email.isEmpty()) {
            Toasty.warning(getApplicationContext(), "plzz Enter Your Email", Toast.LENGTH_SHORT).show();

        }
        else if (name.isEmpty()) {
            Toasty.warning(getApplicationContext(), "plzz Enter  Name", Toast.LENGTH_SHORT).show();

        }
       else  if (password.isEmpty()) {
            Toasty.warning(getApplicationContext(), "plzz Enter Password", Toast.LENGTH_SHORT).show();

        }
        else if (mobile.isEmpty()) {
            Toasty.warning(getApplicationContext(), "plzz Enter Password", Toast.LENGTH_SHORT).show();

        }
        else {

            progressDialog.setMessage("Registering User....");
            progressDialog.show();

            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success
                                Toasty.success(SignUpActivity.this, "Registration Sucess.",
                                        Toast.LENGTH_SHORT).show();
                                finish();
                                Intent i = new Intent(SignUpActivity.this, HomePageActivity.class);
                                startActivity(i);
                            } else {
                                // If sign in fails, display a message to the user.

                                Toasty.error(SignUpActivity.this, "Registration failed.",
                                        Toast.LENGTH_SHORT).show();

                            }

                            // ...
                        }
                    });

        }

    }
}
