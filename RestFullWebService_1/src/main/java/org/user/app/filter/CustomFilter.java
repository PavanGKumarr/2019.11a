package org.user.app.filter;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class CustomFilter {
	@GetMapping("/filter1")
	public FilterBean getApi() {
		return new FilterBean("value1", "value2", "value3");
	}
	
	@GetMapping("/filter2")
	public List<FilterBean> getApi1() {
		return Arrays.asList(new FilterBean("value1", "value2", "value3"),new FilterBean("value4", "value5", "value6"));
	}
	
	@GetMapping("/filter3")
	public MappingJacksonValue getApi3() {
		List<FilterBean1> list = Arrays.asList(
				new FilterBean1("value1", "value2", "value3"),
				new FilterBean1("value4", "value5", "value6"),
				new FilterBean1("value7", "value8", "value9")
				);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SimpleFilterProvider", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		mapping.setFilters(filters);
		return mapping;
	}
	
	
}
