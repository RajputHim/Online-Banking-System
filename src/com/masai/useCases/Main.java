package com.masai.useCases;

import java.util.Scanner;

import com.masai.bean.Customer;
import com.masai.dao.CustomerDao;
import com.masai.dao.CustomerDaoImpl;
import com.masai.exceptions.CustomerException;

public class Main {
	static CustomerDao dao = new CustomerDaoImpl();

	public static void log() {
		Scanner input = new Scanner(System.in);

		System.out.println("--------------*******----------------");
		System.out.println("Enter Email-ID: ");
		String email = input.nextLine();

		System.out.println("Enter Password : ");
		String pw = input.nextLine();

		try {
			Customer user = dao.loginCustomer(email, pw);
		} catch (CustomerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		if (result > 1) {
//			System.out.println("Login Successful...");
//		} else {
//			System.out.println("Invalid Credentials.....");
//		}
//
//		System.out.println(result);
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.println("Please enter your choice : ");

		System.out.println("1). Login as Customer");

		System.out.println("2). Login as Accountant");

		System.out.println("3). New customer? Register");

		int choice = input.nextInt();
		input.nextLine();

		switch (choice) {
		case 1: {
			System.out.println("Logging in as customer");
//			log();
			break;
		}
		case 2: {
			System.out.println("Logging in as accountant");

			System.out.println("--------------*******----------------");
			System.out.println("Enter Email-ID: ");
			String email = input.nextLine();

			System.out.println("Enter Password : ");
			String pw = input.nextLine();

			if (email.equals("123@gmail.com") && pw.equals("1234")) {
				System.out.println("Login successful...");
				System.out.println("----------------******----------------");
				while (true) {

					System.out.println("Enter your choice");

					System.out.println("1).Add new account");
					System.out.println("2).Edit account");
					System.out.println("3).Remove account using account number");
					System.out.println("4).View account details using account number");
					System.out.println("5).View all account details");
					System.out.println("6).Exit");

					System.out.println("-------------------------");
					int opt = input.nextInt();

					if (opt == 5) {
						System.out.println("Thank you...");
						break;
					}

					switch (opt) {
					case 1: {
						System.out.println("Please enter all the details asked below...");

						System.out.println("Enter Customer Id :");
						int cid = input.nextInt();
						input.nextLine();

						System.out.println("Enter First Name :");
						String fname = input.nextLine();

						System.out.println("Enter Last Name :");
						String lname = input.nextLine();

						System.out.println("Enter Gender:");
						String gender = input.nextLine();

						System.out.println("Enter Age :");
						int age = input.nextInt();
						input.nextLine();

						System.out.println("Enter Mobile Number :");
						String mob = input.nextLine();

						System.out.println("Enter Email-ID :");
						String em = input.nextLine();

						System.out.println("Enter Date of Birth :");
						String dob = input.nextLine();

						System.out.println("Enter  City:");
						String city = input.nextLine();

						System.out.println("Enter Account type :");
						String aType = input.nextLine();

						System.out.println("Enter Account Number :");
						int aNum = input.nextInt();

						System.out.println("Enter Deposit amount :");
						int bal = input.nextInt();
						input.nextLine();

						System.out.println("Enter password :");
						String pass = input.nextLine();

						Customer c = new Customer(cid, fname, lname, gender, age, mob, email, dob, city, aType, aNum,
								bal, pass);

						String res = dao.createAccount(c);
						System.out.println(res);
						break;
					}
					case 2: {
						System.out.println("Enter Account Number :");
						int account = input.nextInt();

						break;
					}

					default:
						System.out.println("Invalid options...");
					}

				}

			} else {
				System.out.println("Invalid Credentials....");
			}
			break;
		}
		case 3:

			System.out.println("Enter Email-ID: ");
			String email = input.nextLine();

			System.out.println("Enter Password : ");
			String pw = input.nextLine();

//			CustomerDao dao = new CustomerDaoImpl();

			int result = dao.registerCustomer(email, pw);

			if (result > 1) {
				System.out.println("Registration Successful...");
				System.out.println("Please login to continue...");
				log();
			} else {
				System.out.println("Registration failed.....");
			}
			break;
		default:
			System.out.println("Please enter valid option number");
		}

	}

}
