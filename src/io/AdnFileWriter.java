package io;

import model.AdnSequencia;
import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class AdnFileWriter {

    public void writeBinaryFile(String path, AdnSequencia adn, byte[] dades) throws IOException {
        // Fem servir DataOutputStream perquè ens permet escriure tipus de dades
        // primitius (int, boolean, UTF) de forma binària i neta.
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(path))) {
            
            // 1. Escrivim si hi ha comentari i el comentari mateix
            if (adn.hihaComentari()) {
                dos.writeBoolean(true);
                dos.writeUTF(adn.getComentari());
            } else {
                dos.writeBoolean(false);
            }

            // 2. Escrivim la longitud total de la seqüència (Molt important!)
            // Això ocupa 4 bytes al fitxer.
            dos.writeInt(adn.getSequencia().length());

            // 3. Escrivim el bloc de dades comprimides
            dos.write(dades);
            
            dos.flush(); // Assegurem que tot s'escriu al disc
        }
    }
}