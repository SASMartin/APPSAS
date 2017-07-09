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

import utec.edu.uy.appsas.model.Docente;

public class DocenteAdapter extends ArrayAdapter<Docente> {

    public DocenteAdapter(Context context, List<Docente> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtener inflater.
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (null == convertView) {
            convertView = inflater.inflate(
                    R.layout.list_item_docente,
                    parent,
                    false);
        }

        TextView nombre = (TextView) convertView.findViewById(R.id.tv_nombre_est);
        TextView documento = (TextView)convertView.findViewById(R.id.tv_documento_est);
        TextView telefono = (TextView)convertView.findViewById(R.id.tv_telefono);
        TextView correo = (TextView)convertView.findViewById(R.id.tv_correo);
        TextView fechaNac = (TextView)convertView.findViewById(R.id.tv_fechaNac);
        TextView fechaIngreso = (TextView)convertView.findViewById(R.id.tv_fechaIngreso);
        TextView fechaEgreso = (TextView)convertView.findViewById(R.id.tv_fechaEgreso);

        // Docente actual.
        Docente docente = getItem(position);

        //setup
        nombre.setText(docente.getNombre()+" " + docente.getApellido());
        documento.setText(docente.getDocumento());
        telefono.setText(docente.getTelefono());
        correo.setText(docente.getCorreo());
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        fechaNac.setText(formatoFecha.format(docente.getFechaNac()));
        fechaIngreso.setText(formatoFecha.format(docente.getFechaIngreso()));
        if(docente.getFechaEgreso()!=null)
            fechaEgreso.setText(formatoFecha.format(docente.getFechaEgreso()));
        return convertView ;
    }

}
