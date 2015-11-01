package pl.leniec.pjatk_smb1.arrayadapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import pl.leniec.pjatk_smb1.R;
import pl.leniec.pjatk_smb1.database.ProductsDataSource;
import pl.leniec.pjatk_smb1.models.Product;

public class ProductsArrayAdapter extends ArrayAdapter<Product> {
    private ProductsDataSource productsDataSource;

    public ProductsArrayAdapter(Context context, int resource, List<Product> objects) {
        super(context, resource, objects);

        productsDataSource = new ProductsDataSource(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater layoutInflater = ((Activity)getContext()).getLayoutInflater();
            convertView = layoutInflater.inflate(R.layout.product, parent, false);
        }

        final EditText productQuantity = (EditText)convertView.findViewById(R.id.productQuantity);
        final EditText productName = (EditText)convertView.findViewById(R.id.productName);
        Button updateProduct = (Button)convertView.findViewById(R.id.updateProduct);
        Button destroyProduct = (Button)convertView.findViewById(R.id.destroyProduct);
        CheckBox boughtCheckbox = (CheckBox)convertView.findViewById(R.id.boughtCheckbox);

        final Product product = getItem(position);

        productQuantity.setText(product.getQuantity().toString());
        productName.setText(product.getName().toString());
        boughtCheckbox.setChecked(product.getBought() == 1);

        updateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.setQuantity(Integer.parseInt(productQuantity.getText().toString()));
                product.setName(productName.getText().toString());

                productsDataSource.update(product);
            }
        });

        destroyProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productsDataSource.destroy(product);
                ProductsArrayAdapter.this.remove(product);
            }
        });

        boughtCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                product.setBought(isChecked ? 1 : 0);
                productsDataSource.update(product);
            }
        });

        return convertView;
    }
}
