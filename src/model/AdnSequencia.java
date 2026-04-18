package model;
public class AdnSequencia {


        private String comentari; // Aquí guardarem el comentari sencer, amb el caràcter inicial >
   
        private String sequencia; // Aquí guardarem tota la seqüència de bases A, C, G, T
   
        // Constructor buit
        public AdnSequencia() {
        }
   
        // Constructor complet
        public AdnSequencia(String comentari, String sequencia) {
            this.comentari = comentari;
            this.sequencia = sequencia;
        }
   
        // Getters i setters
   
        public String getComentari() {
            return comentari;
        }
   
        public void setComentari(String comentari) {
            this.comentari = comentari;
        }
   
        public String getSequencia() {
            return sequencia;
        }
   
        public void setSequencia(String sequencia) {
            this.sequencia = sequencia;
        }
   
        public boolean hihaComentari() { // Retorna el comentari si n'hi ha i sinó diu que estgà buit.
            return comentari != null && !comentari.isEmpty();//is.Empty evita considerar comentaris buits (" ") com vàlids
        }
   
        public int length() { // Retorna la longitud de la seqüència
            return sequencia != null ? sequencia.length() : 0;
            //Si la seqüència No és null, retorna la seva longitud, i sí és null, retorna 0.
        }
   
        // Validació: comprova que només hi ha A, C, G, T
        public boolean isValid() {
            if (sequencia == null) return false;
   
            for (int i=0; i < sequencia.length(); i++) {
                String base = sequencia.substring(i, i + 1);
                if (!base.equals("A") && !base.equals("C") && !base.equals("G") && !base.equals("T"))
                    { return false;
                }
            }
            return true;
        }
   
        public String toString() {
            return "AdnSequence{" +
                    "commentari='" + comentari + '\'' +
                    ", sequencia='" + sequencia + '\'' +
                    '}';
        }

}
