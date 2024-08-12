package unal.todosalau.solarsportsv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText edtxEmailE;
    private EditText edtxPasswordE;

    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        //establecer validación usuario
        edtxEmailE = findViewById(R.id.edtxEmailE);
        edtxPasswordE = findViewById(R.id.edtxPasswordE);

        userManager = new UserManager(this);

        Button btnRegisterE = findViewById(R.id.btnRegisterE);
        btnRegisterE.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Registrar nuevo usuario", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), UserRegisterActivity.class);
                startActivity(intent);
            }
        });

        Button btnIngresarE = findViewById(R.id.btnIngresarE);
        btnIngresarE.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String email =edtxEmailE.getText().toString();
                String password =edtxPasswordE.getText().toString();

                if(userManager.loginUser(email, password)) {

                    Toast.makeText(LoginActivity.this, "Ingresando a la APP", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MajorActivity.class);
                    startActivity(intent);
                    finish(); // deshabilita la opcion de devolverse
                }  else {
                    Toast.makeText(LoginActivity.this, "Email o password Incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }
}