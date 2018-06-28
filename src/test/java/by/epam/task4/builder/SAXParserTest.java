package by.epam.task4.builder;

import by.epam.task4.entity.paper.Booklet;
import by.epam.task4.entity.paper.Magazine;
import by.epam.task4.entity.paper.Newspaper;
import by.epam.task4.entity.paper.Paper;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;

public class SAXParserTest {
    private static final String FILEPATH = "data/test.xml";
    static Set<Paper> expectedSet = new HashSet<>();
    static List<Paper> expected = new ArrayList<>();

    @BeforeSuite
    public void initExpected(){
        Newspaper newspaper = new Newspaper();
        newspaper.setId(1);
        newspaper.setTitle("Weekly Newspaper");
        newspaper.setColor(true);
        newspaper.setSubscriptionIndex(12445800);
        newspaper.setMonthly(false);
        newspaper.setFirstPublicationDate("1970-12-12");
        newspaper.setVolume(200);
        expectedSet.add(newspaper);

        Magazine magazine = new Magazine();
        magazine.setId(2);
        magazine.setTitle("Some magazine");
        magazine.setGloss(true);
        magazine.setColor(true);
        magazine.setMonthly(true);
        magazine.setVolume(300);
        magazine.setFirstPublicationDate("2015-08-04");
        magazine.setSubscriptionIndex(65827465);
        expectedSet.add(magazine);

        Booklet booklet = new Booklet();
        booklet.setId(3);
        booklet.setTitle("OSTIS booklet");
        booklet.setGloss(false);
        booklet.setColor(true);
        booklet.setMonthly(false);
        booklet.setVolume(5);
        booklet.setFirstPublicationDate("2011-02-17");
        expectedSet.add(booklet);

        expected = expectedSet.stream()
                .sorted(Comparator.comparing(Paper::getId))
                .collect(Collectors.toList());
    }

    @Test
    public void parse() throws PaperParserException, ParserBuilderFactoryException{
        ParserBuilderFactory factory = ParserBuilderFactory.getInstance();
        PaperParserBuilder parser = factory.initBuilder("SAX");
        Set<Paper> resultSet = parser.parse(FILEPATH);
        List<Paper> result = resultSet.stream()
                .sorted(Comparator.comparing(Paper::getId))
                .collect(Collectors.toList());

        Assert.assertEquals(expected, result);
    }
}
