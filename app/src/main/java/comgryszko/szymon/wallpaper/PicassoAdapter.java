package comgryszko.szymon.wallpaper;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PicassoAdapter extends BaseAdapter {

    private static final String TAG = "PicassoAdapter";

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<String> imageURLs;

    public PicassoAdapter(Context context, ArrayList<String> imageURLs) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.imageURLs = imageURLs;
        Log.d(TAG, "PicassoAdapter: " + imageURLs.size());
    }

    @Override
    public int getCount() {
        return imageURLs.size();
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

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Picasso.get()
                .load(imageURLs.get(position)).placeholder(R.drawable.ic_launcher_foreground)
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
        return view;
    }

    static class ViewHolder {
        SquareImageView imageView;
        ProgressBar progressBar;
    }
}
