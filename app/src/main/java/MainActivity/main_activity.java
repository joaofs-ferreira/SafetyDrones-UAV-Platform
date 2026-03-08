### MainActivity.java

`MainActivity` is the **initial activity of the Safety Drones application**, responsible for managing **user authentication flow** and directing the user to the appropriate screen.

Key responsibilities:
- Initializes **Firebase Authentication (`FirebaseAuth`)** to verify the user's login status.
- Checks if a **user is already authenticated** (`FirebaseUser currentUser`).
- If the user is already logged in:
  - Automatically redirects to the **main application page (`FirstPage`)**.
  - Closes `MainActivity` to prevent returning to the login screen.
- If no user is authenticated:
  - Displays the **main screen with Login and Register options**.
- Handles navigation through two buttons:
  - **Login Button** → Opens `LoginActivity`.
  - **Register Button** → Opens `RegisterActivity`.

Overall, `MainActivity` acts as the **authentication gateway of the app**, ensuring that users either log in/register or are redirected directly to the main interface if they are already authenticated.


package com.example.safetydrones.Main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.safetydrones.Home.FirstPage;
import com.example.safetydrones.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button loginButton, registerButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            Intent intent = new Intent(MainActivity.this, FirstPage.class);
            startActivity(intent);
            finish();
            return;
        }

        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.login_button);
        registerButton = findViewById(R.id.register_button);

        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}
