package com.springanddragons.application;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyInterfaceHolder {

    public interface MyInterface {
        void doStuff();
    }

    @AllArgsConstructor
    public static class MyImpl1 implements MyInterface {
        private int field1;

        MySubInterface subMember;

        @Override
        public void doStuff() {
            log.info("Impl1 - fieldValue: " + field1);
            subMember.doStuffToo();
        }
    }

    @AllArgsConstructor
    public static class MyImpl2 implements MyInterface {
        private int field1;

        MySubInterface subMember;

        @Override
        public void doStuff() {
            log.info("Impl2 - fieldValue: " + field1);
            subMember.doStuffToo();
        }
    }

    public interface MySubInterface {
        void doStuffToo();
    }

    @AllArgsConstructor
    public static class MySubClass1 implements MySubInterface {
        private int field1;
        public void doStuffToo() {
            log.info("MySubClass1 - fieldValue: " + field1);
        }
    }

    @AllArgsConstructor
    public static class MySubClass2 implements MySubInterface {
        private int field1;
        public void doStuffToo() {
            log.info("MySubClass2 - fieldValue: " + field1);
        }
    }

}
