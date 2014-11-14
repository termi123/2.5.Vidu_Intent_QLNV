package tranduythanh.com.model;
/**
 * Enum này để thiết lập chức vụ cho nhân viên
 * @author drthanh
 *
 */
public enum ChucVu {
	//Muốn gán được như thế này: TruongPhong("Trưởng Phòng")
	//thì phải có constructor ChucVu(String cv)
	TruongPhong("Trưởng Phòng"),
	PhoPhong("Phó Phòng"),
	NhanVien("Nhân Viên");
	private String cv;
	ChucVu(String cv)
	{
		this.cv=cv;
	}
	public String getChucVu()
	{
		return this.cv;
	}
}
