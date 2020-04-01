package Data;

import Main.Settings;
import Model.ResultSet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataWriter {

    public static void writeData(ResultSet resultSet) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Report");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        LocalDateTime now = LocalDateTime.now();

        DecimalFormat df = new DecimalFormat("##.##");

        Integer rowCount = 0;

        Row row = sheet.createRow(++rowCount);
        Cell cell = row.createCell(1);
        cell.setCellValue("Results");

        createExcelRow("Correct:", Double.toString(resultSet.numberOfCorrectSelections), sheet, ++rowCount);
        createExcelRow("Incorrect:", Double.toString(resultSet.numberOfIncorrectSelections), sheet, ++rowCount);
        createExcelRow("Accuracy:", df.format(resultSet.getAccuracy()) + "%", sheet, ++rowCount);
        for (int i = 0; i < Settings.categoryItemsList.size(); i++) {
            createExcelRow("Precision " + Settings.categoryItemsList.get(i),
                    df.format(resultSet.getPrecisionRatioList().get(Settings.categoryItemsList.get(i)) * 100) + "%",
                    sheet,
                    ++rowCount);
            createExcelRow("Recall " + Settings.categoryItemsList.get(i),
                    df.format(resultSet.getRecallRatioList().get(Settings.categoryItemsList.get(i))* 100) + "%",
                    sheet,
                    ++rowCount);
        }

        row = sheet.createRow(++rowCount);
        cell = row.createCell(1);
        cell.setCellValue("");
        row = sheet.createRow(++rowCount);
        cell = row.createCell(1);
        cell.setCellValue("Settings");

        createExcelRow("Category", Settings.category, sheet, ++rowCount);
        createExcelRow("Measure", Settings.keyWords, sheet, ++rowCount);
        createExcelRow("Metrics", Settings.metrics_measure, sheet, ++rowCount);
        createExcelRow("Training", Double.toString(Settings.percentOfTraining*100) + "%", sheet, ++rowCount);
        createExcelRow("Testing", Double.toString(100 - Settings.percentOfTraining*100) + "%", sheet, ++rowCount);
        createExcelRow("K value", Integer.toString(Settings.k), sheet, ++rowCount);
        createExcelRow("Key words per category", Integer.toString(Settings.numberOfKeyWordPerCategory), sheet, ++rowCount);
        createExcelRow("Wages", Boolean.toString(Settings.wages), sheet, ++rowCount);
        createExcelRow("Min Wage", Double.toString(Settings.minWage), sheet, ++rowCount);
        createExcelRow("Max Wage", Double.toString(Settings.maxWage), sheet, ++rowCount);

        row = sheet.createRow(++rowCount);
        cell = row.createCell(1);
        cell.setCellValue("");
        row = sheet.createRow(++rowCount);
        cell = row.createCell(1);
        cell.setCellValue("Features");

        for (int i = 0; i < Settings.featuresUsedMap.size(); i++) {
            createExcelRow(Settings.featuresOrder.get(i),
                    Boolean.toString(Settings.featuresUsedMap.get(Settings.featuresOrder.get(i))),
                    sheet,
                    ++rowCount);
        }

        row = sheet.createRow(++rowCount);
        cell = row.createCell(1);
        cell.setCellValue("");
        row = sheet.createRow(++rowCount);
        cell = row.createCell(1);
        cell.setCellValue("Category values");

        for (int i = 0; i < Settings.categoryItemsList.size(); i++) {
            createExcelRow(Settings.categoryItemsList.get(i),
                    "",
                    sheet,
                    ++rowCount);
        }

        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);

        try (FileOutputStream outputStream = new FileOutputStream(
                Settings.pathToReports +
                "KNN_Report_" +
                dtf.format(now) +
                ".xlsx")) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createExcelRow(String label, String value, XSSFSheet sheet, Integer rowCount) {
        Row row = sheet.createRow(rowCount);

        int columnCount = 0;
        Cell LabelCell = row.createCell(++columnCount);
        LabelCell.setCellValue(label);
        Cell valueCell = row.createCell(++columnCount);
        valueCell.setCellValue(value);
    }
}
