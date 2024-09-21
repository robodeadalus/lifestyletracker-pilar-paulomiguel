
/**
 * This is LifestyleTracker file is responsible for initializing array lists
 * for foods, activities, consumed foods/performed activities, servings, hours, calories per activity and food.
 * 
 * This file also makes use of methods to add foods/activities, add consumed foods/performed activities, food servings/activitiy hours, 
 * calories per food consumed/activity performed and outputting the report.

 * It also makes use of methods to delete or edit records of food consumed (name, calorie count, servings) and activity performed (name, calorie count, hours).
 * (editing a food/activity's name with an existing one turns that record into the existing food/activity). It can also add foods/activities
 * when attempting to eat/perform a non-existing one. 
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

import java.util.ArrayList;

public class LifestyleTracker {
    private ArrayList<Food> foods = new ArrayList<Food>();
    private ArrayList<Activity> activities = new ArrayList<Activity>();
    private ArrayList<String> consumedfoods = new ArrayList<String>();
    private ArrayList<String> performedactivities = new ArrayList<String>();
    private ArrayList<Double> CFServings = new ArrayList<Double>();
    private ArrayList<Double> PAHours = new ArrayList<Double>();
    private ArrayList<Double> FCalories = new ArrayList<Double>();
    private ArrayList<Double> ACalories = new ArrayList<Double>();
    private double TotalKcal;
    private double TotalKcalBurned;
    private double KCalinKG;

    public LifestyleTracker() {
        KCalinKG = 0.00012959782;
        TotalKcal = 0;
        TotalKcalBurned = 0;
    }
    // Java string format() method - javatpoint. www.javatpoint.com. (n.d.). Retrieved December 3, 2022, from https://www.javatpoint.com/java-string-format 
    public String addFood(String n, double c) {
        String printStatement = "";
        if (foodGetter(n) != null) {
            foodGetter(n).updateCalories(c);
            printStatement = String.format("Updated Food %s with %.2f kcal", n, c);
            return printStatement;
        }
        foods.add(new Food(n, c));
        printStatement = String.format("Added Food %s with %.2f kcal", n, c);
        return printStatement;
    }

    public String addActivity(String n, double c) {
        String printStatement = "";
        if (activityGetter(n) != null) {
            activityGetter(n).updateCalories(c);
            printStatement = String.format("Updated Activity %s with %.2f kcal", n, c);
            return printStatement;
        }
        activities.add(new Activity(n, c));
        printStatement = String.format("Added Activity %s with %.2f kcal", n, c);
        return printStatement;
    }

    public String eat(String foodName, double servings) {
        String printStatement = "";
        if (foodGetter(foodName) != null) {
            if (servings < 0) {
                printStatement = "Number of servings cannot be negative.";
                return printStatement;
            } else {
                double totalKcalInstance = (foodGetter(foodName).getFoodCalories()) * servings;
                consumedfoods.add(foodName);
                CFServings.add(servings);
                FCalories.add(totalKcalInstance);
                printStatement = String.format("Ate %.2f serving(s) of %s, %.2f kcal", servings, foodName,
                        totalKcalInstance);
                return printStatement;
            }
        }
        printStatement = "The specified food does not exist.";
        return printStatement;
    }

    public String perform(String actName, double hours) {
        String printStatement = "";
        if (activityGetter(actName) != null) {
            if (hours < 0) {
                printStatement = "Number of hours cannot be negative.";
                return printStatement;
            } else {
                double kCalBurnedInstance = (activityGetter(actName).getActivityCalories()) * hours;
                performedactivities.add(actName);
                PAHours.add(hours);
                ACalories.add(kCalBurnedInstance);
                printStatement = String.format("Performed %.2f hour(s) of %s, %.2f kcal", hours, actName,
                        kCalBurnedInstance);
                return printStatement;
            }
        }
        printStatement = "The specified activity does not exist.";
        return printStatement;
    }

    public Food foodGetter(String n) {
        for (int i = 0; i < foods.size(); i++) {
            if (n.equals(foods.get(i).getFoodname())) {
                return foods.get(i);
            }
        }
        return null;
    }

    public Activity activityGetter(String n) {
        for (int i = 0; i < activities.size(); i++) {
            if (n.equals(activities.get(i).getActivityName())) {
                return activities.get(i);
            }
        }
        return null;
    }

    public String foodRecordViewer() {
        String FoodRecord = "\n-------------------------------------RECORDED  FOODS-------------------------------------\n";
        double currentCaloriesConsumed = 0;
        for (int i = 0; i < consumedfoods.size(); i++) {
            if(consumedfoods.get(i).length() <10){
                FoodRecord += String.format("Name: %s\t\t| Calories: %.2f\t| Servings: %.2f\t| Index: %d\t|\n",
                    consumedfoods.get(i), FCalories.get(i), CFServings.get(i), i);
                    currentCaloriesConsumed += FCalories.get(i);
                }
            else{
                FoodRecord += String.format("Name: %s\t| Calories: %.2f\t| Servings: %.2f\t| Index: %d\t|\n",
                consumedfoods.get(i), FCalories.get(i), CFServings.get(i), i);
                currentCaloriesConsumed += FCalories.get(i);
            }
        }
        FoodRecord += "-----------------------------------------------------------------------------------------\n";
        FoodRecord += String.format("Current Consumed Calories: %.2f\n", currentCaloriesConsumed);
        return FoodRecord;
    }

    public String editFoodRecordName(int foodIndex, String newFoodName) {
        String FoodRecordName = "";
        consumedfoods.set(foodIndex, newFoodName);
        for (int i = 0; i < foods.size(); i++) {
            if (consumedfoods.get(foodIndex).equals(foods.get(i).getFoodname())) {
                FoodRecordName += "Duplicate Food Found...Changing Record to Existing Food\n";
                consumedfoods.set(foodIndex, foods.get(i).getFoodname());
                editFoodRecordCalories(foodIndex, foods.get(i).getFoodCalories()*CFServings.get(foodIndex));
                FoodRecordName += foodRecordViewer();
                return FoodRecordName;
            } else {
                continue;
            }
        }
        FoodRecordName += "New food name not found in records..Adding to database\n";
        addFood(newFoodName, (FCalories.get(foodIndex)/CFServings.get(foodIndex)));
        FoodRecordName += foodRecordViewer();
        return FoodRecordName;
    }

    public String editFoodRecordCalories(int foodIndex, double newFoodCalorie) {
        String FoodRecordCalories = "";
        FCalories.set(foodIndex, newFoodCalorie);
        CFServings.set(foodIndex, FCalories.get(foodIndex)/foodGetter(consumedfoods.get(foodIndex)).getFoodCalories());
        FoodRecordCalories += "Successfully changed Food Calories.\n";
        FoodRecordCalories += foodRecordViewer();
        return FoodRecordCalories;
    }

    public String editFoodRecordServings(int foodIndex, double newFoodServings) {
        String FoodRecordServings = "";
        double tempCaloriesAtBase = 0;
        tempCaloriesAtBase = FCalories.get(foodIndex)/CFServings.get(foodIndex);
        CFServings.set(foodIndex, newFoodServings);
        FCalories.set(foodIndex, (newFoodServings * tempCaloriesAtBase));
        FoodRecordServings += "Successfully changed Food Servings.\n";
        FoodRecordServings += foodRecordViewer();
        return FoodRecordServings;
    }

    // How to remove an element from Arraylist in Java? GeeksforGeeks. (2022, July 7). Retrieved December 3, 2022, from https://www.geeksforgeeks.org/remove-element-arraylist-java/ 
    public String deleteActivityRecord(int activityIndex) {
        String ActivityDeleteRecord = "";
        ACalories.remove(activityIndex);
        PAHours.remove(activityIndex);
        performedactivities.remove(activityIndex);
        ActivityDeleteRecord += ("Deleted Activity Record Index " + activityIndex + ".\n");
        ActivityDeleteRecord += activityRecordViewer();
        return ActivityDeleteRecord;
    }

    //How to remove an element from Arraylist in Java? GeeksforGeeks. (2022, July 7). Retrieved December 3, 2022, from https://www.geeksforgeeks.org/remove-element-arraylist-java/ 
    public String deleteFoodRecord(int foodIndex) {
        String FoodDeleteRecord = "";
        FCalories.remove(foodIndex);
        CFServings.remove(foodIndex);
        consumedfoods.remove(foodIndex);
        FoodDeleteRecord += ("Deleted Food Record Index " + foodIndex + ".\n");
        FoodDeleteRecord += foodRecordViewer();
        return FoodDeleteRecord;
    }

    public String activityRecordViewer() {
        String ActivityRecord = "\n-------------------------------RECORDED ACTIVITIES-------------------------------\n";
        double currentCaloriesBurned = 0;
        for (int i = 0; i < performedactivities.size(); i++) {
            if(performedactivities.get(i).length()<10){
                ActivityRecord += String.format("Name: %s\t\t| Calories: %.2f\t| Hours: %.2f\t| Index: %d\t|\n",
                performedactivities.get(i), ACalories.get(i), PAHours.get(i), i);
                currentCaloriesBurned += ACalories.get(i);
                }
            else{
                ActivityRecord += String.format("Name: %s\t| Calories: %.2f\t| Hours: %.2f\t| Index: %d\t|\n",
                performedactivities.get(i), ACalories.get(i), PAHours.get(i), i);
                currentCaloriesBurned += ACalories.get(i);  
            }
        }
        ActivityRecord += "---------------------------------------------------------------------------------\n";
        ActivityRecord += String.format("Current Burned Calories: %.2f\n", currentCaloriesBurned);
        return ActivityRecord;
    }

    public String editActivityRecordName(int activityIndex, String newActivityName) {
        String ActivityRecordName = "";
        performedactivities.set(activityIndex, newActivityName);
        for (int i = 0; i < activities.size(); i++) {
            if (performedactivities.get(activityIndex).equals(activities.get(i).getActivityName())) {
                ActivityRecordName += "Duplicate Activity Found...Changing Record to Existing Activity\n";
                performedactivities.set(activityIndex, activities.get(i).getActivityName());
                editActivityRecordCalories(activityIndex, activities.get(i).getActivityCalories()*PAHours.get(activityIndex));
                ActivityRecordName += activityRecordViewer();
                return ActivityRecordName;
            } else {
                continue;
            }
        }
        ActivityRecordName += "New activity name not found in records..Adding to database\n";
        addActivity(newActivityName, (ACalories.get(activityIndex)/PAHours.get(activityIndex)));
        ActivityRecordName += activityRecordViewer();
        return ActivityRecordName;
    }

    public String editActivityRecordCalories(int activityIndex, double newActivityCalorie) {
        String ActivityRecordCalories = "";
        ACalories.set(activityIndex, newActivityCalorie);
        PAHours.set(activityIndex, ACalories.get(activityIndex)/activityGetter(performedactivities.get(activityIndex)).getActivityCalories());
        ActivityRecordCalories += "Successfully changed Activity Calories.\n";
        ActivityRecordCalories += activityRecordViewer();
        return ActivityRecordCalories;
    }

    public String editActivityRecordHours(int activityIndex, double newActivityHours) {
        String ActivityRecordHours = "";
        double tempCaloriesAtBase = 0;
        tempCaloriesAtBase = ACalories.get(activityIndex)/PAHours.get(activityIndex);
        PAHours.set(activityIndex, newActivityHours);
        ACalories.set(activityIndex, (newActivityHours * tempCaloriesAtBase));
        ActivityRecordHours += "Successfully changed Activity Hours.\n";
        ActivityRecordHours += activityRecordViewer();
        return ActivityRecordHours;
    }

    public String report() {
        String report = "----------------\nLIFESTYLE REPORT\n----------------\n";
        report += printFoodConsumed() + printActivitiesPerformed() + printPrediction();
        return report;
    }

    public String printFoodConsumed() {
        String FoodConsumed = "Food Consumed:\n";
        double reportTotalKcalConsumed = 0;
        for (int i = 0; i < FCalories.size(); i++) {
            FoodConsumed += String.format("%.2f serving(s) of %s, %.2f kcal\n", CFServings.get(i), consumedfoods.get(i),
                    FCalories.get(i));
            reportTotalKcalConsumed += FCalories.get(i);
        }
        FoodConsumed += String.format("----------------\nTotal Calories Consumed: %.2f kcal\n----------------\n",
                reportTotalKcalConsumed);
        TotalKcal = reportTotalKcalConsumed;
        return FoodConsumed;
    }

    public String printActivitiesPerformed() {
        String ActivitiesPerformed = "Activities Performed:\n";
        double reportTotalKcalBurned = 0;
        for (int i = 0; i < ACalories.size(); i++) {
            ActivitiesPerformed += String.format("%.2f hour(s) of %s, %.2f kcal\n", PAHours.get(i),
                    performedactivities.get(i), ACalories.get(i));
            reportTotalKcalBurned += ACalories.get(i);
        }
        ActivitiesPerformed += String.format("----------------\nTotal Calories Burned: %.2f kcal\n----------------\n",
                reportTotalKcalBurned);
        TotalKcalBurned = reportTotalKcalBurned;
        return ActivitiesPerformed;
    }

    public String printPrediction() {
        String Prediction = "";
        double netCal = TotalKcal - TotalKcalBurned;
        double weightGainOrLose = netCal * KCalinKG;
        double oneWeek = weightGainOrLose * 7;
        double oneMonth = weightGainOrLose * 30;
        double threeMonths = weightGainOrLose * 90;
        double sixMonths = weightGainOrLose * 180;

        Prediction += String.format("Net Calories for the Day: %.2f kcal\n", netCal);
        if (netCal < 0) {
            Prediction += String.format(
                    "If you keep up this lifestyle...\nIn a week, you will lose %.2f kilograms.\nIn a month, you will lose %.2f kilograms.\nIn 3 months, you will lose %.2f kilograms.\nIn 6 months, you will lose %.2f kilograms.\n----------------",
                    Math.abs(oneWeek), Math.abs(oneMonth), Math.abs(threeMonths), Math.abs(sixMonths));
        } else {
            Prediction += String.format(
                    "If you keep up this lifestyle...\nIn a week, you will gain %.2f kilograms.\nIn a month, you will gain %.2f kilograms.\nIn 3 months, you will gain %.2f kilograms.\nIn 6 months, you will gain %.2f kilograms.\n----------------",
                    oneWeek, oneMonth, threeMonths, sixMonths);
        }
        return Prediction;
    }

}
