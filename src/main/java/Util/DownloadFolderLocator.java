package Util;

import java.io.File;
import java.nio.file.Paths;

public class DownloadFolderLocator {

    public enum FileFormat {
        PDF(".pdf"),
        CSV(".csv"),
        WORD(".docx"),
        EXCEL(".xlsx");

        private final String extension;

        FileFormat(String extension) {
            this.extension = extension;
        }

        public String getExtension() {
            return extension;
        }
    }

    public static File downloadedFile(String baseName, FileFormat format) {
        String userHome = System.getProperty("user.home");
        String fullFileName = baseName + format.getExtension();
        File downloadFile = Paths.get(userHome, "Downloads", fullFileName).toFile();

        if (!downloadFile.exists()) {
            throw new IllegalStateException("Downloaded file not found: " + downloadFile.getAbsolutePath());
        }

        return downloadFile;
    }
}
