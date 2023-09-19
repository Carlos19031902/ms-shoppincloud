package com.spring.client.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="DRAGON-BALL")
public interface CharacterClients {
	
	@RequestMapping(method = RequestMethod.GET, value="/application")
	public ResponseEntity<String> getApplicationName();
	
}
