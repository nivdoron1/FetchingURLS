import java.net.MalformedURLException;

public class Source {
    private final String Url;
    private final int MaxAmount;
    private final int DepthFactor;
    private final boolean CrossLevelUniqness;

    public Source(String Url,int MaxAmount,int DepthFactor,boolean CrossLevelUniqness) {
        this.Url=Url;
        this.MaxAmount=MaxAmount;
        this.DepthFactor=DepthFactor;
        this.CrossLevelUniqness=CrossLevelUniqness;
    }


    public int getDepthFactor() {
        return DepthFactor;
    }

    public boolean isCrossLevelUniqness() {
        return CrossLevelUniqness;
    }

    public int getMaxAmount() {
        return MaxAmount;
    }

    public String getUrl() {
        return Url;
    }

}
