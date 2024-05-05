package lt.viko.eif.m.trojanovskis.taksi.Transform;
import lt.viko.eif.m.trojanovskis.taksi.Client.DispatchClient;
import lt.viko.eif.m.trojanovskis.taksi.Client.DriverClient;
import lt.viko.eif.m.trojanovskis.taksi.wsdl.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import lt.viko.eif.m.trojanovskis.taksi.Client.OrderClient;

import org.apache.fop.apps.*;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
/**
 * Represents PdfTransform Object
 * This class is designated to convert XML to PDF
 */
public class PdfTransform {
    /**
     *  Method that check if PDF file is deleted
     * @param file PDF file
     */
    private static void checkIfExitsDelete(File file) {
        try {
            // Check if the file exists and delete it if it does
            if (file.exists()) {
                Files.delete(file.toPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *   Method that converts XML file to PDF
     *
     * @throws IOException
     * @throws JAXBException
     */
    public static void convertToPDF() throws IOException, JAXBException {
        File xsltFile = new File("OrdersFop.xsl");
        StreamSource xmlSource = new StreamSource(new File("Orders.xml"));
        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();

        File pdffile = new File("src/main/resources/Orders1.pdf");
        try{
            checkIfExitsDelete(pdffile);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        OutputStream out = null;
        try {
            out = new java.io.FileOutputStream(pdffile);
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));
          //  File foFile = new File("src/main/resources/Orders.fo");
          //  Result res = new StreamResult(new FileWriter(foFile));
            Result res = new SAXResult((fop.getDefaultHandler()));
            transformer.transform(xmlSource, res);
        } catch (FOPException | TransformerException e) {
            throw new RuntimeException(e);
        } finally {
            if (out != null) {
                try {
                    out.close(); // Close the output stream
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * Method that converts response to XML
     * @param response response object
     * @param filePath file path
     * @param <T> generic type
     * @throws JAXBException
     * @throws IOException
     */
    private static <T> void convertResponseToXml(T response, String filePath) throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(response.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);


        File outputFile = new File(filePath);


        try (FileWriter fileWriter = new FileWriter(outputFile)) {
            marshaller.marshal(response, fileWriter);
        }
    }
    /**
     * Method that converts GetClient response to XML
     * @param firstname first name
     * @param lastname last name
     * @throws IOException
     * @throws JAXBException
     */
    public static void ConvertGetClient(String firstname , String lastname) throws IOException, JAXBException {
        OrderClient orderClient = new OrderClient();
        GetClientOrdersResponse response = orderClient.getOrder(firstname,lastname);
        convertResponseToXml(response,"Orders.xml");

        convertToPDF();

    }
    /**
     * Method that converts GetDriverLicense response to XML
     * @param licensePlate license plate
     * @throws JAXBException
     * @throws IOException
     */

    public static void ConvertGetDriverLicense(String licensePlate) throws JAXBException, IOException {

        DriverClient driverClient = new DriverClient();
        GetDriverPlateOrdersResponse response = driverClient.getPlateOrder(licensePlate);
        convertResponseToXml(response,"Orders.xml");

        convertToPDF();


    }
    /**
     * Method that converts GetDriver response to XML
     * @param firstName first name
     * @param lastName last name
     * @throws JAXBException
     * @throws IOException
     */
    public static void ConvertGetDriver(String firstName, String lastName) throws JAXBException, IOException {
        DriverClient driverClient = new DriverClient();
        GetDriverOrdersResponse response = driverClient.getOrder(firstName,lastName);
        convertResponseToXml(response,"Orders.xml");

        convertToPDF();
    }
    /**
     * Method that converts GetDispatchNumber response to XML
     * @param workNumber work number
     * @throws JAXBException
     * @throws IOException
     */
    public static void ConvertGetDispatchNumber(String workNumber) throws JAXBException, IOException {
        DispatchClient dispatchClient = new DispatchClient();
        GetDispatchNumberOrdersResponse response = dispatchClient.getNumberOrder(workNumber);
        convertResponseToXml(response,"Orders.xml");
        convertToPDF();
    }
    /**
     * Method that converts GetDispatch response to XML
     * @param firstName first name
     * @param lastName last name
     * @throws JAXBException
     * @throws IOException
     */
    public static void ConvertGetDispatch(String firstName, String lastName) throws JAXBException, IOException {
        DispatchClient dispatchClient = new DispatchClient();
        GetDispatchOrdersResponse response = dispatchClient.getOrder(firstName,lastName);
        convertResponseToXml(response,"Orders.xml");
        convertToPDF();
    }
}
