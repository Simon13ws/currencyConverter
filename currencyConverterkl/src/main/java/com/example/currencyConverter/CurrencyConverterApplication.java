package com.example.currencyConverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class CurrencyConverterApplication {


	//Uruchomienie aplikacji wraz z pierwszym pobraniem kursu walut.
	public static void main(String[] args) {
		ConverterService.getCourses();
		SpringApplication.run(CurrencyConverterApplication.class, args);
	}

}
