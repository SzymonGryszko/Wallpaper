package comgryszko.szymon.wallpaper.adapters;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import comgryszko.szymon.wallpaper.R;
import comgryszko.szymon.wallpaper.utils.PicassoImageDownloader;
import comgryszko.szymon.wallpaper.utils.SquareImageView;
import comgryszko.szymon.wallpaper.models.Photo;

public class PicassoAdapter extends BaseAdapter {

    private static final String TAG = "PicassoAdapter";
    private static final int WRITE_EXTERNAL_STORAGE_CODE = 1;

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Photo> photos;
    private List<String> URLs;
    Activity activity;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public PicassoAdapter(Activity activity, Context context, ArrayList<Photo> photos) {
        inflater = LayoutInflater.from(context);
        this.activity = activity;
        this.context = context;
        this.photos = photos;
        this.URLs = extractPictureLargeURLs(photos);
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

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d(TAG, "onLongClick: ");
//                Uri uri =   Uri.parse(URLs.get(position));
//                Intent intent = new Intent(Intent.ACTION_ATTACH_DATA);
//                intent.addCategory(Intent.CATEGORY_DEFAULT);
//                intent.setDataAndType(uri, "image/*");
//                intent.putExtra("mimeType", "image/*");
//                activity.startActivity(Intent.createChooser(intent, "Set as:"));
                setAsWallpaper(position);
                PicassoImageDownloader.imageDownload(URLs.get(position));
                String path = PicassoImageDownloader.getFileFullPath(URLs.get(position));
                Log.d(TAG, "onLongClick: " + path);

                return true;

            }
        });

        holder.author.setText(photos.get(position).photographer);

        return view;
    }





    private void setAsWallpaper(int position) {
        Picasso.get().load(photos.get(position).getSrc().getPortrait()).into(new Target(){

            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
                try {
                    wallpaperManager.setBitmap(bitmap);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Log.d("TAG", "onBitmapLoaded: ");
                Toast.makeText(context, "Wallpaper Changed", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                Toast.makeText(context, "Loading image failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                Toast.makeText(context, "Downloading image", Toast.LENGTH_SHORT).show();
            }


        });
    }

    static class ViewHolder {
        SquareImageView imageView;
        ProgressBar progressBar;
        TextView author;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<String> extractPictureLargeURLs(ArrayList<Photo> photos) {
        List<String> URLs = new ArrayList<>();
        photos.forEach(photo -> URLs.add(photo.getSrc().large));
        return URLs;
    }

}
