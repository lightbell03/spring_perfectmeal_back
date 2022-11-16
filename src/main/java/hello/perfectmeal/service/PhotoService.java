package hello.perfectmeal.service;

import hello.perfectmeal.domain.account.Account;
import hello.perfectmeal.domain.food.dto.PhotoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PhotoService {
    public Set<String> analyzePhoto(PhotoDTO photoDTO) throws Exception {
        String base64String = photoDTO.getImgsource().split(",")[1];

        ProcessBuilder processBuilder = new ProcessBuilder("python3", System.getProperty("user.dir")+"/py/test.py", base64String);

        Process process = processBuilder.start();

        InputStream errorStream = process.getErrorStream();
        InputStream inputStream = process.getInputStream();

        String text = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        Set<String> foodList = text.lines().collect(Collectors.toSet());

        return foodList;
    }

}
