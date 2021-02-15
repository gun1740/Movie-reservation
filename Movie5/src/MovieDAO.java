

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MovieDAO {
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

	public String[][] getArea(String area, String city) {
		// ������ �ҷ����� (��,��,��)
		String[][] arr = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int total = 0;

		String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(dbURL, "system", "green1234");
			// ���� �� ���� ���ϱ�
			String sql = "";
			if (area.equals("����")) {
				sql = "select * from city";
				ps = conn.prepareStatement(sql);
			} else if (area.equals("������")) {
				sql = "select * from local where cityName = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, city);
			} else if (area.equals("������")) {
				sql = "select * from business where localName = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, city);
			}

			rs = ps.executeQuery();
			while (rs.next()) {
				total++;
			}
			arr = new String[total][1];

			// �迭�� �� �ֱ�
			String name = "";

			if (area.equals("����")) {
				sql = "select * from city";
				name = "cityName";
				ps = conn.prepareStatement(sql);
			} else if (area.equals("������")) {
				sql = "select * from local where cityName = ?";
				name = "localName";
				ps = conn.prepareStatement(sql);
				ps.setString(1, city);
			} else if (area.equals("������")) {
				sql = "select * from business where localName = ?";
				name = "busName";
				ps = conn.prepareStatement(sql);
				ps.setString(1, city);
			}

			rs = ps.executeQuery();
			int count = 0;
			while (rs.next()) {
				arr[count][0] = rs.getString(name);
				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			release(conn, ps, rs);
		}
		return arr;
	}

	public void resetArea() {
		// ���� �ʱ�ȭ
		Connection conn = null;
		PreparedStatement ps = null;

		String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(dbURL, "system", "green1234");
			// �� ���� ���� �ʱ�ȭ
			String sql = "";
			String name = "";
			sql = "update city set selection ='no'";
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			sql = "update local set selection ='no'";
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			sql = "update business set selection ='no'";
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			release(conn, ps);
		}
	}

	public void selectArea(String area, String city) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(dbURL, "system", "green1234");
			// �� ���� ���� �ʱ�ȭ
			String sql = "";
			String name = "";
			if (area.equals("����")) {
				sql = "update city set selection ='no'";
				ps = conn.prepareStatement(sql);
				ps.executeUpdate();
				sql = "update local set selection ='no'";
				ps = conn.prepareStatement(sql);
				ps.executeUpdate();
				sql = "update business set selection ='no'";
				ps = conn.prepareStatement(sql);
				ps.executeUpdate();
			} else if (area.equals("������")) {
				sql = "update business set selection ='no'";
				ps = conn.prepareStatement(sql);
				ps.executeUpdate();
			} else if (area.equals("������")) {
			}
			// �� ���� ����
			if (area.equals("����")) {
				sql = "update city set selection ='select' where cityName = ?";
			} else if (area.equals("������")) {
				sql = "update local set selection ='select' where localName = ?";
			} else if (area.endsWith("������")) {
				sql = "update business set selection ='select' where busName = ?";
			}
			ps = conn.prepareStatement(sql);
			ps.setString(1, city);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			release(conn, ps, rs);
		}
	}

	public MemberDTO checkArea(MemberDTO member) {
		// ������ ���� �ߴ��� Ȯ���ϱ�
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(dbURL, "system", "green1234");
			// �����ߴ��� ã��
			String sql = "";
			sql = "select * from business";

			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("selection").equals("select")) {
					member.setSi(rs.getString("cityName"));
					member.setGu(rs.getString("localName"));
					member.setDong(rs.getString("busName"));
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			release(conn, ps, rs);
		}
		return member;
	}

	public MovieDTO getMovieInfo() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MovieDTO movie = null;
		ArrayList<MovieDTO> arr = new ArrayList<>();

		String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(dbURL, "system", "green1234");
			// ��ȭ ���� ã��
			String sql = "select * from movie";

			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				movie = new MovieDTO();
				movie.setActor(rs.getString("actor"));
				movie.setAge(rs.getInt("age"));
				movie.setDirector(rs.getString("director"));
				movie.setGenre(rs.getString("genre"));
				movie.setMovieNo(rs.getString("movieNo"));
				movie.setTitle(rs.getString("title"));
				movie.setNation(rs.getString("nation"));
				movie.setPlayTime(rs.getString("playTime"));
				movie.setOpenDate(rs.getString("openDate"));
				arr.add(movie);
				movie.setMovie(arr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			release(conn, ps, rs);
		}
		return movie;
	}

	public int getMovieTotal() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int total = 0;

		String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(dbURL, "system", "green1234");
			// ��ȭ ���� ã��
			String sql = "select * from movie";

			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				total++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			release(conn, ps, rs);
		}
		return total;
	}

	public ArrayList<MemberDTO> getReservation(MemberDTO member) {
		// �������� ��������
		ArrayList<MemberDTO> reservationArr = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(dbURL, "system", "green1234");
			// ���� ���� ã��
			String sql = "select * from reservation where movieNo = ? and busName = ? and startTime = ?";

			ps = conn.prepareStatement(sql);
			ps.setString(1, "" + Integer.parseInt(member.getMovie() + 1));
			ps.setString(2, member.getDong());
			ps.setString(3, member.getStartTime());
			rs = ps.executeQuery();
			while (rs.next()) {
				MemberDTO res = new MemberDTO();
				res.setId(rs.getString("id"));
				res.setResSit(rs.getString("sit"));
				res.setStartTime(rs.getString("startTime"));
				res.setMovie(rs.getString("movieNo"));
				res.setSi(rs.getString("cityName"));
				res.setGu(rs.getString("localName"));
				res.setDong(rs.getString("busName"));
				reservationArr.add(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			release(conn, ps, rs);
		}
		return reservationArr;
	}

	public int totalReservation(MemberDTO member) {
		// ȸ���� ������ ǥ �ҷ�����
		int total = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(dbURL, "system", "green1234");
			// ���� ���� ã��
			String sql = "select * from reservation where id = ?";

			ps = conn.prepareStatement(sql);
			ps.setString(1, "" + member.getId());
			rs = ps.executeQuery();
			while (rs.next()) {
				total++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			release(conn, ps, rs);
		}
		return total;
	}

	public String[][] getUserReservation(MemberDTO member) {
		MovieDAO movieDAO = new MovieDAO();
		int total = movieDAO.totalReservation(member);
		System.out.println(total);
		String[][] arr = new String[total][5];
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(dbURL, "system", "green1234");
			// ���� ���� ã��

			String sql = "select * from reservation where id = ?";

			ps = conn.prepareStatement(sql);
			ps.setString(1, "" + member.getId());
			rs = ps.executeQuery();
			int count = 0;
			while (rs.next()) {
				arr[count][0] = rs.getString("id");
				arr[count][1] = rs.getString("sit");
				arr[count][2] = rs.getString("startTime");
				arr[count][4] = rs.getString("busName");
				String sql2 = "select title from movie where movieNo = ?";
				PreparedStatement ps2 = conn.prepareStatement(sql2);
				ps2.setString(1, rs.getString("movieNo"));
				ResultSet rs2 = ps2.executeQuery();
				if (rs2.next()) {
					arr[count][3] = rs2.getString("title");
				}
				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			release(conn, ps, rs);
		}
		return arr;
	}

	public boolean deleteReservation(String id, String sit, String title, String business, String startTime) {
		boolean ok = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(dbURL, "system", "green1234");
			// ��ȭ ���� ã��
			String movieNo = "";
			String sql = "select movieNo from movie where title = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "" + title);
			rs = ps.executeQuery();
			if (rs.next()) {
				movieNo = rs.getString("movieNo");
				// ���� ���� �����ϱ�
				System.out.println(id + " " + sit + " " + business + " " + movieNo + " " + startTime);
				sql = "delete from reservation where id = ? and sit = ? and busName = ? and movieNo = ? and startTime = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, id);
				ps.setString(2, sit);
				ps.setString(3, business);
				ps.setString(4, movieNo);
				ps.setString(5, startTime);
				int result = ps.executeUpdate();
				if (result == 1) { 
					ok = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			release(conn, ps, rs);
		}
		return ok;
	}
}
