package org.joelson.turf.application.view;

import org.joelson.turf.application.controller.ApplicationActions;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.desktop.QuitStrategy;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Objects;

public class ApplicationUI {

    public static final String FRAME_TITLE = "Turfgame Application";

    private final ApplicationActions applicationActions;
    private final JFrame applicationFrame;
    private final JLabel statusLabel;
    private Container currentContent;

    public ApplicationUI(ApplicationActions applicationActions) {
        this.applicationActions = Objects.requireNonNull(applicationActions);
        applicationFrame = new JFrame(FRAME_TITLE);
        statusLabel = new JLabel("<no status>");
    }

    public void initUI() {
        initApplicationFrame();
        createContent(applicationFrame.getContentPane());
    }

    private void initApplicationFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        applicationFrame.setSize(screenSize.width / 2, screenSize.height / 2);
        applicationFrame.setLocation(screenSize.width / 4, screenSize.height / 4);
        applicationFrame.setExtendedState(applicationFrame.getExtendedState() | Frame.MAXIMIZED_BOTH);
        applicationFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        applicationFrame.addWindowListener(createFrameWindowListener());
        Desktop.getDesktop().disableSuddenTermination();
        try {
            Desktop.getDesktop().setQuitStrategy(QuitStrategy.CLOSE_ALL_WINDOWS);
        } catch (UnsupportedOperationException e) {
            // ignore, not available on all platforms
        }
    }

    private WindowListener createFrameWindowListener() {
        return new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                applicationActions.closeApplication();
            }
        };
    }

    private void createContent(Container contentPane) {
        contentPane.setLayout(new BorderLayout());
        Container emptyContainer = new Container();
        contentPane.add(emptyContainer, BorderLayout.CENTER);
        currentContent = emptyContainer;
        contentPane.add(statusLabel, BorderLayout.PAGE_END);
    }

    public void show() {
        applicationFrame.setVisible(true);
    }

    public void dispose() {
        applicationFrame.dispose();
    }
}
