package comgryszko.szymon.wallpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

public class GridViewActivity extends AppCompatActivity {

    private static final String ADAPTER_STATE = "ADAPTER_STATE";
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        gridView = findViewById(R.id.grid_view);
        ArrayList<String> imgURLs = getImageURLs();

        gridView.setAdapter(new PicassoAdapter(getApplicationContext(), imgURLs));
    }

    private ArrayList<String> getImageURLs() {
        ArrayList<String> imgURLs = new ArrayList<>();
        imgURLs.add("https://i.redd.it/9bf67ygj710z.jpg");
        imgURLs.add("https://c1.staticflickr.com/5/4276/34102458063_7be616b993_o.jpg");
        imgURLs.add("https://i.redd.it/q3g5motlbe151.jpg");
        imgURLs.add("https://i.redd.it/7j1q4oeczd151.jpg");
        imgURLs.add("https://i.redd.it/nenm7ud6fe151.jpg");
        imgURLs.add("https://i.redd.it/efji6i28fa151.jpg");
        imgURLs.add("https://i.redd.it/xq8forhj4e151.jpg");
        imgURLs.add("https://i.redd.it/4589j03uqe151.jpg");
        imgURLs.add("https://i.redd.it/9bf67ygj710z.jpg");
        imgURLs.add("https://c1.staticflickr.com/5/4276/34102458063_7be616b993_o.jpg");
        imgURLs.add("https://i.redd.it/q3g5motlbe151.jpg");
        imgURLs.add("https://i.redd.it/7j1q4oeczd151.jpg");
        imgURLs.add("https://i.redd.it/nenm7ud6fe151.jpg");
        imgURLs.add("https://i.redd.it/efji6i28fa151.jpg");
        imgURLs.add("https://i.redd.it/xq8forhj4e151.jpg");
        imgURLs.add("https://i.redd.it/4589j03uqe151.jpg");
        imgURLs.add("https://i.redd.it/9bf67ygj710z.jpg");
        imgURLs.add("https://c1.staticflickr.com/5/4276/34102458063_7be616b993_o.jpg");
        imgURLs.add("https://i.redd.it/q3g5motlbe151.jpg");
        imgURLs.add("https://i.redd.it/7j1q4oeczd151.jpg");
        imgURLs.add("https://i.redd.it/nenm7ud6fe151.jpg");
        imgURLs.add("https://i.redd.it/efji6i28fa151.jpg");
        imgURLs.add("https://i.redd.it/xq8forhj4e151.jpg");
        imgURLs.add("https://i.redd.it/4589j03uqe151.jpg");
        imgURLs.add("https://i.redd.it/9bf67ygj710z.jpg");
        imgURLs.add("https://c1.staticflickr.com/5/4276/34102458063_7be616b993_o.jpg");
        imgURLs.add("https://i.redd.it/q3g5motlbe151.jpg");
        imgURLs.add("https://i.redd.it/7j1q4oeczd151.jpg");
        imgURLs.add("https://i.redd.it/nenm7ud6fe151.jpg");
        imgURLs.add("https://i.redd.it/efji6i28fa151.jpg");
        imgURLs.add("https://i.redd.it/xq8forhj4e151.jpg");
        imgURLs.add("https://i.redd.it/4589j03uqe151.jpg");
        imgURLs.add("https://i.redd.it/4589j03uqe151.jpg");

        return imgURLs;
    }
}
