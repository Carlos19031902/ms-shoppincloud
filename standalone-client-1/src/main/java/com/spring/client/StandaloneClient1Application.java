package com.spring.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.spring.client.clients.CharacterClients;

@SpringBootApplication
@EnableFeignClients
public class StandaloneClient1Application implements ApplicationRunner{
	
	@Autowired
	private EurekaClient eurekaClient;
	
	@Autowired
	private CharacterClients clients;

	public static void main(String[] args) {
		SpringApplication.run(StandaloneClient1Application.class, args);
	}
	
	private static final Logger log = LoggerFactory.getLogger(StandaloneClient1Application.class);

	@Override
	public void run(ApplicationArguments args) throws Exception {
//		Application app = eurekaClient.getApplication("DRAGON-BALL");
//		log.info(app.getName());
//		List<InstanceInfo> instance = app.getInstances();
//		for(InstanceInfo ins:instance) {
//			log.info("{},{}",ins.getIPAddr(),ins.getPort());
//		}
		ResponseEntity response = clients.getApplicationName();
		log.info("status code {}",response.getStatusCode());
		log.info("body {}",response.getBody());
	}

}
