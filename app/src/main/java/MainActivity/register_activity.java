### RegisterActivity.java – User Registration Activity

`RegisterActivity` manages the **creation of new user accounts** in the Safety Drones application.

Key responsibilities:
- Provides input fields for **name, email, password, and password confirmation** using `EditText`.
- Uses **`Model_User`** to handle validation and registration logic, following the **MVC architecture**.
- Validates user input through the model by checking:
  - Required fields are filled
  - Email format is valid
  - Password and confirmation password match
- Registers new users through **Firebase Authentication** via the `registerUser` method in `Model_User`.
- Displays **Toast messages** to inform users about successful registration or possible errors.
- After successful registration:
  - Notifies the user to **verify their email**.
  - Redirects the user to **`LoginActivity`**.
  - Closes the current activity to prevent returning to the registration screen.

Overall, `RegisterActivity` provides a **secure and validated registration interface**, ensuring that new users can create accounts and access the application safely.

package com.example.safetydrones.Main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.safetydrones.R;
import com.example.safetydrones.Model.Model_User;

public class RegisterActivity extends AppCompatActivity {

    private EditText registerName, registerEmail, registerPassword, registerPassword2;
    private Button registerUserButton;
    private Model_User userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);

        userModel = new Model_User();

        registerName = findViewById(R.id.register_name);
        registerEmail = findViewById(R.id.register_email);
        registerPassword = findViewById(R.id.register_password);
        registerPassword2 = findViewById(R.id.register_password2);
        registerUserButton = findViewById(R.id.btn_register);

        registerUserButton.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {

        String name = registerName.getText().toString().trim();
        String email = registerEmail.getText().toString().trim();
        String password = registerPassword.getText().toString().trim();
        String confirm_password = registerPassword2.getText().toString().trim();


        if (userModel.validarDados(name, email, password, confirm_password, this)) {

            userModel.registerUser(name, email, password, new Model_User.RegisterCallback() {
                @Override
                public void onRegisterSuccess() {
                    Toast.makeText(RegisterActivity.this, "Conta criada com sucesso! Verifique seu e-mail para confirmar.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    finish();
                }

                @Override
                public void onRegisterFailure(String error) {
                    Toast.makeText(RegisterActivity.this, error, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
