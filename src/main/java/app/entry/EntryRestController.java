package app.entry;

import app.common.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EntryRestController {
    @Autowired
    public EntryService entryService;

    @PostMapping("/add-entry")
    public ResponseEntity<Entry> addBlock(@RequestBody EntryModel model) {
        Entry entry = new Entry();
        entry.setAmount(model.getAmount());
        entry.setInsertionTime(model.getInsertionTime());
        entry.setUserId(-1L);
        Entry savedEntry = entryService.saveEntry(entry);

        return savedEntry != null ?
                new ResponseEntity<>(savedEntry, HttpStatus.OK) :
                new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/get-entries-by-user-id/{user-id}")
    public ResponseEntity<List<Entry>> addBlock(@PathVariable("user-id") Long userId) {
        return new ResponseEntity<>(
                entryService.getEntriesByUserId(userId),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/delete-entry/{entry-id}")
    public ResponseEntity<ApiResponse> deleteBlock(@PathVariable("entry-id") Long blockId) {
        return new ResponseEntity<>(
                new ApiResponse(entryService.deleteEntryById(blockId)),
                HttpStatus.OK
        );
    }
}
