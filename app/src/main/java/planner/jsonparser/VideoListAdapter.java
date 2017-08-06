package planner.jsonparser;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by tumi on 2017/07/07.
 */

public class VideoListAdapter extends ArrayAdapter {

    List list = new ArrayList();

    public VideoListAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    public void add(@Nullable VideoList object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {

        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listlayout;

        listlayout = convertView;
        final VideoListHolder videoListHolder;
        if (listlayout == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            listlayout = layoutInflater.inflate(R.layout.listview_layout, parent, false);
            videoListHolder = new VideoListHolder();
            videoListHolder.txtName = (TextView) listlayout.findViewById(R.id.txtName);
            videoListHolder.txtDescription = (TextView) listlayout.findViewById(R.id.txtDesc);
            videoListHolder.txtLecturer = (TextView) listlayout.findViewById(R.id.txtLecture);
            videoListHolder.txtSize = (TextView) listlayout.findViewById(R.id.txtSize);
            listlayout.setTag(videoListHolder);

        } else {
            videoListHolder = (VideoListHolder) listlayout.getTag();
        }

        VideoList videoList = (VideoList) this.getItem(position);
        videoListHolder.txtName.setText("Name: " + videoList.getName());
        videoListHolder.txtDescription.setText("Descripition: " +videoList.getDescription());
        videoListHolder.txtLecturer.setText("Lecturer: " +videoList.getLecturer());
        videoListHolder.txtSize.setText("Size: " +videoList.getSize());

        return listlayout;


    }

    static class VideoListHolder {
        TextView txtName, txtSize, txtDescription, txtLecturer;
    }
}
