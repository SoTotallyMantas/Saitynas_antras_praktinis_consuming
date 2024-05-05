package lt.viko.eif.m.trojanovskis.taksi.Client;


import lt.viko.eif.m.trojanovskis.taksi.wsdl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

/**
 * Represents DriverClient Object that extends WebServiceGatewaySupport
 * This class is designated to communicate with Driver WebService
 */
public class DriverClient extends WebServiceGatewaySupport {

    /**
     * Logger object that logs all the information
     */
    private static final Logger log = LoggerFactory.getLogger(DriverClient.class);
    /**
     *  Method that gets the order by license plate of driver
     * @param Plate License Plate of the order corresponding to driver
     * @return GetDriverPlateOrdersResponse object
     */
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
    /**
     *  Method that gets the order by first name and last name of driver
     * @param FirstName FirstName of the order corresponding to driver
     * @param LastName LastName of the order corresponding to driver
     * @return GetDriverOrdersResponse object
     */
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