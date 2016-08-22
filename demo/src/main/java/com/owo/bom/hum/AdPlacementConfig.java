package com.owo.bom.hum;

import java.util.List;

/**
 * Created by wangli on 16-8-22.
 */
public class AdPlacementConfig {
    //unit days
    private int newOldThreshold;
    private List<AdRequestConfig> newUserConfig;
    private List<AdRequestConfig> oldUserConfig;

    public int getNewOldThreshold() {
        return newOldThreshold;
    }

    public void setNewOldThreshold(int newOldThreshold) {
        this.newOldThreshold = newOldThreshold;
    }

    public List<AdRequestConfig> getNewUserConfig() {
        return newUserConfig;
    }

    public void setNewUserConfig(List<AdRequestConfig> newUserConfig) {
        this.newUserConfig = newUserConfig;
    }

    public List<AdRequestConfig> getOldUserConfig() {
        return oldUserConfig;
    }

    public void setOldUserConfig(List<AdRequestConfig> oldUserConfig) {
        this.oldUserConfig = oldUserConfig;
    }
}
