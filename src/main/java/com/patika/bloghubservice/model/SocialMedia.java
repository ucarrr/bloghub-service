package com.patika.bloghubservice.model;

public class SocialMedia {
    private String name;
    private String link;

    public SocialMedia(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "SocialMedia{" +
                "name='" + name + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
