package org.hackerpins.domain;

import org.hibernate.validator.constraints.URL;

import javax.persistence.Embeddable;

/**
 * Created by shekhargulati on 04/04/14.
 */
@Embeddable
public class Media {
    @URL
    private String mediaUrl;
    private MediaType type;

    public Media() {
    }

    public Media(String mediaUrl, MediaType type) {
        this.mediaUrl = mediaUrl;
        this.type = type;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public MediaType getType() {
        return type;
    }

    public void setType(MediaType type) {
        this.type = type;
    }
}
