package unal.todosalau.solarsportsv2;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;



public class CategoryFieldActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private SpaceAdapter spaceAdapter;
    private List<SportSpace> sportSpaceList = new ArrayList<>();
    private Button btnAdd, btnDelete;
    private Toolbar tbarMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_field);

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterSpaceActivity.class);
                startActivity(intent);
            }
        });
        //borrar ALL "RegisterData"
        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener( v -> showDelete() );

        //inicializa setupToolbar
        Toolbar toolbar = findViewById(R.id.tbarMenu);
        ToolbarUtil.setupToolbar(this, toolbar, CategoryFieldActivity.class);

        //inicializa setupRecyclerView
        recyclerView = findViewById(R.id.recycler_spacesSports);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); //1.Se activa para ver sharedPreferences


        //cargar los datos
        loadSpacesFromPreferences();

        //configurar adaptador
        spaceAdapter = new SpaceAdapter(sportSpaceList); //pasa lista space adapter
        recyclerView.setAdapter(spaceAdapter); // 1.1 Se activa para ver sharedPreferences

        }

    private void loadSpacesFromPreferences() {
        SharedPreferences preferences = getSharedPreferences("RegisterData", MODE_PRIVATE);
        String[] categories = {"Field", "Court", "Room", "Pool"};

        for (String category : categories) {
            int index = preferences.getInt(category + "_index", 0);

            for (int i = 0; i < index; i++) {
                String name = preferences.getString(category + "_name" + i, "");
                String address = preferences.getString(category + "_address" + i, "");
                String city = preferences.getString(category + "_city" + i, "");
                String phone = preferences.getString(category + "_phone" + i, "");
                String month = preferences.getString(category + "_month" + i, "");

                // Leer los valores de Double desde SharedPreferences
                Double power = Double.longBitsToDouble(preferences.getLong(category + "_power" + i, Double.doubleToLongBits(0.0)));
                Double energyGenerated = Double.longBitsToDouble(preferences.getLong(category + "_energyGen" + i, Double.doubleToLongBits(0.0)));
                Double energyConsumed = Double.longBitsToDouble(preferences.getLong(category + "_energyCons" + i, Double.doubleToLongBits(0.0)));

                SportSpace space = new SportSpace(name, address, city, phone, power, energyGenerated, energyConsumed, month);
                sportSpaceList.add(space);

            }
        }
    }

    private void showDelete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Advertencia");
        builder.setMessage("¿Estás seguro de que deseas borrar todos los registros? Esta acción no se puede deshacer.");

        builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clearData(); // Método que se encarga de borrar todos los registros
                Toast.makeText(getApplicationContext(), "Todos los registros han sido borrados.", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel(); // Cierra el diálogo sin hacer nada
            }
        });

        builder.show(); // Muestra el cuadro de diálogo
    }

        //Borrar ALL "RegisterData"
        private void clearData() {
            SharedPreferences preferences = getSharedPreferences("RegisterData", MODE_PRIVATE);
            SharedPreferences.Editor RegisterEditor = preferences.edit();
            RegisterEditor.clear();
            RegisterEditor.apply();
            Toast.makeText(this, "!!! CLEANING ¡¡¡ -->ALL REGISTER", Toast.LENGTH_SHORT).show();
        }
}