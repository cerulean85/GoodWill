package com.kkennib;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ProcessTest {

//    private static PythonInterpreter intp;

    public static void main(String[] args) throws IOException {
        System.out.println("Python Call");

//        try{
//            Process p= Runtime.getRuntime().exec("python C:/Users/JHKIM/Desktop/GoodWill/goodwillpublisher/src/main/resources/UnitTest.py");
//            p.waitFor();
//            p.
//        } catch (InterruptedException ex){
//            System.out.println(ex.getMessage());
//        }


        String line = "python C:/Users/JHKIM/Desktop/GoodWill/goodwillpublisher/src/main/resources/UnitTest.py";
        CommandLine cmdLine = CommandLine.parse(line);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);

        DefaultExecutor executor = new DefaultExecutor();
        executor.setStreamHandler(streamHandler);

        int exitCode = executor.execute(cmdLine);
        System.out.println(outputStream.toString());
//        assertEquals("No errors should be detected", 0, exitCode);
//        assertEquals("Should contain script output: ", "Hello Baeldung Readers!!", outputStream.toString()
//                .trim());

    }

//        PySystemState sys = Py.getSystemState();
//        sys.path.clear();
//        sys.path.append(new PyString("src/main/resources/venv"));
//        System.setProperty("python.cachedir.skip", "true");
////        System.setProperty("python.import.site", "false");
//
//        String[] ss =
//        {
//                "",
//                "C:/Users/JHKIM/Anaconda3/python37.zip",
//                        "C:/Users/JHKIM/Anaconda3/DLLs",
//                        "C:/Users/JHKIM/Anaconda3/lib",
//                        "C:/Users/JHKIM/Anaconda3",
//                        "C:/Users/JHKIM/Desktop/Awesome/Crawler/venv",
//                        "C:/Users/JHKIM/Desktop/Awesome/Crawler/venv/lib/site-packages",
//                        "C:/Users/JHKIM/Desktop/Awesome/Crawler/venv/lib/site-packages/setuptools-40.8.0-py3.7.egg",
//                        "C:/Users/JHKIM/Desktop/Awesome/Crawler/venv/lib/site-packages/pip-19.0.3-py3.7.egg"
//        };
//
//        for (int i=0; i< ss.length; i++) {
//            sys.path.append(new PyString(ss[i]));
//        }
//
//
//
//        intp = new PythonInterpreter();
////        intp.exec("from java.lang import System");
////        intp.exec("s = "Hello World"");
////        intp.exec("System.out.println(s)");
////        intp.exec("print(s)");
////        intp.exec("print(s[1:-1])");
//
////        intp.execfile("src/main/resources/CollectService.py");
////        intp.exec("pieceDonga("sampleurl.txt", "비대면", "202010", "202011")");
////        intp.exec("print(addition(7,8))");

}
