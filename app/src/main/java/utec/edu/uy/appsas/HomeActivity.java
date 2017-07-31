package utec.edu.uy.appsas;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

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

        CircleMenu circleMenu = (CircleMenu)findViewById(R.id.circulo_menu);
        circleMenu.setMainMenu(Color.parseColor("#5EFFEF"),R.drawable.push_button,R.drawable.logo)
                .addSubMenu(Color.parseColor("#C2FFED"),R.drawable.profe_ingreso)
                .addSubMenu(Color.parseColor("#C2FFED"),R.drawable.estu_ingreso)
                .addSubMenu(Color.parseColor("#C2FFED"),R.drawable.profe_listado)
                .addSubMenu(Color.parseColor("#C2FFED"),R.drawable.estu_listado)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int index) {
                        switch (index){
                            case 0 :
                                Intent intent = new Intent(getBaseContext(), ListarDocentesActivity.class);
                                intent.putExtra("usuario",usuario);
                                intent.putExtra("token",token);
                                startActivity(intent);
                                break;
                            case 1:
                                intent = new Intent(getBaseContext(), ListarEstuActivity.class);
                                intent.putExtra("usuario",usuario);
                                intent.putExtra("token",token);
                                startActivity(intent);
                                break;
                            case 2:
                                intent = new Intent(getBaseContext(),CrearDocActivity.class);
                                intent.putExtra("usuario",usuario);
                                intent.putExtra("token",token);
                                startActivity(intent);
                                break;
                            case 3:
                                intent = new Intent(getBaseContext(),CreateEstActivity.class);
                                intent.putExtra("usuario",usuario);
                                intent.putExtra("token",token);
                                startActivity(intent);
                                break;

                        }
                    }
                });

    }

    @Override
    public void onBackPressed(){
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this,getString(R.string.evento_atras),Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

}
