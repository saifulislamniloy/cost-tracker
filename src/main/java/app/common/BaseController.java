package app.common;

import org.springframework.ui.Model;

public class BaseController {
    public static String LAYOUT_PATH = "layouts/layout";

    public String buildPath(Model model, String page) {
        model.addAttribute("content", page);
        model.addAttribute("applicationContextPath", "http://localhost:8080");
        return LAYOUT_PATH;
    }
}
