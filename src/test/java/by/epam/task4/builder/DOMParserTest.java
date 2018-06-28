package by.epam.task4.builder;

import org.testng.annotations.Test;

public class DOMParserTest {
    private static final String FILEPATH = "data/papers.xml";

    @Test
    public void parse() throws PaperParserException, ParserBuilderFactoryException{
        ParserBuilderFactory factory = ParserBuilderFactory.getInstance();
        PaperParserBuilder parser = factory.initBuilder("DOM");
        parser.parse(FILEPATH);
    }
}
