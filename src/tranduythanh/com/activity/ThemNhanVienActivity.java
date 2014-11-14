package tranduythanh.com.activity;

import tranduythanh.com.activity.R;
import tranduythanh.com.model.ChucVu;
import tranduythanh.com.model.NhanVien;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
/**
 * class này cho phép thêm mới một nhân viên vào 
 * Phòng ban đang chọn
 * @author drthanh
 *
 */
public class ThemNhanVienActivity extends Activity {

	private Button btnXoaTrang,btnLuuNhanVien;
	private EditText editManv,editTenNv;
	private RadioButton radNam;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_them_nhan_vien);
		getFormWidgets();
		addEvents();
	}
	/**
	 * hàm lấy các control theo id
	 */
	public void getFormWidgets()
	{
		btnXoaTrang=(Button) findViewById(R.id.btnxoatrang);
		btnLuuNhanVien=(Button) findViewById(R.id.btnluunv);
		editManv=(EditText) findViewById(R.id.editMaNV);
		editTenNv=(EditText) findViewById(R.id.editTenNV);
		radNam=(RadioButton) findViewById(R.id.radNam);
	}
	/**
	 * hàm gán sự kiện
	 */
	public void addEvents()
	{
		btnXoaTrang.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				doXoaTrang();
			}
		});
		btnLuuNhanVien.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				doLuuNhanVien();
			}
		});
	}
	/**
	 * đơn thuần là xóa trắng dữ liệu của các control
	 * rồi focus tới mã
	 */
	public void doXoaTrang()
	{
		editManv.setText("");
		editTenNv.setText("");
		editManv.requestFocus();
	}
	/**
	 * hàm lưu nhân viên theo phòng ban
	 * truyền nhân viên qua MainActivity
	 * MainActivity có nhiệm vụ lấy được nhân viên này
	 * và đưa họ vào phòng ban chọn lúc nãy
	 */
	public void doLuuNhanVien()
	{
		NhanVien nv=new NhanVien();
		nv.setMa(editManv.getText()+"");
		nv.setTen(editTenNv.getText()+"");
		nv.setChucvu(ChucVu.NhanVien);
		nv.setGioitinh(!radNam.isChecked());
		Intent i=getIntent();
		Bundle bundle=new Bundle();
		bundle.putSerializable("NHANVIEN", nv);
		i.putExtra("DATA", bundle);
		setResult(MainActivity.THEM_NHAN_VIEN_THANHCONG, i);
		finish();
	}
}
