package ru.zhigaylo.userApp;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Document.class)
public class DocumentDto {

    private Long id;

    private String name;

    private String number;

    private LocalDateTime expiryDate;

    private Long userId;
}
