package com.ideas2it.fileUpload.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@RedisHash("ProgressCount")
public class ProgressCount {

    @Id
    private String progressCount;

    @TimeToLive
    private Long expiration;

    public String getProgressCount() {
        return progressCount;
    }

    public void setProgressCount(String progressCount) {
        this.progressCount = progressCount;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }
}
