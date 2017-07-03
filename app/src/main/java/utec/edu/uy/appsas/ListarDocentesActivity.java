package utec.edu.uy.appsas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ListarDocentesActivity extends AppCompatActivity {
    String usuario, token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_docentes);

        Intent intent = getIntent();
        usuario = intent.getStringExtra("usuario");
        token = intent.getStringExtra("token");

        DocenteFragment docenteFragment = (DocenteFragment) getSupportFragmentManager().findFragmentById(R.id.docente_container);

        if(docenteFragment == null){
            docenteFragment = DocenteFragment.newInstance(usuario, token);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.docente_container, docenteFragment)
                    .commit();
        }
    }

}
