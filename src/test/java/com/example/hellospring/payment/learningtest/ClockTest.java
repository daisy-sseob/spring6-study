package com.example.hellospring.payment.learningtest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

// 학습 가능한 테스트 예제를 만들어보자.
public class ClockTest {

	@Test
	void clock() {
		Clock clock = Clock.systemDefaultZone();
		
		LocalDateTime dateTime1 = LocalDateTime.now(clock);
		LocalDateTime dateTime2 = LocalDateTime.now(clock);

		Assertions.assertThat(dateTime2).isAfter(dateTime1);
	}

	@Test
	void fixedClock() {
		Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
		
		LocalDateTime dateTime1 = LocalDateTime.now(clock);
		LocalDateTime dateTime2 = LocalDateTime.now(clock);
		LocalDateTime dateTime3 = LocalDateTime.now(clock).plusHours(1);

		Assertions.assertThat(dateTime2).isEqualTo(dateTime1);
		Assertions.assertThat(dateTime3).isEqualTo(dateTime1.plusHours(1));
		
	}
}
