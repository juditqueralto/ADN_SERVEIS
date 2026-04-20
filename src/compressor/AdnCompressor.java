package compressor;

import io.AdnFileReader;
import io.AdnFileWriter;
import model.AdnSequencia;

import java.io.IOException;

public class AdnCompressor {

    public void compress(String inputPath, String outputPath) throws IOException {

        // 1. Llegir el fitxer d'entrada
        AdnFileReader reader = new AdnFileReader();
        AdnSequencia adn = reader.read(inputPath);

        // 2. Validar la seqüència
        if (!adn.isValid()) {
            System.out.println("Error: seqüència invàlida");
            return;
        }

        // 3. Codificar la seqüència en bytes
        BitEncoder encoder = new BitEncoder();
        byte[] dadesComprimides = encoder.encode(adn.getSequencia());

        // 4. Escriure el fitxer comprimit
        AdnFileWriter writer = new AdnFileWriter();
        writer.writeBinary(outputPath, adn, dadesComprimides);

        System.out.println("Compressió completada correctament.");
    }
}