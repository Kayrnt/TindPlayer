
package fr.kayrnt.tindplayer.model;

import com.google.gson.annotations.Expose;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class AuthAPIModel {

    @Expose
    private String token;
    @Expose
    private User user;
    @Expose
    private Versions versions;
    @Expose
    private Globals globals;

    /**
     * 
     * @return
     *     The token
     */
    public String getToken() {
        return token;
    }

    /**
     * 
     * @param token
     *     The token
     */
    public void setToken(String token) {
        this.token = token;
    }

    public AuthAPIModel withToken(String token) {
        this.token = token;
        return this;
    }

    /**
     * 
     * @return
     *     The user
     */
    public User getUser() {
        return user;
    }

    /**
     * 
     * @param user
     *     The user
     */
    public void setUser(User user) {
        this.user = user;
    }

    public AuthAPIModel withUser(User user) {
        this.user = user;
        return this;
    }

    /**
     * 
     * @return
     *     The versions
     */
    public Versions getVersions() {
        return versions;
    }

    /**
     * 
     * @param versions
     *     The versions
     */
    public void setVersions(Versions versions) {
        this.versions = versions;
    }

    public AuthAPIModel withVersions(Versions versions) {
        this.versions = versions;
        return this;
    }

    /**
     * 
     * @return
     *     The globals
     */
    public Globals getGlobals() {
        return globals;
    }

    /**
     * 
     * @param globals
     *     The globals
     */
    public void setGlobals(Globals globals) {
        this.globals = globals;
    }

    public AuthAPIModel withGlobals(Globals globals) {
        this.globals = globals;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
