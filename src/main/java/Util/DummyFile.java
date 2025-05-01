package Util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class DummyFile {

    private final String fileName;
    private final String filePath;
    private final FileFormat fileFormat;
    private final File file;

    public DummyFile(String fileName, FileFormat fileFormat) {
        this.fileName = fileName;
        this.fileFormat = fileFormat;
        this.filePath = System.getProperty("user.dir") + File.separator + fileName + fileFormat.getExtension();
        this.file = new File(filePath);
    }

    public String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void createLoremIpsumFile() {
        String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. " +
                "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. " +
                "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

        try {
            switch (fileFormat) {
                case TXT:
                    createTxtFile(loremIpsum);
                    break;
                case DOCX:
                    createDocxFile(loremIpsum);
                    break;
                case PDF:
                    createPdfFile(loremIpsum);
                    break;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteFile() {
        if (file.exists()) {
            file.delete();
        }
    }

    private void createTxtFile(String text) throws IOException {
        file.createNewFile();
        try (FileWriter writer = new FileWriter(file)) {
            for (int i = 0; i < 500; i++) {
                writer.write(text + System.lineSeparator());
            }
        }
    }

    private void createDocxFile(String text) throws IOException {
        try (XWPFDocument document = new XWPFDocument();
             FileOutputStream out = new FileOutputStream(file)) {
            for (int i = 0; i < 500; i++) {
                document.createParagraph().createRun().setText(text);
            }
            document.write(out);
        }
    }

    private void createPdfFile(String text) throws IOException {
        try (PdfWriter writer = new PdfWriter(filePath);
             PdfDocument pdfDoc = new PdfDocument(writer);
             Document document = new Document(pdfDoc)) {
            for (int i = 0; i < 500; i++) {
                document.add(new Paragraph(text));
            }
        }
    }

    public enum FileFormat {
        PDF(".pdf"), DOCX(".docx"), TXT(".txt");

        private final String extension;

        FileFormat(String extension) {
            this.extension = extension;
        }

        public String getExtension() {
            return extension;
        }
    }
}
