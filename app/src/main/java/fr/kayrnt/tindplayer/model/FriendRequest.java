
package fr.kayrnt.tindplayer.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FriendRequest {

    @SerializedName("status")
    @Expose
    private Long status;
    @SerializedName("results")
    @Expose
    private List<FriendProfile> friendProfiles = new ArrayList<FriendProfile>();

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

    public FriendRequest withStatus(Long status) {
        this.status = status;
        return this;
    }

    /**
     * 
     * @return
     *     The results
     */
    public List<FriendProfile> getFriendProfiles() {
        return friendProfiles;
    }

    /**
     * 
     * @param friendProfiles
     *     The results
     */
    public void setFriendProfiles(List<FriendProfile> friendProfiles) {
        this.friendProfiles = friendProfiles;
    }

    public FriendRequest withResults(List<FriendProfile> friendProfiles) {
        this.friendProfiles = friendProfiles;
        return this;
    }

}
