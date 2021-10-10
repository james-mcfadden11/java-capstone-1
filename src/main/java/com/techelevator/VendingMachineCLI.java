package com.techelevator;

import java.util.Scanner;

public class VendingMachineCLI {
	public static void main(String[] args) {
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

				try {
					menuOneInput = keyboard.nextLine();
					menuOneChoice = Integer.parseInt(menuOneInput);
				} catch (NumberFormatException e) {
					System.out.println("Please enter a digit 1-3.");
				}

			} while (menuOneChoice < 1 || menuOneChoice > 3);

			if (menuOneChoice == 1) {
				machine.printInventory();
			} else if (menuOneChoice == 2) {
				String menuTwoInput;
				int menuTwoChoice = 0;
				while (menuTwoChoice != 3) {
					do {
						System.out.println("(1) Feed Money");
						System.out.println("(2) Select Product");
						System.out.println("(3) Finish Transaction");
						System.out.println("Current Money Provided: $" + String.format("%.2f", machine.getCurrentBalance()));

						try {
							menuTwoInput = keyboard.nextLine();
							menuTwoChoice = Integer.parseInt(menuTwoInput);
						} catch (NumberFormatException e) {
							System.out.println("Please enter a digit 1-3.");
						}

					} while (menuTwoChoice < 1 || menuTwoChoice > 3);

					if (menuTwoChoice == 1) {
						System.out.println("Please enter the amount you would like to add: ");
						try {
							String userAddedFunds = keyboard.nextLine();
							int addedFunds = Integer.parseInt(userAddedFunds);
							machine.addToBalance(addedFunds);
						} catch (NumberFormatException e){
							System.out.println("Please enter a dollar amount of $1, 2$, $5, $10, $20, $50, or $100");
						}
					} else if (menuTwoChoice == 2) {
						machine.printInventory();
						System.out.println("Please enter the location of the product you would like to buy: ");
						String itemLocation = keyboard.nextLine();
						System.out.println(machine.sellItem(itemLocation));
					} else if (menuTwoChoice == 3) {
						System.out.println(machine.change());
					} else {
						System.out.println("Invalid selection - please choose again.");
					}
				}
			} else if (menuOneChoice == 3) {
				System.exit(0);
			} else {
				System.out.println("Invalid selection - please choose again.");
			}
		}
	}
}
