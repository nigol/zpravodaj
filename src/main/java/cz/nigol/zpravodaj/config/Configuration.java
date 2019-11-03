package cz.nigol.zpravodaj.config;

public class Configuration {
    private String url;
    private String archiveUrl;

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the archiveUrl
     */
    public String getArchiveUrl() {
        return archiveUrl;
    }

    /**
     * @param archiveUrl the archiveUrl to set
     */
    public void setArchiveUrl(String archiveUrl) {
        this.archiveUrl = archiveUrl;
    }
}
