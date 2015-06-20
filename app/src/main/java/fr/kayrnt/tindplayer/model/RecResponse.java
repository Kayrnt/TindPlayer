package fr.kayrnt.tindplayer.model;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class RecResponse {

    @SerializedName("results")
    public List<Profile> profiles;
    public int status;

    private List<Profile> getProfiles() {
        return this.profiles;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}