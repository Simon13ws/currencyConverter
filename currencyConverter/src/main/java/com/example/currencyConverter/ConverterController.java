package com.example.currencyConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConverterController {

    @Autowired
    private ConverterService converterService;

    @GetMapping("/{value}/{baseCurrency}/{desiredCurrency}")
    public float convertCurrency(@PathVariable float value, @PathVariable String baseCurrency, @PathVariable String desiredCurrency){
        return converterService.convert(value, baseCurrency, desiredCurrency);
    }
}
