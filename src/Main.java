import comparador.AdnComparador;
import compressor.AdnCompressor;
import compressor.AdnDescompressor;
import io.AdnFileReader;
import model.AdnSequencia;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        String fitxerOriginal = "sequence_adn_100.txt";
        String fitxerComprimit = "sequence_adn_100.cmp";
        String fitxerRecuperat = "sequence_adn_100_recuperat.txt";

        try {
            // 1. Comprimir
            AdnCompressor compressor = new AdnCompressor();
            compressor.compress(fitxerOriginal, fitxerComprimit);

            // 2. Descomprimir
            AdnDescompressor descompressor = new AdnDescompressor();
            descompressor.decompress(fitxerComprimit, fitxerRecuperat);

            // 3. Llegir fitxer original i recuperat
            AdnFileReader reader = new AdnFileReader();
            AdnSequencia original = reader.read(fitxerOriginal);
            AdnSequencia recuperat = reader.read(fitxerRecuperat);

            // 4. Comparar
            AdnComparador comparador = new AdnComparador();
            boolean iguals = comparador.comparar(original, recuperat);

            // 5. Resultat final
            if (iguals) {
                System.out.println("Procés complet correcte.");
            } else {
                System.out.println("Procés complet amb diferències.");
            }

        } catch (IOException e) {
            System.out.println("Error d'entrada/sortida: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error general: " + e.getMessage());
        }
    }
}