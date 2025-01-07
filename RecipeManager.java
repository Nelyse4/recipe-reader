package assn3;
/**
 * This RecipeManager class manages a list of recipe that is defined and uses it to generate a shopping list
 * @author ELYSE NIYONAGIRA
 * @since java version "21.0.4"  */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * RecipeManager manages the list of recipes, loading from "recipelist.txt" file and generating shopping lists.
 */
public class RecipeManager {
    private List<Recipe> recipes; // List of recipes
    /**
     * Constructing a RecipeManager and loads recipes from the specified file.
     */
    public RecipeManager() {
        recipes = new ArrayList<>();
        loadRecipes("recipelist.txt"); // Load recipes from the text file
    }
    /**
     * Loads recipes from a file and populates the recipes list.
     *
     * @param filePath The path to the recipe file.
     */
    private void loadRecipes(String filePath) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.startsWith("Recipe ")) {
                    String recipeName = line.substring(7);
                    Recipe recipe = new Recipe(recipeName);

                    while (scanner.hasNextLine()) {
                        line = scanner.nextLine().trim();
                        if (line.isEmpty()) {
                            break; // End of the current recipe
                        }
                        String[] parts = line.split(" ");
                        if (parts.length == 2) {
                            String ingredient = parts[0];
                            float amount = Float.parseFloat(parts[1]);
                            recipe.addIngredient(ingredient, amount);
                        }
                    }
                    recipes.add(recipe);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: Recipe file not found.");
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Error: Recipe file is formatted incorrectly.");
        }
    }
    /**
     * Orders a specified quantity of a bread recipe.
     *
     * @param index   The index of the recipe in the list.
     * @param quantity The quantity to order.
     */
    public void orderBread(int index, int quantity) throws IllegalArgumentException {
        if (index < 0 || index >= recipes.size()) {
            throw new IllegalArgumentException("Invalid bread number.");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Cannot order a negative quantity.");
        }
        Recipe recipe = recipes.get(index);
        recipe.setQuantityOrdered(recipe.getQuantityOrdered() + quantity);
    }


    /**
     * Prints the shopping list based on the ordered quantities of recipes.
     *
     * @param saveToFile Whether to save the shopping list to a file.
     */
    public void printShoppingList(boolean saveToFile) {
        StringBuilder shoppingList = new StringBuilder();
        int totalEggs = 0; // To count total eggs
        float totalFlour = 0;
        float totalSugar = 0;
        float totalYeast = 0;
        float totalButter = 0;

        for (Recipe recipe : recipes) {
            if (recipe.getQuantityOrdered() > 0) {
                int quantityOrdered = recipe.getQuantityOrdered();
                
                // Calculate totals for each ingredient
                totalEggs += recipe.getIngredients().getOrDefault("eggs", 0f) * quantityOrdered;
                totalFlour += recipe.getIngredients().getOrDefault("flour", 0f) * quantityOrdered;
                totalSugar += recipe.getIngredients().getOrDefault("sugar", 0f) * quantityOrdered;
                totalYeast += recipe.getIngredients().getOrDefault("yeast", 0f) * quantityOrdered;
                totalButter += recipe.getIngredients().getOrDefault("butter", 0f) * quantityOrdered;
            }
        }

        // Generate the shopping list output
        if (totalEggs > 0) {
            shoppingList.append(totalEggs).append(" egg(s)\n");
        }
        if (totalFlour > 0) {
            shoppingList.append(totalFlour).append(" grams of flour\n");
        }
        if (totalSugar > 0) {
            shoppingList.append(totalSugar).append(" grams of sugar\n");
        }
        if (totalYeast > 0) {
            shoppingList.append(totalYeast).append(" grams of yeast\n");
        }
        if (totalButter > 0) {
            shoppingList.append(totalButter).append(" grams of butter\n");
        }

        if (shoppingList.length() == 0) {
            System.out.println("No items to display in the shopping list.");
        } else {
            System.out.println("You will need a total of:");
            System.out.print(shoppingList.toString());
            if (saveToFile) {
                saveShoppingListToFile(shoppingList.toString());
            }
        }
    }

    /**
     * Saves the shopping list to a file.
     *
     * @param shoppingList The shopping list to save.
     */
    private void saveShoppingListToFile(String shoppingList) {
        try (PrintWriter writer = new PrintWriter("shoppinglist.txt")) {
            writer.print(shoppingList);
            System.out.println("Shopping list saved to shoppinglist.txt.");
        } catch (FileNotFoundException e) {
            System.err.println("Error: Unable to save shopping list.");
        }
    }
    /**
     * Displays the available recipes.
     */
    public void showAvailableRecipes() {
        for (int i = 0; i < recipes.size(); i++) {
            System.out.println((i + 1) + ". " + recipes.get(i).getName());
        }
    }
    /**
     * Returns the number of recipes available.
     *
     * @return The size of the recipes list.
     */
    public int getRecipeCount() {
        return recipes.size();
    }
    /**
     * Returns the list of recipes.
     *
     * @return A list of Recipe objects.
     */
    public List<Recipe> getRecipes() {
        return recipes;
    }
}