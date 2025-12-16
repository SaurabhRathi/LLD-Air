package com.root.allInOne.LLD.FileCompression;
import java.util.*;
//FileCompression -> singleton
//FileCompression -> strategy pattern ->
//
//interface File -> class Text
//
//compress(File f) {
//
//
//}
interface Strategy {
    void compress(File f);
}

class Algo1 implements Strategy {
    void compress(File f);
}

class Algo2 implements Strategy {
    void compress(File f);
}


//Facade Pattern ->
FACTORY PATTERN INSTEAD OF A MAP
public class FileCompression {
    Map<Enum, Strategy> strategyMap;

    FileCompression() {

    }
    File compress(File f, String inputPath, String outputPath, Enum AlgorithmName) {

        List<Chunk> chunks = f.getChunks().stream().parallel().map(chunk -> strategyMap.get(AlgorithmName).compress(chunk)).collect();
        return stickTogether(chunks)

    }

    File stickTogether(List<Chunk> chunks )

}
