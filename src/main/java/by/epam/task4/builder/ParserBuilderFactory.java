package by.epam.task4.builder;

public class ParserBuilderFactory {
    private static ParserBuilderFactory instance = new ParserBuilderFactory();

    private ParserBuilderFactory(){}

    public ParserBuilderFactory getInstance() {
        if (instance == null) {
            instance = new ParserBuilderFactory();
        }
        return instance;
    }

    public AbstractParserBuilder initBuilder(String type) throws ParserBuilderFactoryException{
        AbstractParserBuilder builder;
        switch(type){
            case "DOM":
                builder = new DOMParserBuilder();
                break;
            case "SAX":
                builder = new SAXParserBuilder();
                break;
            case "StAX":
                builder = new StAXParserBuilder();
                break;
            default:
                throw new ParserBuilderFactoryException("Illegal parser type.");
        }
        return builder;
    }
}
