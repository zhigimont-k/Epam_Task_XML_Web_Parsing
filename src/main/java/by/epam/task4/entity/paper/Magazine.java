package by.epam.task4.entity.paper;

public class Magazine extends Paper {
    private boolean gloss;
    private int subscriptionIndex;

    @Override
    public boolean getGloss() {
        return gloss;
    }

    @Override
    public void setGloss(boolean glossy) {
        gloss = glossy;
    }

    @Override
    public int getSubscriptionIndex() {
        return subscriptionIndex;
    }

    @Override
    public void setSubscriptionIndex(int postIndex) {
        this.subscriptionIndex = postIndex;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "gloss=" + gloss +
                ", subscriptionIndex=" + subscriptionIndex +
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
                firstPublicationDate.equals(magazine.firstPublicationDate) && subscriptionIndex == magazine.subscriptionIndex;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + title.hashCode() + firstPublicationDate.hashCode() + subscriptionIndex;
        return result;
    }
}
