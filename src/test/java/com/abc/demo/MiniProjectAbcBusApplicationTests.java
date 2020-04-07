package com.abc.demo;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.abc.controller.BusController;
import com.abc.model.Bus;
import com.abc.service.BusService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;


@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = BusController.class)
//@WebAppConfiguration
public class MiniProjectAbcBusApplicationTests {
	
	//private BusController busService;
	
	   protected MockMvc mvc;
	   
	  
	   BusController busController;

	   @Before
	   public void setUp() {
	      mvc = MockMvcBuilders.standaloneSetup(busController).build();
	     
	   }

	@Test
	public void addOrUpdateBus() throws Exception {
		Bus b = new Bus();
		busController.addBus(b);
	}

}
