package gdg.farm_in_palm.domain.common.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Iso8601Formatter {

    private static final DateTimeFormatter ISO_8601_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    private static final DateTimeFormatter ISO_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final ZoneId seoulZoneId = ZoneId.of("Asia/Seoul");

    public static String toIso8601String(LocalDateTime localDateTime) {
        return localDateTime.format(ISO_8601_FORMATTER);
    }

    public static ZonedDateTime getZonedDateTime() {
        LocalDateTime now = LocalDateTime.now();
        return now.atZone(seoulZoneId);
    }

    public static LocalDateTime fromIso8601StringToLocalDateTime(String iso8601String) {
        return LocalDateTime.parse(iso8601String, ISO_8601_FORMATTER);
    }

    public static LocalDate fromIso8601StringToLocalDate(String iso8601String) {
        return LocalDate.parse(iso8601String, ISO_DATE_FORMATTER);
    }

    public static String getIso8601String() {
        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime seoulDateTime = now.atZone(seoulZoneId);
        return seoulDateTime.format(ISO_8601_FORMATTER);
    }

    public static String getIso8601Date() {
        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime seoulDateTime = now.atZone(seoulZoneId);
        return seoulDateTime.format(ISO_DATE_FORMATTER);
    }
}