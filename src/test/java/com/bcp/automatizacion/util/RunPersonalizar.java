package com.bcp.automatizacion.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import cucumber.api.junit.Cucumber;
import net.serenitybdd.cucumber.CucumberWithSerenity;

public class RunPersonalizar extends Runner {

	private CucumberWithSerenity cucumberWithSerenity;
	private Class<CucumberWithSerenity> classValue;

	public RunPersonalizar(Class<CucumberWithSerenity> classValue) throws Exception {
		this.classValue = classValue;
		cucumberWithSerenity = new CucumberWithSerenity(classValue);
	}

	@Override
	public Description getDescription() {
		return cucumberWithSerenity.getDescription();
	}

	@Override
	public void run(RunNotifier notifier) {
		try {
			runAnnotatedMethods(RunBefore.class);
			runAnnotatedMethods(BeforeSuite.class);
			cucumberWithSerenity = new CucumberWithSerenity(classValue);
		    
		} catch (Exception e) {
			e.printStackTrace();
		}

		cucumberWithSerenity.run(notifier);
		try {
            runPredefinedMethods(AfterSuite.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

    private void runPredefinedMethods(Class annotation) throws Exception {
        if (!annotation.isAnnotation()) {
            return;
        }
        Method[] methodList = this.classValue.getMethods();
        for (Method method : methodList) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation item : annotations) {
                if (item.annotationType().equals(annotation)) {
                    method.invoke(null);
                    break;
                }
            }
        }
    }
	
	private void runAnnotatedMethods(Class<?> annotation) throws Exception {
		if (!annotation.isAnnotation()) {
			return;
		}

		Method[] methods = this.classValue.getMethods();

		for (Method method : methods) {
			Annotation[] annotations = method.getAnnotations();
			for (Annotation item : annotations) {
				if (item.annotationType().equals(annotation)) {
					method.invoke(null);
					break;
				}
			}
		}
	}
	

	  
}
