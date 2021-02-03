package com.example.csv;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CsvApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsvApplication.class, args);
		test();
	}

	private static void test() {
		ObjectMapper mapper = new CsvMapper();

		CsvSchema schema = ((CsvMapper) mapper).schemaFor(Pojo.class);
		Pojo value = new Pojo();
		value.setFirstName("mike");
		value.setLastName("smith");
		value.setAge(10);
		try {
			String csv = mapper.writer(schema).writeValueAsString(value);
			String out = mapper.writer(schema).writeValueAsString(value);
System.out.println(out);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}


// Manually-built schema: one with type, others default to "STRING"
		CsvSchema schema2 = CsvSchema.builder()
				.addColumn("firstName")
				.addColumn("lastName")
				.addColumn("age", CsvSchema.ColumnType.NUMBER)
				.build();

// Read schema from the first line; start with bootstrap instance
// to enable reading of schema from the first line
// NOTE: reads schema and uses it for binding
	//	CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();

	}
}
