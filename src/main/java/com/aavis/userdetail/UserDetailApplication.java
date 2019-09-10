package com.aavis.userdetail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class UserDetailApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserDetailApplication.class, args);
	}

	@Bean
	public Datastore cloudDatastoreService() {
		return DatastoreOptions.getDefaultInstance().getService();
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.aavis.userdetail.controller")).paths(PathSelectors.any())
				.build();
	}
}
