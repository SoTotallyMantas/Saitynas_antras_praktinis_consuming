package lt.viko.eif.m.trojanovskis.taksi.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import lt.viko.eif.m.trojanovskis.taksi.Transform.PdfTransform;
import java.io.*;
/**
 * Represents TomcatServlet Object
 * This class is designated to serve PDF files
 * and HTML pages
 */
@WebServlet(name="TomcatServlet",urlPatterns = {"/pdf/*"})
public class TomcatServlet extends HttpServlet {
    /**
     * Method that serves PDF file
     * @param response HttpServletResponse object
     * @throws IOException
     */
    private void servePdfFile(HttpServletResponse response) throws IOException {
        File file = new File("src/main/resources/Orders1.pdf"); // Replace with the actual path to the file
        if (!file.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // Set cache-control headers to prevent caching
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=Orders1.pdf");
        response.setContentLength((int) file.length());

        try (InputStream is = new FileInputStream(file); OutputStream os = response.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        }
    }
    /**
     * Method that serves HTML page for Client Order
     * @param response HttpServletResponse object
     * @throws IOException
     */
    protected void ServeHTMLDispatchNumberClient(HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Dispatch Order</title>");
        out.println("<style>");
        out.println("html, body { height: 100%; margin: 0; padding: 0; display: flex; align-items: center; justify-content: center; font-family: Arial, sans-serif; }");
        out.println("body { background-color: #f4f4f9; }");
        out.println(".container { text-align: center; }");
        out.println("a { background-color: #007bff; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px; }");
        out.println("a:hover { background-color: #0056b3; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h1>Enter Work Number</h1>");
        out.println("<form action='/pdf/GetDispatchNumberOrdersRequest' method='post'>");
        out.println("<label for='Work Number'>Work Number:</label>");
        out.println("<input type='text' id='Work Number' name='Work Number'><br><br>");
        out.println("<input type='submit' value='Submit'>");
        out.println("</form>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    /**
     *  Method that serves HTML page for Dispatch Order
     * @param response HttpServletResponse object
     * @throws IOException
     */
    protected void ServeHTMLDispatchClient(HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Dispatch Order</title>");
        out.println("<style>");
        out.println("html, body { height: 100%; margin: 0; padding: 0; display: flex; align-items: center; justify-content: center; font-family: Arial, sans-serif; }");
        out.println("body { background-color: #f4f4f9; }");
        out.println(".container { text-align: center; }");
        out.println("a { background-color: #007bff; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px; }");
        out.println("a:hover { background-color: #0056b3; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h1>Enter First And Last Name</h1>");
        out.println("<form action='/pdf/GetDispatchOrdersRequest' method='post'>");
        out.println("<label for='FirstName'>First Name:</label>");
        out.println("<input type='text' id='FirstName' name='FirstName'><br><br>");
        out.println("<label for='LastName'>Last Name:</label>");
        out.println("<input type='text' id='LastName' name='LastName'><br><br>");
        out.println("<input type='submit' value='Submit'>");
        out.println("</form>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    /**
     *  Method that serves HTML page for Driver License plate Order
     * @param response HttpServletResponse object
     * @throws IOException
     */
    protected void ServeHTMLDriverLicenseClient(HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Client Order</title>");
        out.println("<style>");
        out.println("html, body { height: 100%; margin: 0; padding: 0; display: flex; align-items: center; justify-content: center; font-family: Arial, sans-serif; }");
        out.println("body { background-color: #f4f4f9; }");
        out.println(".container { text-align: center; }");
        out.println("a { background-color: #007bff; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px; }");
        out.println("a:hover { background-color: #0056b3; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h1>Enter License Plate</h1>");
        out.println("<form action='/pdf/GetDriverPlateOrdersRequest' method='post'>");
        out.println("<label for='LicensePlate'>License Plate:</label>");
        out.println("<input type='text' id='LicensePlate' name='LicensePlate'><br><br>");
        out.println("<input type='submit' value='Submit'>");
        out.println("</form>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    /**
     *  Method that serves HTML page for Driver Order
     * @param response HttpServletResponse object
     * @throws IOException
     */
    protected void ServeHTMLDriverClient(HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Client Order</title>");
        out.println("<style>");
        out.println("html, body { height: 100%; margin: 0; padding: 0; display: flex; align-items: center; justify-content: center; font-family: Arial, sans-serif; }");
        out.println("body { background-color: #f4f4f9; }");
        out.println(".container { text-align: center; }");
        out.println("a { background-color: #007bff; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px; }");
        out.println("a:hover { background-color: #0056b3; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h1>Enter First And Last Name</h1>");
        out.println("<form action='/pdf/GetDriverOrdersRequest' method='post'>");
        out.println("<label for='FirstName'>First Name:</label>");
        out.println("<input type='text' id='FirstName' name='FirstName'><br><br>");
        out.println("<label for='LastName'>Last Name:</label>");
        out.println("<input type='text' id='LastName' name='LastName'><br><br>");
        out.println("<input type='submit' value='Submit'>");
        out.println("</form>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
    /**
     *  Method that serves HTML page for Client Order
     * @param response HttpServletResponse object
     * @throws IOException
     */
    protected void ServeHTMLOrderClient(HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Client Order</title>");
        out.println("<style>");
        out.println("html, body { height: 100%; margin: 0; padding: 0; display: flex; align-items: center; justify-content: center; font-family: Arial, sans-serif; }");
        out.println("body { background-color: #f4f4f9; }");
        out.println(".container { text-align: center; }");
        out.println("a { background-color: #007bff; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px; }");
        out.println("a:hover { background-color: #0056b3; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h1>Enter First And Last Name</h1>");
        out.println("<form action='/pdf/GetCientOrdersRequest' method='post'>");
        out.println("<label for='FirstName'>First Name:</label>");
        out.println("<input type='text' id='FirstName' name='FirstName'><br><br>");
        out.println("<label for='LastName'>Last Name:</label>");
        out.println("<input type='text' id='LastName' name='LastName'><br><br>");
        out.println("<input type='submit' value='Submit'>");
        out.println("</form>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
    /**
     * Method that handles POST requests
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().endsWith("GetCientOrdersRequest")) {
            try {
                String firstName = request.getParameter("FirstName");
                String lastName = request.getParameter("LastName");
                if (firstName == null || lastName == null) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                    return;
                }


                PdfTransform.ConvertGetClient(firstName, lastName);
                servePdfFile(response);


            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }

        }
        if (request.getRequestURI().endsWith("GetDispatchOrdersRequest")) {
            try {
                String firstName = request.getParameter("FirstName");
                String lastName = request.getParameter("LastName");
                if (firstName == null || lastName == null) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                    return;
                }
                PdfTransform.ConvertGetDispatch(firstName, lastName);
                servePdfFile(response);

            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }

        if (request.getRequestURI().endsWith("GetDispatchNumberOrdersRequest")) {
            try {
                String workNumber = request.getParameter("Work Number");
                if (workNumber == null) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                    return;
                }
                PdfTransform.ConvertGetDispatchNumber(workNumber);
                servePdfFile(response);


            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
        if (request.getRequestURI().endsWith("GetDriverOrdersRequest")) {

            try {
                String firstName = request.getParameter("FirstName");
                String lastName = request.getParameter("LastName");
                if (firstName == null || lastName == null) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                    return;
                }
                PdfTransform.ConvertGetDriver(firstName, lastName);
                servePdfFile(response);

            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
        if (request.getRequestURI().endsWith("GetDriverPlateOrdersRequest")) {
            try {
                String licensePlate = request.getParameter("LicensePlate");
                if (licensePlate == null) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                    return;
                }
                PdfTransform.ConvertGetDriverLicense(licensePlate);
                servePdfFile(response);


            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }


    }
    /**
     * Method that handles GET requests
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().endsWith("Orders1.pdf")) {
            servePdfFile(response);
        } else
        if (request.getRequestURI().endsWith("GetCientOrdersRequest")) {
            ServeHTMLOrderClient(response);
        } else if (request.getRequestURI().endsWith("GetDriverPlateOrdersRequest")) {
            ServeHTMLDriverLicenseClient(response);
        } else if (request.getRequestURI().endsWith("GetDriverOrdersRequest")) {
            ServeHTMLDriverClient(response);
        } else if (request.getRequestURI().endsWith("GetDispatchOrdersRequest")) {
            ServeHTMLDispatchClient(response);
        } else if (request.getRequestURI().endsWith("GetDispatchNumberOrdersRequest")) {
            ServeHTMLDispatchNumberClient(response);

        } else {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Open PDF</title>");
            out.println("<style>");
            out.println("html, body { height: 100%; margin: 0; padding: 0; display: flex; align-items: center; justify-content: center; font-family: Arial, sans-serif; }");
            out.println("body { background-color: #f4f4f9; }");
            out.println(".container { text-align: center; }");
            out.println("a { background-color: #007bff; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px; }");
            out.println("a:hover { background-color: #0056b3; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<h1>Open PDF Page</h1>");
            out.println("<p><a href='/pdf/GetCientOrdersRequest'>GetCientOrdersRequest</a></p>");
            out.println("<p><a href='/pdf/GetDispatchOrdersRequest'>GetDispatchOrdersRequest</a></p>");
            out.println("<p><a href='/pdf/GetDispatchNumberOrdersRequest'>GetDispatchNumberOrdersRequest</a></p>");
            out.println("<p><a href='/pdf/GetDriverOrdersRequest'>GetDriverOrdersRequest</a></p>");
            out.println("<p><a href='/pdf/GetDriverPlateOrdersRequest'>GetDriverPlateOrdersRequest</a></p>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}

