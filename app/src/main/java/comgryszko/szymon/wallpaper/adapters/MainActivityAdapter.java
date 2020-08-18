package comgryszko.szymon.wallpaper.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

import comgryszko.szymon.wallpaper.CategoryActivity.CityCategoryActivity;
import comgryszko.szymon.wallpaper.CategoryActivity.GeometricCategoryActivity;
import comgryszko.szymon.wallpaper.CategoryActivity.NatureCategoryActivity;
import comgryszko.szymon.wallpaper.CategoryActivity.SpaceCategoryActivity;
import comgryszko.szymon.wallpaper.R;
import comgryszko.szymon.wallpaper.models.Category;

public class MainActivityAdapter extends PagerAdapter {

    private List<Category> categories;
    private LayoutInflater layoutInflater;
    private Context context;

    public MainActivityAdapter(List<Category> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = LayoutInflater.from(context);
        final View view = layoutInflater.inflate(R.layout.card_item, container, false);

        ImageView imageView = view.findViewById(R.id.card_image_view);
        imageView.setImageResource(categories.get(position).getImage());

        container.addView(view, 0);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position){
                    case 0:
                        Intent intentNature =  new Intent(context, NatureCategoryActivity.class);
                        context.startActivity(intentNature);
                        break;
                    case 1:
                        Intent intentCity =  new Intent(context, CityCategoryActivity.class);
                        context.startActivity(intentCity);
                        break;
                    case 2:
                        Intent intentGeometric =  new Intent(context, GeometricCategoryActivity.class);
                        context.startActivity(intentGeometric);
                        break;
                    case 3:
                        Intent intentSpace =  new Intent(context, SpaceCategoryActivity.class);
                        context.startActivity(intentSpace);
                        break;

                }

            }
        });

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
