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
import com.google.firebase.auth.FirebaseAuth;

import es.dmoral.toasty.Toasty;

public class Forget extends AppCompatActivity {
Button b1,b2;
EditText edtemail,edtpassword;

FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        edtemail = (EditText) findViewById(R.id.edtforgetemail);

        b1 = (Button) findViewById(R.id.btnsubmit);
        firebaseAuth = FirebaseAuth.getInstance();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = edtemail.getText().toString();

                if (email.isEmpty()) {
                    Toasty.warning(getApplicationContext(), "plzz Enter Your Email", Toast.LENGTH_SHORT).show();

                } else {
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toasty.success(getApplicationContext(), "plzz Check Your Email Account", Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(Forget.this, LoginActivity.class);
                                startActivity(i);
                                finish();
                            } else {
                                Toasty.error(getApplicationContext(), "No Record Found", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            }
        });

    }
}
