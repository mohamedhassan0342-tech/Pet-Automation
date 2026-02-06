package api.utilities;

import io.qameta.allure.Allure;

public class AllureLogger {

    public static void step(String message) {
        Allure.step(message);
    }

    public static void attachJson(String title, String json) {
        Allure.addAttachment(title, "application/json", json);
    }

    public static void attachText(String title, String text) {
        Allure.addAttachment(title, "text/plain", text);
        
    }
}