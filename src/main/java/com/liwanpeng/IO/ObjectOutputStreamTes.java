package com.liwanpeng.IO;

import java.io.*;

/**
 * java io读写对象
 * Created by liwanpeng on 2018/1/17.
 */
public class ObjectOutputStreamTes {
    public static void main(String[] args) {
        // TODO自动生成的方法存根
        ObjectOutputStream objectwriter = null;
        ObjectInputStream objectreader = null;

        try {
            objectwriter = new ObjectOutputStream(new FileOutputStream("E:/iotest/student.txt"));
            objectwriter.writeObject(new Student("gg", 22));
            objectwriter.writeObject(new Student("tt", 18));
            objectwriter.writeObject(new Student("rr", 17));
            objectreader = new ObjectInputStream(new FileInputStream("E:/iotest/student.txt"));
            for (int i = 0; i < 3; i++) {
                System.out.println(objectreader.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            // TODO自动生成的 catch 块
            e.printStackTrace();
        } finally {
            try {
                objectreader.close();
            } catch (IOException e) {
                // TODO自动生成的 catch 块
                e.printStackTrace();
            }
            try {
                objectwriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
    class Student implements Serializable{
        private String name;
        private int age;

        public Student(String name, int age) {
            super();
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student [name=" + name + ", age=" + age + "]";
        }


    }
