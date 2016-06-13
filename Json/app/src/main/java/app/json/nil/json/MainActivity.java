package app.json.nil.json;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {

    private static int responsecode;
//    EditText email, password,twitter;
    Button post;
    Persona persona;
    TextView textView,body,code;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        post = (Button) findViewById(R.id.button);

        if(isConnected()){
            textView.setBackgroundColor(0xFF00CC00);
            textView.setText("You are conncted");
        }
        else{
            textView.setText("You are NOT conncted");
        }

        post.setOnClickListener(this);

    }

    public String POST(String url, Persona persona){
        InputStream inputStream = null;
        String result = "";
        String rbody;
        int rcode;

        try{

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://192.168.0.3/api/user/login/");
            // Add your data
            List < NameValuePair > nameValuePairs = new ArrayList < NameValuePair > (4);

            //Registration code
//            nameValuePairs.add(new BasicNameValuePair("password", "aadmin12345"));
//            nameValuePairs.add(new BasicNameValuePair("confirm_password", "aadmin12345"));
//            nameValuePairs.add(new BasicNameValuePair("email", "nilesh.tawar.sid@gmail.com"));
//            nameValuePairs.add(new BasicNameValuePair("full_name", "Nilesh Tawar"));
//            nameValuePairs.add(new BasicNameValuePair("source", "USER_APP"));

            //Log In Code
            nameValuePairs.add(new BasicNameValuePair("email", "nilesh.tawar.sid@gmail.com"));
            nameValuePairs.add(new BasicNameValuePair("password", "aadmin12345"));
            nameValuePairs.add(new BasicNameValuePair("source", "USER_APP"));

            try {
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                Log.d("myapp", "works till here. 2");
                try {
                    HttpResponse response = httpclient.execute(httppost);
                    rbody = EntityUtils.toString(response.getEntity());
                    Log.d("myapp", "response " + response.getEntity());
                    rcode = response.getStatusLine().getStatusCode();
                    System.out.println("Code : " + rbody);
                    System.out.println("Status: " + rcode);

                    URL myUrl = new URL(url);
                    URLConnection connection = myUrl.openConnection();
                    connection.setConnectTimeout(6000);
                    connection.connect();
                    System.out.println("Server is available");

//                    if (rcode == 200){
//                        System.out.println("Successfully Log In");
//                        Intent intent = new Intent(MainActivity.this, SampleActivity.class);
//                        startActivity(intent);
//                    }else if (rcode == 412){
//                        System.out.println("Error in Log In");
//                    }else{
//                        System.out.println("Error occure cant connect");
//                    }

                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    System.out.println("Server not available");
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";
        }catch (Exception e)
        {
            Log.d("InputStream",e.getLocalizedMessage());
        }

        return result;
    }

    private static String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (NameValuePair pair : params)
        {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }

        return result.toString();
    }


    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.button:
                new HttpAsyncTask().execute("http://192.168.0.3/api/user/login/");
                break;
        }

    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            persona = new Persona();
            return POST(urls[0],persona);
        }

    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = " ";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }
}
