package com.example.demo;

import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.example.demo.entity.Payment;
import com.example.demo.services.PaymentService;

@SpringBootApplication
public class PaymentServiceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx=SpringApplication.run(PaymentServiceApplication.class, args);
		Payment ram=ctx.getBean("ram",Payment.class);
		Payment shyam=ctx.getBean("shyam",Payment.class);
		Payment magesh=ctx.getBean("magesh",Payment.class);
		PaymentService service =ctx.getBean(PaymentService.class);
		service.save(ram);
		service.save(shyam);
		service.save(magesh);
		service.findAll().forEach(System.out::println);
		
		
		
	}
	@Bean
	public Payment ram() {
		return new Payment(102,"Ramesh",LocalDate.now(),45050);
	}
	@Bean
	public Payment shyam() {
		return new Payment(202,"Shyam",LocalDate.of(2012,8,9),95050);
	}
	@Bean
	public Payment magesh() {
		return new Payment(302,"Magesh",LocalDate.of(2009,8,10),80050);
	}

}
