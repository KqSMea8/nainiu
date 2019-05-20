package com.jeecg.jiekou.controller;

import org.jeecgframework.core.util.LogUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName PhoneBasicInfo
 * @Desprition TODO
 * @Author shishanshan
 * @Date 2018/11/29 9:00
 * @Version 1.0
 **/
@Controller
@RequestMapping(value = "/phoneBasicInfoController")
public class PhoneBasicInfoController {

    @RequestMapping(params = "getBasicInfo")
    public void getBasicInfo(HttpServletResponse response, HttpServletRequest request){
        try {
            String remoteAddr = request.getRemoteAddr();
            System.out.println(remoteAddr);
            String referrer = request.getHeader("Referer");
            System.out.println("Referer"+referrer);
            String requestHeader = request.getHeader("User-Agent");
            int index_one = requestHeader.indexOf("(");
            String requestBody = requestHeader.substring(index_one+1);
            String userInfo = requestBody.substring(0, requestBody.indexOf(")"));
            String[] userInfoList = userInfo.split(";");
            int length = userInfoList.length;
            String os = userInfoList[0];
            String mobileInfo = userInfoList[length - 1];
            if(os.equals("Windows NT 6.1")){
                System.out.println("您的操作系统为：windows7");
            }else{
                System.out.println("您的操作系统为：" + os);
            }
            int index = mobileInfo.indexOf("/");
            if(index > 0) {
                mobileInfo = mobileInfo.substring(0, mobileInfo.indexOf("/") - 5);
                System.out.println("您的手机型号为：" + mobileInfo);
            }
            String macAddress = getMacAddress(remoteAddr);
            System.out.println(macAddress);
        }catch (Exception e){
            e.printStackTrace();
            LogUtil.error("",e);
        }
    }
    /**
     * 获取MAC地址
     * @author
     * 2011-12
     */
        public  String callCmd(String[] cmd) {
            String result = "";
            String line = "";
            try {
                Process proc = Runtime.getRuntime().exec(cmd);
                InputStreamReader is = new InputStreamReader(proc.getInputStream());
                BufferedReader br = new BufferedReader(is);
                while ((line = br.readLine ()) != null) {
                    result += line;
                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        /**
         *
         * @param cmd 第一个命令
         * @param another 第二个命令
         * @return  第二个命令的执行结果
         */
        public  String callCmd(String[] cmd,String[] another) {
            String result = "";
            String line = "";
            try {
                Runtime rt = Runtime.getRuntime();
                Process proc = rt.exec(cmd);
                proc.waitFor(); //已经执行完第一个命令，准备执行第二个命令
                proc = rt.exec(another);
                InputStreamReader is = new InputStreamReader(proc.getInputStream());
                BufferedReader br = new BufferedReader (is);
                while ((line = br.readLine ()) != null) {
                    result += line;
                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            return result;
        }



        /**
         *
         * @param ip 目标ip,一般在局域网内
         * @param sourceString 命令处理的结果字符串
         * @param macSeparator mac分隔符号
         * @return mac地址，用上面的分隔符号表示
         */
        public  String filterMacAddress(final String ip, final String sourceString,final String macSeparator) {
            String result = "";
            String regExp = "((([0-9,A-F,a-f]{1,2}" + macSeparator + "){1,5})[0-9,A-F,a-f]{1,2})";
            Pattern pattern = Pattern.compile(regExp);
            Matcher matcher = pattern.matcher(sourceString);
            while(matcher.find()){
                result = matcher.group(1);
                if(sourceString.indexOf(ip) <= sourceString.lastIndexOf(matcher.group(1))) {
                    break; //如果有多个IP,只匹配本IP对应的Mac.
                }
            }

            return result;
        }



        /**
         *
         * @param ip 目标ip
         * @return  Mac Address
         *
         */
        public  String getMacInWindows(final String ip){
            String result = "";
            String[] cmd = {
                    "cmd",
                    "/c",
                    "ping " + ip
            };
            String[] another = {
                    "cmd",
                    "/c",
                    "arp -a"
            };

            String cmdResult = callCmd(cmd,another);
            result = filterMacAddress(ip,cmdResult,"-");

            return result;
        }

        /**
         * @param ip 目标ip
         * @return  Mac Address
         *
         */
        public  String getMacInLinux(final String ip){
            String result = "";
            String[] cmd = {
                    "/bin/sh",
                    "-c",
                    "ping " + ip + " -c 2 && arp -a"
            };
            String cmdResult = callCmd(cmd);
            result = filterMacAddress(ip,cmdResult,":");

            return result;
        }

        /**
         * 获取MAC地址
         * @return 返回MAC地址
         */
        public  String getMacAddress(String ip){
            String macAddress = "";
            macAddress = getMacInWindows(ip).trim();
            if(macAddress==null||"".equals(macAddress)){
                macAddress = getMacInLinux(ip).trim();
            }
            return macAddress;
        }
}
