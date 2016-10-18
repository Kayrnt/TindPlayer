package fr.kayrnt.tindplayer.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Match {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("closed")
    @Expose
    private Boolean closed;
    @SerializedName("common_friend_count")
    @Expose
    private Long commonFriendCount;
    @SerializedName("common_like_count")
    @Expose
    private Long commonLikeCount;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("dead")
    @Expose
    private Boolean dead;
    @SerializedName("last_activity_date")
    @Expose
    private String lastActivityDate;
    @SerializedName("message_count")
    @Expose
    private Long messageCount;
    @SerializedName("messages")
    @Expose
    private List<Object> messages = new ArrayList<Object>();
    @SerializedName("muted")
    @Expose
    private Boolean muted;
    @SerializedName("participants")
    @Expose
    private List<String> participants = new ArrayList<String>();
    @SerializedName("pending")
    @Expose
    private Boolean pending;
    @SerializedName("is_super_like")
    @Expose
    private Boolean isSuperLike;
    @SerializedName("is_boost_match")
    @Expose
    private Boolean isBoostMatch;
    @SerializedName("following")
    @Expose
    private Boolean following;
    @SerializedName("following_moments")
    @Expose
    private Boolean followingMoments;
    @SerializedName("person")
    @Expose
    private Profile person;

    /**
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The _id
     */
    public void setId(String id) {
        this.id = id;
    }

    public Match withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * @return The closed
     */
    public Boolean getClosed() {
        return closed;
    }

    /**
     * @param closed The closed
     */
    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public Match withClosed(Boolean closed) {
        this.closed = closed;
        return this;
    }

    /**
     * @return The commonFriendCount
     */
    public Long getCommonFriendCount() {
        return commonFriendCount;
    }

    /**
     * @param commonFriendCount The common_friend_count
     */
    public void setCommonFriendCount(Long commonFriendCount) {
        this.commonFriendCount = commonFriendCount;
    }

    public Match withCommonFriendCount(Long commonFriendCount) {
        this.commonFriendCount = commonFriendCount;
        return this;
    }

    /**
     * @return The commonLikeCount
     */
    public Long getCommonLikeCount() {
        return commonLikeCount;
    }

    /**
     * @param commonLikeCount The common_like_count
     */
    public void setCommonLikeCount(Long commonLikeCount) {
        this.commonLikeCount = commonLikeCount;
    }

    public Match withCommonLikeCount(Long commonLikeCount) {
        this.commonLikeCount = commonLikeCount;
        return this;
    }

    /**
     * @return The createdDate
     */
    public String getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate The created_date
     */
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Match withCreatedDate(String createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    /**
     * @return The dead
     */
    public Boolean getDead() {
        return dead;
    }

    /**
     * @param dead The dead
     */
    public void setDead(Boolean dead) {
        this.dead = dead;
    }

    public Match withDead(Boolean dead) {
        this.dead = dead;
        return this;
    }

    /**
     * @return The lastActivityDate
     */
    public String getLastActivityDate() {
        return lastActivityDate;
    }

    /**
     * @param lastActivityDate The last_activity_date
     */
    public void setLastActivityDate(String lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    public Match withLastActivityDate(String lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
        return this;
    }

    /**
     * @return The messageCount
     */
    public Long getMessageCount() {
        return messageCount;
    }

    /**
     * @param messageCount The message_count
     */
    public void setMessageCount(Long messageCount) {
        this.messageCount = messageCount;
    }

    public Match withMessageCount(Long messageCount) {
        this.messageCount = messageCount;
        return this;
    }

    /**
     * @return The messages
     */
    public List<Object> getMessages() {
        return messages;
    }

    /**
     * @param messages The messages
     */
    public void setMessages(List<Object> messages) {
        this.messages = messages;
    }

    public Match withMessages(List<Object> messages) {
        this.messages = messages;
        return this;
    }

    /**
     * @return The muted
     */
    public Boolean getMuted() {
        return muted;
    }

    /**
     * @param muted The muted
     */
    public void setMuted(Boolean muted) {
        this.muted = muted;
    }

    public Match withMuted(Boolean muted) {
        this.muted = muted;
        return this;
    }

    /**
     * @return The participants
     */
    public List<String> getParticipants() {
        return participants;
    }

    /**
     * @param participants The participants
     */
    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public Match withParticipants(List<String> participants) {
        this.participants = participants;
        return this;
    }

    /**
     * @return The pending
     */
    public Boolean getPending() {
        return pending;
    }

    /**
     * @param pending The pending
     */
    public void setPending(Boolean pending) {
        this.pending = pending;
    }

    public Match withPending(Boolean pending) {
        this.pending = pending;
        return this;
    }

    /**
     * @return The isSuperLike
     */
    public Boolean getIsSuperLike() {
        return isSuperLike;
    }

    /**
     * @param isSuperLike The is_super_like
     */
    public void setIsSuperLike(Boolean isSuperLike) {
        this.isSuperLike = isSuperLike;
    }

    public Match withIsSuperLike(Boolean isSuperLike) {
        this.isSuperLike = isSuperLike;
        return this;
    }

    /**
     * @return The isBoostMatch
     */
    public Boolean getIsBoostMatch() {
        return isBoostMatch;
    }

    /**
     * @param isBoostMatch The is_boost_match
     */
    public void setIsBoostMatch(Boolean isBoostMatch) {
        this.isBoostMatch = isBoostMatch;
    }

    public Match withIsBoostMatch(Boolean isBoostMatch) {
        this.isBoostMatch = isBoostMatch;
        return this;
    }

    /**
     * @return The following
     */
    public Boolean getFollowing() {
        return following;
    }

    /**
     * @param following The following
     */
    public void setFollowing(Boolean following) {
        this.following = following;
    }

    public Match withFollowing(Boolean following) {
        this.following = following;
        return this;
    }

    /**
     * @return The followingMoments
     */
    public Boolean getFollowingMoments() {
        return followingMoments;
    }

    /**
     * @param followingMoments The following_moments
     */
    public void setFollowingMoments(Boolean followingMoments) {
        this.followingMoments = followingMoments;
    }

    public Match withFollowingMoments(Boolean followingMoments) {
        this.followingMoments = followingMoments;
        return this;
    }

    /**
     * @return The person
     */
    public Profile getPerson() {
        return person;
    }

    /**
     * @param person The person
     */
    public void setPerson(Profile person) {
        this.person = person;
    }

    public Match withPerson(Profile person) {
        this.person = person;
        return this;
    }

}