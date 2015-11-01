package pl.leniec.pjatk_smb1.contentproviders;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import java.net.URI;

import pl.leniec.pjatk_smb1.database.Helper;

public class ProductsProvider extends ContentProvider {
    static private final String PROVIDER = "pl.leniec.pjatk_smb1";
    static public final Uri URI = Uri.parse("content://" + PROVIDER + "/products");

    static private final int PRODUCTS_URI = 1;
    static private final int PRODUCT_URI = 2;

    static private final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER, "products", PRODUCTS_URI);
        uriMatcher.addURI(PROVIDER, "products/#", PRODUCT_URI);
    }

    private SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        db = new Helper(getContext()).getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        switch(uriMatcher.match(uri)) {
            case PRODUCTS_URI:
                return db.query("products", projection, selection, selectionArgs, null, null,
                        sortOrder);
            case PRODUCT_URI:
                return db.rawQuery("SELECT * FROM products WHERE id = ?",
                        new String[]{uri.getLastPathSegment()});
        }

        throw  new IllegalArgumentException("unknown uri: " + uri);
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Long id = db.insert("products", null, values);
        return ContentUris.withAppendedId(URI, id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int rowsDeleted = 0;

        switch(uriMatcher.match(uri)) {
            case PRODUCTS_URI:
                rowsDeleted = db.delete("products", selection, selectionArgs);
                break;
            case PRODUCT_URI:
                rowsDeleted = db.delete("products", "id = ?",
                        new String[] { uri.getLastPathSegment() });
                break;
        }

        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int rowsUpdated = 0;

        switch(uriMatcher.match(uri)) {
            case PRODUCTS_URI:
                rowsUpdated = db.update("products", values, selection, selectionArgs);
                break;
            case PRODUCT_URI:
                rowsUpdated = db.update("products", values, "id = ?",
                        new String[] { uri.getLastPathSegment() });
                break;
        }

        return rowsUpdated;
    }
}
