package ru.otus.library.bee.changelog;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "addTestData", author = "aeuge")
    public void insertBasicData(DB db) {
        DBCollection myCollection = db.getCollection("books");
        DBObject dbObject = (DBObject) JSON.parse("{'title':'Отзвуки серебряного ветра','author':'Эльтеррус Иар','genre':'Фантастика'}");
        myCollection.insert(dbObject);
    }

    @ChangeSet(order = "002", id = "addTestData2", author = "aeuge")
    public void insertBasicData2(DB db) {
        DBCollection myCollection = db.getCollection("books");
        DBObject dbObject = (DBObject) JSON.parse("{'title':'Ночной дозор','author':'Лукьяненко Сергей Васильевич','genre':'Фантастика', 'comment':['отлично','восхитительно']}");
        myCollection.insert(dbObject);
        dbObject = (DBObject) JSON.parse("{'title':'Отзвуки серебряного ветра','author':'Эльтеррус Иар','genre':'Фантастика','comment':'+++'}");
        myCollection.insert(dbObject);
    }
}
