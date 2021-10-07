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

			switch (menuOneChoice) {
				case 1:
					machine.printInventory();
					break;
				case 2:
					// go to purchase menu
					String menuTwoInput;
					int menuTwoChoice = 0;
					while (menuTwoChoice != 3) {
						do {
							System.out.println("1 Feed Money");
							System.out.println("2 Select Product");
							System.out.println("3 Finish Transaction");
							System.out.println("Current Money Provided: $" + machine.getCurrentBalance());
							menuTwoInput = keyboard.nextLine();
							menuTwoChoice = Integer.parseInt(menuTwoInput);
						} while (menuTwoChoice < 1 || menuTwoChoice > 3);

						switch (menuTwoChoice) {
							case 1:
								System.out.println("Please enter the amount you would like to add: ");
								String userAddedFunds = keyboard.nextLine();
								int addedFunds = Integer.parseInt(userAddedFunds);
								machine.addToBalance(addedFunds);
								break;
							case 2:
								machine.printInventory();
								System.out.println("Please enter the location of the product you would like to buy: ");
								String itemLocation = keyboard.nextLine();
								machine.sellItem(itemLocation);
								break;
							case 3:
								machine.change();
								break;
						}

					}
					System.out.println("broke out of menu2 while loop");
				case 3:
					System.out.println("inside case 3 under menu1 while loop - exiting");
					System.exit(0);
					break;
			}
		}
	}
}
