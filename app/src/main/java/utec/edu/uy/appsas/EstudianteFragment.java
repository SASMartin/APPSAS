package utec.edu.uy.appsas;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import utec.edu.uy.appsas.model.Estudiante;
import utec.edu.uy.appsas.model.HTTPResponse;
import utec.edu.uy.appsas.model.Pais;
import utec.edu.uy.appsas.ws.client.Client;


public class EstudianteFragment extends Fragment {
    ListView mEstudianteList​;
    static String mUsuario, mToken;

    public EstudianteFragment() {
        // Required empty public constructor
    }

    public static EstudianteFragment newInstance(String usuario, String token) {
        mUsuario = usuario;
        mToken = token;

        EstudianteFragment fragment = new EstudianteFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_estudiante, container, false);
        // Instancia del ListView.
        mEstudianteList​ = (ListView) root.findViewById(R.id.estudiante_list);

        ObtenerEstudianteTask tarea = new ObtenerEstudianteTask();
        tarea.execute();

        return root;
    }

    private class ObtenerEstudianteTask extends AsyncTask<Void,Void,Boolean>{
        private ArrayAdapter<Estudiante> mEstudianteAdapter ;

        @Override
        protected Boolean doInBackground(Void... params) {
            boolean result = true ;
            HTTPResponse response = null;
            try {
                response = Client.getEstudiante(mUsuario, mToken);
                JSONArray respJSON = new JSONArray(response.getMessage());

                List<Estudiante> estudiantes = new ArrayList<>();
                for (int i = 0; i < respJSON.length(); i++) {
                    JSONObject obj = respJSON.getJSONObject(i);
                    String nombre = obj.getString("nombre");
                    String apellido = obj.getString("apellido");
                    String telefono = obj.getString("telefono");
                    String documento = obj.getString("documento");
                    Date fechaNac = converterToDate(obj.getString("fechaNac"));
                    Date fechaPrimerMat =  converterToDate(obj.getString("fechaPrimerMat"));
                    String correo = obj.getString("correo");
                    Pais pais = null;
                    estudiantes.add(new Estudiante(nombre, telefono, documento, apellido, fechaNac, correo, pais, fechaPrimerMat));

                }
                mEstudianteAdapter = new EstudianteAdapter(getActivity(), estudiantes);
            } catch (Exception ex) {
                Log.e("ServicioRest", "Error!", ex);
                result = false;
            }
            return result;

        }
        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean == true) {
                //Relacionando la lista con el adaptador
                mEstudianteList​.setAdapter(mEstudianteAdapter);
            }
        }

    }
    public static Date converterToDate(String dateString) {
        Date date = null;
        if(!dateString.equals("null")){
            try {
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(new Long(dateString));
                date = new Date(cal.getTimeInMillis());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return date;
    }

}
