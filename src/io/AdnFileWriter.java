package io;

import model.AdnSequencia;
import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public class AdnFileWriter {

    public void writeBinaryFile(String path, AdnSequencia adn, byte[] dades, List<Integer> posicionsError) throws IOException {
        
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(path))) {
            
            // 1. Comentari
            if (adn.hihaComentari()) {
                dos.writeBoolean(true);
                dos.writeUTF(adn.getComentari());
            } else {
                dos.writeBoolean(false);
            }

            // 2. Longitud seqüència
            dos.writeInt(adn.getSequencia().length());

            // 3. Nombre d'errors
            dos.writeInt(posicionsError.size());

            // 4. Posicions dels errors
            for (int pos : posicionsError) {
                dos.writeInt(pos);
            }

            // 5. Dades comprimides
            dos.write(dades);

            dos.flush();
        }
    }
}