package br.com.dio.barber_shop_ui_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.dio.barber_shop_ui_api")
public class BarberShopUiApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(BarberShopUiApiApplication.class, args);
    }
}

//s
