package io;


import model.AdnSequencia;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class AdnFileReader {
    public AdnSequencia read(String contingut) throws IOException {


        BufferedReader reader = new BufferedReader(new FileReader(contingut));


        String line;
        String comment = null;
        StringBuilder sequenceBuilder = new StringBuilder();


        // Llegim línia a línia
        while ((line = reader.readLine()) != null) {


            line = line.trim(); // eliminem espais laterals


            // Si és línia de comentari
            if (line.startsWith(">")) { // CERCAR SI HI HA FORMA MÉS SENZILLA DE FER AIXÒ
                comment = line;
            } else {
                // Afegim la seqüència (pot estar en múltiples línies)
                sequenceBuilder.append(line);
            }
        }


        reader.close();


        // Convertim a String final amb majúscules
        String seq = sequenceBuilder.toString().toUpperCase();


        // Creem objecte AdnSequence
        AdnSequencia adnSeq = new AdnSequencia(comment, seq);


        return adnSeq;
    }


}



