package es.webapp13.porthub.utils;

import es.webapp13.porthub.model.PortfolioItem;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface setImage {
    void run(PortfolioItem p, MultipartFile i) throws IOException;
}
