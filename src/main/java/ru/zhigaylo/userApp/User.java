package ru.zhigaylo.userApp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private Long age;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Document> documents = new ArrayList<>();

//    public void setDocuments(List<Document> documents) {
//        if (this.documents == null) {
//            this.documents = documents;
//        }
//        this.documents.clear();
//        this.documents.addAll(documents);
//
//
//    }
}
