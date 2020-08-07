public class PageTableEntry {
    
    int addres;
    boolean inMem;
    
    public PageTableEntry(int adr) {
        addres = adr;
        inMem = false;
    }

}