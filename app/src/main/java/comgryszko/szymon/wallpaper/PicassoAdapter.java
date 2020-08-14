package comgryszko.szymon.wallpaper;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import comgryszko.szymon.wallpaper.models.Photo;

public class PicassoAdapter extends BaseAdapter {

    private static final String TAG = "PicassoAdapter";

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Photo> photos;
    private List<String> URLs;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public PicassoAdapter(Context context, ArrayList<Photo> photos) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.photos = photos;
        this.URLs = extractPictureURLs(photos);
        Log.d(TAG, "PicassoAdapter: " + this.photos.size());
    }

    @Override
    public int getCount() {
        return photos.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.grid_image, parent, false);
            holder = new ViewHolder();
            assert view != null;

            holder.imageView = view.findViewById(R.id.square_image_view);
            holder.progressBar = view.findViewById(R.id.progress_bar);
            holder.author = view.findViewById(R.id.author);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Picasso.get()
                .load(URLs.get(position))
                .error(R.drawable.ic_launcher_foreground).fit().into(holder.imageView, new Callback() {
            @Override
            public void onSuccess() {
                holder.imageView.setVisibility(View.VISIBLE);
                holder.progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError(Exception e) {
                holder.imageView.setVisibility(View.VISIBLE);
                holder.progressBar.setVisibility(View.INVISIBLE);
            }
        });

        holder.author.setText(photos.get(position).photographer);

        return view;
    }

    static class ViewHolder {
        SquareImageView imageView;
        ProgressBar progressBar;
        TextView author;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<String> extractPictureURLs(ArrayList<Photo> photos) {
        List<String> URLs = new ArrayList<>();
        photos.forEach(photo -> URLs.add(photo.getSrc().large));
        return URLs;
    }

}
