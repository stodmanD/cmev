package ru.rec.cmev.mappers;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class MultipartFileMapper {
    public static MultipartFile convertToMultipartFile(byte[] file, String filename) {
        return new MultipartFile() {
            @Override
            public String getName() {
                return filename;
            }

            @Override
            public String getOriginalFilename() {
                return filename;
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return file.length;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return file;
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return new ByteArrayInputStream(file);
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {
                try (FileOutputStream output = new FileOutputStream(dest)) {
                    output.write(file);
                }
            }
        };
    }
}
