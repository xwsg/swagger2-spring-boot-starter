package io.github.xwsg.swagger;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger2 auto configuration.
 *
 * @author wsg
 */
@Configuration
@EnableConfigurationProperties(Swagger2Properties.class)
public class Swagger2AutoConfiguration {

    @Bean
    public Docket createRestApi(Swagger2Properties swagger2Properties) {

        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo(swagger2Properties))
            .select()
            .paths(paths(swagger2Properties))
            .apis(apis(swagger2Properties))
            .build()
            .globalOperationParameters(globalOperationParameters(swagger2Properties))
            ;
    }

    private Predicate<RequestHandler> apis(Swagger2Properties swagger2Properties) {

        List<Predicate<RequestHandler>> basePackages = new LinkedList<>();

        if (swagger2Properties.getBasePackage().isEmpty()) {
            basePackages.add(RequestHandlerSelectors.any());
        }
        for (String basePackage : swagger2Properties.getBasePackage()) {
            basePackages.add(RequestHandlerSelectors.basePackage(basePackage));
        }

        return Predicates.or(basePackages);
    }

    private Predicate<String> paths(Swagger2Properties swagger2Properties) {

        List<Predicate<String>> basePaths = new ArrayList<>();

        if (swagger2Properties.getBasePath().isEmpty()) {
            basePaths.add(PathSelectors.any());
        }
        for (String basePath : swagger2Properties.getBasePath()) {
            basePaths.add(PathSelectors.ant(basePath));
        }

        List<Predicate<String>> excludePaths = new ArrayList<>();
        for (String excludePath : swagger2Properties.getExcludePath()) {
            excludePaths.add(PathSelectors.ant(excludePath));
        }

        return Predicates.and(
            Predicates.not(
                Predicates.or(excludePaths)
            ),
            Predicates.or(basePaths)
        );
    }

    private ApiInfo apiInfo(Swagger2Properties swagger2Properties) {
        return new ApiInfoBuilder()
            .title(swagger2Properties.getApiInfo().getTitle())
            .description(swagger2Properties.getApiInfo().getDescription())
            .termsOfServiceUrl(swagger2Properties.getApiInfo().getTermsOfServiceUrl())
            .contact(
                new Contact(
                    swagger2Properties.getApiInfo().getContact().getName(),
                    swagger2Properties.getApiInfo().getContact().getUrl(),
                    swagger2Properties.getApiInfo().getContact().getEmail()
                )
            )
            .license(swagger2Properties.getApiInfo().getLicense())
            .licenseUrl(swagger2Properties.getApiInfo().getLicenseUrl())
            .version(swagger2Properties.getApiInfo().getVersion())
            .build();
    }

    private List<Parameter> globalOperationParameters(Swagger2Properties swagger2Properties) {
        List<Parameter> globalOperationParameters = new ArrayList<>();

        for (Swagger2Properties.Parameter parameter : swagger2Properties
            .getGlobalOperationParameter()) {
            Parameter globalOperationParameter = new ParameterBuilder()
                .name(parameter.getName())
                .description(parameter.getDescription())
                .modelRef(new ModelRef(parameter.getModelRef()))
                .parameterType(parameter.getParamType())
                .required(parameter.getRequired())
                .build();
            globalOperationParameters.add(globalOperationParameter);
        }
        return globalOperationParameters;
    }
}
