package app.entry;

import app.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EntryController extends BaseController {
    @Autowired
    public EntryService entryService;

    @GetMapping("/")
    public String addNoteBookPage(Model model) {
        model.addAttribute("entries", entryService.getEntriesByUserId(-1L));
        return buildPath(model, "entry/entry");
    }
}