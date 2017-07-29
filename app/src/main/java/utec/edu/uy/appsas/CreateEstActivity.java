package utec.edu.uy.appsas;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.icu.util.GregorianCalendar;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import utec.edu.uy.appsas.model.Estudiante;
import utec.edu.uy.appsas.model.HTTPResponse;
import utec.edu.uy.appsas.model.Pais;
import utec.edu.uy.appsas.ws.client.Client;

public class CreateEstActivity extends AppCompatActivity {

    Button btn_f_nac ,btn_f_mat,btn_crear_est ;
    EditText edt_nombre , edt_apellido ,edt_telefono,edt_documento,edt_correo;
    EditText edt_f_nac, edt_f_mat ;
    TextView msg_error_est ;
    Spinner spiner_est ;
    TextView textMsgErrorFecha ;

    String paisSelected ;
    HashMap<String,Long>mapaPaises;

    private int dia,mes,anio;
    public static Date fechaNacEst=null;
    public static Date fechaMat = null;
    public static Date fechaActual ;
    static String usuario , token , jsonEstudiante ;


    private final static int HTTP_CODE_CREATED_EST = 201 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_est);

        Intent intent = getIntent();
        usuario = intent.getStringExtra("usuario");
        token= intent.getStringExtra("token");

        edt_nombre = (EditText)findViewById(R.id.edit_nombre_est);
        edt_apellido= (EditText)findViewById(R.id.edit_apellido_est);
        edt_correo = (EditText)findViewById(R.id.edit_correo_est);
        edt_telefono = (EditText)findViewById(R.id.edit_telefono_est);
        edt_documento= (EditText)findViewById(R.id.edit_documento_est);
        edt_f_mat = (EditText)findViewById(R.id.edit_fecha_mat_est);
        edt_f_nac= (EditText)findViewById(R.id.edit_fecha_nac_est);


        btn_crear_est = (Button)findViewById(R.id.btn_crear_est);

        edt_f_nac.setFocusable(false);
        edt_f_mat.setFocusable(false);

        msg_error_est = (TextView)findViewById(R.id.text_msg_est);

        spiner_est = (Spinner)findViewById(R.id.spinner_est);


        String [] paises = getPaises() ;
        spiner_est.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, paises));
        spiner_est.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                paisSelected = (String) adapterView.getItemAtPosition(pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    //evento del boton fecha nacimiento
    public void fecha_nac_est_calendar(View view){
        final Calendar calendarMat_est = Calendar.getInstance();
        dia = calendarMat_est.get(Calendar.DAY_OF_MONTH);
        mes = calendarMat_est.get(Calendar.MONTH);
        anio = calendarMat_est.get(Calendar.YEAR);
        fechaActual = calendarMat_est.getTime();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edt_f_nac.setText(dayOfMonth + "/" + (month+1) + "/" + year);
            }
        }, dia, mes, anio);
        datePickerDialog.getDatePicker().setMaxDate(fechaActual.getTime());
        datePickerDialog.show();


    }
    //evento del boton fecha matriculacion
    public void fecha_mat_est_calendar(View view){
        fechaNacEst = new Date();
        try {
            String pattern = "dd/MM/yyyy";
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            fechaNacEst.setTime(formatter.parse(edt_f_nac.getText().toString()).getTime());
        }catch (Exception ex) {
            Log.e("ServicioRest", "Error!", ex);
        }

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edt_f_mat.setText(dayOfMonth + "/" + (month+1) + "/" + year);
            }
        }, dia, mes, anio);
        datePickerDialog.getDatePicker().setMinDate(fechaNacEst.getTime());
        datePickerDialog.show();


    }

    //evento del boton crear
    public void crear_estudiante(View view){
        if (validate_est()){
            try {
                String pattern = "dd/MM/yyyy";
                SimpleDateFormat formatter = new SimpleDateFormat(pattern);
                fechaMat = new Date();
                fechaNacEst = new Date();
                fechaMat.setTime(formatter.parse(edt_f_mat.getText().toString()).getTime());
                fechaNacEst.setTime(formatter.parse(edt_f_nac.getText().toString()).getTime());
                Estudiante estudiante = null ;
                estudiante = new Estudiante(edt_nombre.getText().toString(),edt_telefono.getText().toString(),edt_documento.getText().toString(),edt_apellido.getText().toString(),
                        fechaNacEst,edt_correo.getText().toString(),new Pais(mapaPaises.get(paisSelected),paisSelected),fechaMat);

                ObjectMapper mapper = new ObjectMapper();
                jsonEstudiante=mapper.writeValueAsString(estudiante);

            }catch (Exception ex) {
                Log.e("ServicioRest", "Error!", ex);
            }
            CrearEstudianteTask tarea =  new CrearEstudianteTask();
            tarea.execute();
        }

    }

    private class CrearEstudianteTask extends AsyncTask<String,String,Boolean>{
        HTTPResponse response = null ;

        @Override
        protected Boolean doInBackground(String... params) {
            Boolean result = true ;
            try{
                response= Client.createEstudiante(usuario,token,jsonEstudiante);
            } catch (Exception ex) {
            Log.e("ServicioRest", "Error!", ex);
            result = false ;}
            return result;
        }
        @Override
        protected void onPostExecute(Boolean aBoolean){
            super.onPostExecute(aBoolean);
            if(response.getCode()==HTTP_CODE_CREATED_EST){
                clean();
                msg_error_est.setText(response.getMessage());
                msg_error_est.setTextColor(Color.GREEN);
            }else{
                msg_error_est.setText(response.getMessage());
                msg_error_est.setTextColor(Color.RED);
            }
        }

    }

    private boolean validate_est(){
        boolean isValid = true;
        if(TextUtils.isEmpty(edt_nombre.getText().toString().trim())) {
            edt_nombre.setError(getString(R.string.error_field_required));
            isValid = false;
        }
        if(TextUtils.isEmpty(edt_apellido.getText().toString().trim())) {
            edt_apellido.setError(getString(R.string.error_field_required));
            isValid = false;
        }
        if(TextUtils.isEmpty(edt_documento.getText().toString().trim())) {
            edt_documento.setError(getString(R.string.error_field_required));
            isValid = false;
        }
        if(TextUtils.isEmpty(edt_f_nac.getText().toString().trim())) {
            edt_f_nac.setError(getString(R.string.error_field_required));
            isValid = false;
        }
        if(TextUtils.isEmpty(edt_f_mat.getText().toString().trim())) {
            edt_f_mat.setError(getString(R.string.error_field_required));
            isValid = false;
        }
        if ((edt_correo.getText().toString().length()>0)){
            boolean correo ;
            correo = (edt_correo.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+"));

            if(correo == false){
                isValid = false;
                edt_correo.setError(getString(R.string.error_correo));
            }
        }

        if(esCIValida(edt_documento.getText().toString())==false){
            edt_documento.setError(getString(R.string.error_documento));
            isValid = false ;
        }
        if (validaNombre(edt_nombre.getText().toString())==false){
            edt_nombre.setError(getString(R.string.error_nombre));
            isValid = false ;
        }
        return isValid;

    }

    //validacion cedula
    public static boolean esCIValida(String ci) {

        if(ci.length() != 7 && ci.length() != 8){
            return false;
        }else{
            try{
                Integer.parseInt(ci);
            }catch (NumberFormatException e){
                return false;
            }
        }

        int digVerificador = Integer.parseInt((ci.charAt(ci.length() - 1)) + "" ) ;
        int[] factores;
        if(ci.length() == 7){ // CI viejas
            factores = new int[]{9, 8, 7, 6, 3, 4};
        }else{
            factores = new int[]{2, 9, 8, 7, 6, 3, 4};
        }


        int suma = 0;
        for(int i=0; i<ci.length()-1; i++ ){
            int digito = Integer.parseInt(ci.charAt(i) + "" ) ;
            suma += digito * factores[ i ];
        }

        int resto = suma % 10;
        int checkdigit = 10 - resto;

        if(checkdigit == 10){
            return (digVerificador == 0);
        }else {
            return (checkdigit == digVerificador) ;
        }

    }
    //validacion nombre
    public static boolean validaNombre (String nom){
        boolean valido;
        valido = nom.matches("([a-z]|[A-Z]|\\^s)+");
        return valido ;
    }




    private String[] getPaises(){
        mapaPaises = new HashMap<>();

        //TODO: Llamo a Rest
        //*** datos de prueba
        mapaPaises.put("Uruguay", new Long(3));
        mapaPaises.put("Argentina", new Long(1));
        mapaPaises.put("Brasil", new Long(2));
        mapaPaises.put("Colombia", new Long(4));
        mapaPaises.put("Venezuela", new Long(5));
        mapaPaises.put("Ecuador", new Long(6));
        mapaPaises.put("Chile", new Long(7));
        mapaPaises.put("Bolivia", new Long(8));
        mapaPaises.put("Paraguay", new Long(9));
        mapaPaises.put("Peru", new Long(10));
        //*** datos de prueba

        String[] arrayPaises = new String[mapaPaises.size()];
        int i=0;
        for(String key : mapaPaises.keySet()) {
            arrayPaises[i] = key;
            i++;
        }

        return arrayPaises;
    }

    private void clean(){
        edt_telefono.setText("");
        edt_nombre.setText("");
        edt_apellido.setText("");
        edt_documento.setText("");
        edt_correo.setText("");
        edt_f_nac.setText("");
        edt_f_mat.setText("");
        msg_error_est.setText("");
    }



}
