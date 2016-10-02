package fr.kayrnt.tindplayer.model;

import com.google.gson.annotations.Expose;

/**
 * Created by Kayrnt on 17/01/15.
 */
public class PositionResponseAPIModel {

    @Expose
    private int status;
    @Expose
    private String error;

    public PositionResponseAPIModel(int status, String error) {
        this.status = status;
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
