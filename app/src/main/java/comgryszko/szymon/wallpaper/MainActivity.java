package comgryszko.szymon.wallpaper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    List<Category> categories;
    Adapter adapter;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    private TextSwitcher textSwitcher;
    private TextView poweredBy;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        categories = new ArrayList<>();
        categories.add(new Category(R.drawable.nature, "Nature"));
        categories.add(new Category(R.drawable.city, "City"));
        categories.add(new Category(R.drawable.geometric, "Geometric"));
        categories.add(new Category(R.drawable.space, "Space"));

        textSwitcher = findViewById(R.id.textSwitcher);
        textSwitcher.setCurrentText(categories.get(0).getName());
        textSwitcher.setInAnimation(this, android.R.anim.slide_in_left);
        textSwitcher.setOutAnimation(this, android.R.anim.fade_out);

        poweredBy = findViewById(R.id.poweredby);
        poweredBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.pexels.com/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        adapter = new Adapter(categories, this);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(150, 300, 130, 0);

        colors = new Integer[]{
                ContextCompat.getColor(this, R.color.blue),
                ContextCompat.getColor(this, R.color.dark_orange),
                ContextCompat.getColor(this, R.color.yellow),
                ContextCompat.getColor(this, R.color.green)
        };

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position < (adapter.getCount() - 1) && position < (colors.length - 1)) {
                    viewPager.setBackgroundColor(
                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position + 1]
                            ));
                } else {
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }

            }

            @Override
            public void onPageSelected(int position) {
                textSwitcher.setCurrentText(categories.get(position).getName());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }
}
