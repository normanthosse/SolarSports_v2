package unal.todosalau.solarsportsv2;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterSpaceActivity extends AppCompatActivity {

    private EditText edtxRegName, edtxRegAddress, edtxRegPhone, edtxRegPower, edtxRegEnergyGen, edtxRegEnergyConsum, edtxRegCity;
    private Spinner spinnerCategory, spinnerMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_space);

        edtxRegName = findViewById(R.id.edtxRegName);
        edtxRegAddress = findViewById(R.id.edtxRegAddress);
        edtxRegCity = findViewById(R.id.edtxRegCity);
        edtxRegPhone = findViewById(R.id.edtxRegPhone);
        edtxRegPower = findViewById(R.id.edtxRegPower);
        edtxRegEnergyGen = findViewById(R.id.edtxRegEnergyGen);
        edtxRegEnergyConsum = findViewById(R.id.edtxRegEnergyConsum);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        spinnerMonth = findViewById(R.id.spinnerMonth);

        // Configura el adaptador del Spinner
        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(
                this, R.array.category_array, android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(categoryAdapter);

        // Configuración del Spinner de Meses
        ArrayAdapter<CharSequence> monthAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.month_array,
                android.R.layout.simple_spinner_item
        );
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMonth.setAdapter(monthAdapter);

        //Registrar datos y volver
        Button btnReg = findViewById(R.id.btnReg);
        btnReg.setOnClickListener(v -> register());

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        Button btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(v -> showItemToDelete());

    }


    //Registrar un elemento
    private void register() {
        String name = edtxRegName.getText().toString().trim();
        String address = edtxRegAddress.getText().toString().trim();
        String city = edtxRegCity.getText().toString().trim();
        String phone = edtxRegPhone.getText().toString().trim();
        String powerStr = edtxRegPower.getText().toString().trim();
        String energyGenStr = edtxRegEnergyGen.getText().toString().trim();
        String energyConsStr = edtxRegEnergyConsum.getText().toString().trim();
        String category = spinnerCategory.getSelectedItem().toString().trim();
        String month = spinnerMonth.getSelectedItem().toString().trim();

        //Todas los campos son obligatorios
        if (category.isEmpty() || name.isEmpty() || address.isEmpty() || city.isEmpty() || phone.isEmpty()
                || powerStr.isEmpty() || energyGenStr.isEmpty() || energyConsStr.isEmpty() || month.isEmpty()) {
            Toast.makeText(this, "Complete los campos obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        // Conversión de Strings a Doubles
        Double power = parseDoubleOrDefault(powerStr, 0.0);
        Double energyGen = parseDoubleOrDefault(energyGenStr, 0.0);
        Double energyCons = parseDoubleOrDefault(energyConsStr, 0.0);

       // Guarda los datos en SharedPreferences
        SharedPreferences preferences = getSharedPreferences("RegisterData", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        int index = preferences.getInt(category + "_index", 0);
        editor.putString(category + "_name" + index, name);
        editor.putString(category + "_address" + index, address);
        editor.putString(category + "_phone" + index, phone);
        editor.putString(category + "_city" + index, city);
        editor.putLong(category + "_power" + index, Double.doubleToLongBits(power));
        editor.putLong(category + "_energyGen" + index, Double.doubleToLongBits(energyGen));
        editor.putLong(category + "_energyCons" + index, Double.doubleToLongBits(energyCons));
        editor.putString(category + "_month" + index,month);
        editor.putString(category + "_category" + index,category);

        editor.putInt(category + "_index", index + 1);
        editor.apply();

        Toast.makeText(this, "Campo Registrado", Toast.LENGTH_SHORT).show();
        finish(); //vuelve a la ultima acción
    }

    //parsear String --> Double
    private Double parseDoubleOrDefault(String value, Double defaultValue) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    //? por el nombre a borrar
    private void showItemToDelete(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);  //crea el buider
        builder.setTitle("Ingrese SportSpace que desea ELIMINAR");
        final EditText input = new EditText(this);  //ojo clase anonima "final"
        input.setInputType(InputType.TYPE_CLASS_TEXT); // el tipo de entrada es texto--Pantalla texto
        builder.setView(input);     //permite ingresar name

        builder.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nameToDelete = input.getText().toString().trim();
                if(!nameToDelete.isEmpty()){
                    deleteItemByName(nameToDelete);
                }else {
                    Toast.makeText(getApplicationContext(), "Ingrese SportSpace a ELIMINAR", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    //borrar item por nombre
    private void deleteItemByName(String name) {
        SharedPreferences preferences = getSharedPreferences("RegisterData", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();  // crea editor para eliminar datos

        // arreglo de categorías para buscar el elemento
        String[] categories = {"Field", "Court", "Room", "Pool"};
        // inicializa la variable boleana en false --> indica si encuentra el elemento
        boolean itemFound = false;
        //itera sobre las categorias
        for (String category : categories) {
            int index = preferences.getInt(category + "_index", 0);  //indica cuantos elementos existen
            for (int i = 0; i < index; i++) {   //recorre todos los elementos de la categoria
                String storedName = preferences.getString(category + "_name" + i, ""); //busca por nombre en cada posición
                if (storedName.equals(name)) { //el nombre buscado se compara con el almacenado
                    // Crea arreglo con las claves de los atributos del elemento encontrado
                    String[] keys = {
                            category + "_name" + i,
                            category + "_address" + i,
                            category + "_phone" + i,
                            category + "_city" + i,
                            category + "_power" + i,
                            category + "_energyGen" + i,
                            category + "_energyCons" + i,
                            category + "_month" + i,
                            category + "_category" + i
                    };
                    //itera sobre cada clave y elimina
                    for (String key : keys) {
                        editor.remove(key);
                    }

                    // Actualiza el índice para la categoría
                    editor.putInt(category + "_index", index - 1); //decrementa en 1
                    itemFound = true;       // la variable true indica que se encontró el elemento

                    break; // Sale del bucle una vez que se encontró y elimino
                }
            }

            if (itemFound) {//verifica el cambio de estado de la variable
                break; // Sale del bucle externo si el elemento ha sido encontrado
            }
        }

        if (itemFound) {
            editor.apply();             //aplica cambios y confirma
            Toast.makeText(this, "Elemento eliminado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Elemento no encontrado", Toast.LENGTH_SHORT).show();
        }
    }
}