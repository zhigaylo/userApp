package ru.zhigaylo.userApp.services;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.zhigaylo.userApp.config.OrikaMapper;
import ru.zhigaylo.userApp.model.Document;
import ru.zhigaylo.userApp.model.DocumentDto;
import ru.zhigaylo.userApp.model.User;
import ru.zhigaylo.userApp.model.UserDto;
import ru.zhigaylo.userApp.repos.DocumentRepository;
import ru.zhigaylo.userApp.repos.UserRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;

    private final DocumentRepository documentRepository;

    private final OrikaMapper mapper;

    public UserService(UserRepository userRepository, DocumentRepository documentRepository, OrikaMapper mapper) {
        this.userRepository = userRepository;
        this.documentRepository = documentRepository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public UserDto findUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " doesn't exist"));
        return mapper.map(user, UserDto.class);
    }

    public List<UserDto> findAllUsers() {
        return mapper.mapAsList(userRepository.findAll(), UserDto.class);
    }

    public UserDto createUser(UserDto userDto) {
        if (!(userDto.getAge() >= 0 && userDto.getAge() <= 100)) {
            throw new Error("Incorrect age");
        }
        return mapper.map(userRepository.save(mapper.map(userDto, User.class)), UserDto.class);
    }

    public void updateUser(Long id, UserDto userDto) {
        if (!id.equals(userDto.getId())) {
            throw new IllegalArgumentException("ID mismatch");
        }
        if (!(userDto.getAge() >= 0 && userDto.getAge() <= 100)) {
            throw new Error("Incorrect age");
        }
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " doesn't exist"));
        List<DocumentDto> userDocuments = mapper.mapAsList(user.getDocuments(), DocumentDto.class);
        userDto.setDocuments(userDocuments);
        userRepository.save(mapper.map(userDto, User.class));
    }

    @Transactional
    public void deleteUser(Long id) {
        List<DocumentDto> documents = mapper.mapAsList(documentRepository
                .findDocumentsByUser(userRepository.findById(id).get()), DocumentDto.class);
        documents.forEach(document -> {
                String path = Paths.get("").toAbsolutePath().toString()
                        + "/src/main/resources/userDocuments/"
                        + document.getNumber() + "." + FilenameUtils.getExtension(document.getName());
                File file = Paths.get(path).toFile();
                try {
                    Files.delete(file.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        userRepository.deleteById(id);
    }

    @Transactional
    public void addDocument(Long id, MultipartFile file){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " doesn't exist"));
        String number = UUID.randomUUID().toString();
        String path =Paths.get("").toAbsolutePath().toString() +
                "/src/main/resources/userDocuments/";
        String savePath = path + number + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        File saveFile = Paths.get(savePath).toFile();
        try {
            file.transferTo(saveFile);
        } catch (IOException e) {
            throw new Error("Failed saving " + file.getOriginalFilename(), e);
        }
        Document newDocument = Document.builder()
                .number(number)
                .name(file.getOriginalFilename())
                .user(user)
                .build();
        List<Document> documents = user.getDocuments();
        documents.add(newDocument);
        user.setDocuments(documents);
        userRepository.save(user);
    }

    public List<DocumentDto> getActualDocuments(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->  new IllegalArgumentException("User with id " + id + " doesn't exist"));
        List<DocumentDto> documentsDto = mapper
                .mapAsList(documentRepository.findDocumentsByUser(user), DocumentDto.class)
                .stream()
                .filter(document -> document.getExpiryDate().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());
        return documentsDto;
    }
}
