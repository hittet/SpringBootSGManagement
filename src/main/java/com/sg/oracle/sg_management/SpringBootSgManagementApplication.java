package com.sg.oracle.sg_management;

import com.sun.faces.config.ConfigureListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.faces.webapp.FacesServlet;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, FlywayAutoConfiguration.class})
public class SpringBootSgManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSgManagementApplication.class, args);
	}

	@Bean
	public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener()
	{
		return new ServletListenerRegistrationBean<>(new ConfigureListener());
	}

	@Bean
	public ServletRegistrationBean facesServletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean<>(new FacesServlet(), "*.xhtml");
		registration.setLoadOnStartup(1);
		return registration;
	}

	@Bean
	public ServletContextInitializer servletContextInitializer() {
		return servletContext -> {
			servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
			servletContext.setInitParameter("primefaces.THEME", "nova-light");
		};
	}

	@Bean
	public FlywayMigrationStrategy flywayMigrationStrategy(){
		return args ->{};
	}
}
