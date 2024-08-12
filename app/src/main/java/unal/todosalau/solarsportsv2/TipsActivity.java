package unal.todosalau.solarsportsv2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


public class TipsActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TextView txvTip;

    // Array de identificadores de recursos de imágenes
    int[] imageIds = {
            R.drawable.consumed,
            R.drawable.inclination,
            R.drawable.maintenance,
            R.drawable.generated,
            R.drawable.power
    };
    // Array de recursos de txt
    private String[] titlesTips = {
            "",
            "La inclinación y orientación de los paneles",
            "El mantenimiento del sistema",
            "La eficiencia de los paneles",
            "La Potencia Instalada"
    };

    private ImagePagerAdapter adapter;
    private Handler handler;
    private Runnable runnable;
    private int delay = 3000; // Intervalo de tiempo entre imágenes en milisegundos (3 segundos)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tips);
        // Encuentra el ViewPager en el layout
        viewPager = findViewById(R.id.viewPager);

        txvTip = findViewById(R.id.txvTip);
        Button btnExit = findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setupViewPager();
        loadDailyTips();

    }

    private void loadDailyTips(){  //extructura para que cargue los concejos
        SharedPreferences preferences = getSharedPreferences("dailyTips", MODE_PRIVATE);
        String dailyTip = preferences.getString("currentTip", "CONCEJO NO DISPONIBLE");
        txvTip.setText("Tu concejo para hoy :\n\n" + dailyTip);
    }

    private void setupViewPager(){  //cargar img y txt
        // Crea una instancia del adaptador
        adapter = new ImagePagerAdapter(this, imageIds, titlesTips);
        // Establece el adaptador en el ViewPager
        viewPager.setAdapter(adapter);
        // Configura el Handler para cambiar automáticamente las imágenes
        handler = new Handler(Looper.getMainLooper());
        runnable = new Runnable() {
            @Override
            public void run() {
                int currentItem = viewPager.getCurrentItem();
                int nextItem = (currentItem + 1) % imageIds.length;
                viewPager.setCurrentItem(nextItem, true);
                handler.postDelayed(this, delay);
            }
        };
        // Inicia el cambio automático de imágenes
        handler.postDelayed(runnable, delay);

    }

}