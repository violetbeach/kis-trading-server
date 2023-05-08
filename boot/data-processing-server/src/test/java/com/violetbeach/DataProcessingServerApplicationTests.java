package com.violetbeach;

import com.violetbeach.DataProcessingServerApplication;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.SpringApplication;

import static org.mockito.Mockito.*;

class DataProcessingServerApplicationTests {

	@Test
	void main() {
		try (MockedStatic<SpringApplication> mockApplication = mockStatic(SpringApplication.class)) {
			mockApplication.when(() -> SpringApplication.run(eq(DataProcessingServerApplication.class), any(String[].class)))
					.thenAnswer(invocation -> null);

			DataProcessingServerApplication.main(new String[0]);

			mockApplication.verify(() -> SpringApplication.run(DataProcessingServerApplication.class, new String[0]), times(1));
		}
	}

}
