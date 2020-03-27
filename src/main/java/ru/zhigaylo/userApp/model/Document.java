package ru.zhigaylo.userApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String number;

    @NotNull
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
