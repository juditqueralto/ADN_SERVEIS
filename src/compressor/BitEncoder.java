package compressor;

import java.util.List;

public class BitEncoder {
    
    public byte[] encode(String sequencia, List<Integer> posicionsError) {

        int nBytes = (int) Math.ceil(sequencia.length() / 4.0);
        byte[] resultat = new byte[nBytes];

        for (int i = 0; i < sequencia.length(); i++) {
            char base = Character.toUpperCase(sequencia.charAt(i));
            byte valorBinari = 0;

            if (base == 'A')      valorBinari = 0;
            else if (base == 'C') valorBinari = 1;
            else if (base == 'G') valorBinari = 2;
            else if (base == 'T') valorBinari = 3;
            else {
                valorBinari = 0; // substituïm per A
                posicionsError.add(i);
            }

            int indexByte = i / 4;
            int posicioDinsByte = i % 4;
            int desplacament = 6 - (posicioDinsByte * 2);

            resultat[indexByte] |= (valorBinari << desplacament);
        }

        return resultat;
    }

    public String decode(byte[] dades, int totalBases) {
        StringBuilder sb = new StringBuilder();
        int basesLlegides = 0;

        for (int i = 0; i < dades.length; i++) {
            byte b = dades[i];

            for (int j = 0; j < 4; j++) {
                if (basesLlegides < totalBases) {
                    int desplacament = 6 - (j * 2);
                    int valor = (b >> desplacament) & 3;

                    if (valor == 0) sb.append('A');
                    else if (valor == 1) sb.append('C');
                    else if (valor == 2) sb.append('G');
                    else if (valor == 3) sb.append('T');

                    basesLlegides++;
                }
            }
        }
        return sb.toString();
    }

    public String posarAsteriscs(String sequencia, List<Integer> posicionsError) {
        StringBuilder sb = new StringBuilder(sequencia);

        for (int pos : posicionsError) {
            if (pos >= 0 && pos < sb.length()) {
                sb.setCharAt(pos, '*');
            }
        }

        return sb.toString();
    }
}