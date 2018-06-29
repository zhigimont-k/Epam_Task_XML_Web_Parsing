package by.epam.task4.service;

import by.epam.task4.builder.PaperParserBuilder;
import by.epam.task4.builder.PaperParserException;
import by.epam.task4.builder.ParserBuilderFactory;
import by.epam.task4.builder.ParserBuilderFactoryException;
import by.epam.task4.entity.paper.Paper;

import java.util.Set;

public class ParsingService {
    public Set<Paper> parse(String filename, String parserType) throws ServiceException {
        Set<Paper> resultSet;
        try {
            ParserBuilderFactory factory = ParserBuilderFactory.getInstance();
            PaperParserBuilder parser = factory.initBuilder(parserType.toUpperCase());
            resultSet = parser.parse(filename);
        } catch (ParserBuilderFactoryException | PaperParserException e) {
            throw new ServiceException(e);
        }
        return resultSet;
    }
}
