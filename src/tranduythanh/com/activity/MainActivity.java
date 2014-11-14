package tranduythanh.com.activity;

import java.util.ArrayList;

import tranduythanh.com.activity.R;
import tranduythanh.com.adapter.PhongBanAdapter;
import tranduythanh.com.model.ChucVu;
import tranduythanh.com.model.NhanVien;
import tranduythanh.com.model.PhongBan;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
/**
 * Class MainActivity của ứng dụng
 * @author drthanh
 *
 */
public class MainActivity extends Activity {
	//Khai báo các Request + Result code để xử lý Intent for result
	public static final int MO_ACTIVITY_THEM_NHAN_VIEN=1;
	public static final int MO_ACTIVITY_SUA_NHAN_VIEN=2;
	public static final int THEM_NHAN_VIEN_THANHCONG=3;
	public static final int SUA_NHAN_VIEN_THANHCONG=4;
	public static final int XEM_DS_NHAN_VIEN=5;
	public static final int CAPNHAT_DS_NHAN_VIEN_THANHCONG=6;
	public static final int MO_ACTIVITY_THIET_LAP_TP_PP=7;
	public static final int THIET_LAP_TP_PP_THANHCONG=8;
	public static final int MO_ACTIVITY_CHUYENPHONG=9;
	public static final int CHUYENPHONG_THANHCONG=10;
	
	private Button btnLuuPb;
	private EditText editMapb,editTenpb;
	private ListView lvpb;
	private static ArrayList<PhongBan>arrPhongBan=new ArrayList<PhongBan>();
	//ở đây dùng PhongBanAdapter
	private PhongBanAdapter adapterPb=null;
	private PhongBan pbSelected=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getFormWidgets();
		addEvents();
		fakeData();
	}
	/**
	 * Hàm này dùng để tải dữ liệu tạm (đỡ phải nhập mỏi tay)
	 * Tôi dùng hàm này để ví dụ cho lẹ
	 * bạn có thể xóa nó đi (nói chung vô thưởng vô phạt)
	 */
	public void fakeData()
	{
		NhanVien nv=null;
		PhongBan pb=new PhongBan("pb1", "Kỹ thuật");
		nv=new NhanVien("m4", "Đoàn Ái Nương", true);
		nv.setChucvu(ChucVu.TruongPhong);
		pb.themNv(nv);
		nv=new NhanVien("m5", "Trần Đạo Đức", false);
		nv.setChucvu(ChucVu.PhoPhong);
		pb.themNv(nv);
		nv=new NhanVien("m6", "Nguyễn Đại Tài", false);
		nv.setChucvu(ChucVu.PhoPhong);
		pb.themNv(nv);
		arrPhongBan.add(pb);
		pb=new PhongBan("pb2", "Dịch vụ");
		arrPhongBan.add(pb);
		pb=new PhongBan("pb3", "Truyền thông");
		arrPhongBan.add(pb);
		nv=new NhanVien("m1", "Nguyễn Văn Tẻo", false);
		nv.setChucvu(ChucVu.NhanVien);
		pb.themNv(nv);
		nv=new NhanVien("m2", "Nguyễn Thị Téo", true);
		nv.setChucvu(ChucVu.TruongPhong);
		pb.themNv(nv);
		nv=new NhanVien("m3", "Nguyễn Văn Teo", false);
		nv.setChucvu(ChucVu.NhanVien);
		pb.themNv(nv);
		
		adapterPb.notifyDataSetChanged();
	}
	/**
	 * - Hàm này để load các control theo Id
	 * - Thiết lập Adapter cho ListView phòng ban
	 * - thiết lập context Menu cho ListView
	 */
	public void getFormWidgets()
	{
		btnLuuPb=(Button) findViewById(R.id.btnluupb);
		editMapb=(EditText) findViewById(R.id.editmapb);
		editTenpb=(EditText) findViewById(R.id.editTenpb);
		lvpb=(ListView) findViewById(R.id.lvphongban);
		//khởi tạo đối tượng phòng ban adapter
		//dùng layout_item_custom.xml
		adapterPb=new PhongBanAdapter(this, 
				R.layout.layout_item_custom, 
				arrPhongBan);
		lvpb.setAdapter(adapterPb);
		//Đăng ký contextmenu cho Listview
		registerForContextMenu(lvpb);
	}
	/**
	 * hàm gán sự kiện cho các control: button, ListView
	 */
	public void addEvents()
	{
		//Bấm lưu để lưu phòng ban
		btnLuuPb.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				doLuuPhongBan();
			}
		});
		//xử lý lưu biến tạm khi nhấn long - click
		//phải dùng cái này để biết được trước đó đã chọn item nào
		lvpb.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				//lưu vết lại đối tượng thứ arg2 trong danh sách
				pbSelected=arrPhongBan.get(arg2);
				return false;
			}
			
		});
	}
	/**
	 * hàm xử lý lưu phòng ban
	 * đơn thuần là đưa phòng ban mới vào ArrayList
	 * chú ý bạn phải kiểm tra trùng lắp Id, hay các 
	 * thông tin đã đầy đủ chưa ở đây
	 * gọi adapterPb.notifyDataSetChanged(); để cập nhật ListView
	 */
	public void doLuuPhongBan()
	{
		String mapb=editMapb.getText()+"";
		String tenpb=editTenpb.getText()+"";
		PhongBan pb=new PhongBan(mapb, tenpb);
		arrPhongBan.add(pb);
		adapterPb.notifyDataSetChanged();
	}
	/**
	 * hàm này để gán COntextMenu vào ứng dụng
	 * đã học từ lâu rồi, phải tự hiểu
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.contextmenu_phongban, menu);
	}
	/**
	 * hàm này để xử lý người sử dụng vừa chọn menuitem nào
	 * ta dựa vào Id để xử lý
	 */
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch(item.getItemId())
		{
		case R.id.mnuthemnv:
			doThemNhanVien();
			break;
		case R.id.mnudanhsachnv:
			doDanhSachNhanVien();
			break;
		case R.id.mnutruongphong:
			doThietLapLanhDao();
			break;
		case R.id.mnuxoapb:
			doXoaPhongBan();
			break;
		}
		return super.onContextItemSelected(item);
	}
	/**
	 * khi chọn menu Thêm nhân viên, đơn thuần chỉ là hiển thị
	 * màn hìn thêm nhân viên (với dạng Dialog)
	 * nhớ là dùng startActivityForResult
	 * do đó ta lắng nghe kết quả tại hàm onActivityResult
	 */
	public void doThemNhanVien()
	{
		Intent i=new Intent(this, ThemNhanVienActivity.class);
		startActivityForResult(i, MO_ACTIVITY_THEM_NHAN_VIEN);
	}
	/**
	 * hàm onActivityResult để xử lý kết quả trả về 
	 * sau khi gọi startActivityForResult kết thúc
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		//màn hình thêm mới nhân viên trả kết quả về
		if(resultCode==THEM_NHAN_VIEN_THANHCONG)
		{
			//Cách lấy dữ liệu đã học rồi, ko nói lại
			Bundle bundle= data.getBundleExtra("DATA");
			NhanVien nv= (NhanVien) bundle.getSerializable("NHANVIEN");
			pbSelected.themNv(nv);
			adapterPb.notifyDataSetChanged();
		}
		//Màn hình thiết lập lạnh đão/ cập nhật danh sách trả 
		//kết quả về
		else if(resultCode==THIET_LAP_TP_PP_THANHCONG||
				resultCode==CAPNHAT_DS_NHAN_VIEN_THANHCONG)
		{
			//Cách lấy dữ liệu đã học rồi, ko nói lại
			Bundle bundle= data.getBundleExtra("DATA");
			PhongBan pb= (PhongBan) bundle.getSerializable("PHONGBAN");
			//đơn thuần là xóa danh sách cũ
			pbSelected.getListNhanVien().clear();
			//rồi cập nhật lại toàn bộ danh sách mới
			//sinh viên có thể chọn giải pháp thông minh hơn
			//tức là phần tử nào bị ảnh hưởng thì xử lý phần tử đó
			pbSelected.getListNhanVien().addAll(pb.getListNhanVien());
			adapterPb.notifyDataSetChanged();
		}
	}
	/**
	 * hàm cho phép danh xem danh sách nhân viên của phòng
	 * ban đang chọn, đơn thuần là mở DanhSachNhanVienActivity
	 * và truyền phòng ban qua -->toàn bộ danh sách nhân viên
	 * sẽ được hiển thị trong DanhSachNhanVienActivity
	 */
	public void doDanhSachNhanVien()
	{
		Intent i=new Intent(this, DanhSachNhanVienActivity.class);
		Bundle bundle=new Bundle();
		bundle.putSerializable("PHONGBAN", pbSelected);
		i.putExtra("DATA", bundle);
		startActivityForResult(i, XEM_DS_NHAN_VIEN);
	}
	/**
	 * hàm cho phép mở màn hình thiết lập lãnh đạo phòng ban
	 * Trưởng phòng và phó phòng
	 * ThietLapTruongPhongActivity sẽ có 2 ListView
	 * - Listview 1 hiển thị dang radiobutton để chỉ chọn 1 trưởng phòng
	 * - ListView 2 hiển thị dạng Checkbox cho phép chọn nhiều phó phòng
	 */
	public void doThietLapLanhDao()
	{
		Intent i=new Intent(this, ThietLapTruongPhongActivity.class);
		Bundle bundle=new Bundle();
		bundle.putSerializable("PHONGBAN", pbSelected);
		i.putExtra("DATA", bundle);
		startActivityForResult(i, MO_ACTIVITY_THIET_LAP_TP_PP);
	}
	/**
	 * hàm cho phép xóa phòng ban đang chọn
	 */
	public void doXoaPhongBan()
	{
		AlertDialog.Builder builder=new AlertDialog.Builder (this);
		builder.setTitle("Hỏi xóa");
		builder.setMessage("Bạn có chắc chắn muốn xóa ["+pbSelected.getTen()+"]");
		builder.setIcon(android.R.drawable.ic_input_delete);
		builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				arg0.cancel();
			}
		});
		builder.setPositiveButton("Ừ", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				arrPhongBan.remove(pbSelected);
				adapterPb.notifyDataSetChanged();
			}
		});
		builder.show();
	}
	/**
	 * Tôi cố tình cung cấp hàm này để ở các Activity khác đều
	 * có thể truy suất được danh sách phòng ban tổng thể
	 * @return
	 */
	public static  ArrayList<PhongBan> getListPhongBan()
	{
		return arrPhongBan;
	}
}
