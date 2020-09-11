package com.hansung.web.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {
		List<Parameter> global = new ArrayList<Parameter>();
		global.add(new ParameterBuilder()
					.name("name")
					.parameterType("header")
					.required(false)
					.modelRef(new ModelRef("string")).build());
		return new Docket(DocumentationType.SWAGGER_2)
				.globalOperationParameters(global)
				.apiInfo(this.swaggerInfo())
				.select()
				.apis(RequestHandlerSelectors.any())
				.build()
				.useDefaultResponseMessages(true).enableUrlTemplating(false);
	}

	private ApiInfo swaggerInfo() {
		return new ApiInfoBuilder().title("API 문서")
				.description("online_test_project")
				.version("1.0.0")
				.build();
	}
}