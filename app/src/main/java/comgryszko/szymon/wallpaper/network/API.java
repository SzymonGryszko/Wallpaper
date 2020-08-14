package comgryszko.szymon.wallpaper.network;


import java.util.List;

import comgryszko.szymon.wallpaper.models.Root;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

//563492ad6f91700001000001d7306f12a80a469082cd7b15e69eaebd API key
public interface API {


    @GET("search")
    Call<Root> search(@Query("query") String query, @Query("per_page") int perPage, @Query("page") int page);
}
