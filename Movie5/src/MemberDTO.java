

import java.util.ArrayList;

public class MemberDTO {
	private String id;
	private String pwd;
	private String name;
	private String phone;
	private String addr;
	private String login; 
	private String startTime;
	private String si;
	private String gu;
	private String dong;
	private String movie;
	private String resSit;
	public String getResSit() {
		return resSit;
	}

	public void setResSit(String resSit) {
		this.resSit = resSit;
	}

	private ArrayList<String> sit; 

	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", pwd=" + pwd + ", name=" + name + ", phone=" + phone + ", addr=" + addr
				+ ", login=" + login + ", startTime=" + startTime + ", si=" + si + ", gu=" + gu + ", dong=" + dong
				+ ", movie=" + movie + ", sit=" + sit + "]";
	}

	public String getSi() {
		return si;
	}

	public void setSi(String si) {
		this.si = si;
	}

	public String getGu() {
		return gu;
	}

	public void setGu(String gu) {
		this.gu = gu;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public ArrayList<String> getSit() {
		return sit;
	}

	public void setSit(ArrayList<String> sit) {
		this.sit = sit;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	} 

	public String getMovie() {
		return movie;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String movieTime) {
		this.startTime = movieTime;
	}
}
