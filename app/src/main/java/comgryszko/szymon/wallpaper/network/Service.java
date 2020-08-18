package comgryszko.szymon.wallpaper.network;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import comgryszko.szymon.wallpaper.CategoryActivity.CityCategoryActivity;
import comgryszko.szymon.wallpaper.adapters.PicassoAdapter;
import comgryszko.szymon.wallpaper.models.Photo;
import comgryszko.szymon.wallpaper.models.Root;
import comgryszko.szymon.wallpaper.utils.MyApplication;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Service {

    private static final String TAG = "Service";
    private static final String BASE_URL = "https://api.pexels.com/v1/";
    public static final String HEADER_CACHE_CONTROL = "Cache-Control";
    public static final String HEADER_PRAGMA = "Pragma";
    private static final String PEXELS_KEY = "563492ad6f91700001000001d7306f12a80a469082cd7b15e69eaebd";
    private Context ctx;
    private static Service instance;

    public static Service getInstance(){
        if(instance == null){
            instance = new Service();
        }
        return instance;
    }

    private static final long cacheSize = 10 * 1024 * 1024;


    private static Retrofit retrofit(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static OkHttpClient okHttpClient(){
        return new OkHttpClient.Builder()
                .cache(cache())
                .addInterceptor(headerInterceptor())
                .addInterceptor(loggingInterceptor())
                .addNetworkInterceptor(networkInterceptor()) // only used when network is on
                .addInterceptor(offlineInterceptor())
                .build();
    }

    private static Cache cache(){
        return new Cache(new File(MyApplication.getInstance().getCacheDir(),"Identifier"), cacheSize);
    }


    private static Interceptor loggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    private static Interceptor headerInterceptor(){
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", PEXELS_KEY)
                        .build();
                return chain.proceed(newRequest);
            }
        };
    }

    private static Interceptor offlineInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Log.d(TAG, "offline interceptor: called.");
                Request request = chain.request();

                // prevent caching when network is on. For that we use the "networkInterceptor"
                if (!MyApplication.hasNetwork()) {
                    CacheControl cacheControl = new CacheControl.Builder()
                            .maxStale(7, TimeUnit.DAYS)
                            .build();

                    request = request.newBuilder()
                            .removeHeader(HEADER_PRAGMA)
                            .removeHeader(HEADER_CACHE_CONTROL)
                            .cacheControl(cacheControl)
                            .build();
                }

                return chain.proceed(request);
            }
        };
    }

    /**
     * This interceptor will be called ONLY if the network is available
     * @return
     */
    private static Interceptor networkInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Log.d(TAG, "network interceptor: called.");

                Response response = chain.proceed(chain.request());

                CacheControl cacheControl = new CacheControl.Builder()
                        .maxAge(6, TimeUnit.HOURS)
                        .build();

                return response.newBuilder()
                        .removeHeader(HEADER_PRAGMA)
                        .removeHeader(HEADER_CACHE_CONTROL)
                        .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                        .build();
            }
        };
    }

    public static API getApi(){
        return retrofit().create(API.class);
    }

    public static void APICall(String category, GridView gridView, Context context) {
        Service.getApi().search(category, 20, 1)
                .enqueue(new Callback<Root>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(Call<Root> call, retrofit2.Response<Root> response) {
                        Root root = response.body();
                        Log.d(TAG, "onResponse: " + call.request());
                        Log.d(TAG, "onResponse: " + response.body() + response.code());
                        ArrayList<Photo> photos = new ArrayList<>(root.getPhotos());
                        gridView.setAdapter(new PicassoAdapter(context.getApplicationContext(), photos));
                    }

                    @Override
                    public void onFailure(Call<Root> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t.getMessage());
                        Toast.makeText(context, "Network Fail", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
