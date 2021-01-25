package jdbc_task1_25_1_2021;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import com.mysql.jdbc.ResultSet;

public class Main {

	public static void main(String[] args) {
		Bean s = new Bean();
		Scanner scan = new Scanner(System.in);
		DAO add = new DAO();
//		s.setItemid(1);
//		s.setItemName("item1");
//		s.setItemPrice(200.00);
//		add.insertItem(s);
		while(true) {
			System.out.println("JDBC TASK1:");
			System.out.println("1. insert item details..");
			System.out.println("2. display item details..");
			System.out.println("3. get the total cost..");
			System.out.println("4. exit");
			int ch = scan.nextInt();
			switch(ch) {
			case 1:
				System.out.println("Item ID");
				int id = scan.nextInt();
				System.out.println("Item Name");
				String name = scan.next();
				System.out.println("Item Price");
				double price = scan.nextDouble();
				s.setItemid(id);
				s.setItemName(name);
				s.setItemPrice(price);
				int i = add.insertItem(s);
				if(i > 0)
					System.out.println("Item Inserted");
				else
					System.out.println("Item insertion failed");
				break;
			case 2:
				add.displayItem();
				break;
			case 3:
				System.out.println("Receiving the total cost from database....");
				add.totalCost();
				break;
			case 4:
				System.exit(0);
			default:
				System.out.println("Invalid Choice!");
			}
		}

	}

}

class Bean{
	private int itemid;
	private String itemname;
	private double itemprice;
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	public void setItemName(String itemname) {
		this.itemname = itemname;
	}
	public void setItemPrice(double d) {
		this.itemprice = d;
	}
	public int getItemid() {
		return itemid;
	}
	public String getItemName() {
		return itemname;
	}
	public double getItemPrice() {
		return itemprice;
	}
}

class DBUtil {
	
	final static String forNameURL = "com.mysql.jdbc.Driver";
	final static String dBURL = "jdbc:mysql://localhost:3306/student";
	final static String username = "root";
	final static String password = "root";
	public static Connection DBConnection() throws SQLException, ClassNotFoundException {
		Class.forName(forNameURL);
		Connection con = DriverManager.getConnection(dBURL, username, password);
		return con;
	}
}

class DAO {
	public int insertItem(Bean b) {
		try {
			Connection con = DBUtil.DBConnection();
			PreparedStatement statement = con.prepareStatement("insert into sales values(?, ?, ?)");
			statement.setInt(1,  b.getItemid());
			statement.setString(2,  b.getItemName());
			statement.setDouble(3, b.getItemPrice());
			int i = statement.executeUpdate();
			return i;
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	public void displayItem() {
		try {
			Connection con = DBUtil.DBConnection();
			PreparedStatement statement = con.prepareStatement("select * from sales");
			ResultSet i = (ResultSet) statement.executeQuery();
			System.out.println("ItemNo\t\tItemName\tItemPrice");
			while(i.next()) {
				System.out.print(i.getInt(1));
				System.out.print("\t\t"+i.getString(2));
				System.out.print("\t\t"+i.getString(3));
				System.out.println();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void totalCost() {
		try {
			Connection con = DBUtil.DBConnection();
			PreparedStatement statement = con.prepareStatement("select sum(itemprice) from sales");
			ResultSet i = (ResultSet) statement.executeQuery();
//			System.out.println("TOTAL COST: ");
			i.next();
		    String sum = i.getString(1);
//		    System.out.println(sum);
		    double value = Double.parseDouble(sum);
		    System.out.println(value);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}


