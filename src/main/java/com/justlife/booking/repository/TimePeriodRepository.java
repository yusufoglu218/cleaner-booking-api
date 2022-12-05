package com.justlife.booking.repository;

import com.justlife.booking.model.TimePeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository for TimePeriod
 */
@Repository
public interface TimePeriodRepository extends JpaRepository<TimePeriod, Long> {

    /**
     * Get TimePeriod list between the given parameters
     * @param startTime
     * @param endTime
     * @return list of TimePeriod
     */
    @Query(value = "SELECT \n" +
            "    *\n" +
            "FROM\n" +
            "    cleaner_db.time_period\n" +
            "WHERE\n" +
            "    start_time BETWEEN :startTime AND :endTime",
            nativeQuery = true)
    List<TimePeriod> getTimePeriodBetweenStartEndTime(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

}
