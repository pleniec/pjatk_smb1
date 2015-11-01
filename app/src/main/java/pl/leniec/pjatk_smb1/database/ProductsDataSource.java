package pl.leniec.pjatk_smb1.database;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import pl.leniec.pjatk_smb1.contentproviders.ProductsProvider;
import pl.leniec.pjatk_smb1.models.Product;

public class ProductsDataSource {
    private ContentResolver contentResolver;
    private SQLiteDatabase db;

    public ProductsDataSource(Context context) {
        contentResolver = context.getContentResolver();
        db = new Helper(context).getWritableDatabase();
    }

    /*
        public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
     */

    public List<Product> all() {
        List<Product> products = new ArrayList<>();

        Cursor cursor = contentResolver.query(ProductsProvider.URI,
                new String[] { "id", "quantity", "name", "bought" }, null, null, null);

        if(cursor.moveToFirst()) {
            do {
                products.add(new Product(cursor.getLong(0), cursor.getInt(1),
                        cursor.getString(2), cursor.getInt(3)));
            }
            while(cursor.moveToNext());
        }

        return products;
    }

    public void create(Product product) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("quantity", product.getQuantity());
        contentValues.put("name", product.getName());
        contentValues.put("bought", product.getBought());

        Uri uri = contentResolver.insert(ProductsProvider.URI, contentValues);
        Long id = Long.parseLong(uri.getLastPathSegment());

        product.setId(id);
    }

    public void update(Product product) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("quantity", product.getQuantity());
        contentValues.put("name", product.getName());
        contentValues.put("bought", product.getBought());

        contentResolver.update(ContentUris.withAppendedId(ProductsProvider.URI, product.getId()),
                contentValues, null, null);
    }

    public void destroy(Product product) {
        contentResolver.delete(ContentUris.withAppendedId(ProductsProvider.URI, product.getId()),
                null, null);
    }
}
