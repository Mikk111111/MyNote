package com.organization.mynote.repository;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.organization.mynote.Constant;
import com.organization.mynote.Note;

@Database(
        entities = {Note.class},
        version = Constant.MAIN_DATABASE_VERSION,
        exportSchema = false
)
@TypeConverters({DateConverter.class})
public abstract class MainDatabase extends RoomDatabase {
    private static MainDatabase instance;
    private static int previousVersionDB = Constant.MAIN_DATABASE_VERSION-1;
    private static int newVersionDB = Constant.MAIN_DATABASE_VERSION;

    public abstract NoteDao noteDao();

    public static MainDatabase getRoomInstance(Context context){

        if(instance==null){
            instance =
                    Room.databaseBuilder(
                                    context,
                                    MainDatabase.class,
                                    "main.db"
                            )
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();        }

        return instance;
    };

}
