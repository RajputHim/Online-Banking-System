package com.masai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.masai.bean.Customer;
import com.masai.exceptions.CustomerException;
import com.masai.utility.DBUtil;
import com.mysql.cj.protocol.Resultset;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public int registerCustomer(String email, String pw) {

//		String message = "Not registered";
		int x = 0;
		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("insert into customer_details(email, password) values(?,?)");

			ps.setString(1, email);
			ps.setString(2, pw);

			x = ps.executeUpdate();

//			if (x > 0) {
//				message = "Registered Successfully";
//				addEmail(email);
//			}

		} catch (SQLException e) {
//			message = e.getMessage();
			e.printStackTrace();
		}

		return x;
	}

	@Override
	public String createAccount(Customer customer) {
		String message = "Account cannot be created";

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn
					.prepareStatement("insert into customer_details values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
//			(customer_id,first_name,last_name,gender,age,mob,dob,city,account_type,account_num,balance)
			ps.setInt(1, customer.getCustomer_id());
			ps.setString(2, customer.getFirst_name());
			ps.setString(3, customer.getLast_name());
			ps.setString(4, customer.getGender());
			ps.setInt(5, customer.getAge());
			ps.setString(6, customer.getMob());
			ps.setString(7, customer.getEmail());
			ps.setString(8, customer.getDob());
			ps.setString(9, customer.getCity());
			ps.setString(10, customer.getAccount_type());
			ps.setInt(11, customer.getAccount_num());
			ps.setInt(12, customer.getBalance());
			ps.setString(13, customer.getPassword());

			int x = ps.executeUpdate();

			if (x > 0) {
				message = "Registered Successfully";
			}

		} catch (SQLException e) {
			message = e.getMessage();
		}

		return message;
	}

	@Override
	public Customer loginCustomer(String email, String pw) throws CustomerException {

		Customer user = null;

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn
					.prepareStatement("select * from customer_details where email = ? and password = ?");

			ps.setString(1, email);
			ps.setString(2, pw);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int cid = rs.getInt("Customer_id");
				String fName = rs.getString("first_name");
				String lName = rs.getString("last_name");
				String gender = rs.getString("gender");
				int age = rs.getInt("age");
				String mob = rs.getString("mob");
				String em = rs.getString("email");
				String dob = rs.getString("dob");
				String city = rs.getString("city");
				String aType = rs.getString("account_type");
				int aNumm = rs.getInt("account_num");
				int balance = rs.getInt("balance");
				String password = rs.getString("password");

				Customer c1 = new Customer(cid, fName, lName, gender, age, mob, em, dob, city, aType, aNumm, balance,
						password);

			} else {
				throw new CustomerException("Invalid credentials..");
			}

		} catch (Exception e) {
			throw new CustomerException(e.getMessage());
		}

		return user;
	}

}
