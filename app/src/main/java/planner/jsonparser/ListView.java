package planner.jsonparser;

import android.app.DownloadManager;
import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ListView extends AppCompatActivity {

    String JsonData;
    JSONObject jsonObject;
    JSONArray jsonArray;
    VideoListAdapter videoListAdapter;
    android.widget.ListView listview;
    DownloadManager downloadManager;
    ImageView img;
    String Name,Size,Description,Lecturer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        JsonData = getIntent().getExtras().getString("Json_Data");
        listview = (android.widget.ListView)findViewById(R.id.listview);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {




                        downloadManager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
                        Uri uri = Uri.parse("enter download url");
                        DownloadManager.Request request = new DownloadManager.Request(uri);
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        request.allowScanningByMediaScanner();
                        request.setDestinationInExternalFilesDir(ListView.this, Environment.DIRECTORY_DOWNLOADS,"Sta.jpg");
                        Long reference = downloadManager.enqueue(request);
                        MediaScannerConnection.scanFile(ListView.this, new String[]{uri.toString()}, null, new MediaScannerConnection.OnScanCompletedListener() {
                            @Override
                            public void onScanCompleted(String path, Uri uri) {
                                Toast.makeText(ListView.this,"File Updated",Toast.LENGTH_SHORT).show();
                            }
                        });



            }
        });
        videoListAdapter = new VideoListAdapter(this,R.layout.listview_layout);
        listview.setAdapter(videoListAdapter);
        try {
             jsonObject = new JSONObject(JsonData);
            jsonArray = jsonObject.getJSONArray("Videos");


                for(int i =0; i < jsonArray.length();i++) {
                    JSONObject JObject = jsonArray.getJSONObject(i);
                    Name = JObject.getString("Name");
                    Size = JObject.getString("Size");
                    Description = JObject.getString("Description");
                    Lecturer = JObject.getString("Lecturer");

                    VideoList videolist = new VideoList(Name, Size, Description, Lecturer);
                    videoListAdapter.add(videolist);
                    //Picasso.with(ListView.this).load("http://192.168.43.119/Pictures/215.JPG");
                }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
