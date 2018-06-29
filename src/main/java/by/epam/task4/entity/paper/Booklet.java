package by.epam.task4.entity.paper;

import java.util.Objects;

public class Booklet extends Paper {
    private boolean gloss;

    public boolean getGloss() {
        return gloss;
    }

    public void setGloss(boolean glossy) {
        gloss = glossy;
    }

    @Override
    public void setSubscriptionIndex(int postIndex) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Booklets can't have subscription index");
    }

    @Override
    public int getSubscriptionIndex() throws UnsupportedOperationException {
        return 0;
    }

    @Override
    public String toString() {
        return "Booklet{" +
                "gloss=" + gloss +
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
