package local.json.nilesh.localjson.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import local.json.nilesh.localjson.R;

/**
 * Created by nilesh on 22/3/16.
 */
public class ActorsAdapter extends ArrayAdapter<Actors> {

    ArrayList<Actors> arrayList;
    LayoutInflater layoutInflater;
    int Resorce;
    ViewHolder holder;

    public ActorsAdapter(Context context, int resource, ArrayList<Actors> objects) {
        super(context, resource, objects);

        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resorce = resource;
        arrayList = objects;

    }

    public View getView(int position, View convert, ViewGroup Parent){

        View view = convert;
        if (view == null){
            holder = new ViewHolder();
            view = layoutInflater.inflate(Resorce, null);
            holder.name = (TextView)view.findViewById(R.id.textView);
            holder.description = (TextView)view.findViewById(R.id.textView2);
            holder.dob = (TextView)view.findViewById(R.id.textView3);
            holder.country = (TextView)view.findViewById(R.id.textView4);
            holder.height = (TextView)view.findViewById(R.id.textView5);
            holder.spouse = (TextView)view.findViewById(R.id.textView6);

            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        holder.name.setText(arrayList.get(position).getName());
        holder.description.setText(arrayList.get(position).getDescription());
        holder.dob.setText(arrayList.get(position).getDob());
        holder.country.setText(arrayList.get(position).getCountry());
        holder.height.setText(arrayList.get(position).getHeight());
        holder.spouse.setText(arrayList.get(position).getSpouse());

        return view;
    }

    static class ViewHolder {

        TextView name;
        TextView description;
        TextView dob;
        TextView country;
        TextView height;
        TextView spouse;

    }
}
