package com.cloud.common.swagger.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.google.common.collect.Lists;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger实现
 * @author Aijm
 * @since 2019/7/14
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
@ConditionalOnProperty(name = "swagger.enabled", matchIfMissing = true )
public class SwaggerConfig {


    @Bean
    @ConditionalOnMissingBean
    public SwaggerProperties swaggerProperties() {
        return new SwaggerProperties();
    }

    @Bean
    public Docket api(SwaggerProperties sp) {
        return new Docket(DocumentationType.SWAGGER_2)
                .host(sp.getHost())
                .apiInfo(apiInfo(sp))
                .select()
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private ApiInfo apiInfo(SwaggerProperties properties) {
        return new ApiInfoBuilder()
                .title(properties.getTitle())
                .description(properties.getDesc())
                //服务条款网址
                .termsOfServiceUrl(properties.getTermsOfServiceUrl())
                .version(properties.getVersion())
                .build();
    }

    private List<ApiKey> securitySchemes() {
        return Lists.newArrayList(
                new ApiKey("Authorization", "Authorization", "header"));
    }
    @Bean
    public SecurityScheme oauth(SwaggerProperties sp) {
        return new OAuthBuilder()
                .name("OAuth2")
                .scopes(scopes())
                .grantTypes(grantTypes(sp))
                .build();
    }

    private List<AuthorizationScope> scopes() {
        return Lists.newArrayList(new AuthorizationScope("app", "Grants openid access"));
    }


    public List<GrantType> grantTypes(SwaggerProperties properties) {
        List<GrantType> grantTypes = new ArrayList<>();
        grantTypes.add(new ResourceOwnerPasswordCredentialsGrant(properties.getAuthUri()));
        return grantTypes;
    }




    private List<SecurityContext> securityContexts() {
        return Lists.newArrayList(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("^(?!auth).*$"))
                        .build()
        );
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(
                new SecurityReference("Authorization", authorizationScopes));
    }


}
