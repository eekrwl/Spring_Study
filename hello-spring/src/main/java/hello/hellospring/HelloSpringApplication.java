package hello.hellospring; //이거 하위들은 다 스프링빈으로 등록, 아닌 애들은 스프링빈으로 component scan안함

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}
