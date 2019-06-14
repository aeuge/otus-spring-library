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

    @ChangeSet(order = "001", id = "addTestData", author = "aeuge", runAlways = true)
    public void insertBasicData(DB db) {
        db.dropDatabase();
        DBCollection myCollection = db.getCollection("books");
        DBObject dbObject = (DBObject) JSON.parse("{'title':'Отзвуки серебряного ветра','author':'Эльтеррус Иар','genre':'Фантастика'}");
        myCollection.insert(dbObject);
    }

    @ChangeSet(order = "002", id = "addTestData2", author = "aeuge", runAlways = true)
    public void insertBasicData2(DB db) {
        DBCollection myCollection = db.getCollection("books");
        DBObject dbObject = (DBObject) JSON.parse("{'title':'Ночной дозор','author':'Лукьяненко Сергей Васильевич','genre':'Фантастика', 'comment':['отлично','восхитительно']}");
        myCollection.insert(dbObject);
        dbObject = (DBObject) JSON.parse("{'title':'Отзвуки серебряного ветра','author':'Эльтеррус Иар','genre':'Фантастика','comment':'+++'}");
        myCollection.insert(dbObject);
    }
    @ChangeSet(order = "003", id = "addBasicPrivilege", author = "aeuge", runAlways = true)
    public void insertBasicPrivilege(DB db) {
        DBCollection myCollection = db.getCollection("privilege");
        DBObject dbObject = (DBObject) JSON.parse("{'id':'1','name':'ROLE_ADMIN'}");
        myCollection.insert(dbObject);
        DBObject dbObject2 = (DBObject) JSON.parse("{'id':'2','name':'ROLE_USER'}");
        myCollection.insert(dbObject2);
    }

    @ChangeSet(order = "004", id = "addUser", author = "aeuge", runAlways = true)
    public void insertBasicUser(DB db) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);
        String newpassword = passwordEncoder.encode("admin");
        DBCollection myCollection = db.getCollection("users");
        DBObject dbObject = (DBObject) JSON.parse("{'username':'admin','password':'"+newpassword+"','roles':[{'id':'1', 'name':'ROLE_ADMIN'}]}");
        myCollection.insert(dbObject);
    }
}
