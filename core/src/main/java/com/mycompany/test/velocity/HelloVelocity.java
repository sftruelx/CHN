package com.mycompany.test.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liaoxiang on 2016/6/8.
 */
public class HelloVelocity {
    static String rootPath = "d:\\velocity" ;
    public static void main(String[] args) {
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

        ve.init();
        Template serviceTpt = ve.getTemplate("myService.vm");
        Template modelTpt = ve.getTemplate("myModel.vm");
        ClassDescritpt cd = SysRightFactory.getInstance();
        VelocityContext ctx = new VelocityContext();
/*        ctx.put("tableName","sys_user");
        ctx.put("classNameLowCase", "sysRight");
        ctx.put("className", "SysRight");
        String[][] attrs = {
                {"Integer","id"},
                {"String","rightText"},
                {"String","rightUrl"},
                {"Long","parentId"},
                {"SysRight","parent"}
        };
        ctx.put("attrs", attrs);*/
        ctx.put("cd", cd);

        merge(serviceTpt,ctx,rootPath+"/Teacher.java");
        merge(modelTpt,ctx,rootPath+"/Model.java");

        System.out.println("success..."+ rootPath);
    }

    private static void merge(Template template, VelocityContext ctx, String path) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(path);
            template.merge(ctx, writer);
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }
}
