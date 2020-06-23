package com.ideas2it.fileUpload.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app")
public class MyProperties {

    private String UPLOAD_FOLDER;

    private String OUTPUT_FOLDER;

    public String getUPLOAD_FOLDER() {
        return UPLOAD_FOLDER;
    }

    public void setUPLOAD_FOLDER(String UPLOAD_FOLDER) {
        this.UPLOAD_FOLDER = UPLOAD_FOLDER;
    }

    public String getOUTPUT_FOLDER() {
        return OUTPUT_FOLDER;
    }

    public void setOUTPUT_FOLDER(String OUTPUT_FOLDER) {
        this.OUTPUT_FOLDER = OUTPUT_FOLDER;
    }
}
