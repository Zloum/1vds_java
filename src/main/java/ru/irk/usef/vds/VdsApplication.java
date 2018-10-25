package ru.irk.usef.vds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
public class VdsApplication {
    private static final Logger log = LoggerFactory.getLogger(VdsApplication.class);
	public static void main(String args[]) {
        SpringApplication.run(VdsApplication.class, args);
	}
}


