package com.giomodiogo;

import com.giomodiogo.model.Card;
import com.giomodiogo.model.Deck;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class SpringBootSrsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSrsApplication.class, args);
	}
}
