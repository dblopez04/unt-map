/*
    BookmarkActivity.java
    Daniel Lopez - dbl0072

    This file powers the
    logic behind the Bookmark
    activity. This activity
    allows you to bookmark
    specific classes and their
    class numbers, as well as edit
    the bookmark list.
*/

package com.example.dpmap;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import java.util.ArrayList;
import java.util.Locale;

public class BookmarkActivity extends AppCompatActivity {

    private RecyclerView classRecyclerView; // Contains a view of the class list.
    private ClassRecyclerAdapter classAdapter; // Manages classRecyclerView. See ClassRecyclerAdapter.java.
    private ArrayList<ClassData> classList;
    private Button addClassButton;
    private Context context;
    private SearchView classSearchView; // Allows for the searching of specific classes/rooms

    /* onCreate is the first stage of the activity lifecycle, ran when the activity starts. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bookmark); // Associates BookmarkActivity.java to activity_bookmark.xml
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        context = this;
        classRecyclerView = findViewById(R.id.classRecyclerView); // These variable declarations assign specific layout objects to a variable.
        addClassButton = findViewById(R.id.addClassButton);
        classSearchView = findViewById(R.id.classSearchView);
        classList = new ArrayList<>();
        classAdapter = new ClassRecyclerAdapter(classList, context);
        classRecyclerView.setAdapter(classAdapter);
        classRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        /* When the add class button is clicked, create a new bookmark */
        addClassButton.setOnClickListener(v -> {
           showPopup();
        });

        /* When the user searches, set a TextListener */
        classSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            /* Required function for OnQueryTextListeners. Does nothing since the list searches as soon as the user types */
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            /* When the SearchView text changes, call the searchList function */
            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }

        });
    }

    /* This creates a filtered list based on a search query. */
    private void searchList(String query) {
        if(query != null){
            ArrayList<ClassData> filteredList = new ArrayList<>();
            for (ClassData classData : classList){
                if(classData.getClassName().toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT)) || classData.getRoomNumber().toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT))){
                    filteredList.add(classData);
                }
            }
            classAdapter.searchList(filteredList);
        }
    }

    private void showPopup() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View addPopup = inflater.inflate(R.layout.add_class, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        PopupWindow popup = new PopupWindow(addPopup, width, height, focusable);
        popup.showAtLocation(((Activity) context).getWindow().getDecorView(), Gravity.CENTER, 0, 0);

        EditText classEdit = addPopup.findViewById(R.id.classEditText);
        EditText roomEdit = addPopup.findViewById(R.id.roomEditText);
        Button saveButton = addPopup.findViewById(R.id.saveButton);
        Button cancelButton = addPopup.findViewById(R.id.cancelButton);

        saveButton.setOnClickListener(v -> {
            String className = classEdit.getText().toString();
            String room = roomEdit.getText().toString();
            ClassData newClass = new ClassData(className, room);
            classAdapter.addClass(newClass);
            popup.dismiss();
        });

        cancelButton.setOnClickListener(v -> popup.dismiss());
    }

}

