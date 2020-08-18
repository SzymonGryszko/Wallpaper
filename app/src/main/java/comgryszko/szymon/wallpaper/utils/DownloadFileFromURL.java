//package comgryszko.szymon.wallpaper.utils;
//
//import android.Manifest;
//import android.app.Activity;
//import android.app.DownloadManager;
//import android.content.Context;
//import android.content.pm.PackageManager;
//import android.net.Uri;
//import android.os.Environment;
//
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//
//import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
//
//public class DownloadFileFromURL implements ActivityCompat.OnRequestPermissionsResultCallback{
//
//    private static final int REQUEST_WRITE_EXTERNAL_STORAGE_STATE=1;
//    private static String TAG = "DownloadFile";
//
//    public void download(String url, Context context) {
//        int permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//
//        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_EXTERNAL_STORAGE_STATE);
//        } else {
//            //TODO
//        }
//
//
//
//        DownloadManager downloadmanager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
//        Uri uri = Uri.parse(url);
//
//        DownloadManager.Request request = new DownloadManager.Request(uri);
//        request.setTitle("My File");
//        request.setDescription("Downloading");
//        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"wallpapers");
//
//        assert downloadmanager != null;
//        downloadmanager.enqueue(request);
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case REQUEST_READ_PHONE_STATE:
//                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
//                    //TODO
//                }
//                break;
//
//            default:
//                break;
//        }
//    }
//    }}
