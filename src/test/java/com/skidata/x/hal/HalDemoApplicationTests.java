package com.skidata.x.hal;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class HalDemoApplicationTests {

	@Autowired
	@Qualifier("halObjectMapper")
	private ObjectMapper objectMapper;

	@Test
	public void contextLoads() throws IOException {
		String json = "{\n" +
				"\t\"firstName\": \"Rajappan\",\n" +
				"\t\"lastName\": \"Chettan\",\n" +
				"\t\"address\": \"http://localhost:8080/addresses/1\"\n" +
				"}";
		Customer customer = objectMapper.readValue(json, Customer.class);
		log.info("{}", customer.getFirstName());
	}

}
