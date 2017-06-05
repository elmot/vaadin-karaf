package com.vaadin.test.osgi;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Ignore
public class OsgiDeployIT {
    private static final String ROOT = "http://localhost:8181/";
    private static final String VERSION = System.getenv("vaadin.VERSION");
    private static final String RESOURCES = ROOT + "vaadin-" + VERSION + "/VAADIN/";

    @Test
    public void valoThemeAvailable() throws IOException {
        checkAvailability(RESOURCES + "themes/valo/styles.css");
    }

    @Test
    public void customThemeAvailable() throws IOException {
        checkAvailability(RESOURCES + "themes/karaftesttheme/styles.css");
    }

    @Test
    public void wigdetsetAvailable() throws IOException {
        checkAvailability(RESOURCES + "widgetsets/com.vaadin.DefaultWidgetSet/com.vaadin.DefaultWidgetSet.nocache.js");
    }

    @Test
    public void customWigdetsetAvailable() throws IOException {
        checkAvailability(RESOURCES + "widgetsets/com.vaadin.test.osgi.widgetset.CustomWidgetSet/com.vaadin.test.osgi.widgetset.CustomWidgetSet.nocache.js");
    }

    @Test
    public void app1Available() throws IOException {
        checkAvailability(ROOT + "myapp1");
    }

    @Test
    public void app2Available() throws IOException {
        checkAvailability(ROOT + "myapp2");
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
