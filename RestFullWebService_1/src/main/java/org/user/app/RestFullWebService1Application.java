package org.user.app;


import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.context.support.ResourceBundleMessageSource;

@SpringBootApplication
public class RestFullWebService1Application {

	public static void main(String[] args) {
		SpringApplication.run(RestFullWebService1Application.class, args);
	}
	
	@Bean                 
    public LocaleResolver localeResolver() {

        SessionLocaleResolver localResolver=new SessionLocaleResolver();
        localResolver.setDefaultLocale(Locale.US);
        return localResolver;
    }
//	@Bean
//	public ResourceBundleMessageSource bundleMessageSource() {
//		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
//		source.setBasename("messages");
//		return source;
//	}

}
