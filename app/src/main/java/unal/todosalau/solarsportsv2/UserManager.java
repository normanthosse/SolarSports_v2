package unal.todosalau.solarsportsv2;

import android.content.Context;
import android.content.SharedPreferences;

public class UserManager {
    private static final String PREF_NAME = "UserPrefs"; //archivo para guardar los registros de usuario
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor; //editor para guardar los registros

    public UserManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);  //instancia del SahrePreference asegurando que solo la aplicaciòn la use
        editor=sharedPreferences.edit();
    }

    public void  registerUser(String email, String password) { //metodo registrar usuario
        editor.putString(KEY_EMAIL, email); // guarda email y clave
        editor.putString(KEY_PASSWORD, password); //guarda password y clave
        editor.apply();                         // guarda cambios forma asincronica
    }

    public boolean loginUser(String email, String password){
        String registeredEmail = sharedPreferences.getString(KEY_EMAIL, null);
        String registeredPassword = sharedPreferences.getString(KEY_PASSWORD, null);
        return email.equals(registeredEmail) && password.equals(registeredPassword); // validación
    }
}
