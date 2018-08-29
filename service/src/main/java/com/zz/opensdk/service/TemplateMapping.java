package com.zz.opensdk.service;


import java.util.Map;

public class TemplateMapping {
    private Map<String,CToSExecuteService> cToSExecuteTemplate;

    public Map<String, CToSExecuteService> getcToSExecuteTemplate() {
        return cToSExecuteTemplate;
    }

    public void setcToSExecuteTemplate(Map<String, CToSExecuteService> cToSExecuteTemplate) {
        this.cToSExecuteTemplate = cToSExecuteTemplate;
    }
}
