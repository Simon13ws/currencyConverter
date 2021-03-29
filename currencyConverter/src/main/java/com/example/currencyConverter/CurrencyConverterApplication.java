package com.example.currencyConverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class CurrencyConverterApplication {

	//Uruchomienie aplikacji wraz z pierwszym pobraniem kursu walut i uruchomieniem schedulera, by uruchamiał funkcję pobierającą kurs walut wraz z rozpoczęciem kolejnego dnia
	public static void main(String[] args) {
		ConverterService.getCourses();
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		Calendar now = Calendar.getInstance();
		int initialDelay = 60*24 - now.get(Calendar.MINUTE);
		int period = 60*24;
		TimeUnit timeUnit = TimeUnit.MINUTES;
		executor.scheduleAtFixedRate(ConverterService::getCourses, initialDelay, period, timeUnit);

		SpringApplication.run(CurrencyConverterApplication.class, args);
	}

}
