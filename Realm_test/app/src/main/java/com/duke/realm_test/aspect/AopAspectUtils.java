package com.duke.realm_test.aspect;

import android.util.Log;


import com.duke.realm_test.annotation.AopAnnotationInterface;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import io.realm.Realm;


/**
 * Created by Liyun on 2017/6/16.
 */
@Aspect//aspectJ api
public class AopAspectUtils {
    //切面由哪些方法组成
    //方法中带有BehaviorTrace注解的任意类的任意方法就属于这个切面
    @Pointcut("execution(@com.duke.realm_test.annotation.AopAnnotationInterface * *(..))")
    public void methodAnnotatedWithBehavior(){}

    //针对切面进行编程
    @Around("methodAnnotatedWithBehavior()")
    public Object weaveJointPoint(ProceedingJoinPoint joinPoint){
        long begin = System.currentTimeMillis();
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();//执行方法前做的操作
        Object obj= null;
        try {
            obj = joinPoint.proceed();//执行方法
        } catch (Throwable throwable) {
            realm.cancelTransaction();
            throwable.printStackTrace();
            return obj;
        }
        realm.commitTransaction();//执行方法后的操作
        long duration = System.currentTimeMillis() - begin;

        //获取功能名称，利用的反射api
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        AopAnnotationInterface aopAnnotationInterface = methodSignature.getMethod().getAnnotation(AopAnnotationInterface.class);
        String funcName = aopAnnotationInterface.value();
        Log.i("yunli",funcName + " 被执行,耗时： " + duration );
        return obj;
    }
}
