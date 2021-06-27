package kr.or.ddit.assignment;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// POI라이브러리를 이용하여 Excel파일을 생성하고, 데이터입력하기
public class POIExcelWrite {

	// Excel파일 생성할 경로와 파일명 지정
	// TODO 경로 수정
	private static final String FILE_NAME = "C:/Users/lucid/git/DDITjdbcTest/src/kr/or/ddit/assignment/Excelfile.xlsx";

	public static void main(String[] args) {
		// XSSFWorkbook 객체 생성
		XSSFWorkbook workbook = new XSSFWorkbook();

		XSSFSheet sheet1 = workbook.createSheet("테스트 시트1");

		// 폰트 설정
		XSSFFont font = workbook.createFont();
		font.setFontName(HSSFFont.FONT_ARIAL); // 폰트 스타일
		font.setFontHeightInPoints((short) 10); // 폰트 크기
		font.setBold(true); // 폰트 볼드여부

		// 셀 가운데 정렬
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setFont(font);

		// TODO 데이터 받아오는 방식 수정
		ArrayList<MemberVO> memVoData = new ArrayList<>();
		memVoData.add(new MemberVO("memId1", "memPass1", "memName1", "memTel1", "memAddr1"));
		memVoData.add(new MemberVO("memId2", "memPass2", "memName2", "memTel2", "memAddr2"));
		memVoData.add(new MemberVO("memId3", "memPass3", "memName3", "memTel3", "memAddr3"));
		memVoData.add(new MemberVO("memId4", "memPass4", "memName4", "memTel4", "memAddr4"));

		System.out.println("Excel파일을 생성합니다.");

		String colData = null;
		int rowNum = 0; // 열 카운트용 변수 생성
		for (int i = 0; i < memVoData.size(); i++) { // data1의 데이터를 Object객체의 rowData변수에 넣음
			Row row = sheet1.createRow(rowNum++); // 열 생성 메소드

			int colNum = 0; // 행 카운트용 변수 생성
			for (int j = 0; j < 5; j++) { // data1의 데이터를 Object객체의 rowData변수에 넣음
				Cell cell = row.createCell(colNum++);
				cell.setCellStyle(cellStyle); // 셀 가운데 정렬

				switch (j) {
				case 0:
					colData = memVoData.get(i).getMem_id();
					break;
				case 1:
					colData = memVoData.get(i).getMem_pass();
					break;
				case 2:
					colData = memVoData.get(i).getMem_name();
					break;
				case 3:
					colData = memVoData.get(i).getMem_tel();
					break;
				case 4:
					colData = memVoData.get(i).getMem_addr();
					break;
				default:
					break;
				}
				if (colData instanceof String) { // String타입으로 형변환가능한지 여부
					cell.setCellValue((String) colData);
				}
			}

		}

		try {
			FileOutputStream fos = new FileOutputStream(FILE_NAME);
			workbook.write(fos);
			workbook.close();
			System.out.println("Excel파일을 생성하였습니다.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}