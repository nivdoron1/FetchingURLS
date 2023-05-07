
public class Source {
    private final String url;
    private final int maxAmount;
    private final int depthFactor;
    private final boolean crossLevelUniqness;

    /**
     * Constructor for the Source class.
     *
     * @param url                The URL to use as a source.
     * @param maxAmount          The maximum number of URLs to execute at each level.
     * @param depthFactor        The depth factor for the URL crawling.
     * @param crossLevelUniqness The flag to determine if cross-level uniqueness should be enforced.
     */
    public Source(String url,int maxAmount,int depthFactor,boolean crossLevelUniqness) {
        this.url =url;
        this.maxAmount =maxAmount;
        this.depthFactor=depthFactor;
        this.crossLevelUniqness=crossLevelUniqness;
    }

    /**
     * Gets the depth factor for the source.
     *
     * @return The depth factor as an integer.
     */
    public int getDepthFactor() {
        return depthFactor;
    }
    /**
     * Checks if cross-level uniqueness is enabled for the source.
     *
     * @return True if cross-level uniqueness is enabled, false otherwise.
     */
    public boolean isCrossLevelUniqness() {
        return crossLevelUniqness;
    }
    /**
     * Gets the maximum amount of URLs to execute at each level for the source.
     *
     * @return The maximum amount as an integer.
     */
    public int getMaxAmount() {
        return maxAmount;
    }

    /**
     * Gets the URL for the source.
     *
     * @return The URL as a string.
     */
    public String getUrl() {
        return url;
    }

}
