package com.justlife.booking.controller;

import com.justlife.booking.model.Cleaner;
import com.justlife.booking.service.CleanerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Controller class for cleaner operations
 */
@Tag(name = "Cleaner-Controller", description = "Cleaner Rest API")
@Slf4j
@Validated
@RestController
@RequestMapping("/api/v1/cleaner")
public class CleanerController {

    private CleanerService cleanerService;

    public CleanerController(CleanerService cleanerService) {
        this.cleanerService = cleanerService;
    }

    @Operation(summary = "Get available cleaners by parameters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Cleaner.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))})
    })
    @GetMapping(value = "/available")
    public ResponseEntity getAvailableCleanerList(@Parameter(description = "Date of available time") @RequestParam @FutureOrPresent @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @NotNull LocalDateTime date,
                                                  @Parameter(description = "Start time of appointment") @RequestParam(required = false) @FutureOrPresent @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
                                                  @Parameter(description = "Duration of appointment") @RequestParam(required = false) Integer duration,
                                                  @Parameter(description = "Number of cleaner") @RequestParam(required = false) Integer numberOfCleaner) {
        return ResponseEntity.ok(cleanerService.getAvailableCleanerList(date, startTime, numberOfCleaner, duration));
    }


}
