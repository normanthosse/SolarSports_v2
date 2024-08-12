package unal.todosalau.solarsportsv2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class StatisticsActivity extends AppCompatActivity {

    private TableLayout myTableLayout;
    private Button btnExit;
    private List<SportSpace> sportSpaceList = new ArrayList<>();

    private TextView textViewTotalEnergyGenerated;
    private TextView textViewTotalEnergyConsumed;
    private TextView textViewDifference, textViewEfficiency;
    private TextView textViewTotalPower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_statistics);

        textViewTotalEnergyGenerated = findViewById(R.id.textViewTotalEnergyGenerated);
        textViewTotalEnergyConsumed = findViewById(R.id.textViewTotalEnergyConsumed);
        textViewDifference = findViewById(R.id.textViewDifference);
        textViewTotalPower = findViewById(R.id.textViewTotalPower);
        textViewEfficiency = findViewById(R.id.textViewEfficiency);

        myTableLayout = findViewById(R.id.myTableLayout);
        btnExit = findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MajorActivity.class);
                startActivity(intent);
            }
        });

        loadData();

    }

    private void loadData(){
        SharedPreferences preferences = getSharedPreferences("RegisterData", MODE_PRIVATE);
        String[] categories = {"Field", "Court", "Room", "Pool"};
        sportSpaceList.clear();

        //iniciliza variables para sumar
        double totalEnergyGenerated = 0.0;
        double totalEnergyConsumed = 0.0;
        double totalPower = 0.0;

        for (String category : categories) {
            int index = preferences.getInt(category + "_index", 0);

            if(index>0){//verifica que hayan datos en RegisterData
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

                    //Generar datos dentro de la tabla
                    TableRow tableRow = new TableRow(StatisticsActivity.this);

                    TableRow.LayoutParams params = new TableRow.LayoutParams(
                            0, TableRow.LayoutParams.WRAP_CONTENT, 1
                    );

                    TextView textViewMonth = new TextView(this);  //instanciar tabla
                    textViewMonth.setText(month);
                    textViewMonth.setBackgroundResource(R.color.white);
                    textViewMonth.setPadding(10, 10, 10, 10);
                    textViewMonth.setGravity(Gravity.CENTER);
                    textViewMonth.setLayoutParams(params);
                    tableRow.addView(textViewMonth);

                    TextView textViewSportSpace = new TextView(this);  //instanciar tabla
                    textViewSportSpace.setText(name);
                    textViewSportSpace.setBackgroundResource(R.color.white);
                    textViewSportSpace.setPadding(10, 10, 10, 10);
                    textViewSportSpace.setGravity(Gravity.CENTER);
                    textViewSportSpace.setLayoutParams(params);
                    tableRow.addView(textViewSportSpace);

                    TextView textViewPower = new TextView(this);  //instanciar tabla
                    textViewPower.setText(String.valueOf(power));
                    textViewPower.setBackgroundResource(R.color.white);
                    textViewPower.setPadding(10, 10, 10, 10);
                    textViewPower.setGravity(Gravity.CENTER);
                    textViewPower.setLayoutParams(params);
                    tableRow.addView(textViewPower);

                    TextView textViewEnergyGenerated = new TextView(this);  //instanciar tabla
                    textViewEnergyGenerated.setText(String.valueOf(energyGenerated));
                    textViewEnergyGenerated.setBackgroundResource(R.color.white);
                    textViewEnergyGenerated.setPadding(10, 10, 10, 10);
                    textViewEnergyGenerated.setGravity(Gravity.CENTER);
                    textViewEnergyGenerated.setLayoutParams(params);
                    tableRow.addView(textViewEnergyGenerated);

                    TextView textViewEnergyConsumed = new TextView(this);  //instanciar tabla
                    textViewEnergyConsumed.setText(String.valueOf(energyConsumed));
                    textViewEnergyConsumed.setBackgroundResource(R.color.white);
                    textViewEnergyConsumed.setPadding(10, 10, 10, 10);
                    textViewEnergyConsumed.setGravity(Gravity.CENTER);
                    textViewEnergyConsumed.setLayoutParams(params);
                    tableRow.addView(textViewEnergyConsumed);

                    myTableLayout.addView(tableRow);
                    // Sumar los valores de energía
                    totalEnergyGenerated += energyGenerated;
                    totalEnergyConsumed += energyConsumed;
                    totalPower += power;
                }
            }
        }

        // Mostrar estadisticas de generación y consumo
        textViewTotalPower.setText(String.format("Total Potencia Instalada: %.2f", totalPower) + " KW");
        textViewTotalEnergyGenerated.setText(String.format("Total Energía Generada: %.2f", totalEnergyGenerated) + " KW/h");
        textViewTotalEnergyConsumed.setText(String.format("Total Energía Consumida: %.2f", totalEnergyConsumed) + " KW/h");

        if(totalEnergyGenerated >= totalEnergyConsumed){
            double difference = totalEnergyGenerated - totalEnergyConsumed;
            textViewDifference.setText(String.format("Excedente de Generación: %.2f", difference) + " KW/h");
        }else {
            double difference = totalEnergyGenerated - totalEnergyConsumed;
            textViewDifference.setText(String.format("Deficit de Generación: %.2f", difference) + " KW/h");
        }
        double efficiency = ((totalEnergyGenerated/(totalPower*8.3))*100);
        textViewEfficiency.setText(String.format("Eficiencia Energética: %.2f", efficiency) + " %");
    }
}