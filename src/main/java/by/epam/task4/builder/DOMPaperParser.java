package by.epam.task4.builder;

import by.epam.task4.entity.paper.Booklet;
import by.epam.task4.entity.paper.Magazine;
import by.epam.task4.entity.paper.Newspaper;
import by.epam.task4.entity.paper.Paper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Set;

class DOMPaperParser extends PaperParserBuilder {
    private static Logger logger = LogManager.getLogger();
    private DocumentBuilder documentBuilder;

    DOMPaperParser() throws PaperParserException {
        initDocumentBuilder();
    }

    private void initDocumentBuilder() throws PaperParserException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new PaperParserException(e);
        }
    }

    @Override
    public Set<Paper> parse(String fileName) throws PaperParserException {
        try {
            Document document = documentBuilder.parse(new File(fileName));

            NodeList newspaperList = document.getElementsByTagName(PaperType.NEWSPAPER.name().toLowerCase());
            NodeList magazineList = document.getElementsByTagName(PaperType.MAGAZINE.name().toLowerCase());
            NodeList bookletList = document.getElementsByTagName(PaperType.BOOKLET.name().toLowerCase());

            for (int i = 0; i < newspaperList.getLength(); i++) {
                Element newspaperElement = (Element) newspaperList.item(i);
                Paper newspaper = createNewspaper(newspaperElement);
                paperSet.add(newspaper);
                logger.log(Level.INFO, "Added a paper: " + newspaper);
            }

            for (int i = 0; i < magazineList.getLength(); i++) {
                Element magazineElement = (Element) magazineList.item(i);
                Paper magazine = createMagazine(magazineElement);
                paperSet.add(magazine);
                logger.log(Level.INFO, "Added a paper: " + magazine);
            }

            for (int i = 0; i < bookletList.getLength(); i++) {
                Element bookletElement = (Element) bookletList.item(i);
                Paper booklet = createBooklet(bookletElement);
                paperSet.add(booklet);
                logger.log(Level.INFO, "Added a paper: " + booklet);
            }
        } catch (IOException | SAXException e) {
            throw new PaperParserException(e);
        }
        return paperSet;
    }

    private void createPaper(Paper paper, Element element) {
        NodeList monthlyList = element.getElementsByTagName(PaperProperty.MONTHLY.getValue());
        NodeList colorList = element.getElementsByTagName(PaperProperty.COLOR.getValue());
        NodeList volumeList = element.getElementsByTagName(PaperProperty.VOLUME.getValue());
        NodeList firstPublicationList = element.getElementsByTagName(PaperProperty.FIRST_PUBLICATION.getValue());

        paper.setMonthly(Boolean.parseBoolean(monthlyList.item(0).getTextContent()));
        paper.setColor(Boolean.parseBoolean(colorList.item(0).getTextContent()));
        paper.setVolume(Integer.parseInt(volumeList.item(0).getTextContent()));
        paper.setFirstPublicationDate(firstPublicationList.item(0).getTextContent());

        paper.setId(Integer.parseInt(element.getAttribute(PaperAttribute.ID.name().toLowerCase())));
        paper.setTitle(element.getAttribute(PaperAttribute.TITLE.name().toLowerCase()));
    }

    private Paper createNewspaper(Element newspaperElement) {
        Paper newspaper = new Newspaper();
        createPaper(newspaper, newspaperElement);

        NodeList subscriptionIndexList = newspaperElement.getElementsByTagName(
                PaperProperty.SUBSCRIPTION_INDEX.getValue());
        newspaper.setSubscriptionIndex(Integer.parseInt(subscriptionIndexList.item(0).getTextContent()));
        return newspaper;
    }

    private Paper createMagazine(Element magazineElement) {
        Paper magazine = new Magazine();
        createPaper(magazine, magazineElement);

        NodeList glossList = magazineElement.getElementsByTagName(PaperProperty.GLOSS.getValue());
        NodeList subscriptionIndexList = magazineElement.getElementsByTagName(
                PaperProperty.SUBSCRIPTION_INDEX.getValue());

        magazine.setGloss(Boolean.parseBoolean(glossList.item(0).getTextContent()));
        magazine.setSubscriptionIndex(Integer.parseInt(subscriptionIndexList.item(0).getTextContent()));
        return magazine;
    }

    private Paper createBooklet(Element bookletElement) {
        Paper booklet = new Booklet();
        createPaper(booklet, bookletElement);

        NodeList glossList = bookletElement.getElementsByTagName(PaperProperty.GLOSS.getValue());
        booklet.setGloss(Boolean.parseBoolean(glossList.item(0).getTextContent()));
        return booklet;
    }


}
