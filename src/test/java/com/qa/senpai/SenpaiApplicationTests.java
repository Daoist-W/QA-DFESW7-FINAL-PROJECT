package com.qa.senpai;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql(scripts = { "classpath:schema.sql",
		"classpath:data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class SenpaiApplicationTests {

	@Test
	void contextLoads() {
	}

}

 // join table

// spring generated queries, JPQL, an abstract of sql
// to implement search by availability
// user and availability
// one to many relationship

