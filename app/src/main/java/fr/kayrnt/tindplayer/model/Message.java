package fr.kayrnt.tindplayer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Message {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("match_id")
    @Expose
    private String matchId;
    @SerializedName("to")
    @Expose
    private String to;
    @SerializedName("from")
    @Expose
    private String from;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("sent_date")
    @Expose
    private String sentDate;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("timestamp")
    @Expose
    private Long timestamp;

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The _id
     */
    public void setId(String id) {
        this.id = id;
    }

    public Message withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * 
     * @return
     *     The matchId
     */
    public String getMatchId() {
        return matchId;
    }

    /**
     * 
     * @param matchId
     *     The match_id
     */
    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public Message withMatchId(String matchId) {
        this.matchId = matchId;
        return this;
    }

    /**
     * 
     * @return
     *     The to
     */
    public String getTo() {
        return to;
    }

    /**
     * 
     * @param to
     *     The to
     */
    public void setTo(String to) {
        this.to = to;
    }

    public Message withTo(String to) {
        this.to = to;
        return this;
    }

    /**
     * 
     * @return
     *     The from
     */
    public String getFrom() {
        return from;
    }

    /**
     * 
     * @param from
     *     The from
     */
    public void setFrom(String from) {
        this.from = from;
    }

    public Message withFrom(String from) {
        this.from = from;
        return this;
    }

    /**
     * 
     * @return
     *     The message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 
     * @param message
     *     The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public Message withMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * 
     * @return
     *     The sentDate
     */
    public String getSentDate() {
        return sentDate;
    }

    /**
     * 
     * @param sentDate
     *     The sent_date
     */
    public void setSentDate(String sentDate) {
        this.sentDate = sentDate;
    }

    public Message withSentDate(String sentDate) {
        this.sentDate = sentDate;
        return this;
    }

    /**
     * 
     * @return
     *     The createdDate
     */
    public String getCreatedDate() {
        return createdDate;
    }

    /**
     * 
     * @param createdDate
     *     The created_date
     */
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Message withCreatedDate(String createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    /**
     * 
     * @return
     *     The timestamp
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * 
     * @param timestamp
     *     The timestamp
     */
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Message withTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(matchId).append(to).append(from).append(message).append(sentDate).append(createdDate).append(timestamp).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Message) == false) {
            return false;
        }
        Message rhs = ((Message) other);
        return new EqualsBuilder().append(id, rhs.id).append(matchId, rhs.matchId).append(to, rhs.to).append(from, rhs.from).append(message, rhs.message).append(sentDate, rhs.sentDate).append(createdDate, rhs.createdDate).append(timestamp, rhs.timestamp).isEquals();
    }

}
