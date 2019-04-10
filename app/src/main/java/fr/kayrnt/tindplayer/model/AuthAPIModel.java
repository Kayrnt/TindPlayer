
package fr.kayrnt.tindplayer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthAPIModel {

    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("data")
    @Expose
    private Data data;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public AuthAPIModel withMeta(Meta meta) {
        this.meta = meta;
        return this;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public AuthAPIModel withData(Data data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "AuthAPIModel{" +
                "meta=" + meta +
                ", data=" + data +
                '}';
    }
}
