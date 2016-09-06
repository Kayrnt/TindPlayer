
package fr.kayrnt.tindplayer.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Photo_ {

    @SerializedName("small")
    @Expose
    private String small;
    @SerializedName("medium")
    @Expose
    private String medium;
    @SerializedName("large")
    @Expose
    private String large;

    /**
     * 
     * @return
     *     The small
     */
    public String getSmall() {
        return small;
    }

    /**
     * 
     * @param small
     *     The small
     */
    public void setSmall(String small) {
        this.small = small;
    }

    public Photo_ withSmall(String small) {
        this.small = small;
        return this;
    }

    /**
     * 
     * @return
     *     The medium
     */
    public String getMedium() {
        return medium;
    }

    /**
     * 
     * @param medium
     *     The medium
     */
    public void setMedium(String medium) {
        this.medium = medium;
    }

    public Photo_ withMedium(String medium) {
        this.medium = medium;
        return this;
    }

    /**
     * 
     * @return
     *     The large
     */
    public String getLarge() {
        return large;
    }

    /**
     * 
     * @param large
     *     The large
     */
    public void setLarge(String large) {
        this.large = large;
    }

    public Photo_ withLarge(String large) {
        this.large = large;
        return this;
    }

}
