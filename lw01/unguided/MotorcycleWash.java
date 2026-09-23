public class MotorcycleWash extends WashService {

    //Constructor
    public MotorcycleWash(String id, int days, int jumlah) {
        super(id, days, jumlah);
    }

    @Override 
    public int calculateCharge() {
        return (getDays() * 15000 + 5000) * getJumlah();
    }

    @Override 
    public String label() {
        return "Motorcycle";
    }
    
}
