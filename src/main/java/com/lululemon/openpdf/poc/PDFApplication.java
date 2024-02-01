package com.lululemon.openpdf.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.lululemon.openpdf.poc")
public class PDFApplication {
    public static void main(String[] args) {
        SpringApplication.run(PDFApplication.class, args);
    }
}
