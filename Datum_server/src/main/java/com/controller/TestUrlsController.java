package com.controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestUrlsController {
	
	@RequestMapping(value = "/test")
	public String test(){
		
		return "test";
	}
	
	@RequestMapping(value = "/test/{countryName}")
	public String testCount(@PathVariable(value = "countryName") String countryName) {
		
		return "countryName = " + countryName;
	}
	
	@RequestMapping(value = "/test/path")
	public String testCountry(@RequestParam(value = "countryName") String countryName) {
		
		return countryName;
	}
	
	@RequestMapping(value = "/test/twice")
	public String testCountryTwice(@RequestParam(value = "countryName") String countryName, @RequestParam(value = "gameName") String gameName) {
		
		return countryName + gameName;
	}
	
	@RequestMapping(value = "/test/{countryName}/{gameName}")
	public String testTwice(@PathVariable(value = "countryName") String countryName, @PathVariable(value = "gameName") String gameName) {
		
		return "/test/{countryName}/{gameName} countryName = " + countryName + " " + gameName;
	}
	
	@RequestMapping(value = "/test/country/{countryName}/game/{gameName}")
	public String testT(@PathVariable(value = "countryName") String countryName,@PathVariable(value = "gameName") String gameName) {
		
		return "country url: ="+countryName +"game Url :="+gameName;
	}
	
	@RequestMapping(value={"/testPath", "/getTest"})
	public String multiple() {
		
		return "both";
	}
}	
