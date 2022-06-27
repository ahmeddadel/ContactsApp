package com.dolla.fragmentsrecyclerview;

import android.app.Application;

import java.util.ArrayList;

public class ApplicationClass extends Application {
    public static ArrayList<Person> people;

    @Override
    public void onCreate() {
        super.onCreate();

        people = new ArrayList<>();

        people.add(new Person("Ahmed Adel", "01129025241"));
        people.add(new Person("Abdallah Monzer", "01229465241"));
        people.add(new Person("Ahmed Kamel", "01529028796"));
        people.add(new Person("Mohamed Jamal", "01001252247"));
        people.add(new Person("Omar Sakr", "01156994240"));
        people.add(new Person("Ayman Ashraf", "010569870140"));

    }
}
