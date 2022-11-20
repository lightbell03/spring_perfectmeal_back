package hello.perfectmeal.service;

import hello.perfectmeal.domain.food.dto.PhotoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PhotoService {
    public Set<String> analyzePhoto(PhotoDTO photoDTO) throws Exception {
        String base64String = photoDTO.getImgsource();

        ProcessBuilder processBuilder = new ProcessBuilder("python3", System.getProperty("user.dir")+"/py/index.py");
        Process process = processBuilder.start();

        InputStream errorStream = process.getErrorStream();
        InputStream inputStream = process.getInputStream();
        OutputStream outputStream = process.getOutputStream();

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write(base64String);
        bufferedWriter.flush();
        bufferedWriter.close();

        String text = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        inputStream.close();
        List<String> outputList = text.lines().collect(Collectors.toList());

        Set<String> foodSet = new HashSet<>();

        for(String result : outputList){
            if(result.startsWith("image")){
                if(result.contains("no detection")){
                    return foodSet;
                }
                String[] strings = result.split(" ");

                for(int i=4; i<strings.length; i+=2){
                    StringBuilder stringBuilder = new StringBuilder(strings[i]).delete(strings[i].length() - 1, strings[i].length());
                    foodSet.add(stringBuilder.toString());
                }
            }
        }

        return foodSet;
    }

}
