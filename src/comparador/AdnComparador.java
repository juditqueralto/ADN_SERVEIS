package comparador;

import model.AdnSequencia;

public class AdnComparador {

    /**
     * Compara dues seqüències d'ADN.
     * Retorna true si el comentari i el text de la seqüència són idèntics.
     */
    public boolean comparar(AdnSequencia original, AdnSequencia recuperat) {

        // 1. Comprovem si algun dels dos objectes és null
        if (original == null || recuperat == null) {
            System.out.println("Error: Una de les seqüències és nul·la.");
            return false;
        }

        // 2. Comprovem el comentari
        boolean comentariIgual = compararComentaris(original.getComentari(), recuperat.getComentari());

        // 3. Comprovem la seqüència
        boolean sequenciaIgual = compararText(original.getSequencia(), recuperat.getSequencia());

        // 4. Informe de resultats
        if (comentariIgual && sequenciaIgual) {
            System.out.println("L'ADN és idèntic! La compressió ha estat un èxit.");
            return true;
        } else {
            System.out.println("Alerta! Hi ha diferències entre l'original i el recuperat.");
            if (!comentariIgual) System.out.println(" - El comentari no coincideix.");
            if (!sequenciaIgual) System.out.println(" - La seqüència de bases ha canviat.");
            return false;
        }
    }

    private boolean compararComentaris(String c1, String c2) {
        if (c1 == null && c2 == null) return true;
        if (c1 == null || c2 == null) return false;
        return c1.trim().equals(c2.trim());
    }

    private boolean compararText(String s1, String s2) {
        if (s1 == null && s2 == null) return true;
        if (s1 == null || s2 == null) return false;
        return s1.equals(s2);
    }
}