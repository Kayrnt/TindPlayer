
package fr.kayrnt.tindplayer.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Globals {

    @Expose
    private boolean friends;
    @SerializedName("invite_type")
    @Expose
    private String inviteType;
    @SerializedName("recs_interval")
    @Expose
    private int recsInterval;
    @SerializedName("updates_interval")
    @Expose
    private int updatesInterval;
    @SerializedName("recs_size")
    @Expose
    private int recsSize;
    @SerializedName("matchmaker_default_message")
    @Expose
    private String matchmakerDefaultMessage;
    @SerializedName("share_default_text")
    @Expose
    private String shareDefaultText;
    @SerializedName("boost_decay")
    @Expose
    private int boostDecay;
    @SerializedName("boost_up")
    @Expose
    private int boostUp;
    @SerializedName("boost_down")
    @Expose
    private int boostDown;
    @Expose
    private boolean sparks;
    @Expose
    private boolean kontagent;
    @SerializedName("sparks_enabled")
    @Expose
    private boolean sparksEnabled;
    @SerializedName("kontagent_enabled")
    @Expose
    private boolean kontagentEnabled;
    @Expose
    private boolean mqtt;
    @SerializedName("tinder_sparks")
    @Expose
    private boolean tinderSparks;
    @SerializedName("moments_interval")
    @Expose
    private int momentsInterval;
    @Expose
    private boolean plus;

    /**
     * 
     * @return
     *     The friends
     */
    public boolean isFriends() {
        return friends;
    }

    /**
     * 
     * @param friends
     *     The friends
     */
    public void setFriends(boolean friends) {
        this.friends = friends;
    }

    public Globals withFriends(boolean friends) {
        this.friends = friends;
        return this;
    }

    /**
     * 
     * @return
     *     The inviteType
     */
    public String getInviteType() {
        return inviteType;
    }

    /**
     * 
     * @param inviteType
     *     The invite_type
     */
    public void setInviteType(String inviteType) {
        this.inviteType = inviteType;
    }

    public Globals withInviteType(String inviteType) {
        this.inviteType = inviteType;
        return this;
    }

    /**
     * 
     * @return
     *     The recsInterval
     */
    public int getRecsInterval() {
        return recsInterval;
    }

    /**
     * 
     * @param recsInterval
     *     The recs_interval
     */
    public void setRecsInterval(int recsInterval) {
        this.recsInterval = recsInterval;
    }

    public Globals withRecsInterval(int recsInterval) {
        this.recsInterval = recsInterval;
        return this;
    }

    /**
     * 
     * @return
     *     The updatesInterval
     */
    public int getUpdatesInterval() {
        return updatesInterval;
    }

    /**
     * 
     * @param updatesInterval
     *     The updates_interval
     */
    public void setUpdatesInterval(int updatesInterval) {
        this.updatesInterval = updatesInterval;
    }

    public Globals withUpdatesInterval(int updatesInterval) {
        this.updatesInterval = updatesInterval;
        return this;
    }

    /**
     * 
     * @return
     *     The recsSize
     */
    public int getRecsSize() {
        return recsSize;
    }

    /**
     * 
     * @param recsSize
     *     The recs_size
     */
    public void setRecsSize(int recsSize) {
        this.recsSize = recsSize;
    }

    public Globals withRecsSize(int recsSize) {
        this.recsSize = recsSize;
        return this;
    }

    /**
     * 
     * @return
     *     The matchmakerDefaultMessage
     */
    public String getMatchmakerDefaultMessage() {
        return matchmakerDefaultMessage;
    }

    /**
     * 
     * @param matchmakerDefaultMessage
     *     The matchmaker_default_message
     */
    public void setMatchmakerDefaultMessage(String matchmakerDefaultMessage) {
        this.matchmakerDefaultMessage = matchmakerDefaultMessage;
    }

    public Globals withMatchmakerDefaultMessage(String matchmakerDefaultMessage) {
        this.matchmakerDefaultMessage = matchmakerDefaultMessage;
        return this;
    }

    /**
     * 
     * @return
     *     The shareDefaultText
     */
    public String getShareDefaultText() {
        return shareDefaultText;
    }

    /**
     * 
     * @param shareDefaultText
     *     The share_default_text
     */
    public void setShareDefaultText(String shareDefaultText) {
        this.shareDefaultText = shareDefaultText;
    }

    public Globals withShareDefaultText(String shareDefaultText) {
        this.shareDefaultText = shareDefaultText;
        return this;
    }

    /**
     * 
     * @return
     *     The boostDecay
     */
    public int getBoostDecay() {
        return boostDecay;
    }

    /**
     * 
     * @param boostDecay
     *     The boost_decay
     */
    public void setBoostDecay(int boostDecay) {
        this.boostDecay = boostDecay;
    }

    public Globals withBoostDecay(int boostDecay) {
        this.boostDecay = boostDecay;
        return this;
    }

    /**
     * 
     * @return
     *     The boostUp
     */
    public int getBoostUp() {
        return boostUp;
    }

    /**
     * 
     * @param boostUp
     *     The boost_up
     */
    public void setBoostUp(int boostUp) {
        this.boostUp = boostUp;
    }

    public Globals withBoostUp(int boostUp) {
        this.boostUp = boostUp;
        return this;
    }

    /**
     * 
     * @return
     *     The boostDown
     */
    public int getBoostDown() {
        return boostDown;
    }

    /**
     * 
     * @param boostDown
     *     The boost_down
     */
    public void setBoostDown(int boostDown) {
        this.boostDown = boostDown;
    }

    public Globals withBoostDown(int boostDown) {
        this.boostDown = boostDown;
        return this;
    }

    /**
     * 
     * @return
     *     The sparks
     */
    public boolean isSparks() {
        return sparks;
    }

    /**
     * 
     * @param sparks
     *     The sparks
     */
    public void setSparks(boolean sparks) {
        this.sparks = sparks;
    }

    public Globals withSparks(boolean sparks) {
        this.sparks = sparks;
        return this;
    }

    /**
     * 
     * @return
     *     The kontagent
     */
    public boolean isKontagent() {
        return kontagent;
    }

    /**
     * 
     * @param kontagent
     *     The kontagent
     */
    public void setKontagent(boolean kontagent) {
        this.kontagent = kontagent;
    }

    public Globals withKontagent(boolean kontagent) {
        this.kontagent = kontagent;
        return this;
    }

    /**
     * 
     * @return
     *     The sparksEnabled
     */
    public boolean isSparksEnabled() {
        return sparksEnabled;
    }

    /**
     * 
     * @param sparksEnabled
     *     The sparks_enabled
     */
    public void setSparksEnabled(boolean sparksEnabled) {
        this.sparksEnabled = sparksEnabled;
    }

    public Globals withSparksEnabled(boolean sparksEnabled) {
        this.sparksEnabled = sparksEnabled;
        return this;
    }

    /**
     * 
     * @return
     *     The kontagentEnabled
     */
    public boolean isKontagentEnabled() {
        return kontagentEnabled;
    }

    /**
     * 
     * @param kontagentEnabled
     *     The kontagent_enabled
     */
    public void setKontagentEnabled(boolean kontagentEnabled) {
        this.kontagentEnabled = kontagentEnabled;
    }

    public Globals withKontagentEnabled(boolean kontagentEnabled) {
        this.kontagentEnabled = kontagentEnabled;
        return this;
    }

    /**
     * 
     * @return
     *     The mqtt
     */
    public boolean isMqtt() {
        return mqtt;
    }

    /**
     * 
     * @param mqtt
     *     The mqtt
     */
    public void setMqtt(boolean mqtt) {
        this.mqtt = mqtt;
    }

    public Globals withMqtt(boolean mqtt) {
        this.mqtt = mqtt;
        return this;
    }

    /**
     * 
     * @return
     *     The tinderSparks
     */
    public boolean isTinderSparks() {
        return tinderSparks;
    }

    /**
     * 
     * @param tinderSparks
     *     The tinder_sparks
     */
    public void setTinderSparks(boolean tinderSparks) {
        this.tinderSparks = tinderSparks;
    }

    public Globals withTinderSparks(boolean tinderSparks) {
        this.tinderSparks = tinderSparks;
        return this;
    }

    /**
     * 
     * @return
     *     The momentsInterval
     */
    public int getMomentsInterval() {
        return momentsInterval;
    }

    /**
     * 
     * @param momentsInterval
     *     The moments_interval
     */
    public void setMomentsInterval(int momentsInterval) {
        this.momentsInterval = momentsInterval;
    }

    public Globals withMomentsInterval(int momentsInterval) {
        this.momentsInterval = momentsInterval;
        return this;
    }

    /**
     * 
     * @return
     *     The plus
     */
    public boolean isPlus() {
        return plus;
    }

    /**
     * 
     * @param plus
     *     The plus
     */
    public void setPlus(boolean plus) {
        this.plus = plus;
    }

    public Globals withPlus(boolean plus) {
        this.plus = plus;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
