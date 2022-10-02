package com.masai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.masai.bean.Customer;
import com.masai.bean.TransactionHistory;
import com.masai.exceptions.CustomerException;
import com.masai.utility.DBUtil;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public int registerCustomer(String email, String pw, int id) {

		int x = 0;
		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement(
					"insert into customer_details(customer_id,email, password, balance) values(?,?,?,?)");

			ps.setInt(1, id);
			ps.setString(2, email);
			ps.setString(3, pw);
			ps.setInt(4, x);

			x = ps.executeUpdate();

		} catch (SQLException e) {
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

				user = new Customer(cid, fName, lName, gender, age, mob, em, dob, city, aType, aNumm, balance,
						password);

			} else {
//				throw new CustomerException("Invalid credentials..");
				return null;
			}

		} catch (Exception e) {
			throw new CustomerException(e.getMessage());
		}

		return user;
	}

	@Override
	public String transferMoney(int account_num, int amount, Customer user) throws CustomerException {
		String message = "Transaction failed...";

		LocalDate date = LocalDate.now();

		String curr_date = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn
					.prepareStatement("update customer_details set balance = balance + ? where account_num = ?");

			ps.setInt(1, amount);
			ps.setInt(2, account_num);

			int res = ps.executeUpdate();
			if (res > 0) {
				message = "Transaction successful";

				try (Connection connec = DBUtil.provideConnection()) {
					PreparedStatement ts = connec.prepareStatement("insert into tran_details values(?,?,?,?,?,?)");

					int cid = user.getCustomer_id();
					int acc = user.getAccount_num();
					int bal = (user.getBalance() - amount);
					user.setBalance(user.getBalance() - amount);

					ts.setInt(1, cid);
					ts.setInt(2, account_num);
					ts.setString(3, curr_date);
					ts.setInt(4, acc);
					ts.setInt(5, amount);
					ts.setInt(6, bal);

					ts.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

		} catch (SQLException e) {
			throw new CustomerException(e.getMessage());
		}

		return message;
	}

	@Override
	public String deductMoney(int account_num, int amount) throws CustomerException {
		String message = "You don't have sufficient balance to send money";

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn
					.prepareStatement("update customer_details set balance = balance - ? where account_num = ?");

			ps.setInt(1, amount);
			ps.setInt(2, account_num);

			int res = ps.executeUpdate();

			if (res > 0) {
				message = "Deducted";
			}

		} catch (SQLException e) {
			throw new CustomerException(e.getMessage());
		}

		return message;
	}

	@Override
	public String updateName(int account_num, String fName, String lName) throws CustomerException {
		String message = "Name updated successfully!";

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement(
					"update customer_details set first_name = ?, last_name = ? where account_num = ?");

			ps.setString(1, fName);
			ps.setString(2, lName);
			ps.setInt(3, account_num);

			int res = ps.executeUpdate();
			if (res == 0) {
				message = "Please enter correct details...";
			}

		} catch (SQLException e) {
			message = e.getMessage();
		}

		return message;
	}

	@Override
	public String updateMobileNumber(int account_num, int mob) throws CustomerException {
		String message = "Mobile number updated successfully!";

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("update customer_details set mob = ? where account_num = ?");

			ps.setInt(1, mob);
			ps.setInt(2, account_num);

			int res = ps.executeUpdate();
			if (res == 0) {
				message = "Please enter correct details...";
			}

		} catch (SQLException e) {
			message = e.getMessage();
		}

		return message;
	}

	@Override
	public String updateEmail(int account_num, String email) throws CustomerException {
		String message = "Email-ID updated successfully!";

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("update customer_details set email = ? where account_num = ?");

			ps.setString(1, email);
			ps.setInt(2, account_num);

			int res = ps.executeUpdate();
			if (res == 0) {
				message = "Please enter correct details...";
			}

		} catch (SQLException e) {
			message = e.getMessage();
		}

		return message;
	}

	@Override
	public List<TransactionHistory> transactionHistory(int customer_id) throws CustomerException {

		List<TransactionHistory> data = new ArrayList<>();

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from tran_details where cid = ?");

			ps.setInt(1, customer_id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TransactionHistory tObj = new TransactionHistory();

				tObj.setCid(rs.getInt("cid"));
				tObj.setRecievers_acc(rs.getInt("recievers_acc"));
				tObj.setDot(rs.getString("dot"));
				tObj.setSenders_acc(rs.getInt("senders_acc"));
				tObj.setTransaction_amount(rs.getInt("transaction_amount"));
				tObj.setBalance(rs.getInt("balance"));

				data.add(tObj);

			}

		} catch (SQLException e) {
			throw new CustomerException(e.getMessage());
		}
		if (data.size() == 0)
			throw new CustomerException("No history found..");

		return data;
	}

	@Override
	public String updatePassword(int account_num, String pw) throws CustomerException {
		String message = "Password updated successfully!";

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn
					.prepareStatement("update customer_details set password = ? where account_num = ?");

			ps.setString(1, pw);
			ps.setInt(2, account_num);

			int res = ps.executeUpdate();
			if (res == 0) {
				message = "Please enter correct details...";
			}

		} catch (SQLException e) {
			message = e.getMessage();
		}

		return message;
	}

	@Override
	public String removeAccount(int account_num) throws CustomerException {
		String message = "Account removed!";

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("delete from customer_details where account_num = ?");

			ps.setInt(1, account_num);

			int res = ps.executeUpdate();
			if (res == 0) {
				message = "Please enter correct details...";
			}

		} catch (SQLException e) {
			message = e.getMessage();
		}

		return message;
	}

	@Override
	public Customer viewAccount(int account_num) throws CustomerException {
		Customer customer = null;

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from customer_details where account_num = ?");

			ps.setInt(1, account_num);

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

				customer = new Customer(cid, fName, lName, gender, age, mob, em, dob, city, aType, aNumm, balance,
						password);

			} else {
				return null;
			}

		} catch (Exception e) {
			throw new CustomerException(e.getMessage());
		}

		return customer;
	}

	@Override
	public List<Customer> viewAllAccount() throws CustomerException {
		List<Customer> customers = new ArrayList<>();

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from customer_details");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

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

				Customer customer = new Customer();

				customer = new Customer(cid, fName, lName, gender, age, mob, em, dob, city, aType, aNumm, balance,
						password);

				customers.add(customer);

			}

		} catch (SQLException e) {
			throw new CustomerException(e.getMessage());
		}

		if (customers.size() == 0)
			throw new CustomerException("No Customer found..");

		return customers;
	}

}
