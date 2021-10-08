package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import com.techelevator.Machine;

// Vending Machine Command Line Interface application
public class VendingMachineCLI {

	public static void main(String[] args) {
		// Make some objects here!
		Scanner keyboard = new Scanner(System.in);
		Machine machine = new Machine();

		machine.restock();

		String menuOneInput;
		int menuOneChoice = 0;

		while (menuOneChoice != 3) {
			do {
				System.out.println("(1) Display Vending Machine Items");
				System.out.println("(2) Purchase");
				System.out.println("(3) Exit");
				System.out.println("Please select an option: ");
				menuOneInput = keyboard.nextLine();
				menuOneChoice = Integer.parseInt(menuOneInput);

			} while (menuOneChoice < 1 || menuOneChoice > 3);

			if (menuOneChoice == 1) {
				machine.printInventory();
			} else if (menuOneChoice == 2) {
				// go to purchase menu
				String menuTwoInput;
				int menuTwoChoice = 0;
				while (menuTwoChoice != 3) {
					do {
						System.out.println("1 Feed Money");
						System.out.println("2 Select Product");
						System.out.println("3 Finish Transaction");
						System.out.println("Current Money Provided: $" + String.format("%.2f", machine.getCurrentBalance()));
						menuTwoInput = keyboard.nextLine();
						menuTwoChoice = Integer.parseInt(menuTwoInput);
					} while (menuTwoChoice < 1 || menuTwoChoice > 3);

					if (menuTwoChoice == 1) {
						System.out.println("Please enter the amount you would like to add: ");
						String userAddedFunds = keyboard.nextLine();
						int addedFunds = Integer.parseInt(userAddedFunds);
						machine.addToBalance(addedFunds);
					} else if (menuTwoChoice == 2) {
						machine.printInventory();
						System.out.println("Please enter the location of the product you would like to buy: ");
						String itemLocation = keyboard.nextLine();
						machine.sellItem(itemLocation);
					} else if (menuTwoChoice == 3) {
						machine.change();
					}
				}
			} else if (menuOneChoice == 3) {
				System.exit(0);
			}
		}
	}
}
