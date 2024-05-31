package com.sura.bibloteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.sura.bibloteca.dataprovider.client")
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class BiblotecaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiblotecaApplication.class, args);
	}

}
