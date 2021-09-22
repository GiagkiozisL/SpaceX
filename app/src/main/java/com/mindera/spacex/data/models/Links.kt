package com.mindera.spacex.data.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class Links(@JsonProperty("mission_patch")
            val missionPatch: String? = null,
            @JsonProperty("mission_patch_small")
            val missionPatchSmall: String? = null,
            @JsonProperty("reddit_campaign")
            val redditCampaign: Any? = null,
            @JsonProperty("reddit_launch")
            val redditLaunch: Any? = null,
            @JsonProperty("reddit_recovery")
            val redditRecovery: Any? = null,
            @JsonProperty("reddit_media")
            val redditMedia: Any? = null,
            val presskit: Any? = null,
            @JsonProperty("article_link")
            val articleLink: String? = null,
            val wikipedia: String? = null,
            @JsonProperty("video_link")
            val videoLink: String? = null,
            @JsonProperty("youtube_id")
            val youtubeID: String? = null,
            @JsonProperty("flickr_images")
            val flickrImages: List<String>? = null) {
}