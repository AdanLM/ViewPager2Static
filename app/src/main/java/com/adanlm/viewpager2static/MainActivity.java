package com.adanlm.viewpager2static;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int[] layouts = new int[]{R.layout.slide_one,
            R.layout.slide_two,
            R.layout.slide_three,
            R.layout.slide_four};

    private ViewPager2 viewPager2;
    private LinearLayout layoutDots;
    private Button btnSkip, btnNext;
    //Este callback nos sirve para actualizar la barra de los puntos
    private final ViewPager2.OnPageChangeCallback pageChangeCallback = new ViewPager2.OnPageChangeCallback() {
        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
            addBottomDots(position);

            if (position == (layouts.length - 1)) {
                btnNext.setText(getString(R.string.start));
                btnSkip.setVisibility(View.GONE);
            } else {
                btnNext.setText(getString(R.string.btn_next));
                btnSkip.setVisibility(View.VISIBLE);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutDots = findViewById(R.id.layoutDots);

        btnSkip = findViewById(R.id.btn_skip);
        btnNext = findViewById(R.id.btn_next);

        btnSkip.setOnClickListener(view -> launchHomeScreen());
        btnNext.setOnClickListener(this);

        viewPager2 = findViewById(R.id.view_pager);
        ViewsSliderAdapter adapter = new ViewsSliderAdapter(layouts);
        viewPager2.setAdapter(adapter);
        viewPager2.registerOnPageChangeCallback(pageChangeCallback);
    }

    private void launchHomeScreen() {
        Toast.makeText(MainActivity.this, R.string.welcome, Toast.LENGTH_LONG).show();
        finish();
    }

    //Se ejecuta el pageChangeCallback
    @Override
    public void onClick(View v) {
        int current = getItem(+1);
        if (current < layouts.length) {
            viewPager2.setCurrentItem(current);
        } else {
            launchHomeScreen();
        }
    }

    private int getItem(int i) {
        return viewPager2.getCurrentItem() + 1;
    }

    //Agrega/Actualiza los colores de la barra de puntos
    private void addBottomDots(int currentPage) {
        //Tendremos n TextView como pantallas estaticas tengamos
        TextView[] dots = new TextView[layouts.length];

        //Obtenemos los colores de los puntos cuando esten activos
        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);

        //Obtenemos los colores de los puntos cuando esten inactivos
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        //Quitamos todos los hijos que tenga
        layoutDots.removeAllViews();

        //Aqui se crean los TextViews con los atributos necesarios
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(MainActivity.this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            layoutDots.addView(dots[i]);
        }

        //Si no esta vacio se selecciona el item que se pasa
        if (dots.length > 0) {
            dots[currentPage].setTextColor(colorsActive[currentPage]);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewPager2.unregisterOnPageChangeCallback(pageChangeCallback);
    }
}