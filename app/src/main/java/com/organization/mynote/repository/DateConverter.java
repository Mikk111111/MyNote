package com.organization.mynote.repository;

import androidx.room.TypeConverter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DateConverter {
    @TypeConverter
    public static LocalDateTime fromTimestamp(Long timestamp){
        if(timestamp == null) return null;
        return Instant
                .ofEpochSecond(timestamp)
                .atZone(ZoneOffset.UTC)
                .toLocalDateTime();

    }

    @TypeConverter
    public static Long toTimestamp(LocalDateTime date){
        if(date== null) return null;
        return date.toEpochSecond(ZoneOffset.UTC);
    }
}
