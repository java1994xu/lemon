package com.lemon.utils;

import cn.hutool.core.io.FileUtil;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.FieldNumPages;
import com.aspose.words.FieldType;
import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import com.itextpdf.text.pdf.PdfReader;
import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.hslf.usermodel.SoundData;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xslf.XSLFSlideShow;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;

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
    public static String getFilePages(String suffix, InputStream fis ,String fullPath) throws Exception {

        Integer pageCount = 0;
        if (".doc".equals(suffix) || ".docx".equals(suffix)) {
            String destPath = fullPath.replace(suffix,".pdf");
//第一种1、aspose直接读取
//            Document doc = new Document(fis);
//            pageCount = doc.getPageCount();
//第二种2、aspose转换pdf
            WordPdfUtils.doc2pdf(fullPath,destPath);
            //第三种：转换pdf
//            WordUtils.word2Pdf(fullPath,destPath);
            PdfReader pdfReader = new PdfReader(new FileInputStream(new File(destPath)));
            pageCount = pdfReader.getNumberOfPages();
//            FileUtil.del(destPath);
//            System.out.println("删除文件结束");

        } else if (".ppt".equals(suffix)) {
            SlideShow pptfile = new SlideShow(new HSLFSlideShow(fis));
            pageCount = pptfile.getSlides().length;

        } else if (".pptx".equals(suffix)) {
            XMLSlideShow pptxfile = new XMLSlideShow(fis);
            pageCount = pptxfile.getSlides().length;

        } else if (".pdf".equals(suffix)) {
            PdfReader pdfReader = new PdfReader(fis);
            pageCount = pdfReader.getNumberOfPages();

        } else if (".jpg".equals(suffix) || ".png".equals(suffix)) {
            pageCount = 1;

        }

        return pageCount.toString();
    }



    public static void word2Pdf(String origPath, String destPath) {
        File inputWord = new File(origPath);
        File outputFile = new File(destPath);
        try {
            InputStream docxInputStream = new FileInputStream(inputWord);
            OutputStream outputStream = new FileOutputStream(outputFile);
            IConverter converter = LocalConverter.builder().build();
            converter.convert(docxInputStream).as(DocumentType.DOCX).to(outputStream).as(DocumentType.PDF).execute();
            docxInputStream.close();
            outputStream.close();
            System.out.println("success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
