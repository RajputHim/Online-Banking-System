package com.masai.useCases;

import java.util.List;
import java.util.Scanner;

import com.masai.bean.Customer;
import com.masai.bean.TransactionHistory;
import com.masai.dao.CustomerDao;
import com.masai.dao.CustomerDaoImpl;
import com.masai.exceptions.CustomerException;

public class Main {
	static CustomerDao dao = new CustomerDaoImpl();

	public static int log() {
		Scanner input = new Scanner(System.in);

		System.out.println("--------------*******----------------");
		System.out.println("Enter Email-ID: ");
		String email = input.nextLine();

		System.out.println("Enter Password : ");
		String pw = input.nextLine();

		try {
			Customer user = dao.loginCustomer(email, pw);
			if (user == null) {
				System.out.println("Invalid credentials...");
				System.out.println("--------------*******----------------");
				return 0;
			}

			while (true) {
				System.out.println("Please select option from below");
				System.out.println("1).Transfer money to other account");
				System.out.println("2).Transaction history");
				System.out.println("3).Check Balance");
				System.out.println("4).Exit");
				int choice = input.nextInt();
				if (choice == 4) {
					System.out.println("Thank You!");
					break;
				}
				switch (choice) {
				case 1:
					System.out.println("Please enter receivers account number..");
					int rAcc = input.nextInt();

					System.out.println("Enter amount..");
					int amount = input.nextInt();

					if (amount <= user.getBalance()) {

						/* Transferring money from current users account */
						String mes = dao.transferMoney(rAcc, amount, user);
						System.out.println(mes);

						/* Deducting money from current users account */
						dao.deductMoney(user.getAccount_num(), amount);
						System.out.println("=================================================");

					} else {
						System.out.println("You don't have sufficient balance...");
						System.out.println("Thank You...");
						System.out.println("=================================================");
					}

					break;
				case 2:
					System.out.println("Showing you all transaction details...");
					System.out.println("=================================================");
					System.out.println(user.getCustomer_id());
					List<TransactionHistory> tL = dao.transactionHistory(user.getCustomer_id());

					int count = 1;
					for (TransactionHistory el : tL) {
						System.out.println("----------------------------------------------------");
						System.out.println("Transaction - " + count++);
						System.out.println("----------------------------------------------------");
						System.out.println("Customer-ID : " + el.getCid());
						System.out.println("Recievers Account Number : " + el.getRecievers_acc());
						System.out.println("Date of Transaction : " + el.getDot());
						System.out.println("Sent money from Account : " + el.getSenders_acc());
						System.out.println("Transaction amount : " + el.getTransaction_amount());
						System.out.println("Remaining balance after transaction : " + el.getBalance());
					}
					System.out.println("----------------******----------------");
					break;

				case 3:
					System.out.println("Available balance : " + user.getBalance());
					System.out.println("----------------******----------------");
					break;
				default:
					System.out.println("Please enter valid option");
					break;
				}
			}

		} catch (CustomerException e) {

			e.printStackTrace();
		}

		return 0;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		while (true) {

			System.out.println("Please enter your choice : ");

			System.out.println("1). Login as Customer");

			System.out.println("2). Login as Accountant");

//			System.out.println("3). New customer? Register");

			System.out.println("4). Exit");

			int choice = input.nextInt();
			input.nextLine();

			if (choice == 4) {
				System.out.println("Thank You!");
				break;
			}

			switch (choice) {
			case 1: {
				System.out.println("Logging in as customer");
				log();
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
						System.out.println("2).Edit account using account number");
						System.out.println("3).Remove account using account number");
						System.out.println("4).View account details using account number");
						System.out.println("5).View all account details");
						System.out.println("6).Exit");

						System.out.println("----------------******----------------");
						int opt = input.nextInt();
						input.nextLine();

						if (opt == 6) {
							System.out.println("Thank you...");
							System.out.println("----------------******----------------");
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

							Customer c = new Customer(cid, fname, lname, gender, age, mob, em, dob, city, aType, aNum,
									bal, pass);

							String res = dao.createAccount(c);
							System.out.println(res);
							System.out.println("----------------******----------------");
							break;
						}
						case 2: {

							while (true) {
								System.out.println("What detail you want to edit?");
								System.out.println("1). Name update");
								System.out.println("2). Mobile number update");
								System.out.println("3). Email-ID update");
								System.out.println("4). Change password");
								System.out.println("5). Exit");

								int option = input.nextInt();
								input.nextLine();

								if (option == 5) {
									break;
								}

								String message = "Something went wrong!!";
								System.out.println("Enter Account Number :");
								int account = input.nextInt();
								input.nextLine();

								try {
									if (option == 1) {
										System.out.println("Enter new first name : ");
										String fName = input.nextLine();

										System.out.println("Enter new last name : ");
										String lName = input.nextLine();

										message = dao.updateName(account, fName, lName);

									} else if (option == 2) {

										System.out.println("Enter new mobile number : ");
										int mob = input.nextInt();
										input.nextLine();
										message = dao.updateMobileNumber(account, mob);

									} else if (option == 3) {
										System.out.println("Enter new email id : ");
										String newEmail = input.nextLine();

										message = dao.updateEmail(account, newEmail);
									} else if (option == 4) {

										System.out.println("Enter new password : ");
										String newPw = input.nextLine();

										message = dao.updatePassword(account, newPw);

									}

								} catch (CustomerException e) {

									System.out.println(e.getMessage());
								}
								System.out.println(message);
								System.out.println("----------------******----------------");

							}
							break;
						}

						case 3: {
							System.out.println("Enter Account number : ");
							int account = input.nextInt();
							input.nextLine();

							try {
								String message = dao.removeAccount(account);
								System.out.println(message);
							} catch (CustomerException e) {
								System.out.println(e.getMessage());
							}
							System.out.println("----------------******----------------");
							break;
						}

						case 4: {
							System.out.println("Enter account number : ");
							int account = input.nextInt();
							input.nextLine();
							System.out.println("=================================================");

							try {
								Customer customer = dao.viewAccount(account);

								System.out.println("Customer id : " + customer.getCustomer_id());
								System.out.println(
										"Full Name : " + customer.getFirst_name() + " " + customer.getLast_name());

								System.out.println("Gender : " + customer.getGender());
								System.out.println("Age : " + customer.getAge());
								System.out.println("Mobile Number : " + customer.getMob());
								System.out.println("Email-ID : " + customer.getEmail());
								System.out.println("Date of Birth : " + customer.getDob());
								System.out.println("City : " + customer.getCity());
								System.out.println("Account type : " + customer.getAccount_type());
								System.out.println("Account Number : " + customer.getAccount_num());
								System.out.println("Available Balance : " + customer.getBalance());

							} catch (CustomerException e) {
								System.out.println(e.getMessage());
							}
							System.out.println("=================================================");
							break;
						}

						case 5: {
							System.out.println("=================================================");
							try {
								List<Customer> customersList = dao.viewAllAccount();

								for (Customer customer : customersList) {
									System.out.println("Customer id : " + customer.getCustomer_id());
									System.out.println(
											"Full Name : " + customer.getFirst_name() + " " + customer.getLast_name());

									System.out.println(
											"Gender : " + customer.getGender() + " Age : " + customer.getAge());
									System.out.println("Mobile Number : " + customer.getMob());
									System.out.println("Email-ID : " + customer.getEmail());
									System.out.println("Date of Birth : " + customer.getDob());
									System.out.println("City : " + customer.getCity());
									System.out.println("Account type : " + customer.getAccount_type());
									System.out.println("Account Number : " + customer.getAccount_num());
									System.out.println("Available Balance : " + customer.getBalance());

									System.out.println("=================================================");
								}

							} catch (CustomerException e) {
								System.out.println(e.getMessage());
							}
							System.out.println("----------------******----------------");
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
//			case 3:
//
//				System.out.println("Enter Email-ID: ");
//				String email = input.nextLine();
//
//				System.out.println("Enter ID Number: ");
//				int id = input.nextInt();
//				input.nextLine();
//
//				System.out.println("Enter Password : ");
//				String pw = input.nextLine();
//
//				int result = dao.registerCustomer(email, pw, id);
//
//				if (result > 0) {
//					System.out.println("Registration Successful...");
//					System.out.println("Please login to continue...");
//					log();
//				} else {
//
//					System.out.println("Registration failed.....");
//				}
//				break;
			default:
				System.out.println("Please enter valid option number");
			}
		}

	}

}
