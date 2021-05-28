package co.com.ceiba.mobile.pruebadeingreso.crud;

import android.util.Log;

import java.util.List;
import co.com.ceiba.mobile.pruebadeingreso.models.User;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class CRUDUser {

    private final static int calculateIndex() {
        Realm realm = Realm.getDefaultInstance();
        Number currentIdNum = realm.where(User.class).max("id");
        int nextId;
        if (currentIdNum == null) {
            nextId = 0;
        } else {
            nextId = currentIdNum.intValue()+1;
        }
        return nextId;
    }

    public  static void addUser(List<User> user){
        try {
            Realm realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmList<User> _newList = new RealmList<>();
                    _newList.addAll(user);
                    realm.insert(_newList);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public final static List<User> getAllUsers() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<User> users = realm.where(User.class).findAll();
        for (User user: users){
            Log.e("Ceiba","Usuarios ->" + user.getEmail());
        }
        return users;
    }
}
