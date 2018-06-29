package by.epam.task4.builder;

import by.epam.task4.entity.paper.Paper;

import java.util.HashSet;
import java.util.Set;

abstract public class PaperParserBuilder {
    protected Set<Paper> paperSet = new HashSet<>();
    protected enum PaperType{
        NEWSPAPER, MAGAZINE, BOOKLET
    }
    protected enum PaperAttribute{
        ID, TITLE
    }
    protected enum PaperProperty {
        MONTHLY("monthly"), COLOR("color"), VOLUME("volume"),
        SUBSCRIPTION_INDEX("subscription-index"), FIRST_PUBLICATION("first-publication"), GLOSS("gloss");
        private String value;
        PaperProperty(String value){
            this.value = value;
        }
        public String getValue(){
            return value;
        }
    }

    abstract public Set<Paper> parse(String fileName) throws PaperParserException;
}
