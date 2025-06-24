package telran.imagga.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ColorsDto {
    private List<ColorTypeDto> image_colors;
    private List<ColorTypeDto> foreground_colors;
    private List<ColorTypeDto> background_colors;

}
