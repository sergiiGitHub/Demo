package eventhandling;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloConfig {
	
	@Bean
	public HelloWorld helloWorld(){
		HelloWorld helloWorld = new HelloWorld();
		helloWorld.setMessage("msg");
		return helloWorld;
	}
	
	@Bean
	public CStartEventHandler cStartEventHandler(){
		return new CStartEventHandler();
	}
}
