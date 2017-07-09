package utec.edu.uy.appsas;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import utec.edu.uy.appsas.model.Estudiante;
import utec.edu.uy.appsas.model.HTTPResponse;


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



            return null;
        }
    }

}
