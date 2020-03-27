package ru.zhigaylo.userApp.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zhigaylo.userApp.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
