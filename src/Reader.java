/*
 * File format
 * 
 * - store
 * {NAME}, {QUANTITY}, {ITEM NAME}, {DESCRIPTION}, {VALUE}, {EXPIRATION IF EXPIRES}, ...
 * 
 * - player
 * {STARTING MONEY}, {QUANTITY}, {ITEM NAME}, {DESCRIPTION}, {VALUE}, {EXPIRATION IF EXPIRES}, ...
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Reader {
    private static boolean storeRead;
    private static boolean playerRead;

    static {
        storeRead = false;
        playerRead = false;
    }

    public static App read(String filePath) {
        File file = new File(filePath);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }

        Seller store = null;
        Player player = null;

        String line = "";
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (!line.isEmpty() && line.charAt(0) == '-') {
                if (line.endsWith("store")) {
                    store = readStore(scanner);
                } else if (line.endsWith("player")) {
                    player = readPlayer(scanner);
                }
            }
        }

        if (!storeRead || !playerRead) {
            String msg = "Reader read status: ";
            msg += String.format("Store: %b, Player %b", storeRead, playerRead);
            System.err.println(msg);
            System.err.println("Exiting application");
            System.exit(0);
        }

        return new App(player, store);
    }

    // line format: {NAME}, {QUANTITY}, {ITEM NAME}, {DESCRIPTION}, {VALUE}, {EXPIRATION IF EXPIRES}, ...
    private static Seller readStore(Scanner sc) {
        if (Reader.storeRead) {
            System.err.println("Store written twice or more in data file");
            System.exit(0);
        }
        Reader.storeRead = true;

        String[] data = sc.nextLine().split(",");
        String name = data[0].trim();
        Arrays.copyOfRange(data, 1, data.length);
        ArrayList<ItemInterface> startingItems = readStartingItems(data);
        Inventory startingInventory = new Inventory(startingItems);
        Seller store = new Seller(name, startingInventory);
        return store;
    }

    // line format: {STARTING MONEY}, {QUANTITY}, {ITEM NAME}, {DESCRIPTION}, {VALUE}, {EXPIRATION IF EXPIRES}, ...
    private static Player readPlayer(Scanner sc) {
        if (Reader.playerRead) {
            System.err.println("Player written twice or more in data file");
            System.exit(0);
        }
        Reader.playerRead = true;

        String name = System.getProperty("user.name");
        String[] data = sc.nextLine().split(",");
        double startingMoney = Double.valueOf(data[0].trim());
        ArrayList<ItemInterface> startingItems = readStartingItems(data);
        Inventory startingInventory = new Inventory(startingItems);
        return new Player(name, startingMoney, startingInventory);
    }

    // {QUANTITY}, {ITEM NAME}, {DESCRIPTION}, {VALUE}, {EXPIRATION IF EXPIRES}
    private static ArrayList<ItemInterface> readStartingItems(String[] data) {
        ArrayList<ItemInterface> items = new ArrayList<>();

        for (int i = 1; i < data.length; i += 5) {
            for (int qty = Integer.valueOf(data[i].trim()); qty > 0; qty--) {
                items.add(ItemReader.readStartingItem(Arrays.copyOfRange(data, i + 1, i + 5)));
            }
        }

        return items;
    }

}
