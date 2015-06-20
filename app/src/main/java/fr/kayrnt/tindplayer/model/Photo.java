
package fr.kayrnt.tindplayer.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class Photo {

    @SerializedName("ydistance_percent")
    @Expose
    private double ydistancePercent;
    @Expose
    private String id;
    @SerializedName("xoffset_percent")
    @Expose
    private double xoffsetPercent;
    @SerializedName("yoffset_percent")
    @Expose
    private double yoffsetPercent;
    @SerializedName("xdistance_percent")
    @Expose
    private double xdistancePercent;
    @Expose
    private String fileName;
    @Expose
    private String fbId;
    @Expose
    private String extension;
    @Expose
    private List<ProcessedFile> processedFiles = new ArrayList<ProcessedFile>();
    @Expose
    private String url;
    @Expose
    private String shape;

    /**
     * 
     * @return
     *     The ydistancePercent
     */
    public double getYdistancePercent() {
        return ydistancePercent;
    }

    /**
     * 
     * @param ydistancePercent
     *     The ydistance_percent
     */
    public void setYdistancePercent(double ydistancePercent) {
        this.ydistancePercent = ydistancePercent;
    }

    public Photo withYdistancePercent(double ydistancePercent) {
        this.ydistancePercent = ydistancePercent;
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

    public Photo withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * 
     * @return
     *     The xoffsetPercent
     */
    public double getXoffsetPercent() {
        return xoffsetPercent;
    }

    /**
     * 
     * @param xoffsetPercent
     *     The xoffset_percent
     */
    public void setXoffsetPercent(int xoffsetPercent) {
        this.xoffsetPercent = xoffsetPercent;
    }

    public Photo withXoffsetPercent(int xoffsetPercent) {
        this.xoffsetPercent = xoffsetPercent;
        return this;
    }

    /**
     * 
     * @return
     *     The yoffsetPercent
     */
    public double getYoffsetPercent() {
        return yoffsetPercent;
    }

    /**
     * 
     * @param yoffsetPercent
     *     The yoffset_percent
     */
    public void setYoffsetPercent(double yoffsetPercent) {
        this.yoffsetPercent = yoffsetPercent;
    }

    public Photo withYoffsetPercent(double yoffsetPercent) {
        this.yoffsetPercent = yoffsetPercent;
        return this;
    }

    /**
     * 
     * @return
     *     The xdistancePercent
     */
    public double getXdistancePercent() {
        return xdistancePercent;
    }

    /**
     * 
     * @param xdistancePercent
     *     The xdistance_percent
     */
    public void setXdistancePercent(int xdistancePercent) {
        this.xdistancePercent = xdistancePercent;
    }

    public Photo withXdistancePercent(int xdistancePercent) {
        this.xdistancePercent = xdistancePercent;
        return this;
    }

    /**
     * 
     * @return
     *     The fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 
     * @param fileName
     *     The fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Photo withFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    /**
     * 
     * @return
     *     The fbId
     */
    public String getFbId() {
        return fbId;
    }

    /**
     * 
     * @param fbId
     *     The fbId
     */
    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public Photo withFbId(String fbId) {
        this.fbId = fbId;
        return this;
    }

    /**
     * 
     * @return
     *     The extension
     */
    public String getExtension() {
        return extension;
    }

    /**
     * 
     * @param extension
     *     The extension
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Photo withExtension(String extension) {
        this.extension = extension;
        return this;
    }

    /**
     * 
     * @return
     *     The processedFiles
     */
    public List<ProcessedFile> getProcessedFiles() {
        return processedFiles;
    }

    /**
     * 
     * @param processedFiles
     *     The processedFiles
     */
    public void setProcessedFiles(List<ProcessedFile> processedFiles) {
        this.processedFiles = processedFiles;
    }

    public Photo withProcessedFiles(List<ProcessedFile> processedFiles) {
        this.processedFiles = processedFiles;
        return this;
    }

    /**
     * 
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    public Photo withUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * 
     * @return
     *     The shape
     */
    public String getShape() {
        return shape;
    }

    /**
     * 
     * @param shape
     *     The shape
     */
    public void setShape(String shape) {
        this.shape = shape;
    }

    public Photo withShape(String shape) {
        this.shape = shape;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
