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
		int menuOneChoice;

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
				// sout (vending machine list)
				//machine.printInventory()
			case 2:
				// go to purchase menu
				String menuTwoInput;
				int menuTwoChoice;
				do {
					System.out.println("Feed Money");
					System.out.println("Select Product");
					System.out.println("Finish Transaction");
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
						// add to balance
					case 2:
						//display list, user input for selection
						//machine.printInventory()
						//user.makeSelection
							// - machine.deductFromBalance
							// - machine.deductFromProductInventory()
					case 3:
						// give change
						//exit
				}
			case 3:
				// System.exit
		}


	}
}
