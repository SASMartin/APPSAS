package utec.edu.uy.appsas;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.N)
public class CrearDocActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_fNac ,btn_fIng ,btn_fEgre ;
    EditText edt_fNac , edt_fIng, edt_fEgre ;

    private int dia,mes,anio;
    public static  Date fechaNac = null,fechaIng = null , fechaEgre=null ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_doc);

        btn_fNac = (Button)findViewById(R.id.btn_f_nac);
        edt_fNac = (EditText)findViewById(R.id.edt_fecha_nac);

        btn_fNac.setOnClickListener(this);

        btn_fIng= (Button)findViewById(R.id.btn_f_ing);
        edt_fIng = (EditText)findViewById(R.id.edt_fecha_ing);

        btn_fIng.setOnClickListener(this);

        btn_fEgre = (Button)findViewById(R.id.btn_f_egre);
        edt_fEgre = (EditText)findViewById(R.id.edt_fecha_egre);

        btn_fEgre.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v==btn_fNac){
            final Calendar calendarNac = Calendar.getInstance();
            dia = calendarNac.get(Calendar.DAY_OF_MONTH);
            mes = calendarNac.get(Calendar.MONTH);
            anio = calendarNac.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    edt_fNac.setText(dayOfMonth + "/" +(month + 1)+"/" + year );
                }
            },dia,mes,anio);
            datePickerDialog.show();

            fechaNac = calendarNac.getTime();


        }
        if(v==btn_fIng){

            final Calendar calendarIng = Calendar.getInstance();
            dia = calendarIng.get(Calendar.DAY_OF_MONTH);
            mes = calendarIng.get(Calendar.MONTH);
            anio = calendarIng.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    edt_fIng.setText(dayOfMonth + "/" +(month + 1)+"/" + year );
                }
            },dia,mes,anio);
            datePickerDialog.show();

            fechaIng = calendarIng.getTime();


        }

        if (v==btn_fEgre){
            final Calendar calendarEgre = Calendar.getInstance();
            dia = calendarEgre.get(Calendar.DAY_OF_MONTH);
            mes = calendarEgre.get(Calendar.MONTH);
            anio = calendarEgre.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    edt_fEgre.setText(dayOfMonth + "/" +(month + 1)+"/" + year );
                }
            },dia,mes,anio);
            datePickerDialog.show();

            fechaEgre = calendarEgre.getTime();



        }


    }

    //claudio
   /* private class CrearDocenteTask extends AsyncTask<String ,String  , Boolean>{

        @Override
        protected Boolean doInBackground(String... params) {
            boolean result = true;
            HTTPResponse response = null;
            try{

                response = Client.createDocentes(mUsuario,mToken);
                JSONObject objeto = new JSONObject(response.getMessage());





            }catch (Exception ex) {
                Log.e("ServicioRest", "Error!", ex);
                result = false;





            return null;
        }
    }*/






}
