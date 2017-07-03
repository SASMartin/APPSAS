package utec.edu.uy.appsas;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import org.json.JSONObject;

import java.util.Date;

import utec.edu.uy.appsas.model.HTTPResponse;
import utec.edu.uy.appsas.ws.client.Client;

@RequiresApi(api = Build.VERSION_CODES.N)
public class CrearDocActivity extends AppCompatActivity {
    Button btn_fNac, btn_fIng, btn_fEgre;
    EditText edt_fNac, edt_fIng, edt_fEgre;

    private int dia, mes, anio;
    public static Date fechaNac = null, fechaIng = null, fechaEgre = null;
    static String usuario, token, jsonDocente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_doc);

        Intent intent = getIntent();
        usuario = intent.getStringExtra("usuario");
        token = intent.getStringExtra("token");

        btn_fNac = (Button) findViewById(R.id.btn_f_nac);
        edt_fNac = (EditText) findViewById(R.id.edit_fecha_nac);
        btn_fIng = (Button) findViewById(R.id.btn_f_ing);
        edt_fIng = (EditText) findViewById(R.id.edit_fecha_ing);
        btn_fEgre = (Button) findViewById(R.id.btn_f_egre);
        edt_fEgre = (EditText) findViewById(R.id.edit_fecha_egre);
    }

    //evento del boton fecha nacimiento
    public void fecha_nacimiento(){
        final Calendar calendarNac = Calendar.getInstance();
        dia = calendarNac.get(Calendar.DAY_OF_MONTH);
        mes = calendarNac.get(Calendar.MONTH);
        anio = calendarNac.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edt_fNac.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, dia, mes, anio);
        datePickerDialog.show();

        fechaNac = calendarNac.getTime();
    }

    //evento del boton fecha ingreso
    public void fecha_ingreso(){
        final Calendar calendarIng = Calendar.getInstance();
        dia = calendarIng.get(Calendar.DAY_OF_MONTH);
        mes = calendarIng.get(Calendar.MONTH);
        anio = calendarIng.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edt_fIng.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, dia, mes, anio);
        datePickerDialog.show();

        fechaIng = calendarIng.getTime();
    }

    //evento del boton fecha egreso
    public void fecha_egreso(){
        final Calendar calendarEgre = Calendar.getInstance();
        dia = calendarEgre.get(Calendar.DAY_OF_MONTH);
        mes = calendarEgre.get(Calendar.MONTH);
        anio = calendarEgre.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edt_fEgre.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, dia, mes, anio);
        datePickerDialog.show();

        fechaEgre = calendarEgre.getTime();
    }

    //evento del boton crear
    public void create_docente() {
        //validate();

        //Parseo de Objeto a JSON
        // ObjectMapper mapper = new ObjectMapper();
        //jsonResponse = mapper.writeValueAsString(listaDocentes);

        CrearDocenteTask tarea = new CrearDocenteTask();
        tarea.execute();
    }

    private class CrearDocenteTask extends AsyncTask<String, String, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            boolean result = true;
            HTTPResponse response = null;
            try {
                response = Client.createDocente(usuario, token, jsonDocente);
                JSONObject objeto = new JSONObject(response.getMessage());


            } catch (Exception ex) {
                Log.e("ServicioRest", "Error!", ex);
                result = false;
                return null;
            }
            return result;
        }

    }
}