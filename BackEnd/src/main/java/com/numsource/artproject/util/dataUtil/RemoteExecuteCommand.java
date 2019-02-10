package com.numsource.artproject.util.dataUtil;


import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.*;

@Slf4j
public class RemoteExecuteCommand {

    //字符编码默认是utf-8
    private static String DEFAULTCHART = "UTF-8";
    private static Connection conn;
    private String ip;
    private String userName;
    private String userPwd;

    /**
     * 远程登录linux的主机
     *
     * @return 登录成功返回true，否则返回false
     * @author cyg
     * @since V0.1
     */
    public Boolean login() {

        ServerInformation information = new ServerInformation();

        information.setIp("118.24.9.53");
        information.setUserName("ubuntu");
        information.setUserPwd("clf1256233771");

        ip = information.getIp();
        userName = information.getUserName();
        userPwd = information.getUserPwd();

        boolean flg = false;
        try {
            conn = new Connection(ip);
            conn.connect();//连接
            flg = conn.authenticateWithPassword(userName, userPwd);//认证
            if (flg) {
                System.out.println("服务器认证成功！");
                log.info("connection success...");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flg;
    }

    /**
     * @param cmd 即将执行的命令
     * @return 命令执行完后返回的结果值
     * @author cyg
     * 远程执行shll脚本或者命令
     */
    public String execute(String cmd) {
        String result = "";
        try {
            if (login()) {

                Session session = conn.openSession();//打开一个会话
                session.execCommand(cmd);//执行命令
                result = processStdout(session.getStdout(), DEFAULTCHART);

                //如果得到标准输出为空，说明脚本执行出错了
                if (StringUtils.isEmpty(result)) {
                    System.out.println("无返回值");
                    result = processStdout(session.getStderr(), DEFAULTCHART);
                }
                conn.close();
                session.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * @param cmd 即将执行的命令
     * @return 命令执行成功后返回的结果值，如果命令执行失败，返回空字符串，不是null
     * @author cyg
     * 远程执行shll脚本或者命令
     */
    public String executeSuccess(String cmd) {
        String result = "";
        try {
            if (login()) {
                Session session = conn.openSession();//打开一个会话
                session.execCommand(cmd);//执行命令
                result = processStdout(session.getStdout(), DEFAULTCHART);
                conn.close();
                session.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 解析脚本执行返回的结果集
     * @param in      输入流对象
     * @param charset 编码
     * @return 以纯文本的格式返回
     * @author cyg
     */
    public static String processStdout(InputStream in, String charset) {
        InputStream stdout = new StreamGobbler(in);
        StringBuffer buffer = new StringBuffer();

        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(stdout, charset));
            String line = null;
            while ((line = br.readLine()) != null) {
                buffer.append(line + "\n");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }


    public static void main(String[] args) {

        RemoteExecuteCommand rec = new RemoteExecuteCommand();

        try {
            System.out.println("=====第一个步骤=====");
            String result = rec.execute("ls;pwd");
            System.out.println(result);

            /*System.out.println("=====第二个步骤=====");
            String result2 = rec.execute("java -version");
            System.out.println(result2);*/

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            
        }
//        try {
//
//            String[] cmd = new String[]{"/bin/sh", "-c", " cd /home/hk/;ls "};
//
//            Process ps = Runtime.getRuntime().exec(cmd);
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
//
//            StringBuffer sb = new StringBuffer();
//
//            String line;
//
//            while ((line = br.readLine()) != null) {
//
//                sb.append(line).append("\n");
//
//            }
//
//            String result = sb.toString();
//
//            System.out.println(result);
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//        }

    }


}