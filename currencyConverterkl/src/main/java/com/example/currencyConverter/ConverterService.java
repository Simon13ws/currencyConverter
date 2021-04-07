package com.example.currencyConverter;

import org.json.*;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ConverterService {

    private static Map <String, Float> courses = new HashMap<>();



    //Funkcja pobierająca kursy walut z dwóch tabel znajdujących się na stonie NBP. Wykonywana cyklicznie codziennie o 8:00. Synchronizacja z funkcją służącą do konwersji podanej kwoty.
    @Scheduled(cron = "0 0 8 * * ?")
    public static synchronized void getCourses() {
        char[] tables = {'a', 'b'};
        RestTemplate restTemplate = new RestTemplate();
        for (char c : tables) {
            String jsonResponse = restTemplate.getForObject("http://api.nbp.pl/api/exchangerates/tables/" + c + "/?format=json", String.class);
            JSONObject json = new JSONObject(jsonResponse.substring(1, jsonResponse.length() - 1));
            JSONArray jsonArray = json.getJSONArray("rates");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                courses.put(jsonObject.getString("code"), jsonObject.getFloat("mid"));
            }
        }
    }
    //Funkcja służąca do konwersji podanej kwoty z danej waluty(baseCurrency) na docelową walutę (desiredCurrency)
    public synchronized float convert(float value, String baseCurrency, String desiredCurrency){
        if(!courses.containsKey(baseCurrency))
            throw new Exception("Nie ma takiego kodu waluty: "+baseCurrency);
        else if(!courses.containsKey(desiredCurrency))
            throw new Exception("Nie ma takiego kodu waluty: "+desiredCurrency);

        float firstCurrencyCourse = courses.get(baseCurrency);
        float secondCurrencyCourse = courses.get(desiredCurrency);
        return value * firstCurrencyCourse / secondCurrencyCourse;
    }
}
