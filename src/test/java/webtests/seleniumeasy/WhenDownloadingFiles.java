package webtests.seleniumeasy;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.configuration.SessionLocalTempDirectory;
import org.awaitility.Awaitility;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import webtests.seleniumeasy.pageobjects.DownloadPage;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SerenityRunner.class)
public class WhenDownloadingFiles {

    @Managed
    WebDriver driver;

    DownloadPage downloadPage;
    @Test
    public void weCanDownloadAFileToOurHardDrive() throws InterruptedException {
        downloadPage.open();

        downloadPage.downloadSampleFile();

        File downloadFile = SessionLocalTempDirectory.forTheCurrentSession().resolve("sample.png").toFile();

        Awaitility.await().atMost(15, TimeUnit.SECONDS).until(downloadFile::exists);

        assertThat(downloadFile).exists();

        assertThat(downloadFile.getName()).isEqualTo("sample.png");
    }
}
