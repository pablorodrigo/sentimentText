package sentimentTwitter;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CreateFileExecel {
	
	public void expExcel(String nomeArquivo, JSONArray jsonArray) {

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet firstSheet = workbook.createSheet("Aba1");

		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream(new File(nomeArquivo));

			// Este trecho obtem uma lista de objetos do tipo CD

			// do banco de dados através de um DAO e itera sobre a lista

			// criando linhas e colunas em um arquivo Excel com o conteúdo

			// dos objetos.

			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject texts = (JSONObject) jsonArray.get(i);
				HSSFRow row = firstSheet.createRow(i);

				row.createCell(0).setCellValue(texts.get("originalText").toString());
				row.createCell(1).setCellValue(texts.get("sentiment").toString());

			}

			workbook.write(fos);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao exportar arquivo");
		} finally {
			try {
				fos.flush();
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	} // fim do metodo exp


}
