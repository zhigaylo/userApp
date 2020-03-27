package ru.zhigaylo.userApp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/users/")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("all")
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping("new")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto user = userService.createUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> editUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        userService.updateUser(id, userDto);
        UserDto user = userService.findUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("{id}/document")
    public ResponseEntity<UserDto> addDocument(@PathVariable Long id, @RequestBody MultipartFile file) {
        userService.addDocument(id, file);
        UserDto user = userService.findUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("{id}/document")
    public ResponseEntity<List<DocumentDto>> getUserDocuments(@PathVariable Long id) {
        List<DocumentDto> documents = userService.getActualDocuments(id);
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }
}
