package compressor;

public class BitEncoder {
    public int[] encode(String sequencia){
    // Aquesta funció ens tornarà un array d'enters (binari) corresponent a les bases
    int[] bits = new int[sequencia.length()*2];
    // En aquesta variable hi guardarem el resultat de la funció encode
    
    int pos=0; // posició dins de l'array de "bits" d'ADN

    char[] bases = sequencia.toCharArray();
    // Aquest mètode (.toCharArray) converteix el String "sequencia" en un array de caràcters de manera que hi podem accedir amb un index dins del bucle
    for(int i=0; i<sequencia.length(); i++){
        char base = bases[i];

        if(base =='A') {
            bits[pos] = 0;
            bits[pos + 1]= 0;
        }
        else if (base == 'C') {
        bits[pos] = 0;
        bits[pos + 1] = 1;
        }
        else if (base == 'G') {
        bits[pos] = 1;
        bits[pos + 1] = 0;
        } else if (base == 'T') {
        bits[pos] = 1;
        bits[pos + 1] = 1;
        } else {
            System.out.println("Error:base invàlida" + base);
        }

        pos=pos+2; //Avançam 2 posicions pq cada base ocupa 2 bits
    }
        return bits;
    }
}
