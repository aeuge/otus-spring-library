package ru.otus.library.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="library")
public class YamlProps {
    private String bundlebase;
    private String localeset;

    public String getBundlebase() {
        return bundlebase;
    }

    public void setBundlebase(String bundlebase) {
        this.bundlebase = bundlebase;
    }

    public String getLocaleset() {
        return localeset;
    }

    public void setLocaleset(String localeset) {
        this.localeset = localeset;
    }
}
