package by.epam.task4.entity.paper;

import by.epam.task4.entity.Entity;

public abstract class Paper extends Entity {
    protected int id;
    protected String title;
    protected boolean color;
    protected boolean monthly;
    protected int volume;
    protected String firstPublicationDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public boolean isMonthly() {
        return monthly;
    }

    public void setMonthly(boolean monthly) {
        this.monthly = monthly;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getFirstPublicationDate() {
        return firstPublicationDate;
    }

    public void setFirstPublicationDate(String firstPublicationDate) {
        this.firstPublicationDate = firstPublicationDate;
    }


}
