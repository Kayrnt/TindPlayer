
package fr.kayrnt.tindplayer.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class School {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private String id;

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

    public School withName(String name) {
        this.name = name;
        return this;
    }

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

    public School withId(String id) {
        this.id = id;
        return this;
    }

}
