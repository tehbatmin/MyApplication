package com.example.myapplication;

public class Navigation {

    private String navigationName;
    private String navigationIcon;

    public Navigation(
        String navigationName,
        String navigationIcon) {

        this.navigationName = navigationName;
        this.navigationIcon = navigationIcon;
    }

    public String getNavigationName() {return navigationName;}
    public String getNavigationIcon() {return navigationIcon;}
}
