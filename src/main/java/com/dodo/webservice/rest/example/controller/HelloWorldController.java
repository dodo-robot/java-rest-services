package com.dodo.webservice.rest.example.controller; 

import java.util.Arrays;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.RestController;

import com.dodo.webservice.rest.example.model.HelloWorldBean;
import com.dodo.webservice.rest.example.model.SomeBean;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping(path = "/hello-world")
	public String helloWorld() { 
		return "Hello World!";
	}
	
	@GetMapping(path = "/hello-world-i18n")
	public String helloWorldInternationalized() {
		// @RequestHeader(name="Accept-Language", required=false) Locale locale
		return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
	}
	
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}
	
	
	@GetMapping(path = "/hello-world-bean/path-variable/{name}")
	public HelloWorldBean helloWorldBeanPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World, %s", name));
	}
	
	@GetMapping(path = "/filtering")
	public MappingJacksonValue retireveSomeBean() {
		SomeBean someBean = new SomeBean("value1","value2","value3"); 
		String[] fields = new String[] {"field3","field2"};
		return filterModel(someBean, fields); 
	}
	
	@GetMapping(path = "/filtering-list")
	public MappingJacksonValue retireveListOfSomeBean() {
		List<SomeBean> someBean = Arrays.asList(
				new SomeBean("value1","value2","value3"),
				new SomeBean("value1","value2","value3"));
		
		String[] fields = new String[] {"field1","field2"};
		
		return filterModel(someBean, fields);
	}
	
	private MappingJacksonValue filterModel(Object someBean, String... fields) {
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
				.filterOutAllExcept(fields);
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filters);
		
		
		return mapping;
	}

}
