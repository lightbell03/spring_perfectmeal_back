package hello.perfectmeal.service;

import hello.perfectmeal.domain.food.dto.PhotoDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhotoServiceTest {

    @Autowired
    PhotoService photoService;

    @Test
    @DisplayName("python execute test")
    public void pyExec() throws Exception {
        File file = new File(System.getProperty("user.dir") + "/testimage/image.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        fileInputStream.read(bytes);
        String base64String = Base64.getEncoder().encodeToString(bytes);

        PhotoDTO photoDTO = new PhotoDTO();
        photoDTO.setImgsource("," + base64String);
        Set<String> foodList = photoService.analyzePhoto(photoDTO);

        for(String food : foodList) {
            System.out.println("food : " + food);
        }
    }

    @Test
    public void threadTest() throws Exception {
        File file = new File(System.getProperty("user.dir") + "/testimage/image.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        fileInputStream.read(bytes);
        String base64String = Base64.getEncoder().encodeToString(bytes);

        PhotoDTO photoDTO = new PhotoDTO();
        photoDTO.setImgsource("," + base64String);

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                try {
                    Set<String> foodList = photoService.analyzePhoto(photoDTO);
                    for(String food : foodList) {
                        System.out.println("runnable 1 food : " + food);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                try {
                    Set<String> foodList = photoService.analyzePhoto(photoDTO);
                    for(String food : foodList) {
                        System.out.println("runnable 2 food : " + food);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Runnable runnable3 = new Runnable() {
            @Override
            public void run() {
                try {
                    Set<String> foodList = photoService.analyzePhoto(photoDTO);
                    for(String food : foodList) {
                        System.out.println("runnable 3 food : " + food);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };

        runnable1.run();
        runnable2.run();
        runnable3.run();
    }
}