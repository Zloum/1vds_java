package ru.irk.usef.vds;

public enum Urls {
    SPARES("https://job.firstvds.ru/spares.json"),
    ALTERNATIVES("https://job.firstvds.ru/alternatives.json");
    private String url;

    Urls(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
};