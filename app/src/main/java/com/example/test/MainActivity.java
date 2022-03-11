package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner spnCategory;
    private CategoryAdapter categoryAdapter;

    TextView tvShow1, tvShow2, tvShow3, tvResult1, tvResult2, tvResult3;
    EditText editText;
    RadioButton rbMetre, rbCelcius, rbKilograms;

    private static final int TV_SHOW_1 = 0;
    private static final int TV_SHOW_2 = 1;
    private static final int TV_SHOW_3 = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvShow1 = (TextView) findViewById(R.id.tv_show_1);
        tvShow2 = (TextView) findViewById(R.id.tv_show_2);
        tvShow3 = (TextView) findViewById(R.id.tv_show_3);
        tvResult1 = (TextView) findViewById(R.id.tv_result1);
        tvResult2 = (TextView) findViewById(R.id.tv_result2);
        tvResult3 = (TextView) findViewById(R.id.tv_result3);
        rbMetre = findViewById(R.id.radiobutton_metre);
        rbCelcius = findViewById(R.id.radiobutton_celcius);
        rbKilograms = findViewById(R.id.radiobutton_kilograms);

        spnCategory = findViewById(R.id.spn_category);
        categoryAdapter = new CategoryAdapter(this, R.layout.item_selected, getListCategory());
        spnCategory.setAdapter(categoryAdapter);
        spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, categoryAdapter.getItem(position).getName(), Toast.LENGTH_SHORT).show();
                editText = (EditText) view.findViewById(R.id.edit_text);

                rbMetre.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (position == TV_SHOW_1 && rbMetre.isChecked()) {
                            String metreValueInput = editText.getText().toString();
                            if (metreValueInput.isEmpty()) {
                                Toast.makeText(MainActivity.this, "Please enter value", Toast.LENGTH_SHORT).show();
                            } else {
                                Double metreConvert = Double.parseDouble(metreValueInput);

                                Double CentimetreConvert = metreConvert * 100;
                                Double FootConvert = metreConvert * 3.2808;
                                Double InchConvert = metreConvert * 39.370;

                                tvResult1.setText(String.format("%.2f", CentimetreConvert));
                                tvResult2.setText(String.format("%.2f", FootConvert));
                                tvResult3.setText(String.format("%.2f", InchConvert));
                                tvShow1.setText("Centimetre");
                                tvShow2.setText("Foot");
                                tvShow3.setText("Inch");
                            }
                        } else {
                            Toast.makeText(MainActivity.this,
                                    "Please select the correct conversion icon", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                rbCelcius.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (position == TV_SHOW_2 && rbCelcius.isChecked()) {
                            String celciusValueInput = editText.getText().toString();
                            if (celciusValueInput.isEmpty()) {
                                Toast.makeText(MainActivity.this, "Please enter value", Toast.LENGTH_SHORT).show();
                            } else {
                                Double celciusConvert = Double.parseDouble(celciusValueInput);

                                Double FahConvert = celciusConvert * 1.8000 + 32.00;
                                Double KelvinConvert = celciusConvert + 273.15;

                                tvResult1.setText(String.format("%.2f", FahConvert));
                                tvResult2.setText(String.format("%.2f", KelvinConvert));
                                tvResult3.setText(" ");
                                tvShow1.setText("Fahrenheit");
                                tvShow2.setText("Kelvin");
                                tvShow3.setText(" ");
                            }
                        } else {
                            Toast.makeText(MainActivity.this,
                                    "Please select the correct conversion icon", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                rbKilograms.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (position == TV_SHOW_3 && rbKilograms.isChecked()) {
                            String kgValueInput = editText.getText().toString();
                            if (kgValueInput.isEmpty()) {
                                Toast.makeText(MainActivity.this, "Please enter value", Toast.LENGTH_SHORT).show();
                            } else {
                                Double kgConvert = Double.parseDouble(kgValueInput);

                                Double GramConvert = kgConvert * 1000;
                                Double OunceConvert = kgConvert * 35.274;
                                Double PoundConvert = kgConvert * 2.20462;

                                tvResult1.setText(String.format("%.2f", GramConvert));
                                tvResult2.setText(String.format("%.2f", OunceConvert));
                                tvResult3.setText(String.format("%.2f", PoundConvert));
                                tvShow1.setText("Gram");
                                tvShow2.setText("Ounce(Oz)");
                                tvShow3.setText("Pound(lb)");
                            }
                        } else {
                            Toast.makeText(MainActivity.this,
                                    "Please select the correct conversion icon", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private List<Category> getListCategory() {
        List<Category> list = new ArrayList<>();
        list.add(new Category("Metre"));
        list.add(new Category("Celsius"));
        list.add(new Category("Kilograms"));

        return list;
    }
}