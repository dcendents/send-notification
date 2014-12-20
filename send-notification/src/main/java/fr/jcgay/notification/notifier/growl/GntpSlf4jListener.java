package fr.jcgay.notification.notifier.growl;

import com.google.code.jgntp.GntpErrorStatus;
import com.google.code.jgntp.GntpListener;
import com.google.code.jgntp.GntpNotification;
import fr.jcgay.notification.configuration.OperatingSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class GntpSlf4jListener implements GntpListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(GntpSlf4jListener.class);

    private static final String OSX_URL = "https://github.com/jcgay/send-notification/wiki/Growl-(OS-X)";
    private static final String WINDOWS_URL = "https://github.com/jcgay/send-notification/wiki/Growl-(Windows)";

    private final OperatingSystem currentOs;

    public GntpSlf4jListener() {
        currentOs = new OperatingSystem();
    }

    @Override
    public void onRegistrationSuccess() {
        LOGGER.debug("Client has been successfully registered by Growl");
    }

    @Override
    public void onNotificationSuccess(GntpNotification notification) {
        LOGGER.debug("Notification success: {}", notification);
    }

    @Override
    public void onClickCallback(GntpNotification notification) {

    }

    @Override
    public void onCloseCallback(GntpNotification notification) {

    }

    @Override
    public void onTimeoutCallback(GntpNotification notification) {

    }

    @Override
    public void onRegistrationError(GntpErrorStatus status, String description) {
        LOGGER.error(error("Growl registration has failed.\n {}: {}"), status, description);
    }

    @Override
    public void onNotificationError(GntpNotification notification, GntpErrorStatus status, String description) {
        LOGGER.error(error("Growl notification has failed.\n {}: {}\n {}"), status, description, notification);
    }

    @Override
    public void onCommunicationError(Throwable t) {
        LOGGER.error("Cannot communicate with Growl.", t);
    }

    private String error(String message) {
        return String.format("%s%n%n For more information about the errors and possible solutions, please read the following article:%n%s",
                message, wikiUrl());
    }

    private String wikiUrl() {
        if (currentOs.isWindows()) {
            return WINDOWS_URL;
        }
        if (currentOs.isMac()) {
            return OSX_URL;
        }
        return "https://github.com/jcgay/send-notification/wiki";
    }
}
