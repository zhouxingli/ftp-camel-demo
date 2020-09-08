package com.gdtopway.realtimedata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class FtpCamelDemoApplication  extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(FtpCamelDemoApplication.class);
	}


	public static void main(String[] args) {
		SpringApplication.run(FtpCamelDemoApplication.class, args);
	}
}
