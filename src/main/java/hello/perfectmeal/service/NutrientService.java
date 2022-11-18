package hello.perfectmeal.service;

import hello.perfectmeal.domain.account.Account;
import hello.perfectmeal.domain.food.Breakfast;
import hello.perfectmeal.domain.food.Dinner;
import hello.perfectmeal.domain.food.Lunch;
import hello.perfectmeal.domain.nutrient.BreakfastNutrient;
import hello.perfectmeal.domain.nutrient.DinnerNutrient;
import hello.perfectmeal.domain.nutrient.LunchNutrient;
import hello.perfectmeal.domain.nutrient.Nutrient;
import hello.perfectmeal.repository.nutrient.BreakfastNutrientRepository;
import hello.perfectmeal.repository.nutrient.DinnerNutrientRepository;
import hello.perfectmeal.repository.nutrient.LunchNutrientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class NutrientService {

    private static final String SERVICE_KEY = "Cdsoi66sYw3XVCsm5BQwYjqtCLIQckEbbsPgGOEb3vCPjKrQIz%2Bhr2gah8luOhPKaAxxkW5Zdie6SE2%2B6SLkjg%3D%3D";
    private final BreakfastNutrientRepository breakfastNutrientRepository;
    private final LunchNutrientRepository lunchNutrientRepository;
    private final DinnerNutrientRepository dinnerNutrientRepository;

    public BreakfastNutrient saveBreakfastNutrient(Account account, Breakfast breakfast) throws Exception {
        Set<String> foodSet = breakfast.getFoodSet();
        List<Nutrient> nutrientList = new ArrayList<>();

        for (String food : foodSet){
            nutrientList.add(getNutrient(food));
        }

        Nutrient nutrient = new Nutrient();

        for(Nutrient n : nutrientList){
            for(Field field : nutrient.getClass().getFields()) {
                Field saveFieldVariable = nutrient.getClass().getDeclaredField(field.getName());
                Field fieldVariable = n.getClass().getDeclaredField(field.getName());

                saveFieldVariable.setAccessible(true);
                fieldVariable.setAccessible(true);

                double nutrientValue = saveFieldVariable.getDouble(nutrient) + fieldVariable.getDouble(n);

                saveFieldVariable.setDouble(nutrient, nutrientValue);
            }
        }

        BreakfastNutrient breakfastNutrient = BreakfastNutrient.builder()
                .account(account)
                .breakfast(breakfast)
                .nutrient(nutrient)
                .build();

        return breakfastNutrientRepository.save(breakfastNutrient);
    }

    public LunchNutrient saveLunchNutrient(Lunch lunch){

        return null;
    }

    public DinnerNutrient saveDinnerNutrient(Dinner dinner){

        return null;
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

    private Nutrient getNutrient(String food) throws Exception {
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

    public BreakfastNutrient getBreakfastNutrient(Account account, Breakfast breakfast){
        return breakfastNutrientRepository.findByAccountAndBreakfast(account, breakfast)
                .orElse(null);
    }

    public LunchNutrient getLunchNutrient(Account account, Lunch lunch){
        return lunchNutrientRepository.findByAccountAndLunch(account, lunch)
                .orElse(null);
    }

    public DinnerNutrient getDinnerNutrient(Account account, Dinner dinner){
        return dinnerNutrientRepository.findByAccountAndDinner(account, dinner)
                .orElse(null);
    }
}
