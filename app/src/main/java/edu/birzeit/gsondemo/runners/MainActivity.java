package edu.birzeit.gsondemo.runners;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import edu.birzeit.gsondemo.R;
import edu.birzeit.gsondemo.domain.Book;

public class MainActivity extends AppCompatActivity {

    public static final String DATA = "DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnSave_OnClick(View view) {
        Book[] books = new Book[2];
        books[0] = new Book("Java Core", "John");
        books[1] = new Book("C# in Nutshell","Mark");

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();

        Gson gson = new Gson();
        String booksString = gson.toJson(books);

        editor.putString(DATA, booksString);
        editor.commit();
        Toast.makeText(this,"Data Saved" + booksString, Toast.LENGTH_SHORT).show();

    }

    public void btnLoad_OnClick(View view) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String str = prefs.getString(DATA,"");
        if (!str.equals("")) {
            Book[] books = gson.fromJson(str, Book[].class);
            Toast.makeText(this,"Number Of Books: " + books.length, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,"Data Not Found", Toast.LENGTH_SHORT).show();
        }
    }
}