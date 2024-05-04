package lt.viko.eif.m.trojanovskis.taksi.Client;


import lt.viko.eif.m.trojanovskis.taksi.wsdl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;


public class DriverClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(DriverClient.class);
    public GetDriverPlateOrdersResponse getPlateOrder(String Plate) {

        GetDriverPlateOrdersRequest request = new GetDriverPlateOrdersRequest();
        request.setLicensePlate(Plate);
        log.info("Requesting Orders of " + Plate);
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("lt.viko.eif.m.trojanovskis.taksi.wsdl");


        setMarshaller(marshaller);
        setUnmarshaller(marshaller);

        GetDriverPlateOrdersResponse response = (GetDriverPlateOrdersResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/orders", request,
                        new SoapActionCallback(
                                "http://lt.viko.eif/mantas/springsoap/gen/GetDriverPlateOrdersRequest"));

        return response;
    }
    public GetDriverOrdersResponse getOrder(String FirstName, String LastName) {

        GetDriverOrdersRequest request = new GetDriverOrdersRequest();
        request.setFirstName(FirstName);
        request.setLastName(LastName);


        log.info("Requesting Orders of " + FirstName + " " + LastName);
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("lt.viko.eif.m.trojanovskis.taksi.wsdl");


        setMarshaller(marshaller);
        setUnmarshaller(marshaller);

        GetDriverOrdersResponse response = (GetDriverOrdersResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/orders", request,
                        new SoapActionCallback(
                                "http://lt.viko.eif/mantas/springsoap/gen/GetDriverOrdersRequest"));

        return response;
    }

}