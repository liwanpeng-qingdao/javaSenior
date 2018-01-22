package com.liwanpeng.IO;

import java.io.File;
import java.nio.file.Files;

/**
 * 递归方式输出一个目录所有的文件
 * Created by liwanpeng on 2018/1/19.
 */
public class FileListTest {


        public  void visitFile(String path){
            File file = new File(path);
            File[] files = file.listFiles();
            if(files==null){

                return;

            }
            for (File file1:files)
            {
                if (file1.isDirectory())
                {
                    System.out.println("Directory");
                    visitFile(file1.getPath());
                }else if (file1.isFile())
                {
                    System.out.println(file.getPath()+"\\"+file1.getName());
                }
            }
        }

    public static void main(String[] args) {
        FileListTest aa = new FileListTest();
        aa.visitFile("E:\\iotest\\");
    }
}

