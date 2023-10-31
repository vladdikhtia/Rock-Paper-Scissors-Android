package nl.hva.madlevel6task2.database;


import androidx.room.TypeConverter;

import java.util.Date;

class Converters {

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

}
