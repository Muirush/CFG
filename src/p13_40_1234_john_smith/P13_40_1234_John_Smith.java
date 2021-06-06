/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p13_40_1234_john_smith;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author p13_40_1234_john_smith
 */
public class P13_40_1234_John_Smith {

    /**
     * @param args the command line arguments
     */
    
    
   static String Mint = "";
   static HashMap<String, String[]> hashmap = new HashMap<String, String[]>();
   //prods will be used to enable a user enter specific number of productions
    static String[] prods; 
   
   static void initializeHashing(){
       Stack<String> stack[] = new Stack[Mint.length()];
       String[] hmapString;
       
        for(int i = 0; i < stack.length; i++)
        stack[i] = new Stack<String>();
        for(int i = 0; i < prods.length; i++)
        stack[Mint.indexOf(prods[i].charAt(0))].push(prods[i].substring(3));
        
         for(int i = 0; i < stack.length; i++) {
          hmapString = new String[stack[i].size()];
          
           if(!stack[i].empty()) {
   
            for(int j = 0; !stack[i].empty(); j++)
             hmapString[j] = stack[i].pop();

            hashmap.put((Mint.charAt(i) + ""), hmapString);
           }
         }
        
   }
   
    static void printProds() {
    for(int i = 0; i < prods.length; i++)
    System.out.println("");
    
    }
    
                static void removeImmediate(String indexName, String[] q){
                     Stack<String> x = new Stack<String>();
                     Stack<String> y = new Stack<String>();
 
                    for(int i = 0; i < q.length; i++) {

                            if (indexName.equals(q[i].charAt(0) + ""))
                                x.push(q[i]);    //Recursive productions

                             else
                            y.push(q[i]);
                    }
                            if(!x.empty()) {

                                hashmap.remove(indexName);
                                String[] hmapString = new String[2 * y.size()];

                                int i = 0;
                                while(!y.empty()) {

                                hmapString[i++] = y.peek();
                                hmapString[i++] = (y.pop() + "k" + indexName);
                                 }
                            hashmap.put(indexName, hmapString);

                            hmapString = new String[2 * x.size()];
                            i = 0;
                                while(!x.empty()) {

                                hmapString[i++] = x.peek().substring(1);
                                hmapString[i++] = (x.pop().substring(1) + "k" + indexName);
                                }
                              hashmap.put(("k" + indexName), hmapString);
                            }
                }
                
   
    static void printingHashMap(){
            Set setter = hashmap.entrySet();
            Iterator i = setter.iterator();
            String keyName;
            String keyValues[];
            
            while(i.hasNext()) {

            Map.Entry mapEntry = (Map.Entry)i.next();
            keyName = mapEntry.getKey().toString();
            keyValues = hashmap.get(keyName);

            System.out.print("\n" + keyName + " -> ");
            for(int j = 0; j < keyValues.length; j++)
               
            System.out.print(keyValues[j] + " ");
            }
            
    }
    
            static void findAnyNonTerminal() {

            for(int i = 0; i < prods.length; i++)
            for(int j = 0; j < prods[i].length(); j++)
            if((prods[i].charAt(j) >= 'A' && prods[i].charAt(j) <= 'Z') && (Mint.indexOf(prods[i].charAt(j)) == -1))

            Mint += prods[i].charAt(j);

          //  System.out.println("\nNon Terminals : " + Mint);
            }
    public static void main(String[] args) {
        // TODO code application logic here
         Scanner terminal = new Scanner(System.in);
           // System.out.print("Enter the number of productions : ");
           int noProductions = 1;
            //int noProductions = terminal.nextInt();
            terminal = new Scanner(System.in);
            prods = new String[noProductions];
            
            System.out.println("Enter a production");
           for(int i = 0; i < noProductions; i++)
            prods[i] = terminal.nextLine();
            
           // System.out.println("\nGiven Productions");
            printProds();
            findAnyNonTerminal();
            initializeHashing();
            printingHashMap();
            
            for(int i = 0; i < Mint.length();  i++)
            if(hashmap.containsKey(Mint.charAt(i) + ""))
            removeImmediate((Mint.charAt(i) + ""), hashmap.get(Mint.charAt(i) + ""));
            //
                while(removeIndirect()){}

                for(int i = 0; i < Mint.length();  i++)
                if(hashmap.containsKey(Mint.charAt(i) + ""))
                removeImmediate((Mint.charAt(i) + ""), hashmap.get(Mint.charAt(i) + ""));

                System.out.print("\n\nFinal Results");
                printingHashMap();

                System.out.println();



    }

    private static boolean removeIndirect() {
        Set set = hashmap.entrySet();
        Iterator i = set.iterator();
        String keyName;
        String keyValues[];
        Stack<String> tempp = new Stack<String>();
        
        while(i.hasNext()) {

            Map.Entry me = (Map.Entry)i.next();
            keyName = me.getKey().toString();
            keyValues = hashmap.get(keyName);

                for(int j = 0; j < keyValues.length; j++)
                    if(keyValues[j].charAt(0) >= 'A' && keyValues[j].charAt(0) <= 'Z' && hashmap.containsKey(keyValues[j].charAt(0) + ""))
                        if(Mint.indexOf(keyName.charAt(0)) > Mint.indexOf(keyValues[j].charAt(0))) {
                            String[] sub = hashmap.get(keyValues[j].charAt(0) + "");
                                for(int z = 0; z < keyValues.length; z++)
                        if(z != j)
                        tempp.push(keyValues[z]);
                    for(int z = 0; z < sub.length; z++)
                    tempp.push(sub[z] + keyValues[j].substring(1));

                    String[] hmapString = new String[tempp.size()];
                    for(int z = 0; !tempp.empty(); z++)
                    hmapString[z] = tempp.pop();

                    hashmap.put(keyName, hmapString);
                    return true;
                        }
        }
        return false;
    }
    

   
    
}
