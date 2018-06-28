package by.epam.task4.builder;

public class ParserBuilderFactory {
    private static ParserBuilderFactory instance = new ParserBuilderFactory();

    private ParserBuilderFactory() {
    }

    public static ParserBuilderFactory getInstance() {
        if (instance == null) {
            instance = new ParserBuilderFactory();
        }
        return instance;
    }

    public PaperParserBuilder initBuilder(String type) throws ParserBuilderFactoryException {
        PaperParserBuilder builder;
        switch (type) {
            case "DOM":
                try {
                    builder = new DOMPaperParser();
                } catch (PaperParserException e) {
                    throw new ParserBuilderFactoryException(e);
                }
                break;
            case "SAX":
                builder = new SAXPaperParser();
                break;
            case "StAX":
                builder = new StAXPaperParser();
                break;
            default:
                throw new ParserBuilderFactoryException("Illegal parser type.");
        }
        return builder;
    }
}
