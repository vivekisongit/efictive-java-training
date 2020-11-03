package in.conceptarchitect.banking.repository;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Properties;

import in.conceptarchitect.banking.BankAccount;
import in.conceptarchitect.banking.CurrentAccount;
import in.conceptarchitect.banking.OverDraftAccount;
import in.conceptarchitect.banking.SavingsAccount;

public class JdbcAccountRepository implements AccountRepository {

	AccountRepository baseRepository;

	public JdbcAccountRepository(AccountRepository baseRepository) {
		super();
		this.baseRepository = baseRepository;

		load(); // try to fetch existing data

	}

	public JdbcAccountRepository() {
		this(new HashmapAccountRepository());
	}

	@Override
	public int addAccount(BankAccount account) {
		// TODO Auto-generated method stub
		int id = baseRepository.addAccount(account);
		save();
		return id;

	}

	@Override
	public BankAccount getAccountById(int accountNumber) {
		// TODO Auto-generated method stub
		return baseRepository.getAccountById(accountNumber);
	}

	@Override
	public void removeAccount(int accountNumber) {
		// TODO Auto-generated method stub
		baseRepository.removeAccount(accountNumber);
		save();

	}

	@Override
	public Collection<BankAccount> getAllAccounts() {
		// TODO Auto-generated method stub
		return baseRepository.getAllAccounts();
	}

	public void load() {
		Connection connection = null;
		try {
			connection = getDBConnection();
			Statement statement = connection.createStatement();

			// Step 3 ---> execute a query
			ResultSet rs = statement.executeQuery("select * from BankAccounts");
			// Step 4 ---> loop through the result set

			while (rs.next()) { // moves to the next result and return if there is a next result. first call
								// takes to first result

				String accountType = rs.getString("account_type");
				String name = rs.getString("name");
				int accountNumber = rs.getInt("account_number");
				double balance = rs.getDouble("balance");
				String accountPassword = rs.getString("password");
				double odLimit = rs.getDouble("odLimit");

				BankAccount account = null;
				switch (accountType) {
				default:
				case "SavingsAccount":
					account = new SavingsAccount(name, accountPassword, balance);
					break;
				case "CurrentAccount":
					account = new CurrentAccount(name, accountPassword, balance);
					break;
				case "OverdraftAccount":
					account = new OverDraftAccount(name, accountPassword, balance);
					((OverDraftAccount) account).setOdLimit(odLimit);
					break;
				}

				account.setAccountNumber(accountNumber);

				baseRepository.addAccount(account);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				connection.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

	}

	@Override
	public void save() {
		try {
			Connection connection = getDBConnection();
			for (BankAccount account : getAllAccounts()) {
				double odLimit = 0;
				if (account instanceof OverDraftAccount) {
					OverDraftAccount oda = (OverDraftAccount) account;
					odLimit = oda.getOdLimit();
				}
				String query = "update bankingdb.bankaccounts set account_type=?, name=?,password=?,balance=?, odLimit=? where account_number = ?";
				PreparedStatement preparedStmt = connection.prepareStatement(query);
				preparedStmt.setString(1, account.getClass().getSimpleName().toLowerCase());
				preparedStmt.setString(2, account.getName());
				preparedStmt.setString(3, account.getEncryptedPassword());
				preparedStmt.setDouble(4, account.getBalance());
				preparedStmt.setDouble(5, odLimit);
				preparedStmt.setInt(6, account.getAccountNumber());
				preparedStmt.execute();

			}
			connection.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private Connection getDBConnection() {
		Properties prop = new Properties();
		try {
			prop.load(new FileReader("src/config/app.properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		String url = prop.getProperty("DB_URL"); // "jdbc:mysql://localhost/bankingdb"; //database access url
		String user = prop.getProperty("DB_USER"); // database access user
		String password = prop.getProperty("DB_PASSWORD"); // database access password
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {

		}
		return connection;
	}

}
