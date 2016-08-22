package com.owo.bom.hum;

/**
 * Created by wangli on 16-8-22.
 */
public class AdnConfig {
    private int requestQuota;
    private int impressionQuota;
    //["facebook", "google", "uc"
    private String advertiser;
    private String appId;
    private String placementId;

    public int getRequestQuota() {
        return requestQuota;
    }

    public void setRequestQuota(int requestQuota) {
        this.requestQuota = requestQuota;
    }

    public int getImpressionQuota() {
        return impressionQuota;
    }

    public void setImpressionQuota(int impressionQuota) {
        this.impressionQuota = impressionQuota;
    }

    public String getAdvertiser() {
        return advertiser;
    }

    public void setAdvertiser(String advertiser) {
        this.advertiser = advertiser;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPlacementId() {
        return placementId;
    }

    public void setPlacementId(String placementId) {
        this.placementId = placementId;
    }
}
