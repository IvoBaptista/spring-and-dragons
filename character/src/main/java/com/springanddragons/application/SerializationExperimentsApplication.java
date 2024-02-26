package com.springanddragons.application;

import com.thoughtworks.xstream.XStream;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(value = "com.springanddragons", excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = CharacterApplication.class)})
@EntityScan(value = {"com.springanddragons", "org.axonframework.eventhandling.tokenstore",
        "org.axonframework.modelling.saga.repository.jpa",
        "org.axonframework.eventsourcing.eventstore.jpa"})
@EnableJpaRepositories(value = "com.springanddragons")
@Slf4j
public class SerializationExperimentsApplication implements CommandLineRunner {

    public static void main(String[] arg) {
        SpringApplication.run(SerializationExperimentsApplication.class, arg).close();
    }

    @Autowired
    XStream xStream;

    @Override
    public void run(String... args) {
        log.info("Starting init application");
        MyInterfaceHolder.MyImpl2 obj = new MyInterfaceHolder.MyImpl2(1, new MyInterfaceHolder.MySubClass1(2));
        String objStr = xStream.toXML(obj);
        log.info("serialized object: ");
        log.info(objStr);
        MyInterfaceHolder.MyInterface genObj = (MyInterfaceHolder.MyInterface) xStream.fromXML(objStr);
        genObj.doStuff();
        log.info("Ending init application");
    }

}
