package by.epam.task4.command;

import by.epam.task4.entity.paper.Paper;
import by.epam.task4.service.ParsingService;
import by.epam.task4.service.ServiceException;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.util.Set;

public class ParsingCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if (validateXML(request)) {
            String parserType = request.getParameter("parserType");
            ParsingService service = new ParsingService();
            String path = request.getParameter("xmlPath");
            try {
                Set<Paper> resultSet = service.parse(path, parserType);
                request.setAttribute("resultSet", resultSet);
                request.getRequestDispatcher("/WEB-INF/jsp/result.jsp").forward(request, response);
            } catch (ServiceException e) {
                request.setAttribute("errorMessage", e.getMessage());
                request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "Invalid XML file");
            request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }

    private boolean validateXML(HttpServletRequest request) {
        File XSDFile = new File(getClass().getClassLoader().getResource(request.getParameter("xsdPath")).getFile());
        File XMLFile = new File(getClass().getClassLoader().getResource(request.getParameter("xmlPath")).getFile());
        try {
            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                    .newSchema(XSDFile)
                    .newValidator()
                    .validate(new StreamSource(XMLFile));
        } catch (SAXException | IOException e) {
            return false;
        }
        return true;
    }
}
