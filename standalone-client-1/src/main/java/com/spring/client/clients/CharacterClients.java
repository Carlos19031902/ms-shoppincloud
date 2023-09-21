package com.spring.client.clients;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="DRAGON-BALL")
@LoadBalancerClient(name = "DRAGON-BALL", configuration = LoadBalancerConfiguration.class)
public interface CharacterClients {
	
	@GetMapping(value="/application")
	public ResponseEntity<String> getApplicationName();
	
}
