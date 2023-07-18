package com.palle.dao;

import java.sql.*;
import java.util.ArrayList;

import com.palle.model.customer;

public class CustomerDao {
	private static final String dburl = "jdbc:mysql://localhost:3306/customer__management";
	private static final String dbusername = "root";
	private static final String dbpassword = "admin";

	private static Connection con = null;
	private static PreparedStatement ps = null;
	private static Statement stm = null;
	private static ResultSet rs = null;

	private static final String customerListquery = "select *from customer";
	private static final String customerInsert = "insert into customer(name,email,mobile)values(?,?,?)";
	private static final String customerEditQuery = "select *from customer where id= ?";
	private static final String customerUpdatequery = "update customer set name=?,email=?,mobile=? where  id=?";
	private static final String customerDeleteQuery = "delete from customer where id=?";

	public static void deleteCustomer(int id) 
	{
		
		try {
			con = getconnectionDef();
			ps = con.prepareStatement(customerDeleteQuery);
			ps.setInt(1, id);
			ps.executeUpdate();
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
	
		finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	public static void updatecustomer(customer c)

	{

		try {
			con = getconnectionDef();
			ps = con.prepareStatement(customerUpdatequery);

			ps.setString(1, c.getName());
			ps.setString(2, c.getEmail());
			ps.setLong(3, c.getMobile());
			ps.setInt(4, c.getId());

			ps.executeUpdate();

		} catch (SQLException e)

		{
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
	}

	public static customer editCustomer(int id) {
		customer c = null;

		try {
			con = getconnectionDef();
			ps = con.prepareStatement(customerEditQuery);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			rs.next();

			int i = rs.getInt("id");
			String n = rs.getString("name");
			String e = rs.getString("email");
			long m = rs.getLong("mobile");

			c = new customer(i, n, e, m);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();

				}
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();

					}

				}

			}
		}
		return c;
	}

	public static void addcustomer(customer customer) {

		try {
			con = getconnectionDef();

			ps = con.prepareStatement(customerInsert);
			ps.setString(1, customer.getName());
			ps.setString(2, customer.getEmail());
			ps.setLong(3, customer.getMobile());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}
	}

	public static Connection getconnectionDef() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dburl, dbusername, dbpassword);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public static ArrayList<customer> getAllcustomer() {
		ArrayList<customer> al = new ArrayList<customer>();

		try {
			con = getconnectionDef();
			stm = con.createStatement();

			rs = stm.executeQuery(customerListquery);

			while (rs.next()) {
				int i = rs.getInt("id");
				String n = rs.getString("name");
				String e = rs.getString("email");
				long m = rs.getLong("mobile");

				customer c = new customer(i, n, e, m);

				al.add(c);

			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();

				}
				if (stm != null) {
					try {
						stm.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();

					}

				}

			}
		}
		return al;
	}

//	public static customer getOneCustomer(int i) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
