package compressor;

import model.AdnSequencia;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

            // 4. Llegim el nombre d'errors
            int numErrors = dis.readInt();

            // 5. Llegim diferències i reconstruïm posicions absolutes
            List<Integer> posicionsError = new ArrayList<>();
            int acumulada = 0;

            for (int i = 0; i < numErrors; i++) {
                int diferencia = dis.readUnsignedShort();
                acumulada += diferencia;
                posicionsError.add(acumulada);
            }

            // 6. Calculem quants bytes ocupen les dades comprimides
            int nBytes = (int) Math.ceil(longitudOriginal / 4.0);

            // 7. Llegim les dades comprimides
            byte[] dadesComprimides = new byte[nBytes];
            dis.readFully(dadesComprimides);

            // 8. Descodifiquem la seqüència
            BitEncoder encoder = new BitEncoder();
            String sequencia = encoder.decode(dadesComprimides, longitudOriginal);

            // 9. Tornem a posar '*' a les posicions amb error
            sequencia = encoder.posarAsteriscs(sequencia, posicionsError);

            // 10. Reconstruïm l'objecte ADN
            AdnSequencia adn = new AdnSequencia(comentari, sequencia);

            // 11. Escrivim el fitxer descomprimit
            try (FileWriter fw = new FileWriter(outputPath)) {
                if (adn.hihaComentari()) {
                    fw.write(adn.getComentari());
                    fw.write(System.lineSeparator());
                }
                fw.write(adn.getSequencia());
                fw.write(System.lineSeparator());
            }

            System.out.println("Descompressió completada correctament.");
            System.out.println("Nombre d'errors restaurats amb '*': " + numErrors);
        }
    }
}