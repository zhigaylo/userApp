package ru.zhigaylo.userApp.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DocumentDto {

    private Long id;

    private String name;

    private String number;

    private LocalDateTime expiryDate;

    private Long userId;
}
