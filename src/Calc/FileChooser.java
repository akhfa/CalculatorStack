/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Calc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author akhfa
 */
public class FileChooser {
    String matrik[][] = new String[0][0];
    String [][] matrikAturanTransisiOri = new String[0][0];
    String [] FinalState;
    
    public void importFile() throws IOException{
        JFileChooser chooser = new JFileChooser();
        String temp;
        String kolom3 = "";
        String kolom4;
        if(chooser.showOpenDialog(chooser)== JFileChooser.APPROVE_OPTION){
            try
            {
                Scanner sc = new Scanner(chooser.getSelectedFile());
                int JumlahAturanTransisi = Integer.parseInt(sc.next());
                matrik = new String[JumlahAturanTransisi][5];
                matrikAturanTransisiOri = new String [JumlahAturanTransisi][5];
                int baris = 0;
                while(baris < JumlahAturanTransisi &&  sc.hasNext())
                {
                    int kolom = 0;
                    while (kolom < 5 && sc.hasNext())
                    {
                        
                        temp = sc.next();
                        matrikAturanTransisiOri[baris][kolom] = new String(temp);
                        if(!temp.contains("=q"))
                        {
                            if(kolom == 3)
                            {
                                kolom3 = temp;
                            }
                            else if(kolom == 4)
                            {
                                if(temp.equalsIgnoreCase("null"))
                                    temp = "pop";
                                else
                                    temp = "push";
                                matrik[baris][3] = temp;
                                
                                matrik[baris][kolom] = kolom3;
                            }
                            else
                            {
                                matrik[baris][kolom] = temp;
                            }
                            kolom++;
                        }                            
                        
                    }
                    baris++;
                }
                
                /* input final state */
                int JumlahFinalState = Integer.parseInt(sc.next());
                FinalState = new String[JumlahFinalState];
                int i = 0;
                while(i < JumlahFinalState && sc.hasNext())
                {
                    FinalState[i] = sc.next();
                    i++;
                }
            }catch (FileNotFoundException ioe){
                JOptionPane.showMessageDialog(null, ioe.getMessage());
                System.exit(1);
            }
        }
    }
    
    public String [][] getAturanTransisi()
    {
        return matrik;
    }
    
    public String [][] getAturanTransisiOri()
    {
        return matrikAturanTransisiOri;
    }
    
    public String[]getFinalState()
    {
        return FinalState;
    }
    
    
}
