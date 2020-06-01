package com.example.firstkotlin.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firstkotlin.test.KotlinExercise;

/**
 * Created on 2020/5/28.
 */
public class TestActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        KotlinExercise exercise = new KotlinExercise(this);
        exercise.test();

    }
}
