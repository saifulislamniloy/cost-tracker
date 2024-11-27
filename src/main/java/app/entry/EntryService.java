package app.entry;

import java.util.List;

public interface EntryService {
    Entry saveEntry(Entry entry);

    List<Entry> fetchAllEntry();

    Entry getEntryById(Long id);
    List<Entry> getEntriesByUserId(Long userId);

    Entry updateEntryById(Long id, Entry block);

    String deleteEntryById(Long id);
}
