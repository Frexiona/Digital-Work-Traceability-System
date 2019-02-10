package com.numsource.artproject;

import com.numsource.artproject.util.thread.MyThread;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArtprojectApplication {

	public static void main(String[] args) {
		MyThread myThread = new MyThread();
		myThread.start();
		SpringApplication.run(ArtprojectApplication.class, args);
	}
}
