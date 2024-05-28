package vehbook.vehiclebooker.logger;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Log4j2
@Component
public class LoggerAspect {

  /**
   * Defined to log arguments of executable methods.
   *
   * @param joinPoint JoinPoint of executable method.
   */
  @Before("execution(* vehbook.vehiclebooker.service.*.*(..))")
  public final void logBeforeServiceCommand(final JoinPoint joinPoint) {
    log.info(
        () -> String.format(
            "Running method %s with args %s",
            joinPoint.getSignature().getName(),
            Arrays.toString(joinPoint.getArgs())
        )
    );
  }

  /**
   * Defined to log result of executable methods.
   *
   * @param joinPoint JoinPoint of executable method.
   */
  @AfterReturning(pointcut = "execution(* vehbook.vehiclebooker.service.*.*(..))")
  public final void logAfterServiceCommand(final JoinPoint joinPoint) {
    log.info(
        () -> String.format(
            "Result of %s: success ",
            joinPoint.getSignature().getName()
        )
    );
  }

  /**
   * Defined to log Exception throw.
   *
   * @param joinPoint JoinPont of method that throws exception.
   * @param exception The exception to be handled.
   */
  @AfterThrowing(pointcut = "execution(* vehbook.vehiclebooker.*.*.*(..))", throwing = "exception")
  public final void logAfterError(final JoinPoint joinPoint, final Exception exception) {
    log.error(
        () -> String.format(
            "Error while running %s with args %s: %s",
            joinPoint.getSignature().getName(),
            Arrays.toString(joinPoint.getArgs()),
            exception.getMessage()
        )
    );
  }
}