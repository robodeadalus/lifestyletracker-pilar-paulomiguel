/**
 * This file is for initializing the attributes of the Food class as well as methods for
 * returning its name and calories as well as updating it.
 * 
 * @author Paulo Miguel A. Pilar (225008)
 * @version November 26, 2022
/*
I have not discussed the Java language code in my program 
with anyone other than my instructor or the teaching assistants 
assigned to this course.
I have not used Java language code obtained from another student, 
or any other unauthorized source, either modified or unmodified.
If any Java language code or documentation used in my program 
was obtained from another source, such as a textbook or website, 
that has been clearly noted with a proper citation in the comments 
of my program.
*/
public class Activity {
    private String name;
    private double calories;

    public Activity(String n, double c) {
        name = n;
        calories = c;
    }

    public String getActivityName() {
        return name;
    }

    public double getActivityCalories() {
        return calories;
    }

    public void updateCalories(double c) {
        calories = c;
    }

}
