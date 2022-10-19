package laptrinh.Service.impl;

import org.springframework.stereotype.Service;

import laptrinh.Service.studentService_interface;

@Service
public class studentService implements studentService_interface {

	@Override
	public String xepHang(double diem) {
		if(diem >= 3.6) {
			return "Xuất Sắc";
		}
		if(diem >= 3.2 && diem <3.6) {
			return "Giỏi";
		}
		if(diem >= 2.5 && diem <3.2) {
			return "Khá";
		}
		if(diem >= 2 && diem < 2.5) {
			return "Trung Bình";
		}
		return "Yếu";
	}

}
