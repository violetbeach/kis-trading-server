package com.violetbeach.kistradingserver.boot.config.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
class KISClientDelayAspect {
	private static final Long DELAY = 500L;

	@After("execution(* com.violetbeach.kistradingserver.domain.adapter.out.web.KisChartAdapter.loadMinutesChart(..))")
	public void afterLoadMinutesChart() throws InterruptedException {
		Thread.sleep(DELAY);
	}

}
