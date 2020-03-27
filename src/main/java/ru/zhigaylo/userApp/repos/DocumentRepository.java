package ru.zhigaylo.userApp.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zhigaylo.userApp.model.Document;
import ru.zhigaylo.userApp.model.User;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findDocumentsByUser(User user);
}
