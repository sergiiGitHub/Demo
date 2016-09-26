package com.tutorialspoint.annotation;

import org.springframework.context.annotation.*;

@Configuration
public class HelloAnotationConfig {
	   @Bean
	   @Scope("prototype")
	   public HelloAnotation helloWorld(){
	      return new HelloAnotation();
	   }
	   // the same as 
//	   <beans>
//	   <bean id="helloWorld" class="com.tutorialspoint.HelloWorld" />
//     </beans>
	   
	   /*
	    * add init method
	    */
	   //@Bean(initMethod = "init", destroyMethod = "cleanup" );
	   

	   /*
	    * For text editor has next look
	    */
//	   @Bean 
//	   public TextEditor textEditor(){
//	      return new TextEditor( spellChecker() );
//	   }
//
//	   @Bean 
//	   public SpellChecker spellChecker(){
//	      return new SpellChecker( );
//	   }
}
