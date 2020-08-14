package comgryszko.szymon.wallpaper.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Src{
    public String original;
    public String large2x;
    public String large;
    public String medium;
    public String small;
    public String portrait;
    public String landscape;
    public String tiny;
}
