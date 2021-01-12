package com.lemon.utils;

import com.aspose.words.Document;
import com.itextpdf.text.pdf.PdfReader;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author xubb
 * @Description
 * @create 2021-01-12 23:50
 */
public class WordUtils {

    /**
     * 获取文档的页数
     * 支持 doc,docx,ppt,pptx
     *
     * @param suffix
     * @param fis
     * @return
     * @throws Exception
     */
    public static String getFilePages(String suffix, InputStream fis) throws Exception {

        Integer pageCount = 0;
        if (".doc".equals(suffix) || ".docx".equals(suffix)) {
            Document doc = new Document(fis);
            pageCount = doc.getPageCount();

        } else if (".ppt".equals(suffix) || ".pptx".equals(suffix)) {
            PdfReader pdfReader = new PdfReader(fis);
            pageCount = pdfReader.getNumberOfPages();

        }

        return pageCount.toString();
    }


}
