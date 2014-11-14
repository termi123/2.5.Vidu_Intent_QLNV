package tranduythanh.com.activity;

import tranduythanh.com.model.NhanVien;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
/**
 * class này cho phép sửa nhân viên được chọn trong Danhsachnhanvien
 * sử dụng chung Layout với Thêm nhân viên
 * Không cho sửa mã số nhân viên
 * khi bấm lưu thì cập nhật lại (rất đơn giản)
 * @author drthanh
 *
 */
public class SuaNhanVienActivity extends Activity{
	EditText editMa,editTen;
	RadioButton radNam;
	Button btnClear,btnSave;
	NhanVien nv=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_them_nhan_vien);
		getFormWidgets();
		getDefaultData();
		addEvents();
	}
	/**
	 * hàm lấy các control theo id
	 */
	public void getFormWidgets()
	{
		editMa=(EditText) findViewById(R.id.editMaNV);
		editTen=(EditText) findViewById(R.id.editTenNV);
		radNam=(RadioButton) findViewById(R.id.radNam);
		editMa.setEnabled(false);
		editTen.requestFocus();
		
		btnClear=(Button) findViewById(R.id.btnxoatrang);
		btnSave=(Button) findViewById(R.id.btnluunv);
	}
	/**
	 * hàm lấy giá trị từ DanhSachNhanVienActivity truyền qua
	 * để hiển thị
	 */
	public void getDefaultData()
	{
		Intent i =getIntent();
		Bundle b=i.getBundleExtra("DATA");
		nv=(NhanVien) b.getSerializable("NHANVIEN");
		editMa.setText(nv.getMa());
		editTen.setText(nv.getTen());
		radNam.setChecked(true);
		if(nv.isGioitinh())
			radNam.setChecked(false);
	}
	public void addEvents()
	{
		btnClear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				editTen.setText("");
				editTen.requestFocus();
			}
		});
		/**
		 * bấm lưu thì đóng cửa sổ sửa này
		 * và truyền dữ liệu qua màn hình cha để nó tự cập nhật
		 */
		btnSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i =getIntent();
				nv.setTen(editTen.getText()+"");
				nv.setGioitinh(!radNam.isChecked());
				Bundle b=new Bundle();
				b.putSerializable("NHANVIEN", nv);
				i.putExtra("DATA", b);
				setResult(MainActivity.SUA_NHAN_VIEN_THANHCONG, i);
				finish();
			}
		});
	}
}
