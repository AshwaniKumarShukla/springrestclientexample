package com.spring.restClient;

import org.springframework.http.HttpEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springrest.model.Employee;

public class SpringRestClientExample {
	
	private static final String URL="http://localhost:8080/SpringRestExample/employees/"; 

	public static void main(String[] args) throws JsonProcessingException {
		getEmployee();
		//updateEmployee();
		createEmployee();
	}
	
	public static void getEmployee(){
		RestTemplate restClient=new RestTemplate();		
		String result=restClient.getForObject(URL+"1", String.class);
		System.out.println("The Name>>>>>>"+result);
	}
	
	public static void createEmployee() throws JsonProcessingException{
			
		
		//Employee emp=new Employee(5, "dileep", 34);
		
		Employee emp=new Employee();
		emp.setId(6);
		emp.setName("ramulu");
		emp.setAge(32);
		
		ObjectMapper mapper=new ObjectMapper();
		
		String requestJson=mapper.writeValueAsString(emp);
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String,String>();
		headers.add("Content-Type", "application/json");

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		System.out.println("request"+requestJson);
		HttpEntity<String> request = new HttpEntity<String>(requestJson, headers);

		restTemplate.postForObject(URL+"create", request, Employee.class);

		System.out.println("Successfully creatd");
		
		
	}
	
	
public static void updateEmployee(){
			
		
		//Employee emp=new Employee(1, "dileep", 34);
		
		Employee emp=new Employee();
		emp.setId(1);
		emp.setName("dileep");
		emp.setAge(34);
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String,String>();
		headers.add("Content-Type", "application/json");

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		HttpEntity<Employee> request = new HttpEntity<Employee>(emp, headers);

		restTemplate.put(URL+"1", emp);

		
		
		
	}
	

}
