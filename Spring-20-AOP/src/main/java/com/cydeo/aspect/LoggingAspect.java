package com.cydeo.aspect;

import com.cydeo.controller.CourseController;
import com.cydeo.entity.Course;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Aspect
@Configuration
public class LoggingAspect {

    Logger logger = LoggerFactory.getLogger(CourseController.class);

    /* Example 1 using @Pointcut and @Before adviser

    @Pointcut("execution(* com.cydeo.controller.CourseController.*(..))")
    private void pointcut(){}

    @Before("pointcut()")
    public void log(){
        logger.info("Logger info -----------");
    }
    */


    /* Example 2 using @Before adviser and passing @Pointcut as parameter

    @Before("execution(* com.cydeo.controller.CourseController.*(..))")
    public void beforeAdvice(){
        logger.info("Logger info -----------");
    }
     */


    /* Example 3 using JoinPoint to extract Method, Arguments and Target details

    @Pointcut("execution(* com.cydeo.repository.CourseRepository.findById(*))")
    private void anyProductRepository(){}

    @Before("anyProductRepository()")
    public void beforeCourseRepoOperation(JoinPoint joinPoint){
        logger.info("Before (findById) -> Method: {}, Arguments: {}, Target: {}", joinPoint, joinPoint.getArgs(), joinPoint.getTarget());
    }


     */


    /* Example 4 using @within
    @Pointcut("within(com.cydeo.controller..*)")
    private void anyControllerOperation(){}

    @Pointcut("@within(org.springframework.stereotype.Service)")
    private void anyServiceOperation(){}

    @Before("anyControllerOperation() || anyServiceOperation()")
    public void beforeControllerAdvice(JoinPoint joinPoint){
        logger.info("Before -> Method: {}, Arguments: {}, Target: {}", joinPoint, joinPoint.getArgs(), joinPoint.getTarget());
    }
     */

    /* Example 4 using @annotation

    @Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    private void anyDeleteCourseOperation(){}

    @Before("anyDeleteCourseOperation()")
    public void beforeControllerAdvice(JoinPoint joinPoint){
        logger.info("Before -> Method: {}, Arguments: {}, Target: {}", joinPoint, joinPoint.getArgs(), joinPoint.getTarget());
    }

     */

    /* Example 5 using @AfterReturning, @AfterThrowing, @After and logging result

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    private void anyGetCourseOperation(){}

    @AfterReturning(pointcut = "anyGetCourseOperation()", returning = "result")
    public void afterReturningControllerAdvice(JoinPoint joinPoint, Object result){
        logger.info("After returning -> Method: {}, Result: {}", joinPoint.getSignature().toShortString(), result.toString());
    }
//    @AfterReturning(pointcut = "anyGetCourseOperation()", returning = "result")
//    public void afterReturningControllerAdvice(JoinPoint joinPoint, List<Object> result){
//        logger.info("After returning(List) -> Method: {}, Result: {}", joinPoint.getSignature().toShortString(), result.toString());
//    }

    @AfterThrowing(pointcut = "anyGetCourseOperation()", throwing = "exception")
    public void afterThrowingControllerAdvice(JoinPoint joinPoint, RuntimeException exception){
        logger.info("After throwing -> Method: {}, Exception: {}", joinPoint.getSignature().toShortString(), exception.getMessage());
    }

    @After("anyGetCourseOperation()")
    public void afterControllerAdvice(JoinPoint joinPoint){
        logger.info("After finally -> Method: {}", joinPoint.getSignature().toShortString());
    }

     */

    // Example 6 using @Around and logging result
    @Pointcut("@annotation(com.cydeo.annotation.Loggable)")
    private void anyLoggableMethodOperation(){}

    @Around("anyLoggableMethodOperation()")
    public Object anyLoggableMethodOperationAdvice(ProceedingJoinPoint proceedingJoinPoint){

        logger.info("Before -> Method: {}, Parameters: {}", proceedingJoinPoint.getSignature().toShortString(), proceedingJoinPoint.getArgs());
        Object results = null;

        try {
            results = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        logger.info("After -> Method: {}, Result: {}", proceedingJoinPoint.getSignature().toShortString(), results != null ? results.toString() : null);
        return results;
    }






}
