/*
    ClassRecyclerAdapter.java
    Daniel Lopez - dbl0072

    This class manages the RecyclerView
    that's responsible for listing
    bookmarks. Essentially, this class
    is used to take data from the classList
    array, and display them in ViewHolders.

    Resources used:
    https://developer.android.com/develop/ui/views/layout/recyclerview
    https://developer.android.com/reference/android/content/Context
    https://developer.android.com/reference/android/widget/PopupWindow
*/

/* TODO: Update UI to better match the proposed design */
/* TODO: Fix padding in both popup windows */
/* TODO: Fix search feature */



package com.example.dpmap;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ClassRecyclerAdapter extends RecyclerView.Adapter<ClassRecyclerAdapter.ClassViewHolder> { // Declares that this class is a custom adapter for a recyclerview.

    private ArrayList<ClassData> classList;
    private Context context;

    /* When an adapter is initialized with an array as an argument, set that as the classList. */
    public ClassRecyclerAdapter(ArrayList<ClassData> classList, Context context) { //
        this.classList = classList;
        this.context = context;
    }

    /* Allows for the creation of ViewHolders, which will be an individual display of a class and it's classroom number. They will then populate the recyclerview. */
    public static class ClassViewHolder extends RecyclerView.ViewHolder {
        public TextView className;
        public TextView classroom;
        public Button editButton;

        /* Creates a new ViewHolder with a view containing a singular class as a parameter. */
        public ClassViewHolder(View classObjectView) {
            super(classObjectView); // Passes the classObjectView to the RecyclerView
            className = classObjectView.findViewById(R.id.className); // Takes layout elements and assigns them to ClassViewHolder's variables.
            classroom = classObjectView.findViewById(R.id.classroom);
            editButton = classObjectView.findViewById(R.id.editButton);
        }
        /* TODO: Add accessors */
    }

    /* Required Android function for RecyclerView adapters. Called when a new item is created. While viewType isn't used, it is required for this function or else the code won't compile. */
    @Override
    public ClassViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View classObjectView = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_view, parent, false); // LayoutInflater is used to create the UI elements used in the ViewHolder.
        return new ClassViewHolder(classObjectView);
    }

    /* Required Android function for RecyclerView adapters. Called when a new item is created, and is used to display the data in the ViewHolder. */
    @Override
    public void onBindViewHolder(ClassViewHolder classViewHolder, final int index) {
        ClassData currentClass = classList.get(index);
        classViewHolder.className.setText(currentClass.getClassName());
        classViewHolder.classroom.setText(currentClass.getRoomNumber());
        classViewHolder.editButton.setOnClickListener(v -> showEditPopup(currentClass, index, classViewHolder.getAbsoluteAdapterPosition()));
    }

    /* Get the amount of items in the class list */
   @Override
    public int getItemCount() {
        return classList.size();
    }

    /* Add an item to the class list */
    public void addClass(ClassData item) {
        classList.add(item);
        notifyItemInserted(classList.size() - 1);
    }

    /* Remove an item from the class list */
    public void removeClass(int index) {
        classList.remove(index);
        notifyItemRemoved(index);
    }

    /* Display the search results in the RecyclerView */
    public void searchList(ArrayList<ClassData> filteredList){
        classList = filteredList;
        notifyDataSetChanged();
    }

    /* Display the edit class popup */
    private void showEditPopup(ClassData currentClass, int index, int absoluteAdapterindex) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View editPopup = inflater.inflate(R.layout.edit_class, null);
        int width = LinearLayout.LayoutParams.WRAP_CONTENT; // Define popup dimentions based on the layout
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;

        PopupWindow popup = new PopupWindow(editPopup, width, height, focusable);
        popup.showAtLocation(((Activity) context).getWindow().getDecorView(), Gravity.CENTER, 0, 0); // Displays the popup in the center of the screen
        EditText popupClassEdit = editPopup.findViewById(R.id.classEditText);
        EditText popupRoomEdit = editPopup.findViewById(R.id.roomEditText);
        Button saveButton = editPopup.findViewById(R.id.saveButton);
        Button cancelButton = editPopup.findViewById(R.id.cancelButton);
        Button deleteButton = editPopup.findViewById(R.id.deleteButton);

        popupClassEdit.setText(currentClass.getClassName());
        popupRoomEdit.setText(currentClass.getRoomNumber());

        /* Saves inputted information when the save button is clicked */
        saveButton.setOnClickListener(v -> {
            currentClass.setClassName(popupClassEdit.getText().toString());
            currentClass.setRoomNumber(popupRoomEdit.getText().toString());
            notifyItemChanged(absoluteAdapterindex);
            popup.dismiss();
        });

        /* Remove the class if the delete button is clicked */
        deleteButton.setOnClickListener(v -> {
            removeClass(index);
            popup.dismiss();
        });

        cancelButton.setOnClickListener(v -> popup.dismiss());
    }

}