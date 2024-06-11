package vehbook.vehiclebooker.counter;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import vehbook.vehiclebooker.service.RequestCounterService;

@Aspect
@Log4j2
@Component
public class RequestCounterAspect {

  @Before("execution(* vehbook.vehiclebooker.service.*.*(..))")
  public void loggingCounterAmount(final JoinPoint joinPoint) {
    int counterValue = RequestCounterService.increment();
    log.info("Implements {}.{}."
            + " Current value of counter is {}", joinPoint
            .getSignature().getDeclaringTypeName(),
        joinPoint.getSignature().getName(), counterValue);
  }
}
