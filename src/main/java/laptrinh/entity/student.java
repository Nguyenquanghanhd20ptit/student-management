package laptrinh.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;



import lombok.Data;
import net.bytebuddy.asm.Advice.This;


@Entity
@Table(name = "student")
@Data
public class student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank
	private String msv;
	@NotBlank
	private String hoten;
	@NotBlank
	@Email
	private String email;
	private Date ngaysinh;
	@NotBlank
	private String diachi;
	@NotBlank
	private String sdt;
	@Min(0)
	@Max(4)
	private double gpa;
	private String xeploai;
	
	
	public student(int id, @NotBlank String msv, @NotBlank String hoten, @NotBlank @Email String email, Date ngaysinh,
			@NotBlank String diachi, @NotBlank String sdt, @Min(0) @Max(4) double gpa, String xeploai) {
		super();
		this.id = id;
		this.msv = msv;
		this.hoten = hoten;
		this.email = email;
		this.ngaysinh = ngaysinh;
		this.diachi = diachi;
		this.sdt = sdt;
		this.gpa = gpa;
		this.xeploai = xeploai;
	}
	public student() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMsv() {
		return msv;
	}
	public void setMsv(String msv) {
		this.msv = msv;
	}
	public String getHoten() {
		return hoten;
	}
	public void setHoten(String hoten) {
		this.hoten = hoten;
	}
	public String getNgaysinh() {
		if(this.ngaysinh != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String dateFormat = formatter.format(this.ngaysinh);
			return dateFormat;

		}
		return "";
	}
	public void setNgaysinh(String date){
        try {
            this.ngaysinh = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
	public String getDiachi() {
		return diachi;
	}
	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getGpa() {
		return gpa;
	}
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	public String getXeploai() {
		return xeploai;
	}
	public void setXeploai(String xeploai) {
		this.xeploai = xeploai;
	}
	
	
}
