package assn3;
/**
 * This Assignment3 class is a driver class of Bread recipe where it receives input data from the user
 * and responds to their choice of input
 * @author ELYSE NIYONAGIRA
 * @since java version "21.0.4"  */

import java.util.Scanner;

/**
 * Assignment3 is the driver class for the Bread Recipe Manager application.
 */
public class RecipeController {
    private RecipeManager recipeManager;

    /**
     * Constructs an Assignment3 instance and initialises the RecipeManager.
     */
    public RecipeController() {
        recipeManager = new RecipeManager();
    }

    /**
     * Displays the main menu and processes user input.
     */
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Elyse Niyonagira Bread Recipe Manager.");

        while (true) {
            printMenu();
            int choice = getValidMenuInput(scanner); // Validate menu input

            switch (choice) {
                case 1:
                    recipeManager.showAvailableRecipes();
                    break;
                case 2:
                    orderBread(scanner);
                    break;
                case 3:
                    printShoppingList(scanner);
                    break;
                case 4:
                    System.out.println("Exiting program.");
                    scanner.close();
                    return;
                case 0:
                    // Reprint the menu (handled by printMenu)
                    break;
            }
        }
    }

    /**
     * Prints the menu options for the user.
     */
    private void printMenu() {
        System.out.println("\nPlease select one of the following options:");
        System.out.println("1. Show available recipes.");
        System.out.println("2. Create Shopping List.");
        System.out.println("3. Print Shopping List.");
        System.out.println("4. Quit Program.");
        System.out.println("0. to reprint this menu.");
    }

    /**
     * Orders a specified quantity of a bread recipe.
     *
     * @param scanner The scanner to read user input.
     */
    private void orderBread(Scanner scanner) {
        recipeManager.showAvailableRecipes(); // Show recipes before asking for an order
        int breadNumber = getValidBreadInput(scanner); // Validate bread selection

        int currentQuantity = 0; // Track the quantity for this bread order

        while (true) {
            // Pass the currentQuantity to the method to keep track of changes
            currentQuantity = getValidQuantityInput(scanner, currentQuantity); 

            if (currentQuantity > 0) {
                recipeManager.orderBread(breadNumber - 1, currentQuantity); // Adjust index for zero-based
                System.out.println("You have ordered " + currentQuantity + " of " + recipeManager.getRecipes().get(breadNumber - 1).getName() + ".");
                break;
            }
        }
    }


    /**
     * Prints the shopping list based on the ordered breads.
     *
     * @param scanner The scanner to read user input.
     */
    private void printShoppingList(Scanner scanner) {
        System.out.print("Would you like to save the shopping list? (Y/n): ");
        String saveChoice = scanner.nextLine();
        boolean saveToFile = saveChoice.equalsIgnoreCase("Y");
        recipeManager.printShoppingList(saveToFile);
    }

    /**
     * Gets a valid integer input for the menu selection.
     *
     * @param scanner The scanner to read user input.
     * @return The valid integer input.
     */
    private int getValidMenuInput(Scanner scanner) {
        while (true) {
            System.out.print("Please enter your choice: "); // Prompt for input
            String input = scanner.nextLine(); // Read input as a string
            try {
                int number = Integer.parseInt(input); // Try to parse the input
                if (number >= 0 && number <= 4) {
                    return number; // Return valid number
                } else {
                    System.out.println("Valid input are digits from 0 to 4."); // Range check for menu
                }
            } catch (NumberFormatException e) {
                System.out.println("Please only type digits."); // Invalid number format
                System.out.println("Valid input are digits from 0 to 4.");
            }
        }
    }

    /**
     * Gets a valid integer input for selecting bread.
     *
     * @param scanner The scanner to read user input.
     * @return The valid integer input for bread selection.
     */
    private int getValidBreadInput(Scanner scanner) {
        while (true) {
            System.out.print("Which bread would you like? "); // Prompt for bread selection
            String input = scanner.nextLine(); // Read input as a string
            try {
                int number = Integer.parseInt(input); // Try to parse the input
                if (number >= 1 && number <= recipeManager.getRecipeCount()) {
                    return number; // Return valid number
                } else {
                    System.out.println("Valid input are digits from 1 to " + recipeManager.getRecipeCount() + "."); // Range check for bread
                }
            } catch (NumberFormatException e) {
                System.out.println("Please only type digits."); // Invalid number format
                System.out.println("Valid input are digits from 1 to " + recipeManager.getRecipeCount() + "."); // Range check for bread
            }
        }
    }

    /**
     * Gets a valid integer input for the quantity.
     *
     * @param scanner The scanner to read user input.
     * @return The valid integer input for quantity.
     */
    private int getValidQuantityInput(Scanner scanner, int currentQuantity) {
        while (true) {
            System.out.print("How much of this bread would you like? "); // Prompt for quantity
            String input = scanner.nextLine(); // Read input as a string

            try {
                // Try to parse the input as an integer
                int number = Integer.parseInt(input);

                // If the number is less than 1, adjust the current quantity
                if (number < 0) {
                    // Reduce the quantity
                    if (currentQuantity + number >= 0) {
                        currentQuantity += number; // Adjust the quantity
                        return currentQuantity; // Return the adjusted quantity
                    } else {
                        System.out.println("You cannot have less than 0 loaves. Please enter a valid quantity.");
                    }
                } else {
                    // Add the number to the total if it's positive
                    currentQuantity += number;
                    return currentQuantity; // Return the updated quantity
                }
            } catch (NumberFormatException e) {
                // If the input is not a valid integer, prompt the user to type only digits
                System.out.println("Please only type digits.");
            }
        }
    }


    /**
     * The main method to run the Bread Recipe Manager application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        RecipeController app = new RecipeController();
        app.displayMenu();
    }
}