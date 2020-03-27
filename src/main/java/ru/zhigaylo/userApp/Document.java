package ru.zhigaylo.userApp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String number;

    private LocalDateTime expiryDate;

    @ManyToOne
    @JsonIgnore
    private User user;

    /**
     * prePersist
     */
    @PrePersist
    public void prePersist() {
        this.expiryDate = LocalDateTime.now().plusWeeks(1);
    }
}
