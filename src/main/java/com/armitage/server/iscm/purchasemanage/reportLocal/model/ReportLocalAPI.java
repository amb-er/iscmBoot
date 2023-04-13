package com.armitage.server.iscm.purchasemanage.reportLocal.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 资源国际化,
 * @author Sujy
 *
 */
public class ReportLocalAPI {
    
    public static final String FN_LANGUAGE = "language";
    public static final String FN_CN = "cn";
    public static final String FN_EN = "en";
    
    private String language;
    private String cn;
    private String en;
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public String getCn() {
        return cn;
    }
    public void setCn(String cn) {
        this.cn = cn;
    }
    public String getEn() {
        return en;
    }
    public void setEn(String en) {
        this.en = en;
    }
    
    
}
