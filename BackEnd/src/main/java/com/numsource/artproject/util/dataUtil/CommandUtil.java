package com.numsource.artproject.util.dataUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandUtil {

    public static String doCmd(String commmand){
        try {

            String[] cmd = new String[]{"/bin/sh", "-c", commmand};

            Process ps = Runtime.getRuntime().exec(cmd);

            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));

            StringBuffer sb = new StringBuffer();

            String line;

            while ((line = br.readLine()) != null) {

                sb.append(line).append("\n");

            }

            String result = sb.toString();

            return result;

        } catch (Exception e) {

            e.printStackTrace();
            return e.toString();
        }
    }
}
