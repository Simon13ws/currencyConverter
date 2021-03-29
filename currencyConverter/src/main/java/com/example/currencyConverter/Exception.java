package com.example.currencyConverter;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Klasa wyjątku, który jest wywołany, gdy użytkownik poda kod waluty, której nie ma na stronie z kursami walut
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class Exception extends RuntimeException{
    public Exception(String str){
        super(str);
    }
}
