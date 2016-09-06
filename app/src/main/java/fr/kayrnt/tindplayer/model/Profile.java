
package fr.kayrnt.tindplayer.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Profile {

    public boolean shouldLike = true;
    public boolean liked = false;

    @SerializedName("connection_count")
    @Expose
    private Long connectionCount;
    @SerializedName("common_like_count")
    @Expose
    private int commonLikeCount;
    @SerializedName("common_friend_count")
    @Expose
    private int commonFriendCount;
    @SerializedName("common_likes")
    @Expose
    private List<Object> commonLikes = new ArrayList<Object>();
    @SerializedName("common_interests")
    @Expose
    private List<CommonInterest> commonInterests = new ArrayList<CommonInterest>();
    @SerializedName("common_friends")
    @Expose
    private List<Object> commonFriends = new ArrayList<Object>();
    @SerializedName("_id")
    @Expose
    private String Id;
    @SerializedName("badges")
    @Expose
    private List<Object> badges = new ArrayList<Object>();
    @SerializedName("bio")
    @Expose
    private String bio;
    @SerializedName("birth_date")
    @Expose
    private String birthDate;
    @SerializedName("gender")
    @Expose
    private int gender;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("ping_time")
    @Expose
    private String pingTime;
    @SerializedName("photos")
    @Expose
    private List<Photo> photos = new ArrayList<Photo>();
    @SerializedName("jobs")
    @Expose
    private List<Object> jobs = new ArrayList<Object>();
    @SerializedName("schools")
    @Expose
    private List<School> schools = new ArrayList<School>();
    @SerializedName("birth_date_info")
    @Expose
    private String birthDateInfo;
    @SerializedName("distance_mi")
    @Expose
    private int distanceMi;
    @SerializedName("common_connections")
    @Expose
    private List<CommonConnection> commonConnections = new ArrayList<CommonConnection>();

    public Profile(String id, String name, String birthDate, String url) {
        setId(id);
        setName(name);
        setBirthDate(birthDate);
        ArrayList<Photo> mPhotos = new ArrayList<>();
        ArrayList<ProcessedFile> mProcessedFiles = new ArrayList<>();
        if(url != null) {
            mProcessedFiles.add(new ProcessedFile(url));
            mProcessedFiles.add(new ProcessedFile(url));
            mProcessedFiles.add(new ProcessedFile(url));
        }
        Photo photo = new Photo();
        photo.setProcessedFiles(mProcessedFiles);
        mPhotos.add(photo);
        this.photos = mPhotos;
        this.pingTime = "2014-10-01";
        this.bio = "test bio";
    }


    public int getAge() {
        Date date = new Date();
        if (this.birthDate == null)
            return 0;
        String str = this.birthDate.substring(0, 10);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = dateFormat.parse(str);
            double d = Math.floor((date.getTime() - date1.getTime()) / 86400000L / 365L);
            return (int) d;
        } catch (ParseException exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public String getDistance() {
        return this.distanceMi + " miles away";
    }

    public String getLastActive() {
        Date date = new Date();
        String str = this.pingTime.substring(0, 10);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        int i = 0;
        try {
            Date date1 = format.parse(str);
            double d = Math.floor((date.getTime() - date1.getTime()) / 86400000L);
            i = (int) d;
            if (i == 0)
                return "Active today";
        } catch (ParseException localParseException) {
            localParseException.printStackTrace();
        }
        return "Active " + i + " days ago";
    }

    public String getNameAndAge() {
        return this.name + ", " + getAge();
    }

    /**
     * @return The distanceMi
     */
    public int getDistanceMi() {
        return distanceMi;
    }

    /**
     * @param distanceMi The distance_mi
     */
    public void setDistanceMi(int distanceMi) {
        this.distanceMi = distanceMi;
    }

    /**
     * @return The commonLikeCount
     */
    public int getCommonLikeCount() {
        return commonLikeCount;
    }

    /**
     * @param commonLikeCount The common_like_count
     */
    public void setCommonLikeCount(int commonLikeCount) {
        this.commonLikeCount = commonLikeCount;
    }

    /**
     * @return The commonFriendCount
     */
    public int getCommonFriendCount() {
        return commonFriendCount;
    }

    /**
     * @param commonFriendCount The common_friend_count
     */
    public void setCommonFriendCount(int commonFriendCount) {
        this.commonFriendCount = commonFriendCount;
    }

    /**
     * @return The commonLikes
     */
    public List<Object> getCommonLikes() {
        return commonLikes;
    }

    /**
     * @param commonLikes The common_likes
     */
    public void setCommonLikes(List<Object> commonLikes) {
        this.commonLikes = commonLikes;
    }

    /**
     * @return The commonFriends
     */
    public List<Object> getCommonFriends() {
        return commonFriends;
    }

    /**
     * @param commonFriends The common_friends
     */
    public void setCommonFriends(List<Object> commonFriends) {
        this.commonFriends = commonFriends;
    }

    /**
     * @return The Id
     */
    public String getId() {
        return Id;
    }

    /**
     * @param Id The _id
     */
    public void setId(String Id) {
        this.Id = Id;
    }

    /**
     * @return The bio
     */
    public String getBio() {
        return bio;
    }

    /**
     * @param bio The bio
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     * @return The birthDate
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * @param birthDate The birth_date
     */
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * @return The gender
     */
    public int getGender() {
        return gender;
    }

    /**
     * @param gender The gender
     */
    public void setGender(int gender) {
        this.gender = gender;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The pingTime
     */
    public String getPingTime() {
        return pingTime;
    }

    /**
     * @param pingTime The ping_time
     */
    public void setPingTime(String pingTime) {
        this.pingTime = pingTime;
    }

    /**
     * @return The photos
     */
    public List<Photo> getPhotos() {
        return photos;
    }

    /**
     * @param photos The photos
     */
    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    /**
     * @return The birthDateInfo
     */
    public String getBirthDateInfo() {
        return birthDateInfo;
    }

    /**
     * @param birthDateInfo The birth_date_info
     */
    public void setBirthDateInfo(String birthDateInfo) {
        this.birthDateInfo = birthDateInfo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
