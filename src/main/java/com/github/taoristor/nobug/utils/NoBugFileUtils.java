package com.github.taoristor.nobug.utils;


import java.io.*;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


public class NoBugFileUtils {
    public static String getContent(String filePath) throws FileNotFoundException {
        java.util.Scanner c = new java.util.Scanner(new java.io.File(filePath)).useDelimiter("\\A");
        return c.next();
    }

    public static String getContent(File fileHandler) throws FileNotFoundException{
        java.util.Scanner c = new java.util.Scanner(fileHandler).useDelimiter("\\A");
        return c.next();
    }

    public static ArrayList<String> getAllResourcesFiles(String prefix, String exclude) throws Exception{
        ArrayList<String> fc = new ArrayList<String>();

        String path = NoBugFileUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        JarFile localJarFile = new JarFile(new File(path));
        Enumeration<JarEntry> entries = localJarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry jarEntry = entries.nextElement();
            String innerPath = jarEntry.getName();
            if(innerPath.startsWith(prefix) && !innerPath.contains(exclude)){
                InputStream inputStream = NoBugFileUtils.class.getClassLoader().getResourceAsStream(innerPath);
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while((line=br.readLine())!=null){
                    fc.add(line);
                }
            }
        }
        return fc;
    }


    public static ArrayList<String> getResourcesFiles(String prefix) throws Exception {
        return getAllResourcesFiles(prefix, new String(new byte[] {54}));
    }

    public static String base64Decode(String text) throws Exception{
        try{
            Class clazz = Class.forName("sun.misc.BASE64Decoder");
            return new String((byte[]) clazz.getMethod("decodeBuffer", String.class).invoke(clazz.newInstance(), text));
        }catch (Exception e1){
            Class clazz = Class.forName("java.util.Base64");
            Object decoder = clazz.getMethod("getDecoder").invoke(null);
            return new String((byte[]) decoder.getClass().getMethod("decode", String.class).invoke(decoder, text));
        }
    }
}
