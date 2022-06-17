package com.ghtk.quanlysv.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Student {
	private int stt;
	private String truongHoc;
	private String huyen;
	@Id
	private String maHS;
	private String lop;
	private String hoTen;
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date birthday;
	private String gioiTinh;
	private String noiSinh;
	private String danToc;
	private String hoKhauTT;
	private String sDT;
	private int diemNam1;
	private int diemNam2;
	private int diemNam3;
	private int diemNam4;
	private int diemNam5;
	private int tongDiem5Nam;
	private int diemUuTien;
	private int tongDiemSoTuyen;
	private String ghiChu;
}
