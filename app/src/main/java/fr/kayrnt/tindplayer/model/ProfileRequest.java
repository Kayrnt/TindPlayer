
package fr.kayrnt.tindplayer.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ProfileRequest {

    @SerializedName("status")
    @Expose
    private Long status;
    @SerializedName("results")
    @Expose
    private Profile profile;

    /**
     * 
     * @return
     *     The status
     */
    public Long getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(Long status) {
        this.status = status;
    }

    public ProfileRequest withStatus(Long status) {
        this.status = status;
        return this;
    }

    /**
     * 
     * @return
     *     The profile
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * 
     * @param profile
     *     The profile
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public ProfileRequest withResults(Profile profile) {
        this.profile = profile;
        return this;
    }

}
