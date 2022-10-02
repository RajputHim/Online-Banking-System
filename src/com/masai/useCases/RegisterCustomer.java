package com.masai.useCases;

import java.util.Scanner;

import com.masai.dao.CustomerDao;
import com.masai.dao.CustomerDaoImpl;

public class RegisterCustomer {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		System.out.println("Enter Email-ID: ");
		String email = input.nextLine();

		System.out.println("Enter Password : ");
		String pw = input.nextLine();

		CustomerDao dao = new CustomerDaoImpl();

		int result = dao.registerCustomer(email, pw,0);
		System.out.println(result);

	}

}
