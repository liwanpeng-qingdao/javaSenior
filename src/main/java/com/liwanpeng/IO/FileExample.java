package com.liwanpeng.IO;

import sun.plugin.viewer.context.IExplorerAppletContext;

import java.io.*;

/**
 * Created by liwanpeng on 2018/1/17.
 */
public class FileExample {
    public static void main(String[] args) throws FileNotFoundException {
        BufferedInputStreamTest();
    }
    public static void  creatFile(){
        //File类主要用于文件和目录的创建、文件的查找和文件的删除等
        File f = new File("E:/iotest/create123");
        try {
            //当f中的文件路径不存在时，创建一个新的空文件,可以多级创建
           // f.createNewFile();
            //返回由此抽象路径名表示的文件或目录的名称，此例中输出的是E盘的容量
            System.out.println("该分区大小"+f.getTotalSpace()/(1024*1024*1024)+"G");
            //创建目录
            /**
             * mkdirs()可以建立多级文件夹， mkdir()只会建立一级的文件夹
             */
            f.mkdirs();
            System.out.println("文件名  "+f.getName());//返回文件名即 create123
            //返回父目录即： E:\iotest
            System.out.println("文件父目录字符串 "+f.getParent());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * fileInputStream使用
     */
    public static void inputStreamTest(){
        int count = 0;
        InputStream streamReader = null;//文件输入流
        try {
            //使用FileInputStreamm必须有finally块，因为FileInputStream有缓冲区，所以用完之后必须关闭，否则可能导致内存占满，数据丢失。
            /**
             * read方法，读取字节
             * int read();  //从输入流中读取单个字节数据（0~255），如到输入流末尾则返回-1
             int read(byte b[]);  //读多个字节
             */
            streamReader = new FileInputStream(new File("E:\\iotest\\create.txt"));
            byte[] contents = new byte[1024];
            while(streamReader.read(contents)!=-1) {  //一直读取文件字节
                count++;
            }
            System.out.println(count);
        }catch (final IOException e)
        {
            e.printStackTrace();
        }finally{
            try{
                streamReader.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * BufferedInputStream使用
     *
     */
    public static void  BufferedInputStreamTest() throws FileNotFoundException{
        File file = new File("E:\\iotest\\create.txt");
        FileInputStream fis = new FileInputStream(file);
        //如果文件不存在会自动创建,但不会创建目录，所以目录必须存在
        File outFile = new File("F:\\iotest\\create.txt");
        byte[] buffer=new byte[2];   //一次取出的字节数大小,缓冲区大小
        int numberRead=0;
        FileOutputStream outFil = new FileOutputStream(outFile);
        try {

            byte[] contents = new byte[10];
            int byteRead = 0;
            String strFileContents;
            //numberRead的目的在于防止最后一次读取的字节小于buffer长度，
            while(((numberRead=fis.read(buffer))!=-1)){
                //strFileContents = new String(contents,0,byteRead);
                outFil.write(buffer, 0, numberRead);       //否则会自动被填充0
                //System.out.println(strFileContents);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException ee)
        {
            ee.printStackTrace();
        }finally {
            try {
                outFil.close();
            }catch (IOException e)
            {
                e.printStackTrace();
            }
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
