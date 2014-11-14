package tranduythanh.com.model;

import java.io.Serializable;
/**
 * Class lưu trữ thông tin nhân viên
 * @author drthanh
 *
 */
public class NhanVien extends Infor implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean gioitinh;
	private ChucVu chucvu;
	private PhongBan phongban;
	public NhanVien(String ma, String ten, boolean gioitinh, ChucVu chucvu,
			PhongBan phongban) {
		super(ma,ten);
		this.gioitinh = gioitinh;
		this.chucvu = chucvu;
		this.phongban = phongban;
	}
	public NhanVien(String ma, String ten, boolean gioitinh) {
		super(ma,ten);
		this.gioitinh = gioitinh;
	}
	public NhanVien() {
		super();
	}
	public boolean isGioitinh() {
		return gioitinh;
	}
	public void setGioitinh(boolean gioitinh) {
		this.gioitinh = gioitinh;
	}
	public ChucVu getChucvu() {
		return chucvu;
	}
	public void setChucvu(ChucVu chucvu) {
		this.chucvu = chucvu;
	}
	public PhongBan getPhongban() {
		return phongban;
	}
	public void setPhongban(PhongBan phongban) {
		this.phongban = phongban;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
