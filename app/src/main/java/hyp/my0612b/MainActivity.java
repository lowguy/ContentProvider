package hyp.my0612b;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.RawContacts;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * 组件篇 - ContentProvider
 * 数据存储方式
 * Shared Preferences
 * 文件存储
 * SQLite
 * 其他网络存储
 * ！！！！以上都只是在单独的一个应用中搭档一个数据共享
 * <p/>
 * 如何实现一个ContentProvider
 * 1、继承ContentProvider实现一系列针对数据的增删改查
 * 2、需要在AndroidManifest.xml中完成注册
 */
public class MainActivity extends AppCompatActivity {
    private TextView mPhoneList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPhoneList = (TextView) findViewById(R.id.phoneList);
        ContentResolver cr = getContentResolver();
        Cursor c = cr.query(Contacts.CONTENT_URI, new String[]{Contacts._ID, Contacts.DISPLAY_NAME}, null, null, null);
        if (c != null) {
            while (c.moveToNext()) {
                int id = c.getInt(c.getColumnIndex("_id"));
                Log.i("info", "_id" + id);
                Log.i("info", "name" + c.getString(c.getColumnIndex("display_name")));
                Cursor c1 = cr.query(Phone.CONTENT_URI, new String[]{Phone.NUMBER, Phone.TYPE}, Phone.CONTACT_ID + "=" + id, null, null);
                //根据联系人id查询联系人电话号
                if (c1 != null) {
                    while (c1.moveToNext()) {
                        int type = c1.getInt(c1.getColumnIndex(Phone.TYPE));
                        if (type == Phone.TYPE_HOME) {
                            Log.i("info", "家庭电话" + c1.getString(c1.getColumnIndex(Phone.NUMBER)));
                        } else if (type == Phone.TYPE_MOBILE) {
                            Log.i("info", "手机" + c1.getString(c1.getColumnIndex(Phone.NUMBER)));
                        }
                        mPhoneList.setText("姓名：" + c.getString(c.getColumnIndex("display_name")) + "电话：" + c1.getString(c1.getColumnIndex(Phone.NUMBER)));
                        //TODO 整理为列表显示
                    }
                    c1.close();
                }
                //根据联系人的id查询联系人的额邮箱
            }
        }
        //向联系人插入数据
        ContentValues values = new ContentValues();
        Uri uri = cr.insert(RawContacts.CONTENT_URI,values);
        Long raw_contact_id = ContentUris.parseId(uri);
        values.clear();
        //插入人名
        values.put(StructuredName.RAW_CONTACT_ID,raw_contact_id);
        values.put(StructuredName.DISPLAY_NAME,"郝彦鹏");
        values.put(StructuredName.MIMETYPE,StructuredName.CONTENT_ITEM_TYPE);
        uri = cr.insert(Data.CONTENT_URI,values);
        //插入电话信息
        values.clear();
        values.put(Phone.RAW_CONTACT_ID,raw_contact_id);
        values.put(Phone.NUMBER,"18629141369");
        values.put(Phone.MIMETYPE,Phone.CONTENT_ITEM_TYPE);
        uri = cr.insert(Data.CONTENT_URI,values);
    }
}
