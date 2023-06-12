package com.violetbeach.kistradingserver.domain.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VolumeTest {

	@Test
	@DisplayName("value가 0보다 작으면 IllegalArgumentException를 발생한다.")
	void withNegative_ItThrowIllegalArgumentException() {
		long invalidValue = -100;
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Volume(invalidValue);
		});
	}

}