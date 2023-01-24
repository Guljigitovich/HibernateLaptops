package org.example;

import enums.OperationSystem;
import model.Laptop;
import service.LaptopService;
import service.LaptopServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        LaptopService laptopService = new LaptopServiceImpl();

        Laptop laptop = new Laptop("Asus", OperationSystem.WINDOWS, 512, 50000, LocalDate.of(2020, 10, 12));
        Laptop laptop1 = new Laptop("Lenovo", OperationSystem.LINUX, 256, 55000, LocalDate.of(2021, 8, 20));
        Laptop laptop2 = new Laptop("Acer", OperationSystem.WINDOWS, 512, 45000, LocalDate.of(2019, 7, 15));
        ArrayList<Laptop> laptopArrayList = new ArrayList<>(
                Arrays.asList(laptop1,laptop2,laptop)
        );

        System.out.println(laptopService.saveLaptop(laptop));
        System.out.println(laptopService.saveAll(laptopArrayList));
        System.out.println(laptopService.deleteById(2L));
        System.out.println(laptopService.findAll());
        laptopService.deleteAll();
        System.out.println(laptopService.update(1L, laptop));
        System.out.println(laptopService.groupBy());
        String world = new Scanner(System.in).nextLine();
        String world1 = new Scanner(System.in).nextLine();
        laptopService.sortByDifferentColumn(world,world1);
    }
}
