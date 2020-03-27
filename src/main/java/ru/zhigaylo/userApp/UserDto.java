package ru.zhigaylo.userApp;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private Long age;

    private List<DocumentDto> documents = new ArrayList<>();
}
