package com.tamchack.tamchack.service.image;

import com.tamchack.tamchack.exception.ImageNotFoundException;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Service
public class ImageServiceImpl implements ImageService{


    @Value("${book.image.path}")
    private String imagePath;

    @SneakyThrows
    @Override
    public byte[] getImage(String imageName) {
        File file = new File(imagePath, imageName);
        if (!file.exists()) {
            throw new ImageNotFoundException();
        }
        InputStream inputStream = new FileInputStream(file);

        return IOUtils.toByteArray(inputStream);
    }
    
}
