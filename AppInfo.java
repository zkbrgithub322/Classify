// AppInfo.java
package com.example.appverify;

public class AppInfo {
    private String appName;
    private String packageName;
    private boolean isOfficial;
    private String signatureHash;

    public AppInfo(String appName, String packageName, boolean isOfficial, String signatureHash) {
        this.appName = appName;
        this.packageName = packageName;
        this.isOfficial = isOfficial;
        this.signatureHash = signatureHash;
    }

    // Геттеры...
}