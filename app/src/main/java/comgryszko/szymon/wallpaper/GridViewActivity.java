package comgryszko.szymon.wallpaper;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import comgryszko.szymon.wallpaper.models.Photo;
import comgryszko.szymon.wallpaper.models.Root;
import comgryszko.szymon.wallpaper.network.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GridViewActivity extends AppCompatActivity {
    private static final String TAG = "GridViewActivity";

    private static final String ADAPTER_STATE = "ADAPTER_STATE";
    private GridView gridView;
    static int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: gridView");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        String category = getIntent().getExtras().getString("CATEGORY");
        gridView = findViewById(R.id.grid_view);

        switch (category) {
            case "Nature":
                APICall("nature");
                break;
            case "City":
                APICall("city");
                break;
            case "Geometric":
                APICall("geometric");
                break;
            case "Space":
                APICall("space");
                break;
        }

        int gridViewPosition = savedInstanceState == null ? 0 : savedInstanceState.getInt(ADAPTER_STATE);
        gridView.setVerticalScrollbarPosition(gridViewPosition);
    }

    private void APICall(String category) {
        Service.getApi().search(category,20,1)
                .enqueue(new Callback<Root>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(Call<Root> call, Response<Root> response) {
                        Root root = response.body();
                        Log.d(TAG, "onResponse: "+ call.request());
                        Log.d(TAG, "onResponse: " + response.body() + response.code());
                        ArrayList<Photo> photos = new ArrayList<>(root.getPhotos());
                        gridView.setAdapter(new PicassoAdapter(getApplicationContext(), photos));
                    }

                    @Override
                    public void onFailure(Call<Root> call, Throwable t) {
                        Log.d(TAG, "onFailure: "+ t.getMessage());
                        Toast.makeText(GridViewActivity.this, "Network Fail", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(ADAPTER_STATE, gridView.getFirstVisiblePosition());
        Log.d(TAG, "onSaveInstanceState: "+ gridView.getFirstVisiblePosition());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume(){
        gridView.setSelection(index);
        super.onResume();
    }

    @Override
    public void onPause(){
        index = gridView.getFirstVisiblePosition();
        super.onPause();
    }

}
