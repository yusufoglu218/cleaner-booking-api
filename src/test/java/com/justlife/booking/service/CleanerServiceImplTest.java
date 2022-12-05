package com.justlife.booking.service;

import com.justlife.booking.constant.TestConstants;
import com.justlife.booking.model.Cleaner;
import com.justlife.booking.model.CleanerAvailableTime;
import com.justlife.booking.repository.CleanerRepository;
import com.justlife.booking.util.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Tests for CleanerService
 */
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CleanerServiceImplTest {

    @Mock
    CleanerRepository cleanerRepository;

    @InjectMocks
    CleanerServiceImpl cleanerServiceImpl;

    @Test
    public void getAvailableCleanerListByDate_OK() {
        List<CleanerAvailableTime> cleanerAvailableTimeList = TestUtils.createCleanerAvailableTimeList();
        LocalDateTime startDate = LocalDateTime.parse(TestConstants.START_TIME, TestConstants.DATE_TIME_FORMATTER);

        when(cleanerRepository.findAvailableCleanerListByDate(Mockito.any())).thenReturn(cleanerAvailableTimeList);
        List<CleanerAvailableTime> cleanerAvailableTimeListFromService = cleanerServiceImpl.getAvailableCleanerList(startDate, null, null, null);

        Assertions.assertEquals(cleanerAvailableTimeList, cleanerAvailableTimeListFromService);
    }

    @Test
    public void getAvailableCleanerListByAllParameterOneCleaner_OK() {
        List<Cleaner> cleanerAvailableTimeList = TestUtils.createCleanerList();
        LocalDateTime startDate = LocalDateTime.parse(TestConstants.DATE, TestConstants.DATE_TIME_FORMATTER);
        LocalDateTime startTime = LocalDateTime.parse(TestConstants.START_TIME, TestConstants.DATE_TIME_FORMATTER);

        when(cleanerRepository.findAvailableCleanerListByTimeDuration(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(cleanerAvailableTimeList);
        List<CleanerAvailableTime> cleanerAvailableTimeListFromService = cleanerServiceImpl.getAvailableCleanerList(startDate, startTime, 1, TestConstants.DURATION);

        Assertions.assertEquals(cleanerAvailableTimeList, cleanerAvailableTimeListFromService);
    }

    @Test
    public void getAvailableCleanerListByAllParameterMultipleCleaner_OK() {
        List<Cleaner> cleanerAvailableTimeList = TestUtils.createCleanerList();
        LocalDateTime startDate = LocalDateTime.parse(TestConstants.DATE, TestConstants.DATE_TIME_FORMATTER);
        LocalDateTime startTime = LocalDateTime.parse(TestConstants.START_TIME, TestConstants.DATE_TIME_FORMATTER);

        when(cleanerRepository.findAvailableMultipleCleaner(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(cleanerAvailableTimeList);
        List<CleanerAvailableTime> cleanerAvailableTimeListFromService = cleanerServiceImpl.getAvailableCleanerList(startDate, startTime, 2, TestConstants.DURATION);

        Assertions.assertEquals(cleanerAvailableTimeList, cleanerAvailableTimeListFromService);
    }

}
