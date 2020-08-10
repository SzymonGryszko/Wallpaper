package comgryszko.szymon.wallpaper;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface API {

    @GET("{url}")
    Call<List<Object>> getPhotos(
            @Path("url") String url
    );
}
