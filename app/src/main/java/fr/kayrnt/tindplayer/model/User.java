
package fr.kayrnt.tindplayer.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class User {

    @SerializedName("_id")
    @Expose
    private String Id;
    @SerializedName("active_time")
    @Expose
    private String activeTime;
    @SerializedName("create_date")
    @Expose
    private String createDate;
    @SerializedName("age_filter_max")
    @Expose
    private int ageFilterMax;
    @SerializedName("age_filter_min")
    @Expose
    private int ageFilterMin;
    @SerializedName("api_token")
    @Expose
    private String apiToken;
    @Expose
    private String bio;
    @SerializedName("birth_date")
    @Expose
    private String birthDate;
    @SerializedName("distance_filter")
    @Expose
    private double distanceFilter;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @Expose
    private int gender;
    @SerializedName("gender_filter")
    @Expose
    private int genderFilter;
    @Expose
    private String name;
    @SerializedName("ping_time")
    @Expose
    private String pingTime;
    @Expose
    private boolean discoverable;
    @Expose
    private List<Photo> photos = new ArrayList<Photo>();
    @Expose
    private List<Object> purchases = new ArrayList<Object>();

    /**
     * 
     * @return
     *     The Id
     */
    public String getId() {
        return Id;
    }

    /**
     * 
     * @param Id
     *     The _id
     */
    public void setId(String Id) {
        this.Id = Id;
    }

    public User withId(String Id) {
        this.Id = Id;
        return this;
    }

    /**
     * 
     * @return
     *     The activeTime
     */
    public String getActiveTime() {
        return activeTime;
    }

    /**
     * 
     * @param activeTime
     *     The active_time
     */
    public void setActiveTime(String activeTime) {
        this.activeTime = activeTime;
    }

    public User withActiveTime(String activeTime) {
        this.activeTime = activeTime;
        return this;
    }

    /**
     * 
     * @return
     *     The createDate
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * 
     * @param createDate
     *     The create_date
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public User withCreateDate(String createDate) {
        this.createDate = createDate;
        return this;
    }

    /**
     * 
     * @return
     *     The ageFilterMax
     */
    public int getAgeFilterMax() {
        return ageFilterMax;
    }

    /**
     * 
     * @param ageFilterMax
     *     The age_filter_max
     */
    public void setAgeFilterMax(int ageFilterMax) {
        this.ageFilterMax = ageFilterMax;
    }

    public User withAgeFilterMax(int ageFilterMax) {
        this.ageFilterMax = ageFilterMax;
        return this;
    }

    /**
     * 
     * @return
     *     The ageFilterMin
     */
    public int getAgeFilterMin() {
        return ageFilterMin;
    }

    /**
     * 
     * @param ageFilterMin
     *     The age_filter_min
     */
    public void setAgeFilterMin(int ageFilterMin) {
        this.ageFilterMin = ageFilterMin;
    }

    public User withAgeFilterMin(int ageFilterMin) {
        this.ageFilterMin = ageFilterMin;
        return this;
    }

    /**
     * 
     * @return
     *     The apiToken
     */
    public String getApiToken() {
        return apiToken;
    }

    /**
     * 
     * @param apiToken
     *     The api_token
     */
    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public User withApiToken(String apiToken) {
        this.apiToken = apiToken;
        return this;
    }

    /**
     * 
     * @return
     *     The bio
     */
    public String getBio() {
        return bio;
    }

    /**
     * 
     * @param bio
     *     The bio
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    public User withBio(String bio) {
        this.bio = bio;
        return this;
    }

    /**
     * 
     * @return
     *     The birthDate
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * 
     * @param birthDate
     *     The birth_date
     */
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public User withBirthDate(String birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    /**
     * 
     * @return
     *     The distanceFilter
     */
    public double getDistanceFilter() {
        return distanceFilter;
    }

    /**
     * 
     * @param distanceFilter
     *     The distance_filter
     */
    public void setDistanceFilter(double distanceFilter) {
        this.distanceFilter = distanceFilter;
    }

    public User withDistanceFilter(double distanceFilter) {
        this.distanceFilter = distanceFilter;
        return this;
    }

    /**
     * 
     * @return
     *     The fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 
     * @param fullName
     *     The full_name
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public User withFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    /**
     * 
     * @return
     *     The gender
     */
    public int getGender() {
        return gender;
    }

    /**
     * 
     * @param gender
     *     The gender
     */
    public void setGender(int gender) {
        this.gender = gender;
    }

    public User withGender(int gender) {
        this.gender = gender;
        return this;
    }

    /**
     * 
     * @return
     *     The genderFilter
     */
    public int getGenderFilter() {
        return genderFilter;
    }

    /**
     * 
     * @param genderFilter
     *     The gender_filter
     */
    public void setGenderFilter(int genderFilter) {
        this.genderFilter = genderFilter;
    }

    public User withGenderFilter(int genderFilter) {
        this.genderFilter = genderFilter;
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

    public User withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * 
     * @return
     *     The pingTime
     */
    public String getPingTime() {
        return pingTime;
    }

    /**
     * 
     * @param pingTime
     *     The ping_time
     */
    public void setPingTime(String pingTime) {
        this.pingTime = pingTime;
    }

    public User withPingTime(String pingTime) {
        this.pingTime = pingTime;
        return this;
    }

    /**
     * 
     * @return
     *     The discoverable
     */
    public boolean isDiscoverable() {
        return discoverable;
    }

    /**
     * 
     * @param discoverable
     *     The discoverable
     */
    public void setDiscoverable(boolean discoverable) {
        this.discoverable = discoverable;
    }

    public User withDiscoverable(boolean discoverable) {
        this.discoverable = discoverable;
        return this;
    }

    /**
     * 
     * @return
     *     The photos
     */
    public List<Photo> getPhotos() {
        return photos;
    }

    /**
     * 
     * @param photos
     *     The photos
     */
    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public User withPhotos(List<Photo> photos) {
        this.photos = photos;
        return this;
    }

    /**
     * 
     * @return
     *     The purchases
     */
    public List<Object> getPurchases() {
        return purchases;
    }

    /**
     * 
     * @param purchases
     *     The purchases
     */
    public void setPurchases(List<Object> purchases) {
        this.purchases = purchases;
    }

    public User withPurchases(List<Object> purchases) {
        this.purchases = purchases;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
