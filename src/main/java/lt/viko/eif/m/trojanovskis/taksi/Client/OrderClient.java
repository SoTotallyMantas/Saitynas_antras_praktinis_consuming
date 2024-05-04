package lt.viko.eif.m.trojanovskis.taksi.Client;





import lt.viko.eif.m.trojanovskis.taksi.wsdl.GetClientOrdersRequest;
import lt.viko.eif.m.trojanovskis.taksi.wsdl.GetClientOrdersResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;


public class OrderClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(OrderClient.class);

    public GetClientOrdersResponse getOrder(String FirstName, String LastName) {

        GetClientOrdersRequest request = new GetClientOrdersRequest();
        request.setFirstName(FirstName);
        request.setLastName(LastName);


        log.info("Requesting Orders of " + FirstName + " " + LastName);
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("lt.viko.eif.m.trojanovskis.taksi.wsdl");


        setMarshaller(marshaller);
        setUnmarshaller(marshaller);

        GetClientOrdersResponse response = (GetClientOrdersResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/orders", request,
                        new SoapActionCallback(
                                "http://lt.viko.eif/mantas/springsoap/gen/GetClientOrdersRequest"));

        return response;
    }

}