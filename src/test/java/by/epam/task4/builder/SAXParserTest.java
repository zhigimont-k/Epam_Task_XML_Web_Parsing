package by.epam.task4.builder;

import by.epam.task4.entity.paper.Paper;
import org.testng.annotations.Test;

import java.util.Set;

public class SAXParserTest {
    private static final String FILEPATH = "data/papers.xml";

    @Test
    public void parse() throws PaperParserException, ParserBuilderFactoryException{
        ParserBuilderFactory factory = ParserBuilderFactory.getInstance();
        PaperParserBuilder parser = factory.initBuilder("SAX");
        parser.parse(FILEPATH);
    }
}
