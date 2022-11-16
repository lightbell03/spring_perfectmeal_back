package hello.perfectmeal.controller;

import hello.perfectmeal.domain.account.Account;
import hello.perfectmeal.domain.food.dto.FoodDTO;
import hello.perfectmeal.domain.food.dto.PhotoDTO;
import hello.perfectmeal.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    @PostMapping("/api/photo")
    public ResponseEntity analyzePhoto(
            @RequestBody PhotoDTO photoDTO
    ) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = (Account) authentication.getPrincipal();

        Set<String> foodSet = photoService.analyzePhoto(photoDTO);

        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setFoodSet(foodSet);

        return ResponseEntity.ok(foodSet);
    }
}
