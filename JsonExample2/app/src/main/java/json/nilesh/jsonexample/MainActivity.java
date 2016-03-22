package json.nilesh.jsonexample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActorAdapter adapter;
        final ArrayList<Actors> actorlist = new ArrayList<Actors>();
        ListView listview = (ListView) findViewById(R.id.list);
        adapter = new ActorAdapter(getApplicationContext(),R.layout.row,actorlist);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), actorlist.get(position).getName(), Toast.LENGTH_LONG).show();
            }
        });



        try {
            String json = "";
            InputStream is = this.getAssets().open("jsonActor.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            //noinspection ResultOfMethodCallIgnored
            is.read(buffer);
            is.close();
           // json = new String(buffer, "UTF-8");
            json = new String(buffer);
            JSONArray jsonArray1 = new JSONArray(json);

            JSONObject object = new JSONObject(String.valueOf(jsonArray1));
            JSONArray jsonArray = object.getJSONArray("actors");
            //noinspection MismatchedQueryAndUpdateOfCollection
            ArrayList<HashMap<String, String>> formlist = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> hashMap;

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object1 = jsonArray.getJSONObject(i);
                Log.d("Details-->", object1.getString("name"));
                String name = object1.getString("name");
                String desc = object1.getString("description");
                String dob = object1.getString("dob");
                String spouse = object1.getString("spouse");
                String children = object1.getString("children");
                String image = object1.getString("image");

                //Add your values in your `ArrayList` as below:
                hashMap = new HashMap<String, String>();
                hashMap.put("actors", name);
                hashMap.put("description", desc);
                hashMap.put("dob", dob);
                hashMap.put("spouse", spouse);
                hashMap.put("children", children);
                hashMap.put("image", image);
                formlist.add(hashMap);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Errorrrrrrrrrrrr",Toast.LENGTH_LONG).show();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



/*
    ArrayList<Actors> actorsList;
    ActorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        actorsList = new ArrayList<Actors>();
        new JSONAsyncTask().execute("http://microblogging.wingnity.com/JSONParsingTutorial/jsonActors");

        ListView listView = (ListView) findViewById(R.id.list);
        adapter = new ActorAdapter(getApplicationContext(),R.layout.row,actorsList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), actorsList.get(position).getName(), Toast.LENGTH_LONG).show();
            }
        });

    }



    class JSONAsyncTask extends AsyncTask<String, Void, Boolean>{

        ProgressDialog dialog;

        protected void onPreExecute(){
            super.onPreExecute();
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setMessage("Loading, please wait");
            dialog.setTitle("Connecting server");
            dialog.show();
            dialog.setCancelable(false);
        }

        @Override
        protected Boolean doInBackground(String... params) {
            try {

                //------------------>>
                HttpGet httppost = new HttpGet(params[0]);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);

                // StatusLine stat = response.getStatusLine();
                int status = response.getStatusLine().getStatusCode();

                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);


                    JSONObject jsono = new JSONObject(data);
                    JSONArray jarray = jsono.getJSONArray("actors");

                    for (int i = 0; i < jarray.length(); i++) {
                        JSONObject object = jarray.getJSONObject(i);

                        Actors actor = new Actors();

                        actor.setName(object.getString("name"));
                        actor.setDescription(object.getString("description"));
                        actor.setDob(object.getString("dob"));
                        actor.setCountry(object.getString("country"));
                        actor.setHeight(object.getString("height"));
                        actor.setSpouse(object.getString("spouse"));
                        actor.setChildren(object.getString("children"));
                        actor.setImage(object.getString("image"));

                        actorsList.add(actor);
                    }
                    return true;
                }

                //------------------>>

            }catch(ParseException p){
                p.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return false;
        }

        protected void onPostExecute(Boolean result) {
            dialog.cancel();
            adapter.notifyDataSetChanged();
            if(result == false)
                Toast.makeText(getApplicationContext(), "Unable to fetch data from server", Toast.LENGTH_LONG).show();

        }
    }*/
    }
//
//
//    public String loadJSONFromAssest() {
//
//        String json = "";
//
//        try {
//            InputStream is = this.getAssets().open("jsonActor.json");
//            int size = is.available();
//            byte[] buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//            json = new String(buffer, "UTF-8");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            Toast.makeText(this,"Error in loadJSONAssest",Toast.LENGTH_LONG).show();
//            return null;
//        }
//
//        return json;
//    }

}
