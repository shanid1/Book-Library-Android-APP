package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.UtteranceProgressListener;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {
    static final String BOOK_ID_KEY = "bookId";
    private TextView txtBookName, txtAuthor, txtPages, txtDescription;
    private ImageView imgBookPhoto;
    private Button btnAddToCurrReading, btnAddToWantToRead, btnAddToAlreadyRead, btnAddToFav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book);
        initViews();
        Intent intent =getIntent();
        if(intent!=null){
            int bookId = intent.getIntExtra(BOOK_ID_KEY,-1);
            if(bookId!=-1){
                Book booked = Utils.getInstance(this).getBookById(bookId);
                if(booked!=null){
                    setData(booked);
                    handleAlreadyRead(booked);
                    handleWantToReadBooks(booked);
                    handleCurrentReading(booked);
                    handleFavorites(booked);
                    
                    


                }else{
                    Toast.makeText(this, "Unable to Load BOOK!(Restart APP)", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    private void handleWantToReadBooks(final Book booked) {
        ArrayList<Book> wantToReadBooks = Utils.getInstance(this).getWantToReadLater();
        boolean isWantToRead = false;
        for(Book b:wantToReadBooks){
            if(b.getId()==booked.getId()){
                isWantToRead = true;
            }
        }
        if(isWantToRead){
            btnAddToWantToRead.setEnabled(false);

        }
        else{
            btnAddToWantToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(BookActivity.this).addToWantToRead(booked)){
                        Toast.makeText(BookActivity.this, "Added to WishList", Toast.LENGTH_SHORT).show();
                        btnAddToWantToRead.setEnabled(false);
                        Intent intent = new Intent(BookActivity.this,WantToReadActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(BookActivity.this, "Unable to Add to Try Again ", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }

    private void handleCurrentReading(final Book booked) {
        ArrayList<Book> alreadyReadBooks = Utils.getInstance(this).getCurrentlyReading();
        boolean isAlreadyRead = false;
        for(Book b:alreadyReadBooks){
            if(b.getId()==booked.getId()){
                isAlreadyRead = true;
            }
        }
        if(isAlreadyRead){
            btnAddToCurrReading.setEnabled(false);

        }
        else{
            btnAddToCurrReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToCurrentlyReading(booked)) {

                        Toast.makeText(BookActivity.this, "Added to Currently Reading", Toast.LENGTH_SHORT).show();
                        btnAddToCurrReading.setEnabled(false);
                        Intent intent = new Intent(BookActivity.this, currentlyReadingActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Unable to Add to Try Again ", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

    }

    private void handleFavorites(final Book booked) {
        ArrayList<Book> wantToReadBooks = Utils.getInstance(this).getFavorites();
        boolean isWantToRead = false;
        for(Book b:wantToReadBooks){
            if(b.getId()==booked.getId()){
                isWantToRead = true;
            }
        }
        if(isWantToRead){
            btnAddToFav.setEnabled(false);

        }
        else{
            btnAddToFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToFav(booked)) {

                        Toast.makeText(BookActivity.this, "Added to Favourites", Toast.LENGTH_SHORT).show();
                        btnAddToFav.setEnabled(false);
                        Intent intent = new Intent(BookActivity.this, favouriteBooksActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Unable to Add to Try Again ", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }

    private void handleAlreadyRead(final Book booked) {
        ArrayList<Book> alreadyReadBooks = Utils.getInstance(this).getAlreadyRead();
        boolean isAlreadyRead = false;
        for(Book b:alreadyReadBooks){
            if(b.getId()==booked.getId()){
                isAlreadyRead = true;
            }
        }
        if(isAlreadyRead){
            btnAddToAlreadyRead.setEnabled(false);

        }
        else{
            btnAddToAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(BookActivity.this).addToAlreadyRead(booked)){
                        Toast.makeText(BookActivity.this, "Added to Already Read", Toast.LENGTH_SHORT).show();
                        btnAddToAlreadyRead.setEnabled(false);
                        Intent intent = new Intent(BookActivity.this,AlreadyReadBookActivity.class);
                        startActivity(intent);
                    }
                    else{
                            Toast.makeText(BookActivity.this, "Unable to Add to Try Again ", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }

    private void setData(Book book){
        txtBookName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDescription.setText(book.getLongDesc());
        imgBookPhoto.setImageResource(book.getImageResId());
    }
    private void initViews(){
        txtBookName = findViewById(R.id.txtBoookName);
        txtAuthor = findViewById(R.id.txtAuthor);
        txtPages = findViewById(R.id.txtPages);
        txtDescription = findViewById(R.id.txtDescription);
        imgBookPhoto = findViewById(R.id.imgBookPhoto);
        btnAddToCurrReading = findViewById(R.id.btnAddtoCurrReading);
        btnAddToWantToRead = findViewById(R.id.btnAddToWantToRead);
        btnAddToAlreadyRead = findViewById(R.id.btnToaddtoAlreadyRead);
        btnAddToFav = findViewById(R.id.btnToAddToFav);
    }
}