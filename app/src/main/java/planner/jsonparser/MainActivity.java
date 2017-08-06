package planner.jsonparser;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    String JsonString;
    Button click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void getJson(View view){
        new getJson().execute("enter e-mail");
    }

    public class getJson extends AsyncTask<String, Void, String>{

        String json_url;
        String json_string;
        @Override
        protected void onPreExecute() {
            json_url = "insert url";
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                String Name = params[0];
                URL url = new URL(json_url);
                HttpURLConnection Urlconnection = (HttpURLConnection)url.openConnection();
                Urlconnection.setRequestMethod("POST");
                Urlconnection.setDoOutput(true);
                Urlconnection.setDoInput(true);
                OutputStream os =  Urlconnection.getOutputStream();
                BufferedWriter bfwriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String postdata = URLEncoder.encode("Name_k","UTF-8")+"="+URLEncoder.encode(Name,"UTF-8");
                bfwriter.write(postdata);
                bfwriter.flush();
                bfwriter.close();
                os.close();
                InputStream is = Urlconnection.getInputStream();
                BufferedReader bfreader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
                String result = " ";
                String Line = " ";
                StringBuilder builder = new StringBuilder();
                while ((json_string = bfreader.readLine())!= null){
                    builder.append(json_string + "\n");
                }
                bfreader.close();
                is.close();
                Urlconnection.disconnect();
                return builder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            TextView textView = (TextView)findViewById(R.id.textView);
            textView.setText(result);
            JsonString = result;
        }

    }

    public void Parse(View View){
        if(JsonString == null){
            Toast.makeText(MainActivity.this,"Such Empty",Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(this,ListView.class);
            intent.putExtra("Json_Data",JsonString);
            startActivity(intent);

        }
    }

}
