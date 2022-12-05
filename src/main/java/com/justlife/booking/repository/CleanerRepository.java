package com.justlife.booking.repository;

import com.justlife.booking.model.Cleaner;
import com.justlife.booking.model.CleanerAvailableTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository for Cleaner
 */
@Repository
public interface CleanerRepository extends JpaRepository<Cleaner, Long> {

    /**
     * Find cleaner list by date
     *
     * @param date date for availability
     * @return list of CleanerAvailableTime
     */
    @Query(value = "SELECT \n" +
            "    clr.id as id, clr.name as name, clr.surname as surName, tp.start_time as startTime, tp.end_time as endTime\n" +
            "FROM\n" +
            "    cleaner clr,\n" +
            "    time_period tp\n" +
            "WHERE\n" +
            "    tp.id NOT IN (SELECT \n" +
            "            tp.id\n" +
            "        FROM\n" +
            "            cleaner cl,\n" +
            "            time_period tp,\n" +
            "            booking bk,\n" +
            "            booking_cleaner bkc,\n" +
            "            booking_time_period btp\n" +
            "        WHERE\n" +
            "            cl.id = bkc.cleaner_id " +
            "                AND bk.id = bkc.booking_id\n" +
            "                AND cl.id = clr.id\n" +
            "                AND bk.id = btp.booking_id\n" +
            "                AND btp.time_period_id = tp.id\n" +
            "                AND btp.status = 'a'\n" +
            "                AND bk.status = 'a')\n" +
            "        AND tp.end_time BETWEEN sysdate() and :date\n",
            nativeQuery = true)
    List<CleanerAvailableTime> findAvailableCleanerListByDate(@Param("date") LocalDateTime date);

    /**
     * Find cleaner list
     *
     * @param date             date for availability
     * @param startTimeList    list of time that cleaner has
     * @param numberOfInterval the number to check if cleaner has all list of startTime
     * @return list of Cleaner
     */
    @Query(value = "SELECT \n" +
            "    clr.id, clr.name, clr.surname\n" +
            "FROM\n" +
            "    cleaner clr,\n" +
            "    time_period tp\n" +
            "WHERE\n" +
            "    tp.id NOT IN (SELECT \n" +
            "            tp.id\n" +
            "        FROM\n" +
            "            cleaner cl,\n" +
            "            time_period tp,\n" +
            "            booking bk,\n" +
            "            booking_cleaner bkc,\n" +
            "            booking_time_period btp\n" +
            "        WHERE\n" +
            "          cl.id = bkc.cleaner_id " +
            "                AND bk.id = bkc.booking_id\n" +
            "                AND cl.id = clr.id\n" +
            "                AND bk.id = btp.booking_id\n" +
            "                AND btp.time_period_id = tp.id\n" +
            "                AND btp.status = 'a'\n" +
            "                AND bk.status = 'a')\n" +
            "        AND tp.end_time BETWEEN sysdate() and :date\n" +
            "        AND tp.start_time in :startTimeList\n" +
            "GROUP BY clr.id \n" +
            "HAVING COUNT(clr.id) = :numberOfInterval",
            nativeQuery = true)
    List<Cleaner> findAvailableCleanerListByTimeDuration(@Param("date") LocalDateTime date, @Param("startTimeList") List<LocalDateTime> startTimeList, @Param("numberOfInterval") Integer numberOfInterval);

    /**
     * Find available multiple cleaner belongs same vehicle
     *
     * @param date             date for availability
     * @param startTimeList    list of time that cleaner has
     * @param numberOfCleaner  the number of cleaner that are available together
     * @param numberOfInterval the number to check if cleaner has all list of starTime
     * @return list of Cleaner
     */
    @Query(value = "SELECT id, name, surname FROM \n" +
            "        (SELECT\n" +
            "           vehicle_id, clr.id, clr.name, clr.surname, count(clr.id) over (partition by vehicle_id) as clnrOverVehicle \n" +
            "            FROM\n" +
            "                cleaner clr,\n" +
            "               time_period tp,\n" +
            "               vehicle_cleaner vc\n" +
            "            WHERE\n" +
            "                tp.id NOT IN (SELECT\n" +
            "                        tp.id\n" +
            "                    FROM\n" +
            "                       cleaner cl,\n" +
            "                       time_period tp,\n" +
            "                       booking bk, \n" +
            "                       booking_cleaner bkc,\n" +
            "                       booking_time_period btp\n" +
            "                   WHERE+\n" +
            "                       cl.id = bkc.cleaner_id " +
            "                            AND bk.id = bkc.booking_id\n" +
            "                            AND cl.id = clr.id\n" +
            "                            AND bk.id = btp.booking_id\n" +
            "                            AND btp.time_period_id = tp.id)\n" +
            "                   AND tp.end_time BETWEEN sysdate() and :date\n" +
            "                   AND tp.start_time IN :startTimeList\n" +
            "                   AND vc.cleaner_id = clr.id\n" +
            "            GROUP BY clr.id\n" +
            "            HAVING COUNT(clr.id) = :numberOfInterval) clnVhcl\n" +
            "            WHERE clnVhcl.clnrOverVehicle >= :numberOfCleaner\n" +
            "            ORDER BY vehicle_id\n" +
            "            LIMIT :numberOfCleaner",
            nativeQuery = true)
    List<Cleaner> findAvailableMultipleCleaner(@Param("date") LocalDateTime date, @Param("startTimeList") List<LocalDateTime> startTimeList, @Param("numberOfCleaner") Integer numberOfCleaner, @Param("numberOfInterval") Integer numberOfInterval);

}
