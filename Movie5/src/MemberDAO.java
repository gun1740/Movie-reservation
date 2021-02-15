

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDAO {
	private void release(Connection conn, PreparedStatement ps) {
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

	private void release(Connection conn, PreparedStatement ps, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		release(conn, ps);
	}

	public boolean joinMember(MemberDTO member) {
		// 회원 가입하기
		boolean ok = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(dbURL, "system", "green1234");

			String sql = "insert into member(id,pwd,name,phone,addr,islogin) values(?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, member.getId());
			ps.setString(2, member.getPwd());
			ps.setString(3, member.getName());
			ps.setString(4, member.getPhone());
			ps.setString(5, member.getAddr());
			ps.setString(6, member.getLogin());
			int result = ps.executeUpdate();
			if (result == 1)
				ok = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			release(conn, ps);
		}
		return ok;
	}

	public MemberDTO loginMember(MemberDTO member) {
		// 로그인 하기
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(dbURL, "system", "green1234");
			// 표 선택 초기화 해주기, 시, 구, 동
			String sql = "update city set selection = 'no' ";
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();

			sql = "update local set selection = 'no' ";
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();

			sql = "update business set selection = 'no' ";
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			// 로그인 체크
			sql = "select * from member where id = ? and pwd = ? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, member.getId());
			ps.setString(2, member.getPwd());
			rs = ps.executeQuery();
			if (rs.next()) {
				member.setLogin("logIn");
				member.setName(rs.getString("name"));
				member.setPhone(rs.getString("phone"));
				member.setAddr(rs.getString("addr"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			release(conn, ps, rs);
		}
		return member;
	}

	public MemberDTO logoutMember(MemberDTO member) {
		// 로그아웃 하기
		Connection conn = null;
		PreparedStatement ps = null;

		String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(dbURL, "system", "green1234");

			String sql = "update member set isLogin = 'logOut' where isLogin = 'logIn' ";
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			member.setLogin("logOut");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			release(conn, ps);
		}
		return member;
	}

	public void reservateMember(MemberDTO member) {
		// 예약 최종 정보 넣기 id,sit,movieNo, busName,cityName,localName
		Connection conn = null;
		PreparedStatement ps = null;

		String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(dbURL, "system", "green1234");

			String sql = "insert into reservation(id, movieNo, sit, startTime, cityName, localName, busName) values(?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql); 
			for (int i = 0; i < member.getSit().size(); i++) {
				ps.setString(1, member.getId());
				ps.setString(2, ""+(Integer.parseInt(member.getMovie())+1));
				ps.setString(3, member.getSit().get(i));
				ps.setString(4, member.getStartTime());
				ps.setString(5, member.getSi());
				ps.setString(6, member.getGu());
				ps.setString(7, member.getDong());
				System.out.println(member.toString());
				ps.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			release(conn, ps);
		}
	}

}
