package com.jbank.olb.customerProfile.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jbank.olb.customerProfile.model.weather.Weather;

@Service
public class WeatherService {
	private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

	private String baseURL = "https://samples.openweathermap.org/data/2.5/weather";
	private String appid = "439d4b804bc8187953eb36d2a8c26a02";
	
	private RestTemplate restTemplate;
	
	@Autowired
	public WeatherService(RestTemplateBuilder builder) {
		this.restTemplate = builder.build();
	}
	
	public Weather getWearch(String city) {
		String url = this.baseURL + "?q=" + city + "&appid=" + this.appid;
		if (logger.isDebugEnabled()) logger.debug("Get weather for city: " + city + ", url: " + url);
		
		Weather weather = this.restTemplate.getForObject(url, Weather.class);
		
		return weather;
	}

}

/**
{
	"coord": {
		"lon": -0.13,
		"lat": 51.51
	},
	"weather": [
		{
			"id": 300,
			"main": "Drizzle",
			"description": "light intensity drizzle",
			"icon": "09d"
		}
	],
	"base": "stations",
	"main": {
		"temp": 280.32,
		"pressure": 1012,
		"humidity": 81,
		"temp_min": 279.15,
		"temp_max": 281.15
	},
	"visibility": 10000,
	"wind": {
		"speed": 4.1,
		"deg": 80
	},
	"clouds": {
		"all": 90
	},
	"dt": 1485789600,
	"sys": {
		"type": 1,
		"id": 5091,
		"message": 0.0103,
		"country": "GB",
		"sunrise": 1485762037,
		"sunset": 1485794875
	},
	"id": 2643743,
	"name": "London",
	"cod": 200
}
 */
