package com.ipnet.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * java启动python脚本，我们规定路径以python项目中脚本实际位置为准
 * 路径应由src/main/python为前置路径
 */

public class PythonRunnerUtil {

    private static final String COMMAND = "python3";

   /**
    * params表示传入的参数
    */
   public static void run(String pythonFilePath, String[] params){
       String[] args = new String[2 + params.length];
      System.out.println(args.length);
       args[0] = COMMAND;
       args[1] = pythonFilePath;
     //  System.out.println(args[0]);
     //  System.out.println(args[1]);
       System.arraycopy(params, 0, args, 2, params.length);
   //    System.out.println(args[2]);
       try {
           Process process = Runtime.getRuntime().exec(args);
           BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
           String line;
           while ((line = in.readLine()) != null) {
               System.out.println(line);
           }
           in.close();
           process.waitFor();
           System.out.println("end");
       }catch (Exception ex){
           ex.printStackTrace();
       }
   }

//   public static void main(String[] args){
//       /**
//        *
//        String path = "src/main/python/tracking/tracker.py";
//        String[] pArgs = {};
//        run(path, pArgs);**/
//
//   }

}
