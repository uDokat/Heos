package ua.dokat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.dokat.entity.UserData;

public interface UserDataDAO extends JpaRepository<UserData, Long> {
}
