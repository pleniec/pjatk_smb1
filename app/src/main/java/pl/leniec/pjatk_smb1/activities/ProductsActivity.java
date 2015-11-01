package pl.leniec.pjatk_smb1.activities;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.List;

import pl.leniec.pjatk_smb1.R;
import pl.leniec.pjatk_smb1.arrayadapters.ProductsArrayAdapter;
import pl.leniec.pjatk_smb1.database.ProductsDataSource;
import pl.leniec.pjatk_smb1.models.Product;

public class ProductsActivity extends ListActivity {
    private ProductsDataSource productsDataSource;

    private ProductsArrayAdapter productsArrayAdapter;
    private EditText productQuantity;
    private EditText productName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products);

        productQuantity = (EditText)findViewById(R.id.productQuantity);
        productName = (EditText)findViewById(R.id.productName);

        productsDataSource = new ProductsDataSource(this);

        List<Product> products = productsDataSource.all();

        productsArrayAdapter = new ProductsArrayAdapter(this,
                R.layout.product, products);
        setListAdapter(productsArrayAdapter);
    }

    public void createProduct(View view) {
        Product product = new Product(null,
                Integer.parseInt(productQuantity.getText().toString()),
                productName.getText().toString(),
                0);

        productsDataSource.create(product);

        productsArrayAdapter.add(product);
    }
}
