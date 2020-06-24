package com.project.project5.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Shanghai *** Technology Co.,Ltd.
 *
 * @author RookieDe
 * @ClassName ProjectCoreProperties
 * @date 2020/6/23 18:32
 */
@ConfigurationProperties(prefix = "project")
public class ProjectCoreProperties {

    /**
     * 权限中心接口类
     */
    private DockingProperties docking = new DockingProperties();

    public DockingProperties getDocking() {
        return docking;
    }

    public void setDocking(DockingProperties docking) {
        this.docking = docking;
    }
}
