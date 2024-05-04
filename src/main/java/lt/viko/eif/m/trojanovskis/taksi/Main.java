package lt.viko.eif.m.trojanovskis.taksi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@ServletComponentScan
public class Main extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

//    @Bean
//    CommandLineRunner lookup(OrderClient orderClient) {
//        return args -> {
//            String FirstName = "Tomas";
//            String LastName = "Kavaliauskas";
//
//            if (args.length > 0) {
//                FirstName = args[0];
//            }
//            GetClientOrdersResponse response = orderClient.getOrder(FirstName,LastName);
//            response.getOrderList().getOrder().forEach(System.err::println);
//
//        };
//    }
}