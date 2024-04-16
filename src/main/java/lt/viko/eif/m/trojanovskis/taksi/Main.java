package lt.viko.eif.m.trojanovskis.taksi;

import lt.viko.eif.m.trojanovskis.taksi.Client.OrderClient;
import lt.viko.eif.m.trojanovskis.taksi.wsdl.GetClientOrdersResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner lookup(OrderClient orderClient) {
        return args -> {
            String FirstName = "Tomas";
            String LastName = "Kavaliauskas";

            if (args.length > 0) {
                FirstName = args[0];
            }
            GetClientOrdersResponse response = orderClient.getOrder(FirstName,LastName);
            response.getOrderList().getOrder().forEach(System.err::println);

        };
    }
}