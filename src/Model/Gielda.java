package Model;

public abstract class Gielda {
    private String nazwa;
    private String kraj;
    private String miasto;
    private String adresSiedziby;

    private Waluta waluta;
    private double marza;

   public Gielda(String nazwa, String kraj, String miasto, String adresSiedziby, Waluta waluta){
       this.nazwa = nazwa;
       this.kraj = kraj;
       this.kraj = miasto;
       this.kraj = adresSiedziby;
       this.waluta = waluta;
   }

    public double getMarza() {
        return marza;
    }

    public void setMarza(double marza) {
        this.marza = marza;
    }
}
