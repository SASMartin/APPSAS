package utec.edu.uy.appsas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ListarEstuActivity extends AppCompatActivity {

    String usuario, token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_estu);

        Intent intent = getIntent();
        usuario = intent.getStringExtra("usuario");
        token = intent.getStringExtra("token");

        EstudianteFragment estudianteFragment = (EstudianteFragment)getSupportFragmentManager().findFragmentById(R.id.estudiante_container);

        if(estudianteFragment == null){
            estudianteFragment = EstudianteFragment.newInstance(usuario, token);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.docente_container, estudianteFragment)
                    .commit();
        }

    }
}
