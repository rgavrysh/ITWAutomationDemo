package appium;

import driverFactory.MainProperties;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;

import java.io.IOException;

/**
 * Created by rgavrysh on 10/7/2016.
 */
public class AppiumController {

    private static final String appiumJSPath = MainProperties.getProperty("appium_js_path"); //"C:\\Program Files (x86)\\Appium\\node_modules\\appium\\bin\\appium.js";

    public static void startAppiumServer() throws IOException, InterruptedException {
        CommandLine commandLine = new CommandLine("cmd");
        commandLine.addArgument("/c")
                .addArgument("node")
                .addArgument(appiumJSPath)
                .addArgument("--platform-name")
                .addArgument(MainProperties.getProperty("platform_name"))
                .addArgument("--automation-name")
                .addArgument("Appium");
        executeCommand(commandLine);
        //todo: control command line output, wait until server is ready
        Thread.sleep(15000L);
    }

    public static void stopAppiumServer() throws IOException {
        CommandLine commandLine = new CommandLine("cmd");
        commandLine.addArgument("/c")
                .addArgument("taskkill")
                .addArgument("/F")
                .addArgument("/IM")
                .addArgument("node.exe");
        executeCommand(commandLine);
    }

    private static void executeCommand(CommandLine commandLine) throws IOException {
        DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
        DefaultExecutor executor = new DefaultExecutor();
        executor.setExitValue(1);
        executor.execute(commandLine, resultHandler);
    }
}
