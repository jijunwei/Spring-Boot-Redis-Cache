package com.springboot.util.xls;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;


public class XlsxUtil {

    private String workBookName;
    private XSSFWorkbook workBook;

    private List<SheetWapper> sheets;
    private static final Logger LOGGER = LoggerFactory.getLogger(XlsxUtil.class);


    public XlsxUtil() {
        this.workBook = new XSSFWorkbook();
        this.sheets = new ArrayList<SheetWapper>();
    }

    public String getWorkBookName() {
        return this.workBookName;
    }

    public void createWorkBook(String name) {
        this.workBookName = name;
    }

    public void createSheet(String name, List<Title> titles, List<?> datas) {
        XSSFSheet sheet = this.workBook.createSheet(name);
        Title[] titleArr = new Title[titles.size()];
        for (int i = 0; i < titleArr.length; i++) {
            sheet.setColumnWidth(i, 5000);
        }
        SheetWapper wapper = new SheetWapper(sheet, titles.toArray(titleArr), datas);
        sheets.add(wapper);
    }


    public XSSFSheet in(File file, int sheetIdx) throws Exception {
        XSSFWorkbook wb = new XSSFWorkbook(file);
        return wb.getSheetAt(sheetIdx);
    }


    public void out(OutputStream os) {
        for (SheetWapper sheetWapper : sheets) {
            sheetWapper.init();
        }

        try {
            workBook.write(os);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private static class SheetWapper {
        private final SimpleDateFormat dateFormat;
        private final short currencyFormat;

        private final XSSFSheet sheet;

        private Title[] titles;

        private List<?> datas;

        public SheetWapper(XSSFSheet sheet, Title[] titles, List<?> datas) {
            this.sheet = sheet;
            this.titles = titles;
            this.datas = datas;
            this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            this.currencyFormat = sheet.getWorkbook().createDataFormat().getFormat("###,##0.00");
        }

        public void init() {
            initTitle();
            initData();
        }

        private void initTitle() {
            XSSFRow row = sheet.createRow(0);

            XSSFCell firstCell = row.createCell(0);
            firstCell.setCellValue("");

            for (int i = 0; i < titles.length; i++) {
                XSSFCell cell = row.createCell(i);
                XSSFRichTextString text = new XSSFRichTextString(titles[i].getName());
                cell.setCellValue(text);

                if (null != titles[i].getStyle()) {
                    Style style = titles[i].getStyle();
                    XSSFCellStyle cellStyle = getCellStyle(style);
                    cell.setCellStyle(cellStyle);
                }
            }
        }

        private void initData() {
            Map<String, Method> methodMapper = getMethodMapper();

            int lastRowNum = sheet.getLastRowNum() + 1;
            for (Object obj : datas) {
                XSSFRow row = sheet.createRow(lastRowNum);

                for (int i = 0; i < titles.length; i++) {
                    XSSFCell cell = row.createCell(i);
                    Title title = titles[i];

                    Method method = methodMapper.get(title.getFieldName());
                    Comparable<?> value = null;
                    try {
                        value = (Comparable<?>) method.invoke(obj);
                    } catch (Exception e) {
                        LOGGER.error(e.getMessage(), e);
                    }

                    XSSFCellStyle cellStyle = null;
                    Map<Rule, Style> ruleStyleMapper = title.getRuleStyleMapper() != null ? title.getRuleStyleMapper() : new HashMap<Rule, Style>();
                    Set<Rule> rules = ruleStyleMapper.keySet();
                    for (Rule rule : rules) {
                        if (!rule.validate(value)) {
                            Style style = ruleStyleMapper.get(rule);
                            cellStyle = getCellStyle(style);
                            cell.setCellStyle(cellStyle);
                            break;
                        }
                    }

                    Title.ToType toType = title.getToType();
                    switch (toType) {
                        case String:
                            if (null != value) {
                                cell.setCellValue(value.toString());
                            } else {
                                cell.setCellValue("");
                            }
                            break;
                        case Number:
                            if (null != value) {
                                cell.setCellValue(Double.valueOf(value.toString()));
                            } else {
                                cell.setCellValue(0);
                            }
                            break;
                        case Currency:
                            if (null != value) {
                                XSSFCellStyle currencyStyle = cell.getCellStyle();
                                if (null == currencyStyle) {
                                    XSSFWorkbook workbook = sheet.getWorkbook();
                                    currencyStyle = workbook.createCellStyle();
                                    cell.setCellStyle(currencyStyle);
                                }
                                currencyStyle.setDataFormat(currencyFormat);
                                cell.setCellValue(Double.valueOf(value.toString()));
                            } else {
                                cell.setCellValue("0.00");
                            }
                            break;
                        case Date:
                            if (null != value) {
                                cell.setCellValue(dateFormat.format(value));
                            } else {
                                cell.setCellValue("");
                            }
                            break;
                        default:
                            cell.setCellValue("");
                    }
                }

                lastRowNum++;
            }
            //统计
            statistics();
        }

        private Map<String, Method> getMethodMapper() {
            Map<String, Method> methodMap = new HashMap<String, Method>();

            for (Object obj : datas) {
                Class<?> cls = obj.getClass();

                Method[] methods = cls.getMethods();
                for (Method method : methods) {
                    String methodName = method.getName();
                    if (methodName.startsWith("get")) {
                        if (methodName.length() > 3) {
                            StringBuilder builder = new StringBuilder(methodName.substring(3));
                            char chr = Character.toLowerCase(builder.charAt(0));
                            builder.setCharAt(0, chr);
                            methodMap.put(builder.toString(), method);
                        } else {
                            methodMap.put("", method);
                        }
                    }
                }

                break;
            }

            return methodMap;
        }

        private XSSFCellStyle getCellStyle(Style style) {
            XSSFWorkbook workbook = sheet.getWorkbook();

            XSSFCellStyle cellStyle = workbook.createCellStyle();

            XSSFFont font = workbook.createFont();
            font.setColor(style.getFontColor());
            cellStyle.setFont(font);

            cellStyle.setFillForegroundColor(style.getBackgroundColor());
            cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

            return cellStyle;
        }

        private void statistics() {
            int rowNum = sheet.getLastRowNum() + 1;
            XSSFRow row = sheet.createRow(rowNum);
            for (int i = 0; i < titles.length; i++) {
                Title title = titles[i];
                if (title.getFormula() != null) {
                    XSSFCell createCell = row.createCell(i);
                    String formula = title.getFormula().getFormula(i, 2, i, sheet.getLastRowNum(), title.getPrefix(), title.getSuffix());
                    createCell.setCellFormula(formula);
                }
            }
        }
    }


}
