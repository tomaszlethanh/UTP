package zad1;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Futil extends SimpleFileVisitor<Path> {

    public static void processDir(String dirName, String resultFileName){
        Path outputPath = Paths.get(resultFileName);
        PathMatcher matcher;
        try {
            matcher = FileSystems.getDefault().getPathMatcher("glob:*.txt");
            Files.walkFileTree(Paths.get(dirName), new SimpleFileVisitor<Path>(){
                public FileVisitResult visitFile(Path file, BasicFileAttributes a) throws IOException {
                    if (matcher.matches(file.getFileName()) && !file.getFileName().toString().equals(resultFileName)) {
                        System.out.println("Read: " + file);
                        FileChannel inputFileChannel = FileChannel.open(file);
                        ByteBuffer buffer = ByteBuffer.allocate((int) a.size());
                        buffer.clear();
                        inputFileChannel.read(buffer);
                        buffer.flip();
                        CharBuffer charBuffer = Charset.forName("Cp1250").decode(buffer);
                        ByteBuffer byteBuffer = StandardCharsets.UTF_8.encode(charBuffer);
                        FileChannel outputFileChannel = FileChannel.open(outputPath, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                        while(byteBuffer.hasRemaining())
                            outputFileChannel.write(byteBuffer);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}