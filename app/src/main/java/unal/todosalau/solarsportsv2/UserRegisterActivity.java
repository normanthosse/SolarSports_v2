package unal.todosalau.solarsportsv2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class UserRegisterActivity extends AppCompatActivity {

    private EditText edtxPassword;
    private EditText edtxName;
    private EditText edtxEmail;
    private EditText edtxConfirmPass;

    private CheckBox checkBoxRegister;

    //clase para los datos del usuario
    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_register);

        edtxEmail = findViewById(R.id.edtxEmail);
        edtxName = findViewById(R.id.edtxName);
        edtxPassword = findViewById(R.id.edtxPassword);
        edtxConfirmPass = findViewById(R.id.edtxConfirmPass);
        checkBoxRegister = findViewById(R.id.checkBoxRegister);

        userManager = new UserManager(this);

        Button btnExitR = findViewById(R.id.btnExitR);
        btnExitR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserRegisterActivity.this, "Saliendo al inicio", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        Button btnRegisterR = findViewById(R.id.btnRegisterR);
        btnRegisterR.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String name = edtxName.getText().toString().trim();
                String email = edtxEmail.getText().toString().trim();
                String password = edtxPassword.getText().toString().trim();
                String confirmPass = edtxConfirmPass.getText().toString().trim();

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(UserRegisterActivity.this, "Ingrese su nombre", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(email)) {
                    Toast.makeText(UserRegisterActivity.this, "Ingrese un correo electrónico", Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(email)) {
                    Toast.makeText(UserRegisterActivity.this, "Ingrese un correo electrónico válido", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(UserRegisterActivity.this, "Ingrese una contraseña", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(confirmPass)) {
                    Toast.makeText(UserRegisterActivity.this, "Confirme su constraseña", Toast.LENGTH_SHORT).show();
                }else if(!TextUtils.equals(password,confirmPass)) {
                    Toast.makeText(UserRegisterActivity.this, "Su contraseña NO COINCIDE", Toast.LENGTH_SHORT).show();
                } else if (!checkBoxRegister.isChecked()) {
                    Toast.makeText(UserRegisterActivity.this, "Debe aceptar los Términos y Condiciones", Toast.LENGTH_SHORT).show();
                } else {
                    registrarUsuario(email, password);
                }
            }
        });


    }
    private boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    private void registrarUsuario(String email, String password) {
        userManager.registerUser(email, password);
        Toast.makeText(UserRegisterActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
        finish();
    }

}