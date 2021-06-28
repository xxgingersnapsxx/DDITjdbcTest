//package kr.or.ddit.basic.poitest;
//
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import org.apache.poi.hssf.usermodel.HSSFFont;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.HorizontalAlignment;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFCellStyle;
//import org.apache.poi.xssf.usermodel.XSSFFont;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import kr.or.ddit.board.vo.JdbcBoardVO;
//import kr.or.ddit.mvc.vo.MemberVO;
//
//public class poiTestMap {
//
//	// Excel파일을 생성할 경로와 파일명 지정
//	private static final String FILE_NAME = "D:/D_Other/poiTest.xlsx";
//	
//	public static void main(String[] args) {
//		// XSSFWrokbook 객체 생성
//		XSSFWorkbook workbook = new XSSFWorkbook();
//		
//		XSSFSheet sheet1 = workbook.createSheet("테스트 시트1");
//		
//		// 폰트 설정
//		XSSFFont font = workbook.createFont();
//		font.setFontName(HSSFFont.FONT_ARIAL); // 폰트 스타일
//		font.setFontHeightInPoints((short) 10); // 폰트 크기
//		font.setBold(true); // 폰트 볼드 여부
//		
//		// 셀 가운데 정렬
//		XSSFCellStyle cellStyle = workbook.createCellStyle();
//		cellStyle.setAlignment(HorizontalAlignment.CENTER);
//		cellStyle.setFont(font);
//		
//		// 셀 병합(첫번째 열, 마지막 열, 첫번째 행, 마지막행)
//		// 셀 병합시 기준이 되는 열의 뒤에 오는 데이터는 비워지면서 병합됨
////		sheet1.addMergedRegion(new CellRangeAddress(0, 5, 0, 8));
//		
//		// 이중배열로 시트에 데이터 입력
//		// 데이터1
//		Object[][] data1 = {         
//				{ "C1R1", "C2R1", "C3R1", "C4R1", "C5R1" }, 
//				{ "C1R2", "C2R2", "C3R2", "C4R2", "C5R2" },
//				{ "C1R3", "C2R3", "C3R3", "C4R3", "C5R3" }, 
//				};
//		
//		ArrayList<String> memData = new ArrayList<>();
//		
//		
//		HashMap<String, Object> data2 = new HashMap<>();
//		
//		data2.put("1", new MemberVO("id", "pass", "tel", "name", "addr"));
//		data2.put("2", new MemberVO("id", "pass", "tel", "name", "addr"));
//		data2.put("3", new MemberVO("id", "pass", "tel", "name", "addr"));
//		
//		System.out.println("엑셀 파일 생성 시작");
//		
//		
//		int rowNum = 0; // 열 카운트용 변수 생성
//		for (String memberKey : data2.keySet()) {
//			Row row = sheet1.createRow(rowNum++);
//			
//			int colNum = 0; // 행 카운트용 변수 생성
//
//			for (int i = 0; i < 5; i++) {
//				Cell cell = row.createCell(colNum++);
//				cell.setCellStyle(cellStyle);
//				if(colData instanceof String) {
//					cell.setCellValue((String) colData); 
//				} else if (colData instanceof Integer) {
//					cell.setCellValue((Integer) colData);
//				}
//				
//			}
//			MemberVO memberObj = (MemberVO)data2.get(memberKey);
//			
//			System.out.println(memberKey + " : " + memberObj.getMem_id() + ", " + memberObj.getMem_pass());
//		}
//		
//		for (Object[] rowData : data1) {
//			Row row = sheet1.createRow(rowNum++);
//			
//			int colNum = 0; // 행 카운트용 변수 생성
//			for (Object colData : rowData) {
//				Cell cell = row.createCell(colNum++);
//				cell.setCellStyle(cellStyle);
//				
//				if(colData instanceof String) {
//					cell.setCellValue((String) colData); 
//				} else if (colData instanceof Integer) {
//					cell.setCellValue((Integer) colData);
//				}
//			}
//		}
//		
//		try {
//			FileOutputStream fos = new FileOutputStream(FILE_NAME);
//			workbook.write(fos);
//			workbook.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch(IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println("엑셀파일 생성 완료");
//	}
//
//}
