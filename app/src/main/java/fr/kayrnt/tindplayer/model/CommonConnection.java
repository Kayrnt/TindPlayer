
package fr.kayrnt.tindplayer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommonConnection {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("photo")
    @Expose
    private Photo_ photo;
    @SerializedName("degree")
    @Expose
    private Long degree;

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
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    public CommonConnection withId(String id) {
        this.id = id;
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

    public CommonConnection withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * 
     * @return
     *     The photo
     */
    public Photo_ getPhoto() {
        return photo;
    }

    /**
     * 
     * @param photo
     *     The photo
     */
    public void setPhoto(Photo_ photo) {
        this.photo = photo;
    }

    public CommonConnection withPhoto(Photo_ photo) {
        this.photo = photo;
        return this;
    }

    /**
     * 
     * @return
     *     The degree
     */
    public Long getDegree() {
        return degree;
    }

    /**
     * 
     * @param degree
     *     The degree
     */
    public void setDegree(Long degree) {
        this.degree = degree;
    }

    public CommonConnection withDegree(Long degree) {
        this.degree = degree;
        return this;
    }

}
