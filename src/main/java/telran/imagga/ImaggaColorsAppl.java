package telran.imagga;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import telran.imagga.dto.ColorTypeDto;
import telran.imagga.dto.ColorsResponseDTO;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class ImaggaColorsAppl {
    public static void main(String[] args) {
        String baseUrl = "https://api.imagga.com/v2/colors";
        String imageUrl = "https://imagga.com/static/images/tagging/wind-farm-538576_640.jpg";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic ");
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl)
                .queryParam("image_url", imageUrl);
        URI url =  builder.build().toUri();
        RequestEntity<String> request = new RequestEntity<>(headers, HttpMethod.GET, url);
        ResponseEntity<ColorsResponseDTO> response = restTemplate.exchange(request, ColorsResponseDTO.class);

        var colorsDto = response.getBody().getResult().getColors();

        List<ColorTypeDto> allColors = new ArrayList<>();
        if (colorsDto.getImage_colors() != null) allColors.addAll(colorsDto.getImage_colors());
        if (colorsDto.getBackground_colors() != null) allColors.addAll(colorsDto.getBackground_colors());
        if (colorsDto.getForeground_colors() != null) allColors.addAll(colorsDto.getForeground_colors());

        System.out.printf("%-25s %-25s %-15s%n", "Color Name", "Parent Color", "Coverage (%)");
        System.out.println("-----------------------------------------------------------------------");
        for (ColorTypeDto color : allColors) {
            System.out.printf("%-25s %-25s %-15.2f%n",
                    color.getClosest_palette_color(),
                    color.getClosest_palette_color_parent(),
                    color.getPercent());
        }



    }
}
