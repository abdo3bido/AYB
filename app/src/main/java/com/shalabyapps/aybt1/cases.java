package com.shalabyapps.aybt1;

import java.util.ArrayList;

public class cases {
    private String caseType;
    private String Description;
    private String Title;
    private ArrayList<String> URLs;

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setURLs(ArrayList<String> URLs) {
        this.URLs = URLs;
    }

    public String getCaseType() {
        return caseType;
    }

    public String getDescription() {
        return Description;
    }

    public String getTitle() {
        return Title;
    }

    public ArrayList<String> getURLs() {
        return URLs;
    }
}
