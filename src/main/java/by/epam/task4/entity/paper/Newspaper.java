package by.epam.task4.entity.paper;

public class Newspaper extends Paper {
    private int subscriptionIndex;

    public Newspaper(){

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
    public boolean getGloss() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Newspaper can't be glossy");
    }

    @Override
    public void setGloss(boolean glossy) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Newspaper can't be glossy");
    }

    @Override
    public String toString() {
        return "Newspaper{" +
                "subscriptionIndex=" + subscriptionIndex +
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
                firstPublicationDate.equals(newspaper.firstPublicationDate) && subscriptionIndex == newspaper.subscriptionIndex;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + title.hashCode() + firstPublicationDate.hashCode() + subscriptionIndex;
        return result;
    }
}
