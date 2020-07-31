package comgryszko.szymon.wallpaper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.Switch;

import java.util.ArrayList;

public class GridViewActivity extends AppCompatActivity {
    private static final String TAG = "GridViewActivity";

    private static final String ADAPTER_STATE = "ADAPTER_STATE";
    private GridView gridView;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: gridView");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        String category = getIntent().getExtras().getString("CATEGORY");
        gridView = findViewById(R.id.grid_view);

        switch (category) {
            case "Nature":
                gridView.setAdapter(new PicassoAdapter(getApplicationContext(), getImageURLs1()));
                break;
            case "Modern":
                gridView.setAdapter(new PicassoAdapter(getApplicationContext(), getImageURLs2()));
                break;
            case "Animal":
                gridView.setAdapter(new PicassoAdapter(getApplicationContext(), getImageURLs1()));
                break;
            case "Space":
                gridView.setAdapter(new PicassoAdapter(getApplicationContext(), getImageURLs2()));
                break;
        }

        int gridViewPosition = savedInstanceState == null ? 0 : savedInstanceState.getInt(ADAPTER_STATE);
        gridView.setVerticalScrollbarPosition(gridViewPosition);
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ADAPTER_STATE, gridView.getLastVisiblePosition());
        Log.d(TAG, "onSaveInstanceState: "+ gridView.getLastVisiblePosition());
    }

    private ArrayList<String> getImageURLs1() {
        ArrayList<String> imgURLs = new ArrayList<>();

        imgURLs.add("https://i.redd.it/q3g5motlbe151.jpg");
        imgURLs.add("https://i.redd.it/7j1q4oeczd151.jpg");
        imgURLs.add("https://i.redd.it/q3g5motlbe151.jpg");
        imgURLs.add("https://i.redd.it/7j1q4oeczd151.jpg");
        imgURLs.add("https://i.redd.it/q3g5motlbe151.jpg");
        imgURLs.add("https://i.redd.it/7j1q4oeczd151.jpg");
        imgURLs.add("https://i.redd.it/q3g5motlbe151.jpg");
        imgURLs.add("https://i.redd.it/7j1q4oeczd151.jpg");
        imgURLs.add("https://i.redd.it/q3g5motlbe151.jpg");
        imgURLs.add("https://i.redd.it/7j1q4oeczd151.jpg");
        imgURLs.add("https://i.redd.it/q3g5motlbe151.jpg");
        imgURLs.add("https://i.redd.it/7j1q4oeczd151.jpg");
        imgURLs.add("https://i.redd.it/q3g5motlbe151.jpg");
        imgURLs.add("https://i.redd.it/7j1q4oeczd151.jpg");
        imgURLs.add("https://i.redd.it/q3g5motlbe151.jpg");
        imgURLs.add("https://i.redd.it/7j1q4oeczd151.jpg");
        imgURLs.add("https://i.redd.it/q3g5motlbe151.jpg");
        imgURLs.add("https://i.redd.it/7j1q4oeczd151.jpg");
        imgURLs.add("https://i.redd.it/q3g5motlbe151.jpg");
        imgURLs.add("https://i.redd.it/7j1q4oeczd151.jpg");
        imgURLs.add("https://i.redd.it/q3g5motlbe151.jpg");
        imgURLs.add("https://i.redd.it/7j1q4oeczd151.jpg");


        return imgURLs;
    }

    private ArrayList<String> getImageURLs2() {
        ArrayList<String> imgURLs = new ArrayList<>();
        imgURLs.add("https://i.redd.it/9bf67ygj710z.jpg");
        imgURLs.add("https://c1.staticflickr.com/5/4276/34102458063_7be616b993_o.jpg");
        imgURLs.add("https://i.redd.it/9bf67ygj710z.jpg");
        imgURLs.add("https://c1.staticflickr.com/5/4276/34102458063_7be616b993_o.jpg");
        imgURLs.add("https://i.redd.it/9bf67ygj710z.jpg");
        imgURLs.add("https://c1.staticflickr.com/5/4276/34102458063_7be616b993_o.jpg");
        imgURLs.add("https://i.redd.it/9bf67ygj710z.jpg");
        imgURLs.add("https://c1.staticflickr.com/5/4276/34102458063_7be616b993_o.jpg");
        imgURLs.add("https://i.redd.it/9bf67ygj710z.jpg");
        imgURLs.add("https://c1.staticflickr.com/5/4276/34102458063_7be616b993_o.jpg");
        imgURLs.add("https://i.redd.it/9bf67ygj710z.jpg");
        imgURLs.add("https://c1.staticflickr.com/5/4276/34102458063_7be616b993_o.jpg");
        imgURLs.add("https://i.redd.it/9bf67ygj710z.jpg");
        imgURLs.add("https://c1.staticflickr.com/5/4276/34102458063_7be616b993_o.jpg");
        imgURLs.add("https://i.redd.it/9bf67ygj710z.jpg");
        imgURLs.add("https://c1.staticflickr.com/5/4276/34102458063_7be616b993_o.jpg");
        imgURLs.add("https://i.redd.it/9bf67ygj710z.jpg");
        imgURLs.add("https://c1.staticflickr.com/5/4276/34102458063_7be616b993_o.jpg");
        imgURLs.add("https://i.redd.it/9bf67ygj710z.jpg");
        imgURLs.add("https://c1.staticflickr.com/5/4276/34102458063_7be616b993_o.jpg");






        return imgURLs;
    }

//    private ArrayList<String> getImageURLs2() {
//        ArrayList<String> imgURLs = new ArrayList<>();
//        imgURLs.add("https://i.redd.it/9bf67ygj710z.jpg");
//        imgURLs.add("https://c1.staticflickr.com/5/4276/34102458063_7be616b993_o.jpg");
//        imgURLs.add("https://i.redd.it/q3g5motlbe151.jpg");
//        imgURLs.add("https://i.redd.it/7j1q4oeczd151.jpg");
//        imgURLs.add("https://i.redd.it/nenm7ud6fe151.jpg");
//        imgURLs.add("https://i.redd.it/efji6i28fa151.jpg");
//        imgURLs.add("https://i.redd.it/xq8forhj4e151.jpg");
//        imgURLs.add("https://i.redd.it/4589j03uqe151.jpg");
//        imgURLs.add("https://i.redd.it/9bf67ygj710z.jpg");
//        imgURLs.add("https://c1.staticflickr.com/5/4276/34102458063_7be616b993_o.jpg");
//        imgURLs.add("https://i.redd.it/q3g5motlbe151.jpg");
//        imgURLs.add("https://i.redd.it/7j1q4oeczd151.jpg");
//        imgURLs.add("https://i.redd.it/nenm7ud6fe151.jpg");
//        imgURLs.add("https://i.redd.it/efji6i28fa151.jpg");
//        imgURLs.add("https://i.redd.it/xq8forhj4e151.jpg");
//        imgURLs.add("https://i.redd.it/4589j03uqe151.jpg");
//        imgURLs.add("https://i.redd.it/9bf67ygj710z.jpg");
//        imgURLs.add("https://c1.staticflickr.com/5/4276/34102458063_7be616b993_o.jpg");
//        imgURLs.add("https://i.redd.it/q3g5motlbe151.jpg");
//        imgURLs.add("https://i.redd.it/7j1q4oeczd151.jpg");
//        imgURLs.add("https://i.redd.it/nenm7ud6fe151.jpg");
//        imgURLs.add("https://i.redd.it/efji6i28fa151.jpg");
//        imgURLs.add("https://i.redd.it/xq8forhj4e151.jpg");
//        imgURLs.add("https://i.redd.it/4589j03uqe151.jpg");
//        imgURLs.add("https://i.redd.it/9bf67ygj710z.jpg");
//        imgURLs.add("https://c1.staticflickr.com/5/4276/34102458063_7be616b993_o.jpg");
//        imgURLs.add("https://i.redd.it/q3g5motlbe151.jpg");
//        imgURLs.add("https://i.redd.it/7j1q4oeczd151.jpg");
//        imgURLs.add("https://i.redd.it/nenm7ud6fe151.jpg");
//        imgURLs.add("https://i.redd.it/efji6i28fa151.jpg");
//        imgURLs.add("https://i.redd.it/xq8forhj4e151.jpg");
//        imgURLs.add("https://i.redd.it/4589j03uqe151.jpg");
//        imgURLs.add("https://i.redd.it/4589j03uqe151.jpg");
//
//        return imgURLs;
//    }
}
