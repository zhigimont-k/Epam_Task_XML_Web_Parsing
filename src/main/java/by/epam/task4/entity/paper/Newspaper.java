package by.epam.task4.entity.paper;

import java.util.Objects;

public class Newspaper extends Paper {
    private int postIndex;

    public int getPostIndex() {
        return postIndex;
    }

    public void setPostIndex(int postIndex) {
        this.postIndex = postIndex;
    }

    @Override
    public String toString() {
        return "Newspaper{" +
                "postIndex=" + postIndex +
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
        Newspaper newspaper = (Newspaper) o;
        return id == newspaper.id && title.equals(newspaper.title) &&
                firstPublicationDate.equals(newspaper.firstPublicationDate) && postIndex == newspaper.postIndex;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + title.hashCode() + firstPublicationDate.hashCode() + postIndex;
        return result;
    }
}
