package telran.imagga.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ColorTypeDto {
    private String closest_palette_color;
    //closest_palette_color = colorName
    private String closest_palette_color_parent;
    //closest_palette_color_parent = parentColorName
    private double percent;
    //percent = coveragePercent
}
