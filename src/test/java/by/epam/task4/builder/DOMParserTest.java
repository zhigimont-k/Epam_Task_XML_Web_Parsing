package by.epam.task4.builder;

import by.epam.task4.entity.paper.Paper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DOMParserTest {
    private static final String FILEPATH = "data/test.xml";

    @Test
    public void parse() throws PaperParserException, ParserBuilderFactoryException{
        ParserBuilderFactory factory = ParserBuilderFactory.getInstance();
        PaperParserBuilder parser = factory.initBuilder("DOM");
        Set<Paper> resultSet = parser.parse(FILEPATH);
        List<Paper> result = resultSet.stream()
                .sorted(Comparator.comparing(Paper::getId))
                .collect(Collectors.toList());

        Assert.assertEquals(SAXParserTest.expected, result);
    }
}
