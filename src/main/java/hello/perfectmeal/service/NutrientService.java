package hello.perfectmeal.service;

import hello.perfectmeal.domain.account.Account;
import hello.perfectmeal.domain.account.Gender;
import hello.perfectmeal.domain.food.Breakfast;
import hello.perfectmeal.domain.food.Dinner;
import hello.perfectmeal.domain.food.Lunch;
import hello.perfectmeal.domain.nutrient.*;
import hello.perfectmeal.repository.nutrient.BreakfastNutrientRepository;
import hello.perfectmeal.repository.nutrient.DinnerNutrientRepository;
import hello.perfectmeal.repository.nutrient.LunchNutrientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.lang.reflect.Field;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NutrientService {

    private static final String SERVICE_KEY = "Cdsoi66sYw3XVCsm5BQwYjqtCLIQckEbbsPgGOEb3vCPjKrQIz%2Bhr2gah8luOhPKaAxxkW5Zdie6SE2%2B6SLkjg%3D%3D";

    private final FoodService foodService;

    private final BreakfastNutrientRepository breakfastNutrientRepository;
    private final LunchNutrientRepository lunchNutrientRepository;
    private final DinnerNutrientRepository dinnerNutrientRepository;

    @Transactional
    public BreakfastNutrient saveBreakfastNutrient(Account account, Breakfast breakfast) throws Exception {
        Set<String> foodSet = breakfast.getFoodSet();

        Nutrient nutrient = getNutrient(foodSet);

        BreakfastNutrient saveBreakfastNutrient = breakfastNutrientRepository.save(
                BreakfastNutrient.builder()
                        .account(account)
                        .breakfast(breakfast)
                        .nutrient(nutrient)
                        .build()
        );

        return saveBreakfastNutrient;
    }

    @Transactional
    public LunchNutrient saveLunchNutrient(Account account, Lunch lunch) throws Exception {
        Set<String> foodSet = lunch.getFoodSet();

        Nutrient nutrient = getNutrient(foodSet);

        LunchNutrient saveLunchNutrient = lunchNutrientRepository.save(
                LunchNutrient.builder()
                        .account(account)
                        .lunch(lunch)
                        .nutrient(nutrient)
                        .build()
        );

        return saveLunchNutrient;
    }

    @Transactional
    public DinnerNutrient saveDinnerNutrient(Account account, Dinner dinner) throws Exception {
        Set<String> foodSet = dinner.getFoodSet();

        Nutrient nutrient = getNutrient(foodSet);

        DinnerNutrient saveDinnerNutrient = dinnerNutrientRepository.save(
                DinnerNutrient.builder()
                        .account(account)
                        .dinner(dinner)
                        .nutrient(nutrient)
                        .build()
        );

        return saveDinnerNutrient;
    }

    private Nutrient getNutrient(Set<String> foodSet) throws Exception {

        List<Nutrient> nutrientList = new ArrayList<>();

        for (String food : foodSet){
            nutrientList.add(getFoodNutrient(food));
        }

        Nutrient nutrient = new Nutrient();

        for(Nutrient n : nutrientList){
            for(Field field : nutrient.getClass().getFields()) {
                Field saveFieldVariable = nutrient.getClass().getDeclaredField(field.getName());
                Field fieldVariable = n.getClass().getDeclaredField(field.getName());

                saveFieldVariable.setAccessible(true);
                fieldVariable.setAccessible(true);

                double nutrientValue = saveFieldVariable.getDouble(nutrient) + fieldVariable.getDouble(n);
                double saveNutrientValue = Math.round(nutrientValue * 100) / 100;
                saveFieldVariable.setDouble(nutrient, saveNutrientValue);
            }
        }

        return nutrient;
    }

    private String getFoodCode(String food) throws Exception {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1390802/AgriFood/MzenFoodCode/getKoreanFoodList"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + SERVICE_KEY); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("food_Name","UTF-8") + "=" + URLEncoder.encode(food, "UTF-8")); /*식품명 (검색어 입력값 포함 검색)*/
        String urlString = urlBuilder.toString();

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(urlString);
        document.getDocumentElement().normalize();

        NodeList nodeList = document.getElementsByTagName("food_Code");
        Node node = nodeList.item(0);

        return node.getTextContent();
    }

    private Nutrient getFoodNutrient(String food) throws Exception {
        String foodCode = getFoodCode(food);

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1390802/AgriFood/MzenFoodNutri/getKoreanFoodIdntList"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + SERVICE_KEY); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("food_Code","UTF-8") + "=" + URLEncoder.encode(foodCode, "UTF-8")); /*음식 코드 식별ID 값 * 식단관리(메뉴젠) 음식 정보 API 참조 (https://www.data.go.kr/data/15077804/openapi.do)*/
        String url = urlBuilder.toString();

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(url);

        document.getDocumentElement().normalize();

        NodeList nodeList = document.getElementsByTagName("idnt_List");
        Nutrient nutrient = new Nutrient();

        for(int i=0; i<nodeList.getLength(); i++){
            Node node = nodeList.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) node;

                for(Field field : nutrient.getClass().getFields()) {
                    Field fieldVariable = nutrient.getClass().getDeclaredField(field.getName());
                    fieldVariable.setAccessible(true);

                    double nutrientValue = fieldVariable.getDouble(nutrient) + getTagValue(field.getName(), element);
                    fieldVariable.setDouble(nutrient, nutrientValue);
                }
            }
        }

        return nutrient;
    }

    private Double getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = nlList.item(0);
        if(nValue == null)
            return null;
        return Double.valueOf(nValue.getNodeValue());
    }

    public Nutrient getTodayTotalNutrient(Account account) throws Exception {
        Breakfast breakfast = foodService.getTodayBreakfast(account);
        Nutrient breakfastNutrient = breakfast == null ? new Nutrient() : breakfast.getBreakfastNutrient().getNutrient();

        Lunch lunch = foodService.getTodayLunch(account);
        Nutrient lunchNutrient = lunch == null ? new Nutrient() : lunch.getLunchNutrient().getNutrient();

        Dinner dinner = foodService.getTodayDinner(account);
        Nutrient dinnerNutrient = dinner == null ? new Nutrient() : dinner.getDinnerNutrient().getNutrient();

        Nutrient nutrient = new Nutrient();

        for (Field field : nutrient.getClass().getFields()){
            Field fieldVariable = Nutrient.class.getField(field.getName());
            fieldVariable.setAccessible(true);

            double breakfastNutrientValue = fieldVariable.getDouble(breakfastNutrient);
            double lunchNutrientValue = fieldVariable.getDouble(lunchNutrient);
            double dinnerNutrientValue = fieldVariable.getDouble(dinnerNutrient);

            double sumNutrient = breakfastNutrientValue + lunchNutrientValue + dinnerNutrientValue;
            fieldVariable.setDouble(nutrient, sumNutrient);
        }

        return nutrient;
    }

    public Nutrient getUnderNutrient(Account account, Nutrient todayTotalNutrient) {
        Nutrient nutrient = new Nutrient();
        if(account.getGender().equals(Gender.MAN)){
            nutrient = UnderNutrient.getManUnderNutrient(account, todayTotalNutrient);
        }
        else if(account.getGender().equals(Gender.WOMAN)){
            nutrient = UnderNutrient.getWomanUnderNutrient(account, todayTotalNutrient);
        }

        return nutrient;
    }
}
