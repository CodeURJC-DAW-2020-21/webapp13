package es.webapp13.porthub.api.portfolio_item;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import java.sql.Date;

@Data
public class PortfolioItemDTO {
    
    private long id;

    private String userId;

    private String name;

    private String description;

    private MultipartFile previewImage;

    private MultipartFile image1;

    private MultipartFile image2;

    private MultipartFile image3;

    private String category;

    private String client;

    private String url;

    private Date date;
}
