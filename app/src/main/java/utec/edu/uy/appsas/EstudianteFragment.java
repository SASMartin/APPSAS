package utec.edu.uy.appsas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class EstudianteFragment extends Fragment {
    ListView mEstudianteList​;
    static String mUsuario, mToken;

    public EstudianteFragment() {
        // Required empty public constructor
    }

    public static EstudianteFragment newInstance(String param1, String param2) {
        EstudianteFragment fragment = new EstudianteFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_estudiante, container, false);
    }

}
