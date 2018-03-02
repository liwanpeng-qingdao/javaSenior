package com.liwanpeng.TimerAndTimeTask;

import java.io.File;
import java.util.TimerTask;

/**
 * Created by liwanpeng on 2018/3/2.
 */
public class DeleteFileTimeTask extends TimerTask {
    @Override
    public void run() {
        File file=new File("f://iotest");
        deleteFolder(file);
    }
    public void deleteFolder(File file){
        File[] files = file.listFiles();
        for (File file1:files)
        {

            if (file1.isDirectory())
            {
                //递归调用
                deleteFolder(file1);
            }else {
                file1.delete();
                System.out.println("执行完毕");
            }
        }
    }
}
