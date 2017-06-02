package com.vaadin.test.osgi;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class OsgiDeployIT {
    private static final String ROOT = "http://localhost:8181/";
    private static final String VERSION = System.getenv("vaadin.VERSION");
    private static final String RESOURCES = ROOT + "vaadin-" + VERSION + "/VAADIN/";

    @Test
    public void themeAvailable() throws IOException {
        checkAvailability(RESOURCES + "themes/valo/styles.css");
    }

    @Test
    public void wigdetsetAvailable() throws IOException {
        checkAvailability(RESOURCES + "widgetsets/com.vaadin.DefaultWidgetSet/com.vaadin.DefaultWidgetSet.nocache.js");
    }

    @Test
    public void appAvailable() throws IOException {
        checkAvailability(ROOT + "myapp");
    }

    @Test
    public void bootstrapAvailable() throws IOException {
        checkAvailability(RESOURCES + "vaadinBootstrap.js?v=" + VERSION);
    }

    private void checkAvailability(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            Assert.assertEquals(200, connection.getResponseCode());
        } finally {
            connection.disconnect();
        }
    }
}
