package ru.otus.library.bee.changelog;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
    @ChangeSet(order = "003", id = "addUser", author = "aeuge")
    public void insertBasicUser(DB db) {
        DBCollection myCollection = db.getCollection("users");
        DBObject dbObject = (DBObject) JSON.parse("{'username':'admin','password':'admin','roles':'ADMIN'}");
        myCollection.insert(dbObject);
    }
    @ChangeSet(order = "004", id = "updateUser", author = "aeuge")
    public void updateBasicUser(DB db) {
        DBCollection myCollection = db.getCollection("users");
        DBObject dbObject = (DBObject) JSON.parse("{'username':'admin','password':'admin','roles':'ADMIN'}");
        DBObject dbObject2 = (DBObject) JSON.parse("{'username':'admin','password':'admin','roles':'ROLE_ADMIN'}");
        myCollection.update(dbObject, dbObject2);
    }
    @ChangeSet(order = "005", id = "updateUser2", author = "aeuge")
    public void updateBasicUser2(DB db) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);
        String newpassword = passwordEncoder.encode("admin");
        DBCollection myCollection = db.getCollection("users");
        DBObject dbObject = (DBObject) JSON.parse("{'username':'admin','password':'admin','roles':'ROLE_ADMIN'}");
        DBObject dbObject2 = (DBObject) JSON.parse("{'username':'admin','password':'"+newpassword+"','roles':'ROLE_ADMIN'}");
        myCollection.update(dbObject, dbObject2);
    }
}
