
package fr.kayrnt.tindplayer.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class Versions {

    @SerializedName("active_text")
    @Expose
    private String activeText;
    @SerializedName("age_filter")
    @Expose
    private String ageFilter;
    @Expose
    private String matchmaker;
    @Expose
    private String trending;
    @SerializedName("trending_active_text")
    @Expose
    private String trendingActiveText;

    /**
     * 
     * @return
     *     The activeText
     */
    public String getActiveText() {
        return activeText;
    }

    /**
     * 
     * @param activeText
     *     The active_text
     */
    public void setActiveText(String activeText) {
        this.activeText = activeText;
    }

    public Versions withActiveText(String activeText) {
        this.activeText = activeText;
        return this;
    }

    /**
     * 
     * @return
     *     The ageFilter
     */
    public String getAgeFilter() {
        return ageFilter;
    }

    /**
     * 
     * @param ageFilter
     *     The age_filter
     */
    public void setAgeFilter(String ageFilter) {
        this.ageFilter = ageFilter;
    }

    public Versions withAgeFilter(String ageFilter) {
        this.ageFilter = ageFilter;
        return this;
    }

    /**
     * 
     * @return
     *     The matchmaker
     */
    public String getMatchmaker() {
        return matchmaker;
    }

    /**
     * 
     * @param matchmaker
     *     The matchmaker
     */
    public void setMatchmaker(String matchmaker) {
        this.matchmaker = matchmaker;
    }

    public Versions withMatchmaker(String matchmaker) {
        this.matchmaker = matchmaker;
        return this;
    }

    /**
     * 
     * @return
     *     The trending
     */
    public String getTrending() {
        return trending;
    }

    /**
     * 
     * @param trending
     *     The trending
     */
    public void setTrending(String trending) {
        this.trending = trending;
    }

    public Versions withTrending(String trending) {
        this.trending = trending;
        return this;
    }

    /**
     * 
     * @return
     *     The trendingActiveText
     */
    public String getTrendingActiveText() {
        return trendingActiveText;
    }

    /**
     * 
     * @param trendingActiveText
     *     The trending_active_text
     */
    public void setTrendingActiveText(String trendingActiveText) {
        this.trendingActiveText = trendingActiveText;
    }

    public Versions withTrendingActiveText(String trendingActiveText) {
        this.trendingActiveText = trendingActiveText;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
