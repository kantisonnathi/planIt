package in.ac.bitspilani.webapp.diary;


import org.springframework.data.repository.Repository;

import java.time.LocalDate;
import java.util.List;

public interface DiaryEntryRepository extends Repository<DiaryEntry, Integer> {

    List<DiaryEntry> findAllByByUserId(Integer userId);

    DiaryEntry findByDate(LocalDate date);
}
