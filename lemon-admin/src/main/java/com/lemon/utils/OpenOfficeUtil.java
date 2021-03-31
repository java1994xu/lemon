package com.lemon.utils;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.AbstractOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConfiguration;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.artofsolving.jodconverter.openoffice.converter.StreamOpenOfficeDocumentConverter;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;

import java.io.File;
import java.net.ConnectException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author xubb
 * @Description
 * @create 2021-03-04 0:04
 */
public class OpenOfficeUtil {

    private String UPLOAD_URL;

    private String DOCUMENT_URL;

    /**
     * 使Office2003-2007全部格式的文档(.doc|.docx|.xls|.xlsx|.ppt|.pptx) 转化为pdf文件<br>
     *
     * @param inputFilePath  源文件路径，如："e:/test.docx"
     * @param outputFilePath 目标文件路径，如："e:/test_docx.pdf"
     * @return
     */
    public static Map openOfficeToPDF(String inputFilePath, String outputFilePath) {
        return office2pdf(inputFilePath, outputFilePath);
    }

    /**
     * 根据操作系统的名称，获取OpenOffice 的安装目录<br>
     * 如我的OpenOffice 安装在：C:/Program Files (x86)/OpenOffice 4
     *
     * @return OpenOffice 的安装目录
     */
    private static String getOfficeHome() {
        String osName = System.getProperty("os.name");
        if (Pattern.matches("Linux.*", osName)) {
            return "/opt/openoffice4";
        } else if (Pattern.matches("Windows.*", osName)) {
            return "C:/Program Files (x86)/OpenOffice 4";
        } else if (Pattern.matches("Mac.*", osName)) {
            return "/Application/OpenOffice.org.app/Contents";
        }
        return null;
    }

    /**
     * 连接OpenOffice 并且启动OpenOffice
     *
     * @return
     */
    private static OfficeManager getOfficeManager() {


        DefaultOfficeManagerConfiguration config = new DefaultOfficeManagerConfiguration();
        // 获取OpenOffice 安装目录
        String officeHome = getOfficeHome();
        System.out.println("officeHome="+officeHome);
        config.setOfficeHome(officeHome);
        // 启动OpenOffice的服务
        OfficeManager officeManager = config.buildOfficeManager();
        officeManager.start();
        return officeManager;
    }

    /**
     * 转换文件
     *
     * @param inputFile
     * @param outputFilePath_end
     * @param inputFilePath
     * @param outputFilePath
     * @param converter
     */
    private static void converterFile(File inputFile, String outputFilePath_end, String inputFilePath, String outputFilePath, DocumentConverter converter) {
        File outputFile = new File(outputFilePath_end);
        // 假如目标路径不存在,则新建该路径
        if (!outputFile.getParentFile().exists()) {
            outputFile.getParentFile().mkdirs();
        }
        converter.convert(inputFile, outputFile);
        System.out.println("文件：" + inputFilePath + "\n转换为\n目标文件：" + outputFile + "\n成功！");
    }

    /**
     * 使Office2003-2007全部格式的文档(.doc|.docx|.xls|.xlsx|.ppt|.pptx) 转化为pdf文件<br>
     *
     * @param inputFilePath  源文件路径
     * @param outputFilePath 目标文件路径
     * @return
     */
    private static Map office2pdf(String inputFilePath, String outputFilePath) {
        System.out.println("start office2pdf");
        Map<String, String> tResultMap = new HashMap<>();
        tResultMap.put("status", "fail");
        boolean flag = false;

        // 连接OpenOffice
        OpenOfficeConnection connection = new SocketOpenOfficeConnection("127.0.0.1",8100);
        DocumentConverter converter = new StreamOpenOfficeDocumentConverter(connection);
        long begin_time = System.currentTimeMillis();
        outputFilePath =  inputFilePath; //这两行代码不可以调换，你可以试试调换之后会出现什么错误
        inputFilePath =  inputFilePath; //你也可以不建，他会自己建

        String outputFilePath_end = "";
        File tFile = new File(inputFilePath);
        File oFile = new File(outputFilePath);
        if (!tFile.exists() || tFile.isDirectory()) {
            tResultMap.put("msg", "该文件或文件夹不存在");
            return tResultMap;
        }
        if (!oFile.getParentFile().exists()) {
            oFile.getParentFile().mkdirs();// 新建文件夹
        }
        //file.transferTo(dest);// 文件写入

        if (null != inputFilePath) {
            File inputFile = new File(inputFilePath);
            // 判断目标文件路径是否为空
            if (null == outputFilePath) {
                // 转换后的文件路径
                outputFilePath_end = getOutputFilePath(inputFilePath);
                if (inputFile.exists()) {// 找不到源文件, 则返回
                    converterFile(inputFile, outputFilePath_end, inputFilePath, outputFilePath, converter);
                    flag = true;
                }
            } else {
                outputFilePath_end = getOutputFilePath(outputFilePath);
                if (inputFile.exists()) {// 找不到源文件, 则返回
                    converterFile(inputFile, outputFilePath_end, inputFilePath, outputFilePath, converter);
                    flag = true;
                }
            }
            connection.disconnect();

        } else {
            System.out.println("con't find the resource");
        }
        long end_time = System.currentTimeMillis();
        System.out.println("文件转换耗时：[" + (end_time - begin_time) + "]ms");
        tResultMap.put("result", outputFilePath_end);
        tResultMap.put("status", "success");
        return tResultMap;
    }

    /**
     * 获取输出文件
     *
     * @param inputFilePath
     * @return
     */
    private static String getOutputFilePath(String inputFilePath) {
        String outputFilePath = inputFilePath.replaceAll("." + getPostfix(inputFilePath), ".pdf");
        return outputFilePath;
    }

    /**
     * 获取inputFilePath的后缀名，如："e:/test.pptx"的后缀名为："pptx"<br>
     *
     * @param inputFilePath
     * @return
     */
    private static String getPostfix(String inputFilePath) {
        return inputFilePath.substring(inputFilePath.lastIndexOf(".") + 1);
    }


}
