package com.vastparser.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Linear {
    @JsonProperty("duration")
    private String duration;

    @JsonProperty("trackingEvents")
    private List<TrackingEvent> trackingEvents;

    @JsonProperty("videoClicks")
    private List<VideoClick> videoClicks;

    @JsonProperty("mediaFiles")
    private List<MediaFile> mediaFiles;


    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<TrackingEvent> getTrackingEvents() {
        return trackingEvents;
    }

    public void setTrackingEvents(List<TrackingEvent> trackingEvents) {
        this.trackingEvents = trackingEvents;
    }

    public List<VideoClick> getVideoClicks() {
        return videoClicks;
    }

    public void setVideoClicks(List<VideoClick> videoClicks) {
        this.videoClicks = videoClicks;
    }

    public List<MediaFile> getMediaFiles() {
        return mediaFiles;
    }

    public void setMediaFiles(List<MediaFile> mediaFiles) {
        this.mediaFiles = mediaFiles;
    }
}
