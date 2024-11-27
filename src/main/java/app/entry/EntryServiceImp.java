package app.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EntryServiceImp implements EntryService {
    @Autowired
    private EntryRepository entryRepository;

    @Override
    public Entry saveEntry(Entry entry) {
        return entryRepository.save(entry);
    }

    @Override
    public List<Entry> fetchAllEntry() {
        return entryRepository.findAll();
    }

    @Override
    public Entry getEntryById(Long id) {
        Optional<Entry> entries = entryRepository.findById(id);
        return entries.orElse(null);
    }

    @Override
    public List<Entry> getEntriesByUserId(Long noteBookId) {
        return entryRepository.findAll().stream()
                .filter(task -> Objects.equals(task.getUserId(), noteBookId)).collect(Collectors.toList());
    }

    @Override
    public Entry updateEntryById(Long id, Entry task) {
        return entryRepository.save(task);
    }

    @Override
    public String deleteEntryById(Long id) {
        if (entryRepository.findById(id).isPresent()) {
            entryRepository.deleteById(id);
            return "Entry deleted successfully";
        }
        return "No such entry in the database";
    }
}
