/**
 * This file is for taking in user commands such as Food,Activity,Eat,Perform,Report,Record Food, Record Activity
 * With the last two commands listed above, the user can choose to delete or edit their respective records (name, calorie, servings/hours). 
 * In addition to that the user can also view their current consumed/burned calories.
 * 
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
import java.util.Scanner;

public class TrackerConsole {
    public static void main(String[] args) {
        System.out.println("Welcome to " + args[0] + "'s Lifestyle Tracker!");
        String printStatement = "";
        Scanner in = new Scanner(System.in);
        LifestyleTracker lifestyleTracker1 = new LifestyleTracker();

        while (true) {
            String input = in.next();
            if (input.equals("Report")) {
                break;
            } else if (input.equals("Food")) {
                String foodName = in.next();
                double calories = in.nextDouble();
                printStatement = lifestyleTracker1.addFood(foodName, calories);
                System.out.println(printStatement);
            } else if (input.equals("Activity")) {
                String activityName = in.next();
                double calories = in.nextDouble();
                printStatement = lifestyleTracker1.addActivity(activityName, calories);
                System.out.println(printStatement);
            } else if (input.equals("Eat")) {
                String foodName = in.next();
                double servings = in.nextDouble();
                printStatement = lifestyleTracker1.eat(foodName, servings);
                System.out.println(printStatement);
                if (lifestyleTracker1.foodGetter(foodName) == null) {
                    System.out.print("Do you want to add " + foodName + " to the database (Y/N):");
                    String foodAdder = in.next();
                    if (foodAdder.equals("Y")) {
                        System.out.print(("Calorie per serving of " + foodName + ":"));
                        double calorieAdder = in.nextDouble();
                        printStatement = lifestyleTracker1.addFood(foodName, calorieAdder);
                        printStatement += "\n";
                        printStatement += lifestyleTracker1.eat(foodName, servings);
                        System.out.println(printStatement);
                    } else if (foodAdder.equals("N")) {
                        continue;
                    }
                }
            } else if (input.equals("Perform")) {
                String activityName = in.next();
                double hours = in.nextDouble();
                printStatement = lifestyleTracker1.perform(activityName, hours);
                System.out.println(printStatement);
                if (lifestyleTracker1.activityGetter(activityName)==null) {
                    System.out.print("Do you want to add " + activityName + " to the database (Y/N):");
                    String activityAdder = in.next();
                    if (activityAdder.equals("Y")) {
                        System.out.print(("Calorie per hour of " + activityName + ":"));
                        double calorieAdder = in.nextDouble();
                        printStatement = lifestyleTracker1.addActivity(activityName, calorieAdder);
                        printStatement += "\n";
                        printStatement += lifestyleTracker1.perform(activityName, hours);
                        System.out.println(printStatement);
                    } else if (activityAdder.equals("N")) {
                        continue;
                    }
                }
            } else if (input.equals("Record")) {
                String recordType = in.next();
                if (recordType.equals("Food")) {
                    printStatement = lifestyleTracker1.foodRecordViewer();
                    System.out.println(printStatement);
                    System.out.print("Would you like to edit database (Y/N):");
                    String editDatabasePrompt = in.next();
                    if (editDatabasePrompt.equals("Y")) {
                        System.out.print("Index of Food:");
                        int foodIndex = in.nextInt();
                        while (true) {
                            System.out.print(
                                    "Editing Food Index " + foodIndex + " (Name/Calories/Servings/Delete/Quit):");
                            String choice = in.next();
                            if (choice.equals("Quit")) {
                                break;
                            } else if (choice.equals("Name")) {
                                System.out.print("Replace Food Index " + foodIndex + "'s Name With:");
                                String replaceName = in.next();
                                printStatement = lifestyleTracker1.editFoodRecordName(foodIndex, replaceName);
                                System.out.println(printStatement);
                            } else if (choice.equals("Calories")) {
                                System.out.print("Replace Food Index " + foodIndex + "'s Calories With:");
                                double replaceCalories = in.nextDouble();
                                printStatement = lifestyleTracker1.editFoodRecordCalories(foodIndex, replaceCalories);
                                System.out.println(printStatement);
                            } else if (choice.equals("Servings")) {
                                System.out.print("Replace Food Index " + foodIndex + "'s Servings With:");
                                double replaceServings = in.nextDouble();
                                printStatement = lifestyleTracker1.editFoodRecordServings(foodIndex, replaceServings);
                                System.out.println(printStatement);
                            } else if (choice.equals("Delete")) {
                                System.out
                                        .print("Are you sure you want to delete Food Record " + foodIndex + " (Y/N):");
                                String deleteChoice = in.next();
                                if (deleteChoice.equals("Y")) {
                                    printStatement = lifestyleTracker1.deleteFoodRecord(foodIndex);
                                    System.out.println(printStatement);
                                } else if (choice.equals("N")) {
                                    continue;
                                }
                            }
                        }
                    } else if (editDatabasePrompt.equals("N")) {
                        continue;
                    }
                } else if (recordType.equals("Activity")) {
                    printStatement = lifestyleTracker1.activityRecordViewer();
                    System.out.println(printStatement);
                    System.out.print("Would you like to edit database (Y/N):");
                    String editDatabasePrompt = in.next();
                    if (editDatabasePrompt.equals("Y")) {
                        System.out.print("Index of Activity:");
                        int activityIndex = in.nextInt();
                        while (true) {
                            System.out.print(
                                    "Editing Activity Index " + activityIndex + " (Name/Calories/Hours/Delete/Quit):");
                            String choice = in.next();
                            if (choice.equals("Quit")) {
                                break;
                            } else if (choice.equals("Name")) {
                                System.out.print("Replace Activity Index " + activityIndex + "'s Name With:");
                                String replaceName = in.next();
                                printStatement = lifestyleTracker1.editActivityRecordName(activityIndex, replaceName);
                                System.out.println(printStatement);
                            } else if (choice.equals("Calories")) {
                                System.out.print("Replace Activity Index " + activityIndex + "'s Calories With:");
                                double replaceCalories = in.nextDouble();
                                printStatement = lifestyleTracker1.editActivityRecordCalories(activityIndex,
                                        replaceCalories);
                                System.out.println(printStatement);
                            } else if (choice.equals("Hours")) {
                                System.out.print("Replace Activity Index " + activityIndex + "'s Hours With:");
                                double replaceHours = in.nextDouble();
                                printStatement = lifestyleTracker1.editActivityRecordHours(activityIndex, replaceHours);
                                System.out.println(printStatement);
                            } else if (choice.equals("Delete")) {
                                System.out.print(
                                        "Are you sure you want to delete Activity Record " + activityIndex + " (Y/N):");
                                String deleteChoice = in.next();
                                if (deleteChoice.equals("Y")) {
                                    printStatement = lifestyleTracker1.deleteActivityRecord(activityIndex);
                                    System.out.println(printStatement);
                                } else if (choice.equals("N")) {
                                    continue;
                                }
                            }
                        }
                    } else if (editDatabasePrompt.equals("N")) {
                        continue;
                    }
                }
            }

        }
        printStatement = lifestyleTracker1.report();
        System.out.println(printStatement);
    }
}
