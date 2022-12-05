CREATE TABLE `cleaner` (
  `id` bigInt NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `status` varchar(1) DEFAULT 'a',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `customer` (
  `id` bigInt NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `status` varchar(255) DEFAULT 'a',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `driver` (
  `id` bigInt NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `status` varchar(1) DEFAULT 'a',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `vehicle` (
  `id` bigInt NOT NULL AUTO_INCREMENT,
  `driver_id` bigInt DEFAULT NULL,
  `brand` varchar(255) NOT NULL,
  `status` varchar(1)  DEFAULT 'a',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `driver_id` (`driver_id`),
  CONSTRAINT `vehicle_ibfk_1` FOREIGN KEY (`driver_id`) REFERENCES `driver` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `vehicle_cleaner` (
  `id` bigInt NOT NULL AUTO_INCREMENT,
  `vehicle_id` bigInt NOT NULL,
  `cleaner_id` bigInt NOT NULL,
  `status` varchar(1) DEFAULT 'a',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `vehicle_id` (`vehicle_id`),
  KEY `cleaner_id` (`cleaner_id`),
  CONSTRAINT `vehicle_cleaner_ibfk_1` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `vehicle_cleaner_ibfk_2` FOREIGN KEY (`cleaner_id`) REFERENCES `cleaner` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS Booking (
    id bigInt AUTO_INCREMENT PRIMARY KEY,
    customer_id bigInt NOT NULL,
    status VARCHAR(1) DEFAULT 'a',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     FOREIGN KEY (customer_id)
        REFERENCES customer (id)
        ON UPDATE RESTRICT ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Booking_Cleaner (
    id bigInt AUTO_INCREMENT PRIMARY KEY,
    booking_id bigInt NOT NULL,
	cleaner_id bigInt NOT NULL,
    status VARCHAR(1) DEFAULT 'a',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     FOREIGN KEY (booking_id)
        REFERENCES booking (id)
        ON UPDATE RESTRICT ON DELETE CASCADE,
    FOREIGN KEY (cleaner_id)
        REFERENCES cleaner (id)
        ON UPDATE RESTRICT ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Time_Period (
    id bigInt AUTO_INCREMENT PRIMARY KEY,
    start_time TIMESTAMP,
    end_time TIMESTAMP,
    status VARCHAR(1) DEFAULT 'a',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS Booking_Time_Period (
    id bigInt AUTO_INCREMENT PRIMARY KEY,
    time_period_id bigInt NOT NULL,
	booking_id bigInt NOT NULL,
    status VARCHAR(1) DEFAULT 'a',
	booking_type VARCHAR(1), 
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     FOREIGN KEY (time_period_id)
        REFERENCES time_period (id)
        ON UPDATE RESTRICT ON DELETE CASCADE,
    FOREIGN KEY (booking_id)
        REFERENCES booking (id)
        ON UPDATE RESTRICT ON DELETE CASCADE
);





