package comgryszko.szymon.wallpaper.CategoryActivity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import comgryszko.szymon.wallpaper.adapters.PicassoAdapter;
import comgryszko.szymon.wallpaper.R;
import comgryszko.szymon.wallpaper.models.Photo;
import comgryszko.szymon.wallpaper.models.Root;
import comgryszko.szymon.wallpaper.network.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NatureCategoryActivity extends AppCompatActivity {
    private static final String TAG = "GridViewActivity";

    private static final String ADAPTER_STATE = "ADAPTER_STATE";
    private GridView gridView;
    static int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: gridView");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        gridView = findViewById(R.id.grid_view);
        Service.APICall(NatureCategoryActivity.this, "nature", gridView, this);

        int gridViewPosition = savedInstanceState == null ? 0 : savedInstanceState.getInt(ADAPTER_STATE);
        gridView.setVerticalScrollbarPosition(gridViewPosition);
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(ADAPTER_STATE, gridView.getFirstVisiblePosition());
        Log.d(TAG, "onSaveInstanceState: " + gridView.getFirstVisiblePosition());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        gridView.setSelection(index);
        super.onResume();
    }

    @Override
    public void onPause() {
        index = gridView.getFirstVisiblePosition();
        super.onPause();
    }

}
