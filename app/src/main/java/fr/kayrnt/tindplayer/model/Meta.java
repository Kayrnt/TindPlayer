
package fr.kayrnt.tindplayer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Meta {

    @SerializedName("status")
    @Expose
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Meta withStatus(int status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "Meta{" +
                "status=" + status +
                '}';
    }
}
