package by.epam.task4.builder;

import by.epam.task4.entity.paper.Booklet;
import by.epam.task4.entity.paper.Magazine;
import by.epam.task4.entity.paper.Newspaper;
import by.epam.task4.entity.paper.Paper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.Set;

class SAXPaperParser extends PaperParserBuilder {

    SAXPaperParser() {
    }

    @Override
    public Set<Paper> parse(String fileName) throws PaperParserException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(new File(getClass().getClassLoader().getResource(fileName).getFile()),
                    new PaperHandler());
        } catch (IOException | SAXException | ParserConfigurationException e) {
            throw new PaperParserException(e);
        }
        return paperSet;
    }

    class PaperHandler extends DefaultHandler {
        private Paper paper;
        private String qName;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            this.qName = qName;
            if (PaperType.NEWSPAPER.name().equalsIgnoreCase(qName)) {
                paper = new Newspaper();
            } else if (PaperType.MAGAZINE.name().equalsIgnoreCase(qName)) {
                paper = new Magazine();
            } else if (PaperType.BOOKLET.name().equalsIgnoreCase(qName)) {
                paper = new Booklet();
            }

            for (int i = 0; i < attributes.getLength(); i++) {
                String attributeName = attributes.getLocalName(i);
                String attributeValue = attributes.getValue(i);
                handleAttributes(attributeName, attributeValue);
            }
        }

        private void handleAttributes(String attribute, String value) {
            if (PaperAttribute.ID.name().equalsIgnoreCase(attribute)) {
                paper.setId(Integer.parseInt(value));
            } else if (PaperAttribute.TITLE.name().equalsIgnoreCase(attribute)) {
                paper.setTitle(value);
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            String value = new String(ch, start, length).trim();
            if (!value.isEmpty()) {
                if (PaperProperty.MONTHLY.getValue().equals(qName)) {
                    paper.setMonthly(Boolean.parseBoolean(value));
                } else if (PaperProperty.COLOR.getValue().equals(qName)) {
                    paper.setColor(Boolean.parseBoolean(value));
                } else if (PaperProperty.GLOSS.getValue().equals(qName)) {
                    paper.setGloss(Boolean.parseBoolean(value));
                } else if (PaperProperty.VOLUME.getValue().equals(qName)) {
                    paper.setVolume(Integer.parseInt(value));
                } else if (PaperProperty.SUBSCRIPTION_INDEX.getValue().equals(qName)) {
                    paper.setSubscriptionIndex(Integer.parseInt(value));
                } else if (PaperProperty.FIRST_PUBLICATION.getValue().equals(qName)) {
                    paper.setFirstPublicationDate(value);
                }
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            if (PaperType.NEWSPAPER.name().equalsIgnoreCase(qName) ||
                    PaperType.MAGAZINE.name().equalsIgnoreCase(qName) ||
                    PaperType.BOOKLET.name().equalsIgnoreCase(qName)) {
                paperSet.add(paper);
            }
        }
    }
}
