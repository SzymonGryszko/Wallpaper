package comgryszko.szymon.wallpaper.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Photo{
    public int id;
    public int width;
    public int height;
    public String url;
    public String photographer;
    public String photographer_url;
    public int photographer_id;
    public Src src;
    public boolean liked;
}
