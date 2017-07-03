package utec.edu.uy.appsas;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;

import utec.edu.uy.appsas.model.Docente;
import utec.edu.uy.appsas.model.HTTPResponse;
import utec.edu.uy.appsas.model.Pais;
import utec.edu.uy.appsas.ws.client.Client;

@RequiresApi(api = Build.VERSION_CODES.N)
public class CrearDocActivity extends AppCompatActivity {
    Button btn_fNac, btn_fIng, btn_fEgre;
    EditText edit_telefono, edit_nombre, edit_apellido, edit_documento, edit_correo;
    EditText edit_fNac, edit_fIng, edit_fEgre;

    private int dia, mes, anio;
    public static Date fechaNac = null, fechaIng = null, fechaEgre = null;
    static String usuario, token, jsonDocente;
    
    private final static int HTTP_CODE_CREATED = 201;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_doc);

        Intent intent = getIntent();
        usuario = intent.getStringExtra("usuario");
        token = intent.getStringExtra("token");

        edit_telefono = (EditText) findViewById(R.id.edit_telefono);
        edit_nombre = (EditText) findViewById(R.id.edit_nombre);
        edit_apellido = (EditText) findViewById(R.id.edit_apellido);
        edit_documento = (EditText) findViewById(R.id.edit_documento);
        edit_correo = (EditText) findViewById(R.id.edit_correo);
        btn_fNac = (Button) findViewById(R.id.btn_f_nac);
        edit_fNac = (EditText) findViewById(R.id.edit_fecha_nac);
        btn_fIng = (Button) findViewById(R.id.btn_f_ing);
        edit_fIng = (EditText) findViewById(R.id.edit_fecha_ing);
        btn_fEgre = (Button) findViewById(R.id.btn_f_egre);
        edit_fEgre = (EditText) findViewById(R.id.edit_fecha_egre);
    }

    //evento del boton fecha nacimiento
    public void fecha_nacimiento(View view){
        final Calendar calendarNac = Calendar.getInstance();
        dia = calendarNac.get(Calendar.DAY_OF_MONTH);
        mes = calendarNac.get(Calendar.MONTH);
        anio = calendarNac.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edit_fNac.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, dia, mes, anio);
        datePickerDialog.show();

        fechaNac = calendarNac.getTime();
    }

    //evento del boton fecha ingreso
    public void fecha_ingreso(View view){
        final Calendar calendarIng = Calendar.getInstance();
        dia = calendarIng.get(Calendar.DAY_OF_MONTH);
        mes = calendarIng.get(Calendar.MONTH);
        anio = calendarIng.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edit_fIng.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, dia, mes, anio);
        datePickerDialog.show();

        fechaIng = calendarIng.getTime();
    }

    //evento del boton fecha egreso
    public void fecha_egreso(View view){
        final Calendar calendarEgre = Calendar.getInstance();
        dia = calendarEgre.get(Calendar.DAY_OF_MONTH);
        mes = calendarEgre.get(Calendar.MONTH);
        anio = calendarEgre.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edit_fEgre.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, dia, mes, anio);
        datePickerDialog.show();

        fechaEgre = calendarEgre.getTime();
    }

    //evento del boton crear
    public void create_docente(View view) {
        if(validate()) {
            try {
                Docente docente = new Docente(
                        edit_nombre.getText().toString(),
                        edit_telefono.getText().toString(),
                        edit_documento.getText().toString(),
                        edit_apellido.getText().toString(),
                        fechaNac,
                        edit_correo.getText().toString(),
                        new Pais(new Long(3), "Uruguay"),
                        fechaEgre,
                        fechaIng);

                //Parseo de Objeto a JSON
                ObjectMapper mapper = new ObjectMapper();
                jsonDocente = mapper.writeValueAsString(docente);
            } catch (Exception ex) {
                Log.e("ServicioRest", "Error!", ex);
            }
            CrearDocenteTask tarea = new CrearDocenteTask();
            tarea.execute();
        }
    }

    private class CrearDocenteTask extends AsyncTask<String, String, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            boolean result = true;
            HTTPResponse response = null;
            try {
                response = Client.createDocente(usuario, token, jsonDocente);
                Toast.makeText(CrearDocActivity.this,response.getMessage(), Toast.LENGTH_LONG);
                if(response.getCode()==HTTP_CODE_CREATED)
                    clean();
            } catch (Exception ex) {
                Log.e("ServicioRest", "Error!", ex);
                result = false;
            }
            return result;
        }

    }

    //Valida los campos de texto requeridos
    private boolean validate(){
        boolean isValid = true;
        if(TextUtils.isEmpty(edit_nombre.getText().toString().trim())) {
            edit_nombre.setError(getString(R.string.error_field_required));
            isValid = false;
        }
        if(TextUtils.isEmpty(edit_apellido.getText().toString().trim())) {
            edit_apellido.setError(getString(R.string.error_field_required));
            isValid = false;
        }
        if(TextUtils.isEmpty(edit_documento.getText().toString().trim())) {
            edit_documento.setError(getString(R.string.error_field_required));
            isValid = false;
        }
        if(TextUtils.isEmpty(edit_fNac.getText().toString().trim())) {
            edit_fNac.setError(getString(R.string.error_field_required));
            isValid = false;
        }
        if(TextUtils.isEmpty(edit_fIng.getText().toString().trim())) {
            edit_fIng.setError(getString(R.string.error_field_required));
            isValid = false;
        }
        return isValid;
    }

    //Limpia los campos de texto
    private void clean(){
        edit_telefono.setText("");
        edit_nombre.setText("");
        edit_apellido.setText("");
        edit_documento.setText("");
        edit_correo.setText("");
        edit_fNac.setText("");
        edit_fIng.setText("");
        edit_fEgre.setText("");
    }
    
}