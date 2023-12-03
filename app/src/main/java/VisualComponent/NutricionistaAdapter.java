package VisualComponent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import Model.Nutricionista;

public class NutricionistaAdapter extends ArrayAdapter<Nutricionista> {

    public NutricionistaAdapter(Context context, List<Nutricionista> nutricionistas) {
        super(context, 0, nutricionistas);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createView(position, convertView, parent);
    }
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return createView(position, convertView, parent);
    }
    private View createView(int position, View convertView, ViewGroup parent) {
        Nutricionista nutricionista = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(nutricionista.getNome() + " - CRM " + nutricionista.getCrm());

        return convertView;
    }
}
