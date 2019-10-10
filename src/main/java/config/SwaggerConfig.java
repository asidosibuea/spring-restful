package config;

import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
public class SwaggerConfig {
	
	private ApiInfo getApiInfo() {
		   return new ApiInfo("REST Api Documentation",
				      "REST Api Documentation", 
				      "1.0", 
				      "urn:tos",
				      new Contact("ITC08", "", ""), 
			          "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
	   }
	   @Bean
	   public Docket customImplementation(){
	      return new Docket(DocumentationType.SWAGGER_2)
	    	  .apiInfo(getApiInfo())	  
	    	  .select()
		      .apis(RequestHandlerSelectors.basePackage("controller"))
		      .paths(PathSelectors.any())
		      .build();
	   }
	   

}
