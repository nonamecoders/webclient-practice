package com.alan.webclientpratice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
@Slf4j
public class WebclientPraticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebclientPraticeApplication.class, args);
	}

}
