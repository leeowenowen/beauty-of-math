package com.owo.bom.hum;

import java.util.List;

/**
 * Created by wangli on 16-8-22.
 */
public class AdRequestConfig {
    //["sequence","random"]
    private String requestStrategy;
    private List<AdnConfig> adnConfigs;

    public String getRequestStrategy() {
        return requestStrategy;
    }

    public void setRequestStrategy(String requestStrategy) {
        this.requestStrategy = requestStrategy;
    }

    public List<AdnConfig> getAdnConfigs() {
        return adnConfigs;
    }

    public void setAdnConfigs(List<AdnConfig> adnConfigs) {
        this.adnConfigs = adnConfigs;
    }
}
