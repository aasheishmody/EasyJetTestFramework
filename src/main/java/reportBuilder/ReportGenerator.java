package reportBuilder;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.picocontainer.classname.ClassName;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

public class ReportGenerator {
    private static Logger logger = Logger.getLogger(ClassName.class.getName());

    public static void main(String[] args) throws Throwable {
        ReportGenerator reportGenerator = new ReportGenerator();
        reportGenerator.mergeReports();
    }

    private void mergeReports() throws Exception {
        logger.info("Merging reports");
        AtomicReference<File> reportOutputDirectory;
        reportOutputDirectory = new AtomicReference<>(new File((System.getProperty("user.dir") + "/target/cucumber-html-report")));
        List<String> list = Arrays.asList(reportOutputDirectory.get().list((dir, name) -> name.endsWith(".json")));
        List<String> jsonReportFiles = new ArrayList<>();
        for (int i = 1; i <= list.size(); i++) {
            jsonReportFiles.add((System.getProperty("user.dir") + "/target/cucumber-html-report/" + i + ".json"));
        }
        logger.info("Reports merged");
        Configuration configuration;
        configuration = new Configuration(reportOutputDirectory.get(), "easyJet Test Report - Web" );
        ReportBuilder reportBuilder = new ReportBuilder(jsonReportFiles, configuration);
        logger.info("Generating report");
        try {
            reportBuilder.generateReports();
            logger.info("Report generated");
        } catch (Throwable exception) {
            exception.printStackTrace();
            logger.severe(exception.getMessage());
        }
    }
}