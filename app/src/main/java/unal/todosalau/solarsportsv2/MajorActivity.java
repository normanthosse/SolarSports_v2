package unal.todosalau.solarsportsv2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class MajorActivity extends AppCompatActivity {

    private String [] dailyTips = {
            "La incorrecta orientación delos paneles solares, puede disminuir la producción de energia solar entre el 5% al 20%",
            "Un panel solar inclinado en un ángulo igual a la latitud del lugar, generará mayor cantidad de energía",
            "La producción de energía solar también depende de la epoca del año, la hora del día y las condiciones climáticas",
            "Opta por instalar paneles solares que utilizan tecnologías de celulas solares monocristalinas HIT e IBC, " +
                    "estan se construyen con silicio tipo N y pueden entregar una eficiencia que supera el 20%",
            "Para escoger el mejor panel solar, hay que considerar la calidad de los materiales y " +
                    "su rendimiento en diversos climas o condiciones ambientales",
            "Durante las horas de mayor producción solar, puedes usar los electrodomésticos de mayor consumo energético, " +
                    "como: aires acondicionados, lavadoras y secadoras, hornos eléctricos y microondas, plancha eléctrica, " +
                    "refrigeradores y congeladores, para reducir el consumo de energía de la red eléctrica",
            "Realizar una limpieza del panel semestralmente con agua mineralizada, jabón y esponja suave",
            "Un panel limpio proporciona su mayor capacidad de generación de energía",
            "Elementos como polvo, hojas secas, manchas que dan sombra al panel disminuyen la producción energética del panel"

    };

    private TextView txvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major);

        //Crear archivo "dailyTips" y darle atributo para editar
        SharedPreferences preferences = getSharedPreferences("dailyTips", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        //concejos guardados?
        boolean isInitialized = preferences.getBoolean("isInitialized", false);
        if (!isInitialized) {
            for (int i = 0; i < dailyTips.length; i++) {
                editor.putString("Tip_" + i, dailyTips[i]);
            }
            editor.putBoolean("isInitialized", true);
            editor.apply();
        }

        //Colocar fecha en el layout [colombia]
        txvDate = findViewById(R.id.txvDate);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy", new Locale("es", "CO"));
        String currentDate = sdf.format(new Date());
        txvDate.setText(currentDate);


        Button btnExitMajor = findViewById(R.id.btnExitMajor);
        btnExitMajor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MajorActivity.this, "Saliendo de la APP", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        LinearLayout linlayoutCategory = findViewById(R.id.linlayoutCategory);
        linlayoutCategory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(MajorActivity.this,"Entrando a categorias", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), CategoryFieldActivity.class);
                startActivity(intent);
            }
        });



        LinearLayout linlayoutStatistics = findViewById(R.id.linlayoutStatistics);
        linlayoutStatistics.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(MajorActivity.this,"Ingresando a Estadisticas", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), StatisticsActivity.class);
                startActivity(intent);

            }
        });



        LinearLayout linlayoutTips = findViewById(R.id.linlayoutTips);
        linlayoutTips.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //obtener fecha guarda en sharedpreference
                String saveDate = preferences.getString("saveDate", "");
                //comparar fecha actual con la guardada
                if (!currentDate.equals(saveDate)) {
                    Random random = new Random();
                    int randomIndex = random.nextInt(dailyTips.length);
                    String newTip = dailyTips[randomIndex];
                    editor.putString("currentTip", newTip);
                    editor.putString("saveDate", currentDate);
                    editor.apply();
                }
                Toast.makeText(getApplicationContext(),"Ingresando a TIPS", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), TipsActivity.class);
                startActivity(intent);
            }
        });

    }

}