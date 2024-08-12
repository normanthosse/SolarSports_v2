package unal.todosalau.solarsportsv2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CategoryCourtActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SpaceAdapter spaceAdapter;
    //lista todos objetos
    private List<SportSpace> sportSpaceList = new ArrayList<>();
    //lista para guardar objetos de "court"
    private List<SportSpace> courtSpace = new ArrayList<>();
    //lista para guardar objetos  de "room"
    private List<SportSpace> roomSpace = new ArrayList<>();
    //lista para guardar objetos  de "pool"
    private List<SportSpace> poolSpace = new ArrayList<>();
    //lista para guardar objetos  de "field"
    private List<SportSpace> fieldSpace = new ArrayList<>();

    private Button btnAdd, btnDelete;
    private Toolbar tbarMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_court);

        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterSpaceActivity.class);
                startActivity(intent);
            }
        });

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterSpaceActivity.class);
                startActivity(intent);
            }
        });

        //inicializa setupToolbar
        Toolbar toolbar = findViewById(R.id.tbarMenu);
        ToolbarUtil.setupToolbar(this, toolbar, CategoryCourtActivity.class);

        //inicializa setupRecyclerView
        recyclerView = findViewById(R.id.recycler_spacesSports);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); //1.Se activa para ver sharedPreferences

        //cargar datos según opcion y configura adaptador
        String methodToCall = getIntent().getStringExtra("Method");
        if(methodToCall != null) {
            switch (methodToCall) {
                case "loadRoomSpaces":
                    spaceAdapter = new SpaceAdapter(roomSpace);
                    loadRoomSpacesFromPreferences();
                    break;
                case "loadPoolSpaces":
                    spaceAdapter = new SpaceAdapter(poolSpace);
                    loadPoolSpacesFromPreferences();
                    break;
                case "loadCourtSpaces":
                    spaceAdapter = new SpaceAdapter(courtSpace);
                    loadCourtSpacesFromPreferences();
                    break;
                case "loadFieldSpaces" :
                    spaceAdapter = new SpaceAdapter(fieldSpace);
                    loadFieldSpacesFromPreferences();
                default:
                    break;
            }
        }
        // Conectar recycler con adaptador
        recyclerView.setAdapter(spaceAdapter);
    }


//crear lista court
    public void loadCourtSpacesFromPreferences() {
        SharedPreferences preferences = getSharedPreferences("RegisterData", MODE_PRIVATE);
        String[] categories = {"Field", "Court", "Room", "Pool"};
        //categoria a mostrar
        String category ="Court";
        //obtener indice de elementos en "Court"
        int index = preferences.getInt(category + "_index", 0);
        courtSpace.clear();
        //itera en los elementos de "field"
            for (int i = 0; i < index; i++) {
                //guarda cada valor de field
                String name = preferences.getString(category + "_name" + i, "");
                String address = preferences.getString(category + "_address" + i, "");
                String city = preferences.getString(category + "_city" + i, "");
                String phone = preferences.getString(category + "_phone" + i, "");
                String month = preferences.getString(category + "_month" + i, "");
                // Leer los valores de Double desde SharedPreferences
                Double power = Double.longBitsToDouble(preferences.getLong(category + "_power" + i, Double.doubleToLongBits(0.0)));
                Double energyGenerated = Double.longBitsToDouble(preferences.getLong(category + "_energyGen" + i, Double.doubleToLongBits(0.0)));
                Double energyConsumed = Double.longBitsToDouble(preferences.getLong(category + "_energyCons" + i, Double.doubleToLongBits(0.0)));
                //crea un objeto con los valores recuperados
                SportSpace space = new SportSpace(name, address, city, phone, power, energyGenerated, energyConsumed, month);
                //añade la lista
                courtSpace.add(space);
      }
            //notificar cambios en el spaceAdapter
        spaceAdapter.notifyDataSetChanged();
    }
//crear lista Room
    public void loadRoomSpacesFromPreferences() {
        SharedPreferences preferences = getSharedPreferences("RegisterData", MODE_PRIVATE);
        String[] categories = {"Field", "Court", "Room", "Pool"};
        //categoria a mostrar
        String category ="Room";
        //obtener indice de elementos en "Court"
        int index = preferences.getInt(category + "_index", 0);
        roomSpace.clear();
        //itera en los elementos de "field"
        for (int i = 0; i < index; i++) {
            //guarda cada valor de field
            String name = preferences.getString(category + "_name" + i, "");
            String address = preferences.getString(category + "_address" + i, "");
            String city = preferences.getString(category + "_city" + i, "");
            String phone = preferences.getString(category + "_phone" + i, "");
            String month = preferences.getString(category + "_month" + i, "");

            // Leer los valores de Double desde SharedPreferences
            Double power = Double.longBitsToDouble(preferences.getLong(category + "_power" + i, Double.doubleToLongBits(0.0)));
            Double energyGenerated = Double.longBitsToDouble(preferences.getLong(category + "_energyGen" + i, Double.doubleToLongBits(0.0)));
            Double energyConsumed = Double.longBitsToDouble(preferences.getLong(category + "_energyCons" + i, Double.doubleToLongBits(0.0)));

            //crea un objeto con los valores recuperados
            SportSpace space = new SportSpace(name, address, city, phone, power, energyGenerated, energyConsumed, month);
            //añade la lista
            roomSpace.add(space);
        }
        //notificar cambios en el spaceAdapter
        spaceAdapter.notifyDataSetChanged();
    }

//crear lista pool
    public void loadPoolSpacesFromPreferences() {
        SharedPreferences preferences = getSharedPreferences("RegisterData", MODE_PRIVATE);
        String[] categories = {"Field", "Court", "Room", "Pool"};
        //categoria a mostrar
        String category ="Pool";
        //obtener indice de elementos en "Pool"
        int index = preferences.getInt(category + "_index", 0);
        poolSpace.clear();
        //itera en los elementos de "field"
        for (int i = 0; i < index; i++) {
            //guarda cada valor de field
            String name = preferences.getString(category + "_name" + i, "");
            String address = preferences.getString(category + "_address" + i, "");
            String city = preferences.getString(category + "_city" + i, "");
            String phone = preferences.getString(category + "_phone" + i, "");
            String month = preferences.getString(category + "_month" + i, "");

            // Leer los valores de Double desde SharedPreferences
            Double power = Double.longBitsToDouble(preferences.getLong(category + "_power" + i, Double.doubleToLongBits(0.0)));
            Double energyGenerated = Double.longBitsToDouble(preferences.getLong(category + "_energyGen" + i, Double.doubleToLongBits(0.0)));
            Double energyConsumed = Double.longBitsToDouble(preferences.getLong(category + "_energyCons" + i, Double.doubleToLongBits(0.0)));

            //crea un objeto con los valores recuperados
            SportSpace space = new SportSpace(name, address, city, phone, power, energyGenerated, energyConsumed, month);
            //añade la lista
            poolSpace.add(space);
        }
        //notificar cambios en el spaceAdapter
        spaceAdapter.notifyDataSetChanged();
    }
//crear lista field
    public void loadFieldSpacesFromPreferences() {
        SharedPreferences preferences = getSharedPreferences("RegisterData", MODE_PRIVATE);
        String[] categories = {"Field", "Court", "Room", "Pool"};
        //categoria a mostrar
        String category ="Field";
        //obtener indice de elementos en "Field"
        int index = preferences.getInt(category + "_index", 0);
        fieldSpace.clear();
        //itera en los elementos de "field"
        for (int i = 0; i < index; i++) {
            //guarda cada valor de field
            String name = preferences.getString(category + "_name" + i, "");
            String address = preferences.getString(category + "_address" + i, "");
            String city = preferences.getString(category + "_city" + i, "");
            String phone = preferences.getString(category + "_phone" + i, "");
            String month = preferences.getString(category + "_month" + i, "");
            // Leer los valores de Double desde SharedPreferences
            Double power = Double.longBitsToDouble(preferences.getLong(category + "_power" + i, Double.doubleToLongBits(0.0)));
            Double energyGenerated = Double.longBitsToDouble(preferences.getLong(category + "_energyGen" + i, Double.doubleToLongBits(0.0)));
            Double energyConsumed = Double.longBitsToDouble(preferences.getLong(category + "_energyCons" + i, Double.doubleToLongBits(0.0)));
            //crea un objeto con los valores recuperados
            SportSpace space = new SportSpace(name, address, city, phone, power, energyGenerated, energyConsumed, month);
            //añade la lista
            fieldSpace.add(space);
        }
        //notificar cambios en el spaceAdapter
        spaceAdapter.notifyDataSetChanged();
    }

}
