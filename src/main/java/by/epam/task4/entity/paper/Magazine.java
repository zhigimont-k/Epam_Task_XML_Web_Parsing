package by.epam.task4.entity.paper;

import java.util.Objects;

public class Magazine extends Paper {
    private boolean isGlossy;
    private int postIndex;

    public boolean isGlossy() {
        return isGlossy;
    }

    public void setGlossy(boolean glossy) {
        isGlossy = glossy;
    }

    public int getPostIndex() {
        return postIndex;
    }

    public void setPostIndex(int postIndex) {
        this.postIndex = postIndex;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "isGlossy=" + isGlossy +
                ", postIndex=" + postIndex +
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
        Magazine magazine = (Magazine) o;
        return id == magazine.id && title.equals(magazine.title) &&
                firstPublicationDate.equals(magazine.firstPublicationDate) && postIndex == magazine.postIndex;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + title.hashCode() + firstPublicationDate.hashCode() + postIndex;
        return result;
    }
}
