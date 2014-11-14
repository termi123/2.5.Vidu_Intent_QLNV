package tranduythanh.com.adapter;

import java.util.ArrayList;

import tranduythanh.com.activity.R;
import tranduythanh.com.model.NhanVien;
import tranduythanh.com.model.PhongBan;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
/**
 * Class này dùng để custom layout cho Danh sách phòng ban
 * @author drthanh
 *
 */
public class PhongBanAdapter extends ArrayAdapter<PhongBan> {
	Activity context;
	int layoutId;
	ArrayList<PhongBan> arrPhongBan;
	public PhongBanAdapter(Activity context, int textViewResourceId,
			ArrayList<PhongBan> objects) {
		super(context, textViewResourceId, objects);
		this.context=context;
		this.layoutId=textViewResourceId;
		this.arrPhongBan=objects;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//gán layout vào coding
		convertView=context.getLayoutInflater().inflate(layoutId, null);
		//lấy các control ra theo id
		TextView txtpb= (TextView) convertView.findViewById(R.id.txtShortInfor);
		TextView txtmotapb= (TextView) convertView.findViewById(R.id.txtDetailInfor);
		//Lấy phòng ban thứ position
		PhongBan pb=arrPhongBan.get(position);
		txtpb.setText(pb.toString());
		/**
		 * Các Dòng lệnh dưới này để kiểm tra Trưởng phòng, phó phòng
		 */
		String strMota="";
		String tp="Trưởng Phòng: [Chưa có]";
		NhanVien nv=pb.getTruongPhong();
		if(nv!=null)
		{
			tp="Trưởng Phòng: ["+nv.getTen()+"]";
		}
		ArrayList<NhanVien> dsPp=pb.getPhoPhong();
		String pp="Phó phòng: [Chưa có]";
		if(dsPp.size()>0)
		{
			pp="Phó phòng:\n";
			for(int i=0;i<dsPp.size();i++)
			{
				pp+=(i+1)+". "+dsPp.get(i).getTen()+"\n";
			}
		}
		strMota=tp+"\n"+pp;
		//gán thông tin  cho phần chi tiết
		txtmotapb.setText(strMota);
		return convertView;
	}

}
