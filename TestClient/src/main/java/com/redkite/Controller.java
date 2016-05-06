package com.redkite;

import org.kaaproject.kaa.client.DesktopKaaPlatformContext;
import org.kaaproject.kaa.client.Kaa;
import org.kaaproject.kaa.client.KaaClient;
import org.kaaproject.kaa.client.SimpleKaaClientStateListener;
import org.kaaproject.kaa.client.profile.ProfileContainer;
import org.kaaproject.kaa.schema.sample.profile.OS;
import org.kaaproject.kaa.schema.sample.profile.ProfileTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Controller {

    private static final Logger LOG = LoggerFactory.getLogger(Controller.class);

    ProfileTest profile;

    public static void main(String[] args) {
        new Controller().launch();
    }

    private void launch() {
        // Create instance of desktop Kaa client for Kaa SDK
        KaaClient client = Kaa.newClient(new DesktopKaaPlatformContext(),
                new SimpleKaaClientStateListener());
        // Sample profile
        profile = new ProfileTest("id", OS.Linux, "3.17", "0.0.1-SNAPSHOT");
        // Simple implementation of ProfileContainer interface that is provided by the SDK
        client.setProfileContainer(new ProfileContainer() {
            @Override
            public ProfileTest getProfile() {
                return profile;
            }
        });

        // Starts Kaa
        client.start();
        // Update to profile variable
        profile.setBuild("0.0.1-SNAPSHOT");
        // Report update to Kaa SDK. Force delivery of updated profile to server.
        client.updateProfile();

    }
}