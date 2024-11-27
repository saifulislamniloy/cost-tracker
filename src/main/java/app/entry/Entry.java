package app.entry;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long amount;
    private Long userId;
    private Long insertionTime;
}
