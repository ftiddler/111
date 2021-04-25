package com.example.demo.common.utils;

import com.example.demo.common.FileType;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Huang Yuchen
 * @version 1.0
 * @date 2021-03-20 13:56
 */
public class FileUtils {

    public final static String separator = File.separator.replace("\\", "/");
    public final static String ZIP_EX = ".zip";

    /**
     * 获取当前服务器target/classes的路径
     * @return
     * @throws FileNotFoundException
     */
    public static String getWebPath() throws FileNotFoundException {
        return ResourceUtils.getURL("classpath:").getPath();
    }

    public static boolean createDir(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {
            System.out.println("创建目录" + destDirName + "成功，目标目录已经存在");
            return true;
        }
        destDirName.replace("\\", "/");
        if (!destDirName.endsWith(separator)) {
            destDirName = destDirName + separator;
        }
        //创建目录
        if (dir.mkdirs()) {
            System.out.println("创建目录" + destDirName + "成功！");
            return true;
        } else {
            System.out.println("创建目录" + destDirName + "失败！");
            return false;
        }
    }

    public static boolean removeDir(String destDirName) {
        File file = new File(destDirName);
        //判断文件不为null或文件目录存在
        if (file == null || !file.exists()){
            System.out.println("文件删除失败,请检查文件路径是否正确");
            return false;
        }
        //取得这个目录下的所有子文件对象
        File[] files = file.listFiles();
        //遍历该目录下的文件对象
        for (File f: files){
            //判断子目录是否存在子目录,如果是文件则删除
            if (f.isDirectory()){
                deleteFile(f.getPath());
            }else {
                f.delete();
            }
        }
        //删除空文件夹  for循环已经把上一层节点的目录清空。
        file.delete();
        return true;
    }

    public static boolean deleteFile(String path) {
        File file = new File(path);
        boolean delete = file.delete();
        return delete;
    }

    public static File[] getFiles(String destDirName) {
        File dir = new File(destDirName);
        File[] files = dir.listFiles();
        return files;
    }

    public static File newFile(String filepath) {
        File file = new File(filepath);
        if (file.exists()) return file;
        return null;
    }

    public static long fileCopyForImage(File f1,File f2)  throws  Exception{
        long  time = new  Date().getTime();
        int  length = 2097152 ;
        FileInputStream in = new  FileInputStream(f1);
        RandomAccessFile out = new  RandomAccessFile(f2, "rw" );
        FileChannel inC = in.getChannel();
        MappedByteBuffer outC = null ;
        MappedByteBuffer inbuffer = null ;
        byte [] b = new byte [length];
        while ( true ){
            if (inC.position() == inC.size()){
                inC.close();
                outC.force();
                out.close();
                return new Date().getTime() - time;
            }
            if ((inC.size() - inC.position()) < length){
                length = ( int )(inC.size() - inC.position());
            } else {
                length = 20971520 ;
            }
            b = new   byte [length];
            inbuffer = inC.map(FileChannel.MapMode.READ_ONLY,inC.position(),length);
            inbuffer.load();
            inbuffer.get(b);
            outC = out.getChannel().map(FileChannel.MapMode.READ_WRITE,inC.position(),length);
            inC.position(b.length + inC.position());
            outC.put(b);
            outC.force();
        }
    }

    public static File packedFilesToZip(String zipName, String tempDirName) {
        File[] files = FileUtils.getFiles(tempDirName);
        File zipTmpFile = new File(zipName);
        try {
            if (zipTmpFile.exists()) {
                zipTmpFile.delete();
            }
            zipTmpFile.createNewFile();
            // 创建文件输出流
            FileOutputStream fous = new FileOutputStream(zipTmpFile);
            ZipOutputStream zipOut = new ZipOutputStream(fous);
            zipFile(files, zipOut);
            zipOut.close();
            fous.close();
//            downloadZip(zipTmpFile, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return zipTmpFile;
    }

    private static void zipFile(File[] files, ZipOutputStream outputStream) {
        for (File file : files) {
            zipFile(file, outputStream);
        }
    }

    private static void zipFile(File inputFile, ZipOutputStream ouputStream) {
        try {
            if (inputFile.exists()) {
                if (inputFile.isFile()) {
                    FileInputStream IN = new FileInputStream(inputFile);
                    BufferedInputStream bins = new BufferedInputStream(IN, 512);
                    ZipEntry entry = new ZipEntry(inputFile.getName());
                    ouputStream.putNextEntry(entry);
                    int nNumber;
                    byte[] buffer = new byte[512];
                    while ((nNumber = bins.read(buffer)) != -1) {
                        ouputStream.write(buffer, 0, nNumber);
                    }
                    bins.close();
                    IN.close();
                } else {
                    try {
                        File[] files = inputFile.listFiles();
                        for (int i = 0; i < files.length; i++) {
                            zipFile(files[i], ouputStream);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将文件头转换成16进制字符串
     * @param src
     * @return 16进制字符串
     */
    private static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 得到文件头
     * @param is
     * @return 文件头
     * @throws IOException
     */
    private static String getFileContent(InputStream is) throws IOException {
        byte[] b = new byte[28];
        InputStream inputStream = null;
        try {
            is.read(b, 0, 28);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw e;
                }
            }
        }
        return bytesToHexString(b);
    }

    /**
     * 获取文件类型类
     * @param is
     * @return 文件类型
     */
    public static FileType getType(InputStream is) throws IOException {
        String fileHead = getFileContent(is);
        if (fileHead == null || fileHead.length() == 0) {
            return null;
        }
        fileHead = fileHead.toUpperCase();
        FileType[] fileTypes = FileType.values();
        for (FileType type : fileTypes) {
            if (fileHead.startsWith(type.getValue())) {
                return type;
            }
        }
        return null;
    }

    /**
     * 获取文件类型
     * @param is
     * @return
     * @throws Exception
     */
    public static String getFileType(InputStream is) throws Exception{
        FileType fileType = getType(is);
        if(fileType!=null){
            return fileType.getValue();
        }
        return null;
    }

}
