package tranduythanh.com.adapter;

import java.util.ArrayList;

import tranduythanh.com.activity.R;
import tranduythanh.com.model.NhanVien;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * Class để tạo customlayout cho danh sách nhân viên
 * ImageVIew để hiển thị hình ảnh theo giới tính
 * @author drthanh
 *
 */
public class NhanVienAdapter extends ArrayAdapter<NhanVien> {

	Activity context;
	int layoutId;
	ArrayList<NhanVien> arrNhanVien;
	public NhanVienAdapter(Activity context, int textViewResourceId,
			ArrayList<NhanVien> objects) {
		super(context, textViewResourceId, objects);
		this.context=context;
		this.layoutId=textViewResourceId;
		this.arrNhanVien=objects;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//gán XML layout vào coding
		convertView=context.getLayoutInflater().inflate(layoutId, null);
		//lấy các control ra
		TextView txtnv= (TextView) convertView.findViewById(R.id.txtShortInfor);
		TextView txtmotanv= (TextView) convertView.findViewById(R.id.txtDetailInfor);
		ImageView img=(ImageView) convertView.findViewById(R.id.imgview);
		//lấy nhân viên thứ position
		NhanVien nv=arrNhanVien.get(position);
		txtnv.setText(nv.toString());
		String strMota="";
		String cv="Chức vụ: "+nv.getChucvu().getChucVu();
		String gt="Giới tính: "+(nv.isGioitinh()?"Nữ":"Nam");
		//Kiểm tra giới tính để gán cho đúng hình đại diện
		img.setImageResource(R.drawable.girlicon);
		if(!nv.isGioitinh())
			img.setImageResource(R.drawable.boyicon);
		strMota=cv+"\n"+gt;
		txtmotanv.setText(strMota);
		return convertView;
	}

}
