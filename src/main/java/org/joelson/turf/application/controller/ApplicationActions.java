package org.joelson.turf.application.controller;

import org.joelson.turf.application.view.ApplicationUI;

import java.util.Objects;

public class ApplicationActions {

    private ApplicationUI applicationUI;

    public ApplicationActions() {
    }

    public void setApplicationUI(ApplicationUI applicationUI) {
        this.applicationUI = Objects.requireNonNull(applicationUI);
    }

    public void closeApplication() {
        applicationUI.dispose();
        System.exit(0);
    }
}
