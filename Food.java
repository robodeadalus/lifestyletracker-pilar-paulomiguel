/**
 * This file is for initializing the attributes of the Food class as well as
 * methods for returning its name and calories as well as updating it.
 * 
 * @author Paulo Miguel A. Pilar (225008)
 * @version November 26, 2022
 *
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
public class Food {
    private String foodname;
    private double calories;

    public Food(String f, double c) {
        foodname = f;
        calories = c;
    }

    public String getFoodname() {
        return foodname;
    }

    public double getFoodCalories() {
        return calories;
    }

    public void updateCalories(double c) {
        calories = c;
    }
}
