package compressor;

public class BitEncoder {
    
    /* Cada byte contindrà 4 bases (2 bits x 4 = 8 bits).*/
    public byte[] encode(String sequencia) {

        // calculem quants bytes necessitem (longitud / 4)
        int nBytes = (int) Math.ceil(sequencia.length() / 4.0);
        byte[] resultat = new byte[nBytes];

        for (int i = 0; i < sequencia.length(); i++) {
            char base = sequencia.charAt(i);
            byte valorBinari = 0;

            // assignem el valor de 2 bits segons la base
            if (base == 'A')      valorBinari = 0; // 00
            else if (base == 'C') valorBinari = 1; // 01
            else if (base == 'G') valorBinari = 2; // 10
            else if (base == 'T') valorBinari = 3; // 11

            // trobem en quin byte de l'array hem de guardar la base
            int indexByte = i / 4;
            
            // trobem la posició dins del byte (0, 1, 2 o 3)
            int posicioDinsByte = i % 4;

            // "desplaçament a l'esquerra" (<<)
            int desplacament = 6 - (posicioDinsByte * 2);
            
            // l'operador |= (OR) afegeix els bits al byte sense esborrar els anteriors
            resultat[indexByte] |= (valorBinari << desplacament);
        }

        return resultat;
    }


    public String decode(byte[] dades, int totalBases) {
        StringBuilder sb = new StringBuilder();
        int basesLlegides = 0;

        for (int i = 0; i < dades.length; i++) {
            byte b = dades[i];
            
            // Cada byte pot contenir fins a 4 bases
            for (int j = 0; j < 4; j++) {
                if (basesLlegides < totalBases) {
                    
                    // 1. Calculem quant hem de desplaçar a la dreta per posar els 
                    //    2 bits que volem al final del byte.
                    int desplacament = 6 - (j * 2);
                    
                    // 2. Desplacem i usem una màscara & 3 (binari 11) 
                    //    per "esborrar" tota la resta de bits.
                    int valor = (b >> desplacament) & 3;

                    // 3. Traduïm el valor de 2 bits a caràcter
                    if (valor == 0)      sb.append('A');
                    else if (valor == 1) sb.append('C');
                    else if (valor == 2) sb.append('G');
                    else if (valor == 3) sb.append('T');
                    
                    basesLlegides++;
                }
            }
        }
        return sb.toString();
    }
}