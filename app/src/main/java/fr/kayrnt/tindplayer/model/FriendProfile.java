
package fr.kayrnt.tindplayer.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FriendProfile {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("photo")
    @Expose
    private List<Photo> photo = new ArrayList<Photo>();
    @SerializedName("in_squad")
    @Expose
    private Boolean inSquad;
    @SerializedName("was_in_group")
    @Expose
    private Boolean wasInGroup;

    /**
     * 
     * @return
     *     The userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 
     * @param userId
     *     The user_id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public FriendProfile withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    public FriendProfile withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * 
     * @return
     *     The photo
     */
    public List<Photo> getPhoto() {
        return photo;
    }

    /**
     * 
     * @param photo
     *     The photo
     */
    public void setPhoto(List<Photo> photo) {
        this.photo = photo;
    }

    public FriendProfile withPhoto(List<Photo> photo) {
        this.photo = photo;
        return this;
    }

    /**
     * 
     * @return
     *     The inSquad
     */
    public Boolean getInSquad() {
        return inSquad;
    }

    /**
     * 
     * @param inSquad
     *     The in_squad
     */
    public void setInSquad(Boolean inSquad) {
        this.inSquad = inSquad;
    }

    public FriendProfile withInSquad(Boolean inSquad) {
        this.inSquad = inSquad;
        return this;
    }

    /**
     * 
     * @return
     *     The wasInGroup
     */
    public Boolean getWasInGroup() {
        return wasInGroup;
    }

    /**
     * 
     * @param wasInGroup
     *     The was_in_group
     */
    public void setWasInGroup(Boolean wasInGroup) {
        this.wasInGroup = wasInGroup;
    }

    public FriendProfile withWasInGroup(Boolean wasInGroup) {
        this.wasInGroup = wasInGroup;
        return this;
    }

}
