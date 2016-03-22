package json.nilesh.jsonexample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by nilesh on 18/3/16.
 */
public class ActorAdapter extends ArrayAdapter<Actors> {

    ArrayList<Actors> actorsArrayList;
    LayoutInflater layoutInflater;
    int Resource;
    ViewHolder holder;
    Context context;

    public ActorAdapter(Context context, int resource, ArrayList<Actors> objects) {
        super(context, resource, objects);

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource = resource;
        actorsArrayList = objects;
        this.context = context;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // convert view = design
        View v = convertView;
        if (v == null) {
            holder = new ViewHolder();
            v = layoutInflater.inflate(Resource, null);
            holder.imageview = (ImageView) v.findViewById(R.id.ivImage);
            holder.tvName = (TextView) v.findViewById(R.id.tvName);
            holder.tvDescription = (TextView) v.findViewById(R.id.tvDescriptionn);
            holder.tvDOB = (TextView) v.findViewById(R.id.tvDateOfBirth);
            holder.tvCountry = (TextView) v.findViewById(R.id.tvCountry);
            holder.tvHeight = (TextView) v.findViewById(R.id.tvHeight);
            holder.tvSpouse = (TextView) v.findViewById(R.id.tvSpouse);
            holder.tvChildren = (TextView) v.findViewById(R.id.tvChildren);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.imageview.setImageResource(R.drawable.sidvitrech);
        new DownloadImageTask(holder.imageview).execute(actorsArrayList.get(position).getImage());
        holder.tvName.setText(actorsArrayList.get(position).getName());
        holder.tvDescription.setText(actorsArrayList.get(position).getDescription());
        holder.tvDOB.setText("B'day: " + actorsArrayList.get(position).getDob());
        holder.tvCountry.setText(actorsArrayList.get(position).getCountry());
        holder.tvHeight.setText("Height: " + actorsArrayList.get(position).getHeight());
        holder.tvSpouse.setText("Spouse: " + actorsArrayList.get(position).getSpouse());
        holder.tvChildren.setText("Children: " + actorsArrayList.get(position).getChildren());
        return v;

    }

    static class ViewHolder {
        public ImageView imageview;
        public TextView tvName;
        public TextView tvDescription;
        public TextView tvDOB;
        public TextView tvCountry;
        public TextView tvHeight;
        public TextView tvSpouse;
        public TextView tvChildren;

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }

    }
}
