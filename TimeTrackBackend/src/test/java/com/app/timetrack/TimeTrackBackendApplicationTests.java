package com.app.timetrack;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.timetrack.repository.EmployeeRepository;

@SpringBootTest
class TimeTrackBackendApplicationTests {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	void contextLoads() {
		int size = employeeRepository.findAll().size();

		assertEquals(6, size);
	}

	@Test
	void contextLoads1() {
		assertEquals(1, 1);
	}

	@Test
	void contextLoads2() {
		assertEquals(1, 1);
	}

	@Test
	void contextLoads3() {
		assertEquals(1, 1);
	}

}
