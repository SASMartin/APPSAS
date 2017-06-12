package utec.edu.uy.appsas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
        // ¿Existe el view actual?
        if (null == convertView) {
            convertView = inflater.inflate(
                    R.layout.list_item_docente,
                    parent,
                    false);
        }
        // Referencias UI.
        TextView nombre = (TextView) convertView.findViewById(R.id.tv_nombre);
        TextView documento = (TextView) convertView.findViewById(R.id.tv_documento);
        TextView pais = (TextView) convertView.findViewById(R.id.tv_pais);
        // Docente actual.
        Docente docente = getItem(position);
        // Setup.
        nombre.setText(docente.getmNombre());
        documento.setText(docente.getmDocumento());
        pais.setText(docente.getmPais());
        return convertView;
    }

}
