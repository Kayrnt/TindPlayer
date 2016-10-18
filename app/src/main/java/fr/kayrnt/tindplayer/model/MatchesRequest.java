package fr.kayrnt.tindplayer.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class MatchesRequest {

    @SerializedName("matches")
    @Expose
    private List<Match> matches = new ArrayList<Match>();
    @SerializedName("blocks")
    @Expose
    private List<Object> blocks = new ArrayList<Object>();
    @SerializedName("lists")
    @Expose
    private List<Object> lists = new ArrayList<Object>();
    @SerializedName("deleted_lists")
    @Expose
    private List<Object> deletedLists = new ArrayList<Object>();
    @SerializedName("last_activity_date")
    @Expose
    private String lastActivityDate;

    /**
     * 
     * @return
     *     The matches
     */
    public List<Match> getMatches() {
        return matches;
    }

    /**
     * 
     * @param matches
     *     The matches
     */
    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public MatchesRequest withMatches(List<Match> matches) {
        this.matches = matches;
        return this;
    }

    /**
     * 
     * @return
     *     The blocks
     */
    public List<Object> getBlocks() {
        return blocks;
    }

    /**
     * 
     * @param blocks
     *     The blocks
     */
    public void setBlocks(List<Object> blocks) {
        this.blocks = blocks;
    }

    public MatchesRequest withBlocks(List<Object> blocks) {
        this.blocks = blocks;
        return this;
    }

    /**
     * 
     * @return
     *     The lists
     */
    public List<Object> getLists() {
        return lists;
    }

    /**
     * 
     * @param lists
     *     The lists
     */
    public void setLists(List<Object> lists) {
        this.lists = lists;
    }

    public MatchesRequest withLists(List<Object> lists) {
        this.lists = lists;
        return this;
    }

    /**
     * 
     * @return
     *     The deletedLists
     */
    public List<Object> getDeletedLists() {
        return deletedLists;
    }

    /**
     * 
     * @param deletedLists
     *     The deleted_lists
     */
    public void setDeletedLists(List<Object> deletedLists) {
        this.deletedLists = deletedLists;
    }

    public MatchesRequest withDeletedLists(List<Object> deletedLists) {
        this.deletedLists = deletedLists;
        return this;
    }

    /**
     * 
     * @return
     *     The lastActivityDate
     */
    public String getLastActivityDate() {
        return lastActivityDate;
    }

    /**
     * 
     * @param lastActivityDate
     *     The last_activity_date
     */
    public void setLastActivityDate(String lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    public MatchesRequest withLastActivityDate(String lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(matches).append(blocks).append(lists).append(deletedLists).append(lastActivityDate).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MatchesRequest) == false) {
            return false;
        }
        MatchesRequest rhs = ((MatchesRequest) other);
        return new EqualsBuilder().append(matches, rhs.matches).append(blocks, rhs.blocks).append(lists, rhs.lists).append(deletedLists, rhs.deletedLists).append(lastActivityDate, rhs.lastActivityDate).isEquals();
    }

}
