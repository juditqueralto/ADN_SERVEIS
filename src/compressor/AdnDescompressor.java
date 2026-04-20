package compressor;

import model.AdnSequencia;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class AdnDescompressor {

    public void decompress(String inputPath, String outputPath) throws IOException {

        try (DataInputStream dis = new DataInputStream(new FileInputStream(inputPath))) {

            // 1. Llegim si hi ha comentari
            boolean teComentari = dis.readBoolean();

            // 2. Llegim el comentari si existeix
            String comentari = null;
            if (teComentari) {
                comentari = dis.readUTF();
            }

            // 3. Llegim la longitud original de la seqüència
            int longitudOriginal = dis.readInt();

            // 4. Calculem quants bytes ocupen les dades comprimides
            int nBytes = (int) Math.ceil(longitudOriginal / 4.0);

            // 5. Llegim les dades comprimides
            byte[] dadesComprimides = new byte[nBytes];
            dis.readFully(dadesComprimides);

            // 6. Descodifiquem la seqüència
            BitEncoder encoder = new BitEncoder();
            String sequencia = encoder.decode(dadesComprimides, longitudOriginal);

            // 7. Reconstruïm l'objecte ADN
            AdnSequencia adn = new AdnSequencia(comentari, sequencia);

            // 8. Escrivim el fitxer descomprimit en text
            try (FileWriter fw = new FileWriter(outputPath)) {
                if (adn.hihaComentari()) {
                    fw.write(adn.getComentari());
                    fw.write(System.lineSeparator());
                }
                fw.write(adn.getSequencia());
                fw.write(System.lineSeparator());
            }

            System.out.println("Descompressió completada correctament.");
        }
    }
}