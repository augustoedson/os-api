package com.edson.os.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.edson.os.services.DBService;

@Configuration
@Profile("dev")
public class DevConfig  {
	
	@Autowired
	private DBService dbservice;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String ddl;
	@Bean
	public Boolean instanciaDB() {
		if (ddl.equals("create")) {
			this.dbservice.instanciaDB();	
		}
		return false;
	}

}
