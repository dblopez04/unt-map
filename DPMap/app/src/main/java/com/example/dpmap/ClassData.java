/*
    ClassData.java
    Daniel Lopez - dbl0072

    Java class, this is
    used to create bookmarked
    classes. Contains the name
    of the class and the room
    number.
*/

package com.example.dpmap;

public class ClassData {
    private String className;
    private String roomNumber;

    /* Default constructor */
    public ClassData(String className, String roomNumber) {
        this.className = className;
        this.roomNumber = roomNumber;
    }

    /* Class name mutator */
    public void setClassName(String className) {
        this.className = className;
    }

    /* Classroom mutator */
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    /* Class name accessor */
    public String getClassName() {
        return className;
    }

    /* Classroom accessor */
    public String getRoomNumber() {
        return roomNumber;
    }
}
