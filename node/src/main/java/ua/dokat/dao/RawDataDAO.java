package ua.dokat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.dokat.entity.RawData;

public interface RawDataDAO extends JpaRepository<RawData, Long> {
}
