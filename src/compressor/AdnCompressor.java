package compressor;

import io.AdnFileReader;
import io.AdnFileWriter;
import model.AdnSequencia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdnCompressor {

    public void compress(String inputPath, String outputPath) throws IOException {

        // 1. Llegir el fitxer d'entrada
        AdnFileReader reader = new AdnFileReader();
        AdnSequencia adn = reader.read(inputPath);

        // 2. Crear llista per guardar posicions amb errors
        List<Integer> posicionsError = new ArrayList<>();

        // 3. Codificar la seqüència en bytes
        //    Si troba una base invàlida, la substitueix per A
        //    i guarda la posició a posicionsError
        BitEncoder encoder = new BitEncoder();
        byte[] dadesComprimides = encoder.encode(adn.getSequencia(), posicionsError);

        // 4. Escriure el fitxer comprimit, incloent les posicions d'error
        AdnFileWriter writer = new AdnFileWriter();
        writer.writeBinaryFile(outputPath, adn, dadesComprimides, posicionsError);

        System.out.println("Compressió completada correctament.");
        System.out.println("Nombre d'errors detectats: " + posicionsError.size());
    }
}