package cccr;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;
import org.junit.jupiter.api.extension.TestWatcher;

import java.net.URL;
import java.net.URLEncoder;
import java.util.Optional;

public class CCCRTestExecutionListener implements TestWatcher, AfterAllCallback {

    private int total = 0;
    private int success = 0;

    private static final String GH_CI = System.getenv("CI");
    private static final String GH_ACTOR = System.getenv("GITHUB_ACTOR");
    private static final String GH_REPO = System.getenv("GITHUB_REPOSITORY");
    private static final String GH_SERVER = System.getenv("GITHUB_SERVER_URL");
    private static final String GH_URL = GH_SERVER + "/" + GH_REPO;
    private static final String CC_AID = System.getenv("AID");
    private static final String CC_CID = System.getenv("CID");

    @Override
    public void testDisabled(ExtensionContext extensionContext, Optional<String> optional) {
        total++;
    }

    @Override
    public void testSuccessful(ExtensionContext extensionContext) {
        total++;
        success++;
    }

    @Override
    public void testAborted(ExtensionContext extensionContext, Throwable throwable) {
        total++;
    }

    @Override
    public void testFailed(ExtensionContext extensionContext, Throwable throwable) {
        total++;
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) {
        sendResults();
    }

    private void sendResults() {
        if (GH_CI == null) {
            return;
        }
        try {
            String ghName = URLEncoder.encode(GH_ACTOR, "UTF-8");
            String repo = URLEncoder.encode(GH_URL, "UTF-8");
            String url = "https://ccclassroom.appspot.com/submit?aid="
                    + CC_AID + "&cid=" + CC_CID + "&ghn=" + ghName + "&t=" + total + "&s=" + success + "&r=" + repo;
            URL u = new URL(url);
            Object o = u.getContent();
        } catch (Exception ex) {
            System.out.println("CCCR sending results FAILED:");
            ex.printStackTrace();
            throw new RuntimeException("Failed to upload test results", ex);
        }
    }
}