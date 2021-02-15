	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;
	
	

public class PaymentDAO {
	
	private PaymentDAO () {}
	private static PaymentDAO  instance=new PaymentDAO ();
	public static PaymentDAO  getInstance() {
		return instance;
	}

		private void release1(Connection conn, PreparedStatement ps) {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		public boolean payment(MemberDTO member,PaymentDTO payment) {
		
		boolean ok = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(dbURL, "system", "green1234");

			String sql = "insert into payment(id,name,kardname,kardnumber,yymm,cvc) values(?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, member.getId());
			ps.setString(2, member.getName());
			ps.setString(3, payment.getkardname());
			ps.setString(4, payment.getkardnumner());
			ps.setString(5, payment.getyymm());
			ps.setString(6, payment.getcvc());
			int result = ps.executeUpdate();
			System.out.println(result);
			if (result == 1)
				ok = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			release1(conn, ps);
		}
		return ok;
	}

	@SuppressWarnings("unused")
	private void release(Connection conn, PreparedStatement ps) {
		// TODO Auto-generated method stub
		
	}

}
