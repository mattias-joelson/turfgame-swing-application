package org.joelson.turf.application;

import org.joelson.turf.application.controller.ApplicationActions;
import org.joelson.turf.application.view.ApplicationUI;

public class Application {

    private final ApplicationUI applicationUI;

    public Application() {
        ApplicationActions applicationActions = new ApplicationActions();
        applicationUI = new ApplicationUI(applicationActions);
        applicationUI.initUI();
        applicationActions.setApplicationUI(applicationUI);
    }

    public static void main(String[] args) {
        new Application().show();
    }

    public void show() {
        applicationUI.show();
    }
}
