package com.owo.bom.hum;

import java.util.List;

/**
 * Created by wangli on 16-8-22.
 */
public interface HostAppAdDelegate {
    boolean isForceUpdate();

    String getConfigPath();

    void onConfigPaserComplete();

    void onStatistic(List<String> lines);
}
