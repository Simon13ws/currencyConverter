package com.example.currencyConverter;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConverterController {


    private ConverterService converterService;

    public void setup(){
        converterService = new ConverterService();
    }

    @GetMapping("/{value}/{baseCurrency}/{desiredCurrency}")
    public float convertCurrency(@PathVariable float value, @PathVariable String baseCurrency, @PathVariable String desiredCurrency){
        setup();
        return converterService.convert(value, baseCurrency, desiredCurrency);
    }
}
