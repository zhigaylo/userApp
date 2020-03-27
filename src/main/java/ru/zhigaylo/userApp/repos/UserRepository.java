package ru.zhigaylo.userApp.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zhigaylo.userApp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
