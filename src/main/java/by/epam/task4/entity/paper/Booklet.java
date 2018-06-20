package by.epam.task4.entity.paper;

import java.util.Objects;

public class Booklet extends Paper {
    private boolean isGlossy;

    public boolean isGlossy() {
        return isGlossy;
    }

    public void setGlossy(boolean glossy) {
        isGlossy = glossy;
    }

    @Override
    public String toString() {
        return "Booklet{" +
                "isGlossy=" + isGlossy +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", color=" + color +
                ", monthly=" + monthly +
                ", volume=" + volume +
                ", firstPublicationDate='" + firstPublicationDate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Booklet booklet = (Booklet) o;
        return id == booklet.id && title.equals(booklet.title) &&
                firstPublicationDate.equals(booklet.firstPublicationDate);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + title.hashCode() + firstPublicationDate.hashCode();
        return result;
    }
}
