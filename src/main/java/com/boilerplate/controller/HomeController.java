package com.boilerplate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.scheduling.annotation.Scheduled;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;

import java.io.IOException;
import java.net.URL;
import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @RequestMapping(value = {"/",})
    public String index(Model model) {
        return "home/index";
    }

    @GetMapping(path = {"/showCalculator"})
    @ResponseBody
    public Map<String, String> showCalculator(Model model,
                                              @RequestParam Map<String, String> reqParam) throws Exception {
        Map<String, String> result = new HashMap<String, String>();
        Path winiumPath = Paths.get("src/main", "resources/winium", "Winium.Desktop.Driver.exe");
        result.put("output", OpenCalcultor(winiumPath.toAbsolutePath().toString()));
        return result;

    }

    public void startWiniUmDesktop(String WINI_DESKTOP_EXE) throws IOException {
        Runtime.getRuntime().exec(WINI_DESKTOP_EXE, null);
    }

    public String OpenCalcultor(String WINI_DESKTOP_EXE) {
        WiniumDriver app = null;
        String output = "";
        try {
            Process process = Runtime.getRuntime().exec("taskkill /F /IM Winium.Desktop.Driver.exe");
            process.waitFor();
            process.destroy();
            startWiniUmDesktop(WINI_DESKTOP_EXE);
            Thread.sleep(5000);
            DesktopOptions options;
            String CALCULATOR = "C:\\Windows\\System32\\calc.exe";
            String WINIUM_PATH = "http://localhost:9999";
            options = new DesktopOptions();
            options.setDebugConnectToRunningApp(true);
            System.out.println(CALCULATOR);
            System.out.println(WINIUM_PATH);
            System.out.println(WINI_DESKTOP_EXE);
            options.setApplicationPath(CALCULATOR);
            app = new WiniumDriver(new URL(WINIUM_PATH), options);

            Thread.sleep(5000);

            app.findElement(By.name("Seven")).click();
            app.findElement(By.name("Plus")).click();
            app.findElement(By.name("Eight")).click();
            app.findElement(By.name("Equals")).click();
            Thread.sleep(5000);
            output = app.findElement(By.id("CalculatorResults")).getAttribute("Name");
            System.out.println("Result after addition is: " + output);
            app.quit();
        } catch (Exception e) {
            System.out.println("-------------------------------------------------------------------------------------------------");
            System.out.println("Calculator is not working....");
            System.out.println("-------------------------------------------------------------------------------------------------");
            app.quit();
        }

        return output;
    }

}
