package com.jbank.olb.customerProfile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jbank.olb.customerProfile.model.weather.Weather;
import com.jbank.olb.customerProfile.service.WeatherService;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {
	@Autowired
	private WeatherService weatherService;

	@GetMapping()
	public Weather getWearchForCity(@RequestParam(value = "city") String city) {
		return this.weatherService.getWearch(city);
	}
}
