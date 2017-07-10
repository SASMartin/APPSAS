package utec.edu.uy.appsas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import utec.edu.uy.appsas.model.Estudiante;

/**
 * Created by usuario on 09-jul-17.
 */

public class EstudianteAdapter extends ArrayAdapter<Estudiante> {

    public EstudianteAdapter(Context context, List<Estudiante> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtener inflater.
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (null == convertView) {
            convertView = inflater.inflate(
                    R.layout.list_item_estudiante,
                    parent,
                    false);
        }

        TextView nombre = (TextView) convertView.findViewById(R.id.tv_nombre_est);
        TextView documento = (TextView)convertView.findViewById(R.id.tv_documento_est);
        TextView telefono = (TextView)convertView.findViewById(R.id.tv_telefono_est);
        TextView correo = (TextView)convertView.findViewById(R.id.tv_correo_est);
        TextView fechaNac = (TextView)convertView.findViewById(R.id.tv_fecha_nac_est);
        TextView fechaPrimerMat = (TextView)convertView.findViewById(R.id.tv_fecha_mat_est);

        // Docente actual.
        Estudiante estudiante = getItem(position);

        //setup
        nombre.setText(estudiante.getNombre()+" " + estudiante.getApellido());
        documento.setText(estudiante.getDocumento());
        telefono.setText(estudiante.getTelefono());
        correo.setText(estudiante.getCorreo());
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        fechaNac.setText(formatoFecha.format(estudiante.getFechaNac()));
        fechaPrimerMat.setText(formatoFecha.format(estudiante.getFechaPrimerMat()));

        return convertView ;
    }


}
