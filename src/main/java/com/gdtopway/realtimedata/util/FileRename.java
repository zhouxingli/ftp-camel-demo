package com.gdtopway.realtimedata.util;

import com.gdtopway.realtimedata.domain.State;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
//重命名文件
public class FileRename {

    public File reNameFile(String oldfilePath, String oldfileName,State state) {
        Map<String,String> map=new HashMap<String,String>();
        String filePath=oldfilePath+File.separator +oldfileName;//文件路径
        File oldFile=new File(filePath);//文件
        InputStream input=null;
        FileOutputStream output=null;
        String  reNameFilePath=null;//重命名文件路径
        File reNameFile=null;//重命名文件
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (oldFile.isFile() && oldFile.exists()){//判断文件是否已经存在
            try {
                InputStreamReader read = null;
                try {
                    read = new InputStreamReader(new FileInputStream(oldFile), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                BufferedReader bufferedReader = new BufferedReader(read);//读取文件
                String lineTxt = null;
                int num=0;
                String newStr=null;
                String[] s1=null;
                String[] s2=null;
                String s3=null;
                if ((lineTxt = bufferedReader.readLine()) != null) {
                    System.out.println(lineTxt);
                    s1=lineTxt.split(" ");//读取第一行数据，以空格分割成数组
                    s2=s1[1].split(":");//取出时分秒 ，以冒号分割成数组
                    s3=s2[0]+"-"+s2[1];//取出时、分
                    s2[2]="00";//将秒钟变成00
                    num=Integer.parseInt( s2[1]);//取出分钟
                    Date actualTime=formatter.parse(lineTxt.toString());
                    state.setActualTime(actualTime);//文件实际时间
                    switch(num/15){
                        case 0:
                            s2[1]="00";
                            newStr=s1[0]+" "+s2[0]+":"+s2[1]+":"+s2[2];
                            break;
                        case 1:
                            s2[1]="15";
                            newStr=s1[0]+" "+s2[0]+":"+s2[1]+":"+s2[2];
                            break;
                        case 2:
                            s2[1]="30";
                            newStr=s1[0]+" "+s2[0]+":"+s2[1]+":"+s2[2];
                            break;
                        case 3:
                            s2[1]="45";
                            newStr=s1[0]+" "+s2[0]+":"+s2[1]+":"+s2[2];
                            break;
                    }
                    Date stateTime = formatter.parse(newStr.toString());
                    state.setStateTime(stateTime);//状态表中加入规准时间
                }
                read.close();//关闭流
                reNameFilePath=oldFile.getParent()+ File.separator +s1[0]+" "+s3+".txt";
                reNameFile = new File(reNameFilePath);//重命名文件
                state.setOriginallyFilePath(reNameFilePath);//状态表中加入重命名文件路径
               //判断重命名文件是否已经存在
                if(!reNameFile.exists()){//如果不存在，改名
                    oldFile.renameTo(reNameFile);//修改临时文件名称（将从ftp文件改成重命名文件名）
                    state.setOriginallyFileState("success");//重命名文件是否成功
                    System.out.println("改名成功!");
                } else if(reNameFile.exists()){//如果已经存在重命名文件，则删除该文件
                    reNameFile.delete();
                    state.setOriginallyFileState("fail");
                    System.out.println("改名失败");
                }
            } catch (UnsupportedEncodingException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("文件不存在");
        }
        return reNameFile;
    }




}
