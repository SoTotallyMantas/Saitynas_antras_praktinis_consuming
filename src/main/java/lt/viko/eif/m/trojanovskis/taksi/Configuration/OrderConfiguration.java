package lt.viko.eif.m.trojanovskis.taksi.Configuration;


import lt.viko.eif.m.trojanovskis.taksi.Client.OrderClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
/**
 * Represents OrderConfiguration Object
 * This class is designated to configure OrderClient
 */
@Configuration
public class OrderConfiguration {
    /**
     * Method that creates marshaller object
     *
     * @return Jaxb2Marshaller object
     */
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath("lt.viko.eif.m.trojanovskis.taksi.wsdl");
        return marshaller;
    }
    /**
     * Method that creates OrderClient object
     *
     * @param marshaller Jaxb2Marshaller object
     * @return OrderClient object
     */
    @Bean
    public OrderClient orderClient(Jaxb2Marshaller marshaller) {
        OrderClient client = new OrderClient();
        client.setDefaultUri("http://localhost:8080/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}
