package by.epam.task4.builder;

import by.epam.task4.entity.paper.Booklet;
import by.epam.task4.entity.paper.Magazine;
import by.epam.task4.entity.paper.Newspaper;
import by.epam.task4.entity.paper.Paper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.XMLReader;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.util.Set;

class StAXPaperParser extends PaperParserBuilder {
    private XMLInputFactory factory;

    StAXPaperParser() {
        factory = XMLInputFactory.newInstance();
    }

    @Override
    public Set<Paper> parse(String fileName) throws PaperParserException {
        String name;
        try (InputStream inputStream = new FileInputStream(new File(getClass().getClassLoader().getResource(fileName).getFile()))) {
            XMLStreamReader reader = factory.createXMLStreamReader(inputStream);

            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (PaperType.NEWSPAPER.name().equalsIgnoreCase(name)) {
                        Paper newspaper = buildNewspaper(reader);
                        paperSet.add(newspaper);
                    } else if (PaperType.MAGAZINE.name().equalsIgnoreCase(name)) {
                        Paper magazine = buildMagazine(reader);
                        paperSet.add(magazine);
                    } else if (PaperType.BOOKLET.name().equalsIgnoreCase(name)) {
                        Paper booklet = buildBooklet(reader);
                        paperSet.add(booklet);
                    }
                }
            }
        } catch (IOException | XMLStreamException e) {
            throw new PaperParserException(e);
        }
        return paperSet;
    }

    private Paper buildPaper(Paper paper, XMLStreamReader reader) throws PaperParserException {
        paper.setId(Integer.parseInt(reader.getAttributeValue(null, PaperAttribute.ID.name().toLowerCase())));
        paper.setTitle(reader.getAttributeValue(null, PaperAttribute.TITLE.name().toLowerCase()));

        try {
            String name;
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();

                    if (PaperProperty.MONTHLY.getValue().equalsIgnoreCase(name)) {
                        name = getXMLText(reader);
                        paper.setMonthly(Boolean.parseBoolean(name));
                    } else if (PaperProperty.COLOR.getValue().equalsIgnoreCase(name)) {
                        name = getXMLText(reader);
                        paper.setColor(Boolean.parseBoolean(name));
                    } else if (PaperProperty.VOLUME.getValue().equalsIgnoreCase(name)) {
                        name = getXMLText(reader);
                        paper.setVolume(Integer.parseInt(name));
                    } else if (PaperProperty.GLOSS.getValue().equalsIgnoreCase(name)) {
                        name = getXMLText(reader);
                        if (paper instanceof Magazine || paper instanceof Booklet){
                            paper.setGloss(Boolean.parseBoolean(name));
                        }
                    }  else if (PaperProperty.SUBSCRIPTION_INDEX.getValue().equalsIgnoreCase(name)) {
                        name = getXMLText(reader);
                        if (paper instanceof Newspaper || paper instanceof Magazine){
                            paper.setSubscriptionIndex(Integer.parseInt(name));}
                    } else if (PaperProperty.FIRST_PUBLICATION.getValue().equalsIgnoreCase(name)) {
                        name = getXMLText(reader);
                        paper.setFirstPublicationDate(name);
                    }
                } else if (type == XMLStreamConstants.END_ELEMENT){
                    name = reader.getLocalName();
                    if (PaperType.NEWSPAPER.name().equalsIgnoreCase(name) ||
                            PaperType.MAGAZINE.name().equalsIgnoreCase(name) ||
                            PaperType.BOOKLET.name().equalsIgnoreCase(name)){
                        return paper;
                    }
                }
            }
        } catch (XMLStreamException e) {
            throw new PaperParserException(e);
        }
        throw new PaperParserException("Unknown XML tag found");
    }

    private Paper buildNewspaper(XMLStreamReader reader) throws PaperParserException {
        Paper newspaper = new Newspaper();
        buildPaper(newspaper, reader);
        return newspaper;
    }

    private Paper buildMagazine(XMLStreamReader reader) throws PaperParserException {
        Paper magazine = new Magazine();
        buildPaper(magazine, reader);
        return magazine;
    }

    private Paper buildBooklet(XMLStreamReader reader) throws PaperParserException {
        Paper booklet = new Booklet();
        buildPaper(booklet, reader);
        return booklet;
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
