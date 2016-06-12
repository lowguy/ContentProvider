package hyp.my0612b;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2016/6/12 0012.
 */
public class MyContentProvider extends ContentProvider {
    /**
     * 在ContentProvider创建后被调用
     * @return
     */
    @Override
    public boolean onCreate() {
        return false;
    }

    /**
     * 根据uri查询selection指定的条件所匹配的全部记录
     * @param uri
     * @param projection
     * @param selection
     * @param selectionArgs
     * @param sortOrder
     * @return
     */
    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    /**
     * 当前uri的MIME类型
     * @param uri
     * @return
     */
    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    /**
     * 根据uri插入VALUES对应的数据 （多个[vnd.android.dir]和一个[vnd.android.cursor.item]的区分）
     * @param uri
     * @param values
     * @return
     */
    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    /**
     * 根据uri删除selection指定的条件删除
     * @param uri
     * @param selection
     * @param selectionArgs
     * @return
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    /**
     * 根据uri修改selection 指定的条件所匹配的记录
     * @param uri
     * @param values
     * @param selection
     * @param selectionArgs
     * @return
     */
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
