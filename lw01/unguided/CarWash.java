public class CarWash extends WashService {
    
    //Constructor
    public CarWash (String id, int days, int jumlah) {
        super(id, days, jumlah);
    }

    @Override 
    public int calculateCharge(){
        
        if (getDays() <= 3) {
            return (getDays() * 35000 + 15000) * getJumlah();
        }

        else {
            return (15000 + (getDays() - 3) * 25000)) * getJumlah();
        }
    }

    @Override 
    public String label(){
        return "Car";
    }
}

