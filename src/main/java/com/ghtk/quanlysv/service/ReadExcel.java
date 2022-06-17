package com.ghtk.quanlysv.service;

import com.ghtk.quanlysv.model.Student;
import com.ghtk.quanlysv.repository.StudentRepository;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ReadExcel {
	@Autowired
	private StudentRepository studentRepository;

	public void addDataFromExcelFile(MultipartFile multipartFile) throws IOException, NumberFormatException, ParseException {
		//List<Student> listStudent = new ArrayList<Student>();
		Workbook workBook = new XSSFWorkbook(multipartFile.getInputStream());
		Sheet sheet = workBook.getSheet("data");
		DataFormatter fmt = new DataFormatter();

		for (int index = 5; index < sheet.getPhysicalNumberOfRows(); index++) {
			Row row  = sheet.getRow(index);
			Student stu = new Student();
			stu.setTruongHoc(fmt.formatCellValue(row.getCell(1)));
			stu.setHuyen(fmt.formatCellValue(row.getCell(2)));
			stu.setMaHS(fmt.formatCellValue(row.getCell(3)));
			stu.setLop((fmt.formatCellValue(row.getCell(4))));
			stu.setHoTen(fmt.formatCellValue(row.getCell(5)));

			SimpleDateFormat DateFor = new SimpleDateFormat("yyyy/MM/dd");
			Date birthday = DateFor.parse(row.getCell(8) + "/" + row.getCell(7) + "/" + row.getCell(6));
			stu.setBirthday(birthday);
			stu.setGioiTinh(fmt.formatCellValue(row.getCell(9)));
			stu.setNoiSinh(fmt.formatCellValue(row.getCell(10)));
			stu.setDanToc(fmt.formatCellValue(row.getCell(11)));
			stu.setHoKhauTT(fmt.formatCellValue(row.getCell(12)));
			stu.setSDT(fmt.formatCellValue(row.getCell(13)));
			stu.setDiemNam1(isNumeric(fmt.formatCellValue(row.getCell(14))));
			stu.setDiemNam2(isNumeric(fmt.formatCellValue(row.getCell(15))));
			stu.setDiemNam3(isNumeric(fmt.formatCellValue(row.getCell(16))));
			stu.setDiemNam4(isNumeric(fmt.formatCellValue(row.getCell(17))));
			stu.setDiemNam5(isNumeric(fmt.formatCellValue(row.getCell(18))));
			stu.setTongDiem5Nam(isNumeric(fmt.formatCellValue(row.getCell(19))));
			stu.setDiemUuTien(isNumeric(fmt.formatCellValue(row.getCell(20))));
			stu.setTongDiemSoTuyen(isNumeric(fmt.formatCellValue(row.getCell(21))));
			stu.setGhiChu(fmt.formatCellValue(row.getCell(22)));

			studentRepository.save(stu);
		}
	}

	public List<Student> getAllStudent() {
		return studentRepository.findAll();
	}

	public List<Student> searchStudent(String id, String name) {
		name = formatString(name);
		id = formatString(id);

		if (id.equals("")) {
			return studentRepository.findByName(name);
		}

		return studentRepository.findByIds(id);
	}

	private String formatString(String str) {
		return str.toLowerCase().replaceAll("\\s+", " ").trim();
	}

	private int isNumeric(String str){
		if (NumberUtils.isParsable(str)) {
			return Integer.parseInt(str);
		} else {
			return 0;
		}
	}
}
