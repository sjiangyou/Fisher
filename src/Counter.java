public class Counter {
    private int count;
    public Counter(){
        this.count = 0;
    }
    public void updateCount(Card card){
        try{
            if(Integer.parseInt(card.getValue()) >= 2 && Integer.parseInt(card.getValue()) <= 6){
                this.count++;
            } else if(Integer.parseInt(card.getValue()) == 10 || Integer.parseInt(card.getValue()) == 1){
                this.count--;
            }
        } catch (NumberFormatException e){
            this.count--;
        }   
    }
    public int getCount(){
        return this.count;
    }
}
