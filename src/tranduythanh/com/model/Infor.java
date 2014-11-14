package tranduythanh.com.model;

import java.io.Serializable;
/**
 * Class này để lưu mã và tên là class cha của NhanVien
 * và PhongBan. Vì cả nhân viên hay phòng ban đều có mã và tên
 * @author drthanh
 *
 */
public class Infor implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ma;
	private String ten;
	public Infor(String ma, String ten) {
		super();
		this.ma = ma;
		this.ten = ten;
	}
	public Infor() {
		super();
	}
	public String getMa() {
		return ma;
	}
	public void setMa(String ma) {
		this.ma = ma;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.ma+" - "+this.ten;
	}
}
