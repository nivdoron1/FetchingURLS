
public class Source {
    private final String Url;
    private final int MaxAmount;
    private final int DepthFactor;
    private final boolean CrossLevelUniqness;

    /**
     * Constructor for the Source class.
     *
     * @param Url                The URL to use as a source.
     * @param MaxAmount          The maximum number of URLs to execute at each level.
     * @param DepthFactor        The depth factor for the URL crawling.
     * @param CrossLevelUniqness The flag to determine if cross-level uniqueness should be enforced.
     */
    public Source(String Url,int MaxAmount,int DepthFactor,boolean CrossLevelUniqness) {
        this.Url=Url;
        this.MaxAmount=MaxAmount;
        this.DepthFactor=DepthFactor;
        this.CrossLevelUniqness=CrossLevelUniqness;
    }

    /**
     * Gets the depth factor for the source.
     *
     * @return The depth factor as an integer.
     */
    public int getDepthFactor() {
        return DepthFactor;
    }
    /**
     * Checks if cross-level uniqueness is enabled for the source.
     *
     * @return True if cross-level uniqueness is enabled, false otherwise.
     */
    public boolean isCrossLevelUniqness() {
        return CrossLevelUniqness;
    }
    /**
     * Gets the maximum amount of URLs to execute at each level for the source.
     *
     * @return The maximum amount as an integer.
     */
    public int getMaxAmount() {
        return MaxAmount;
    }

    /**
     * Gets the URL for the source.
     *
     * @return The URL as a string.
     */
    public String getUrl() {
        return Url;
    }

}
