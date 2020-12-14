package com.example.lab062;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

public class NhanVienProvider extends ContentProvider {
    // Tên package của ứng dụng, cũng là nơi để lưu dữ liệu dùng chung.
    static final String PROVIDER_TEN = "com.example.lab062";

    static final String URI = "content://" + PROVIDER_TEN + "/NhanVien";
    static final Uri CONTENT_URI =Uri.parse(URI);
    private SQLiteDatabase db;
    private static HashMap<String,  String> NHANVIEN_PROJECTION_MAP;

    // Quy định truy xuất đến bảng
    static final int NHANVIEN = 1; // Quy định truy xuất đến dòng
    static final int NHANVIEN_ID = 2;


    /*
     * UriMatcher là cây Uri dùng để chứa các node là Uri. * Khi khởi tạo ta cần truyền cho nó mã gốc.
     */
    static final UriMatcher uriMatcher;
    /*
     * Khởi tạo UriMaticher chứa node Uri lấy tất cả dữ * liệu và lấy theo id
     */
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_TEN, "NhanVien", NHANVIEN);
        uriMatcher.addURI(PROVIDER_TEN, "NhanVien/#", NHANVIEN_ID);
    }




    @Override
    public boolean onCreate() {
        Context context = getContext();
        DBHelper dbHelper = new DBHelper(context); // Truy xuất đọc, ghi file
        db = dbHelper.getWritableDatabase();
        return (db == null) ? false:true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        // Tạo đối tượng truy vấn dữ liệu
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder(); // Bảng cần thực hiện truy vấn
        qb.setTables(DBHelper.TEN_BANG_NHANVIEN);
// Kiểm tra đối số truyền vào thuộc các lấy nào
        switch (uriMatcher.match(uri)) { case NHANVIEN:
            qb.setProjectionMap(NHANVIEN_PROJECTION_MAP);
            break;
            case NHANVIEN_ID:
                qb.appendWhere(DBHelper._ID +
                        "=" + uri.getPathSegments().get(1) );
                break; default:
                throw new IllegalArgumentException("Unknown URI " + uri); }
// Thực hiện sắp xếp theo tên
        if(s1 == null || s1 == ""){ s1 = DBHelper.TEN;
        }
// Thực hiện truy vấn theo đối số truyền vào
        Cursor c = qb.query(db, strings, s, strings1, null, null, s1);
// Đăng ký để xem có một Uri thay đổi
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) { case NHANVIEN:
            /*
             * Loại trả về là danh sách dữ liệu */
            return "vnd.android.cursor.dir/NhanVien"; case NHANVIEN_ID:
            /*
             * Loại trả về là 1 dòng dữ liệu */
            return "vnd.android.cusor.item/NhanVien"; default:
            throw new IllegalArgumentException("Unsupport URI " + uri); }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        // Thêm cơ sơ dữ liệu
        long rowID = db.insert(DBHelper.TEN_BANG_NHANVIEN,
                "", contentValues); if(rowID > 0){
// Trả về Uri đã thêm thành công
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
// Thông báo đã có 1 dòng dữ liệu đã thay đổi để đồng bộ
// dữ liệu. getContext().getContentResolver().notifyChange(_uri, null); return _uri;
        }
        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        int count = 0;
        // Lấy dữ liệu theo đúng Uri truyền vào
                switch (uriMatcher.match(uri)){
                    case NHANVIEN:
        // Xóa dòng dữ liệu theo điều kiện
                   count = db.delete(DBHelper.TEN_BANG_NHANVIEN,s,strings);
                    case NHANVIEN_ID:
        // Xóa dòng dữ liệu theo id và điều kiện count = db.delete(DBHelper
                        count = db.delete(DBHelper
                                .TEN_BANG_NHANVIEN, DBHelper._ID + "="
                                + uri.getPathSegments().get(1)
                                + (!TextUtils.isEmpty(s) ? " AND (" + s + ')': ""), strings);
        // Thông báo đã có 1 dòng dữ liệu đã thay đổi để đồng bộ // dữ liệu. getContext().getContentResolver().notifyChange(uri, null); return count;
                        break; default:
                        throw new IllegalArgumentException("Unknown URI " + uri);
                }
        getContext().getContentResolver().notifyChange(uri, null);
                return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        int count = 0;
        switch (uriMatcher.match(uri)){ case NHANVIEN:
// Cập nhập dữ liệu theo điều kiện
            count = db.update(DBHelper.TEN_BANG_NHANVIEN, contentValues, s,
                    strings); break;
            case NHANVIEN_ID:
            count = db.update(DBHelper.TEN_BANG_NHANVIEN,
                    contentValues, DBHelper._ID + "=" + uri.getPathSegments().get(1) + (!TextUtils.isEmpty(s) ? " AND (" + s + ')': ""), strings);
                throw new IllegalArgumentException("Unknown URI " + uri); }
                return count;
    }
}
