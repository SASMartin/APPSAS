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
import java.util.List;

import utec.edu.uy.appsas.model.Docente;
import utec.edu.uy.appsas.model.HTTPResponse;
import utec.edu.uy.appsas.ws.client.Client;

public class DocenteFragment extends Fragment {
    ListView mDocentesList​;
    static String mUsuario, mToken;

    public DocenteFragment() {
        // Required empty public constructor
    }

    public static DocenteFragment newInstance(String usuario, String token) {
        mUsuario = usuario;
        mToken = token;

        DocenteFragment fragment = new DocenteFragment();
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
        View root = inflater.inflate(R.layout.fragment_docentes, container, false);
        // Instancia del ListView.
        mDocentesList​ = (ListView) root.findViewById(R.id.docente_list);

        ObtenerDocentesTask tarea = new ObtenerDocentesTask();
        tarea.execute();

        return root;
    }

    private class ObtenerDocentesTask extends AsyncTask<Void,Void,Boolean> {
        private ArrayAdapter<Docente> mDocentesAdapter​;

        @Override
        protected Boolean doInBackground(Void... params) {
            boolean result = true;
            HTTPResponse response = null;
            try {
                response = Client.getDocentes(mUsuario, mToken);
                JSONArray respJSON = new JSONArray(response.getMessage());
                List<Docente> docentes = new ArrayList<>();
                for (int i = 0; i < respJSON.length(); i++) {
                    JSONObject obj = respJSON.getJSONObject(i);
                    String nombre = obj.getString("nombre");
                    String documento = obj.getString("documento");
                    String pais = obj.getString("apellido");
                    docentes.add(new Docente(nombre, documento, pais));
                }
                mDocentesAdapter​ = new DocenteAdapter(getActivity(), docentes);
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
                mDocentesList​.setAdapter(mDocentesAdapter​);
            }
        }
    }

}
