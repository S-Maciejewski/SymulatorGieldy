package Model;

public abstract class Gielda {
    private String nazwa;
    private String kraj;
    private String miasto;
    private String adresSiedziby;

    private double marza;

   public Gielda(String nazwa, String kraj, String miasto, String adresSiedziby, double marza){
       this.nazwa = nazwa;
       this.kraj = kraj;
       this.miasto = miasto;
       this.adresSiedziby = adresSiedziby;
       this.marza = marza;
   }

    public double getMarza() {
        return marza;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getKraj() {
        return kraj;
    }

    public String getMiasto() {
        return miasto;
    }

    public String getAdresSiedziby() {
        return adresSiedziby;
    }
}
