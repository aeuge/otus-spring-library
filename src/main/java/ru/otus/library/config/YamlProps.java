package ru.otus.library.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="exam")
public class YamlProps {

    private String filename;
    private String bundlebase;
    private String localeset;
    private String defaultAge;
    private String numberOfQuestions;

    public String getDefaultAge() {
        return defaultAge;
    }

    public void setDefaultAge(String defaultAge) {
        this.defaultAge = defaultAge;
    }

    public String getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(String numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

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
