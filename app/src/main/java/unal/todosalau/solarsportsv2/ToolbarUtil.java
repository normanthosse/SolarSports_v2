package unal.todosalau.solarsportsv2;

import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class ToolbarUtil {

    public static void setupToolbar(Activity activity, Toolbar toolbar, Class<?> MajorActivity) {
        // Configura la Toolbar como ActionBar
        if (activity instanceof androidx.appcompat.app.AppCompatActivity) {
            ((androidx.appcompat.app.AppCompatActivity) activity).setSupportActionBar(toolbar);
        } else {
            throw new IllegalArgumentException("Activity must be an instance of AppCompatActivity");
        }

        // Habilita el botón de retroceso en la Toolbar
        if (((androidx.appcompat.app.AppCompatActivity) activity).getSupportActionBar() != null) {
            ((androidx.appcompat.app.AppCompatActivity) activity).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((androidx.appcompat.app.AppCompatActivity) activity).getSupportActionBar().setDisplayShowHomeEnabled(true);
            ((androidx.appcompat.app.AppCompatActivity) activity).getSupportActionBar().setDisplayShowTitleEnabled(false);

        }
            // Configura los botones y enlaces
            setupButtons(activity);

            // Configura el retorno en la Toolbar
            toolbar.setNavigationOnClickListener(v -> {
            Intent intent = new Intent(activity, MajorActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(intent);
           // activity.finish();
        });
        }

    private static void setupButtons(Activity activity) {
        // Configura el botón para registrar nuevo
        Button btnAdd = activity.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(activity, RegisterSpaceActivity.class);
            activity.startActivity(intent);
        });

        // Configura los TextViews para navegar a diferentes actividades
        TextView txvCampos = activity.findViewById(R.id.txvCampos);
        txvCampos.setOnClickListener(v -> {
            Intent intent = new Intent(activity, CategoryCourtActivity.class);
            intent.putExtra("Method", "loadFieldSpaces");
            activity.startActivity(intent);
        });

        TextView txvCanchas = activity.findViewById(R.id.txvCanchas);
        txvCanchas.setOnClickListener(v -> {

            Intent intent = new Intent(activity.getApplicationContext(), CategoryCourtActivity.class);
            intent.putExtra("Method", "loadCourtSpaces");
            activity.startActivity(intent);
        });

        TextView txvSalas = activity.findViewById(R.id.txvSalas);
        txvSalas.setOnClickListener(v -> {

            Intent intent = new Intent(activity, CategoryCourtActivity.class);
            intent.putExtra("Method", "loadRoomSpaces");
            activity.startActivity(intent);
        });

        TextView txvPiscinas = activity.findViewById(R.id.txvPiscinas);
        txvPiscinas.setOnClickListener(v -> {
            Intent intent = new Intent(activity.getApplicationContext(), CategoryCourtActivity.class);
            intent.putExtra("Method", "loadPoolSpaces");
            activity.startActivity(intent);
        });

    }

    public static void setupRecyclerView(Activity activity, RecyclerView recyclerView) { //1.3 Desactiva : Activity activity, RecyclerView recyclerView, SpaceAdapter adapter
        // Configura el LinearLayoutManager para el RecyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity); // Vista en lista
        recyclerView.setLayoutManager(linearLayoutManager); // Set en lista
    }

}
