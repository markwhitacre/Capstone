package UI;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.abcvanstock.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import Database.Repository;

public class LoginScreen extends AppCompatActivity {

    private FirebaseAuth auth;
    EditText userName;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        auth = FirebaseAuth.getInstance();
        Repository repository = new Repository(getApplication());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            currentUser.reload();
        }
    }

    public void validateUser(View view) {
        userName = findViewById(R.id.editTextEmailAddress);
        password = findViewById(R.id.editTextPassword);
        if (!TextUtils.isEmpty(String.valueOf(userName.getText())) && !TextUtils.isEmpty(String.valueOf(password.getText()))) {
            String username = userName.getText().toString();
            String pwd = password.getText().toString();

            auth.signInWithEmailAndPassword(username, pwd)
                    .addOnFailureListener(e -> {
                        String message = e.getLocalizedMessage();
                        Toast.makeText(LoginScreen.this, message,
                                Toast.LENGTH_SHORT).show();
                    })
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();
                            Intent intent = new Intent(this, OptionsScreen.class);
                            startActivity(intent);


                        }

                    });
        } else {
            Toast.makeText(LoginScreen.this, "Empty Username or Password", Toast.LENGTH_LONG).show();
        }
    }
}
