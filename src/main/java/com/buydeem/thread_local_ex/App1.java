package com.buydeem.thread_local_ex;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.ref.WeakReference;

/**
 * Created by zengchao on 2019/11/22.
 */
public class App1 {

    public static void main(String[] args) {
        Student tom = new Student("tom");
        WeakReference<Student> weakReTom = new WeakReference<>(tom);
        System.out.println(weakReTom.get());
        //tom = null;
        System.gc();
        System.out.println(weakReTom.get());

    }
}

@Data
@AllArgsConstructor
class Student{
    private String name;

    @Override
    protected void finalize() throws Throwable {
        System.out.println(this.toString()+"被回收了");
        super.finalize();
    }
}
