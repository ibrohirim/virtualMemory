

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    
    public static String[] lines;
    static ArrayList<PageTable> pageList = new ArrayList<>();
    public static String input;
    
    static int place = 0;
    
    static int hit = 0;
    static int count = 0;
    static int miss = 0;
    static int compuls = 0;
    
    static PageTableEntry[] memory = new PageTableEntry[30];
    
    static PageTable myPage;

    public static void main(String[] args) {
        
        String filename = "VMInput.txt";
        File vm = new File(filename);
        
        try {
            Scanner s = new Scanner(vm);
            while(s.hasNext()) {
                input = s.nextLine();
                lines = input.split(" ");
                
                if(lines[0].equalsIgnoreCase("new")) {
                    pageList.add(new PageTable());
                }
                if(lines[0].equalsIgnoreCase("switch")) {
                    int index = Integer.parseInt(lines[1]) -1;
                    myPage = pageList.get(index);
                }
                if(lines[0].equalsIgnoreCase("access")) {
                    for(int i=0; i < myPage.entry.length; i++) {
                        if(myPage.entry[i] == null) {
                            myPage.entry[i] = new PageTableEntry(Integer.parseInt(lines[1])>>10);
                            myPage.entry[i] = findMem(myPage.entry[i]);
                            miss++;
                            count++;
                            compuls++;
                            break;
                        } else if (myPage.entry[i].addres == Integer.parseInt(lines[1])>>10) {
                            if(myPage.entry[i].inMem) {
                                hit++;
                                count++;
                                break;
                            } else {
                                myPage.entry[i] = findMem(myPage.entry[i]);
                                miss++;
                                count++;
                                break;
                            }
                        }
                    }
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println("Could not find the file");
        }
        
        System.out.println("Access: " + count);
        System.out.println("Hits: " + hit);
        System.out.println("Misses: " + miss);
        System.out.println("Compulsory: " + compuls);
        
    } //main
    
    public static PageTableEntry findMem(PageTableEntry pte) {
        if(memory[place] == null) {
            pte.inMem = true;
            memory[place] = pte;
        } else {
            memory[place].inMem = false;
            pte.inMem = true;
            memory[place] = pte;
        }
        
        place++;
        if (place > 29) {
            place = 0;
        }
        return pte;
    }

}
