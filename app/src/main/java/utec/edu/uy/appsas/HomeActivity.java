package utec.edu.uy.appsas;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    private static boolean doubleBackToExitPressedOnce = false;
    private static String usuario = "";
    private static String token = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        usuario = intent.getStringExtra("usuario");
        token  = intent.getStringExtra("token");
    }

    //Evento OnClick del boton Listar Docentes
    public void go_to_get_docentes(View view){
        Intent intent = new Intent(this, ListarDocentesActivity.class);
        intent.putExtra("usuario",usuario);
        intent.putExtra("token",token);
        startActivity(intent);
    }

    //Evento OnClick del boton Listar Docentes
    public void go_to_get_estudiante(View view){
        Intent intent = new Intent(this, ListarEstuActivity.class);
        intent.putExtra("usuario",usuario);
        intent.putExtra("token",token);
        startActivity(intent);
    }

    //Evento OnClick del boton Crear Docente
    public void go_to_set_docente (View view){
        Intent intent = new Intent(this,CrearDocActivity.class);
        intent.putExtra("usuario",usuario);
        intent.putExtra("token",token);
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this,"Para salir presione nuevamente",Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

}
