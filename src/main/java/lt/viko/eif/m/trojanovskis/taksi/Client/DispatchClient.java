package lt.viko.eif.m.trojanovskis.taksi.Client;


import lt.viko.eif.m.trojanovskis.taksi.wsdl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;


public class DispatchClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(DispatchClient.class);
    public GetDispatchNumberOrdersResponse getNumberOrder(String WorkNumber)
    {

            GetDispatchNumberOrdersRequest request = new GetDispatchNumberOrdersRequest();
            request.setWorkNumber(WorkNumber);
            log.info("Requesting Orders of " + WorkNumber);
            Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
            marshaller.setContextPath("lt.viko.eif.m.trojanovskis.taksi.wsdl");
        setMarshaller(marshaller);
        setUnmarshaller(marshaller);

        GetDispatchNumberOrdersResponse response = (GetDispatchNumberOrdersResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/orders", request,
                        new SoapActionCallback(
                                "http://lt.viko.eif/mantas/springsoap/gen/GetDispatchNumberOrdersRequest"));

        return response;
    }
    public GetDispatchOrdersResponse getOrder(String FirstName, String LastName) {

        GetDispatchOrdersRequest request = new GetDispatchOrdersRequest();
        request.setFirstName(FirstName);
        request.setLastName(LastName);


        log.info("Requesting Orders of " + FirstName + " " + LastName);
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("lt.viko.eif.m.trojanovskis.taksi.wsdl");


        setMarshaller(marshaller);
        setUnmarshaller(marshaller);

        GetDispatchOrdersResponse response = (GetDispatchOrdersResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/orders", request,
                        new SoapActionCallback(
                                "http://lt.viko.eif/mantas/springsoap/gen/GetDispatchOrdersRequest"));

        return response;

    }

}