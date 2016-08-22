package com.owo.bom.hum;

import java.util.Map;

/**
 * Created by wangli on 16-8-22.
 */
public class AdConfig {
    //mill second
    private int expire;
    private Map<String, AdPlacementConfig> placementConfigs;

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public Map<String, AdPlacementConfig> getPlacementConfigs() {
        return placementConfigs;
    }

    public void setPlacementConfigs(Map<String, AdPlacementConfig> placementConfigs) {
        this.placementConfigs = placementConfigs;
    }
}
