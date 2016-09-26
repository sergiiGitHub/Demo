package com.tutorialspoint;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tutorialspoint.annotation.HelloAnotation;
import com.tutorialspoint.annotation.HelloAnotationConfig;
import com.tutorialspoint.spell.TextEditor;

import eventhandling.HelloConfig;
import eventhandling.HelloWorld;

public class MainApp {
	public static void main(String[] args) {
		//		AbstractApplicationContext context = 
		//				new ClassPathXmlApplicationContext("Beans.xml");

		//		HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
		//		obj.getMessage();
		//		Hello objB = (Hello) context.getBean("hello");
		//	    objB.getMessage();
		//		context.registerShutdownHook();

		//setter
		//	      TextEditor te = (TextEditor) context.getBean("textEditor");
		//	      te.spellCheck();

//		AnnotationConfigApplicationContext ctx = 
//				new AnnotationConfigApplicationContext(HelloAnotationConfig.class);
//		//ctx.register(HelloAnotationConfig.class);
//
//		HelloAnotation hello = ctx.getBean(HelloAnotation.class);
//		hello.setMessage("Hello World!");
//		System.out.print( hello.getMessage() );
		
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext();
		ctx.register(HelloConfig.class);
		ctx.refresh();
		ctx.start();
		
		HelloWorld hello = ctx.getBean(HelloWorld.class);
		hello.getMessage();
	}
}
