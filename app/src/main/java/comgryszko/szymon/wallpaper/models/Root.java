package comgryszko.szymon.wallpaper.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Root{
    public int total_results;
    public int page;
    public int per_page;
    public List<Photo> photos;
    public String next_page;
}
