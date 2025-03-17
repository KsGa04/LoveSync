package com.example.lovesync;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class NavigationActivity extends AppCompatActivity {

    private LinearLayout navHome, navTasks, navShopping, navGifts, navFinance, navProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Получаем ссылки на кнопки навигации
        navHome = findViewById(R.id.nav_home);
        navTasks = findViewById(R.id.nav_tasks);
        navShopping = findViewById(R.id.nav_shopping);
        navGifts = findViewById(R.id.nav_gifts);
        navFinance = findViewById(R.id.nav_finance);
        navProfile = findViewById(R.id.nav_profile);

        // Устанавливаем активное состояние (вызываем метод с текущей активностью)
        setActiveTab(navHome);

        // Устанавливаем обработчики кликов для навигации
        navHome.setOnClickListener(v -> switchActivity(NavigationActivity.this, DashboardActivity.class, navHome));
        navTasks.setOnClickListener(v -> switchActivity(NavigationActivity.this, TasksActivity.class, navTasks));
        navShopping.setOnClickListener(v -> switchActivity(NavigationActivity.this, ShoppingActivity.class, navShopping));
        navGifts.setOnClickListener(v -> switchActivity(NavigationActivity.this, GiftsActivity.class, navGifts));
        navFinance.setOnClickListener(v -> switchActivity(NavigationActivity.this, FinanceActivity.class, navFinance));
        navProfile.setOnClickListener(v -> switchActivity(NavigationActivity.this, ProfileActivity.class, navProfile));
    }

    // Метод для переключения активности и обновления активного элемента
    private void switchActivity(AppCompatActivity currentActivity, Class<?> newActivity, LinearLayout clickedTab) {
        setActiveTab(clickedTab);
        Intent intent = new Intent(currentActivity, newActivity);
        startActivity(intent);
        overridePendingTransition(0, 0); // Без анимации перехода
        finish();
    }

    // Метод для установки активного элемента
    private void setActiveTab(LinearLayout activeTab) {
        // Сбрасываем все элементы
        resetAllTabs();

        // Устанавливаем активный стиль для выбранной кнопки
        activeTab.setBackgroundResource(R.drawable.rounded_nav_item_active);
        for (int i = 0; i < activeTab.getChildCount(); i++) {
            View child = activeTab.getChildAt(i);
            if (child instanceof android.widget.TextView) {
                ((android.widget.TextView) child).setTextColor(Color.parseColor("#FF8585")); // Активный цвет
            }
        }
    }

    // Метод для сброса всех кнопок в стандартное состояние
    private void resetAllTabs() {
        LinearLayout[] allTabs = {navHome, navTasks, navShopping, navGifts, navFinance, navProfile};
        for (LinearLayout tab : allTabs) {
            tab.setBackgroundResource(R.drawable.rounded_nav_item);
            for (int i = 0; i < tab.getChildCount(); i++) {
                View child = tab.getChildAt(i);
                if (child instanceof android.widget.TextView) {
                    ((android.widget.TextView) child).setTextColor(Color.parseColor("#808080")); // Серый цвет
                }
            }
        }
    }
}
