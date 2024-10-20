import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class ZipTest {
    private final ClassLoader cl = ZipTest.class.getClassLoader();
        PDF pdfFile = null;
        XLS xlsFile = null;
        CSVReader csvFile = null;
        String nameOfArchive = "HW.zip";
        InputStream is = cl.getResourceAsStream(nameOfArchive);
    @Test
    void pdfFileParsingTest() throws Exception {
        try (ZipInputStream zip = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zip.getNextEntry()) != null) {
                if (entry.getName().equals("TestPDF.pdf")) {
                    pdfFile = new PDF(zip);
                    break;
                }
            }
            Assertions.assertTrue(pdfFile.text.contains("Тестировщик в баре"));
        }
    }
    @Test
    void csvFileParsingTest() throws Exception {
        try (ZipInputStream zip = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zip.getNextEntry()) != null) {
                if (entry.getName().equals("TestCSV.csv")) {
                    csvFile = new CSVReader(new InputStreamReader(zip));
                    break;
                }
            }
            List<String[]> data = csvFile.readAll();
            Assertions.assertEquals(3, data.size());
            Assertions.assertArrayEquals(
                    new String[]{"testov@yandex.ru"},
                    data.get(0)
            );
            Assertions.assertArrayEquals(
                    new String[]{"petrov@yandex.ru"},
                    data.get(1)
            );
            Assertions.assertArrayEquals(
                    new String[]{"ivanov@yandex.ru"},
                    data.get(2)
            );
        }
    }
    @Test
    void xlsxFileParsingTest() throws Exception {
        try (ZipInputStream zip = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zip.getNextEntry()) != null) {
                if (entry.getName().equals("TestXLSX.xlsx")) {
                    xlsFile = new XLS(zip);
                    break;
                }
            }
            String actualValue = xlsFile.excel.getSheetAt(0).getRow(3).getCell(2).getStringCellValue();
            Assertions.assertTrue(actualValue.contains("Иван"));
        }
    }
    }

