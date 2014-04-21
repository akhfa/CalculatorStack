/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Calc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.JOptionPane;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine; 
import javax.script.ScriptException;
/**
 *
 * @author akhfa
 */
public class Calculator {
    String input;
    
    String [][] aturan;
    
    String [][]aturanOri;
    
    String []FinalState;
    
    ArrayList Angka = new ArrayList();
    
    PDA pda = new PDA();
    
    public void SetInput(String Input)
    {
        input = Input;
    }
    
    public void InputAturan()
    {
        FileChooser FC = new FileChooser();
        try {
            FC.importFile();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Input Not Valid");
        }
        aturan = FC.getAturanTransisi();
        aturanOri = FC.getAturanTransisiOri();
        FinalState = FC.getFinalState();
        ConvertToMatrikPDA();
    }
    
    private void ConvertToMatrikPDA()
    {
        for(String[]i:aturanOri)
        {
            pda.setState(Integer.parseInt(i[0]) , i[1], i[2], Integer.parseInt(i[3]), i[4]);
        }
        for(String i : FinalState)
        {
            pda.addFinalState(Integer.parseInt(i));
        }
    }
    
    public boolean InputValid()
    {
        boolean ketemu = false;
        String cc = "" + input.charAt(0);
        int i = 1;
        Stack StackInput = new Stack();
        StackInput.push("null");
        String [] NextAndAksi = new String[2];
        char Asal = '0';
        while (!ketemu && i <= input.length())
        {
            System.out.println("i = " + i);
            try
            {
                if(IsNumeric(""+cc))
                {
                    cc = "A";
                }
                NextAndAksi = GetNextStateAndAksi(""+Asal, cc, ""+StackInput.peek());
                //System.out.println("PrintGetNextState dari "+Asal+" dengan input " + cc + " dan TOS = "+StackInput.peek());
                
                /* Jika ketemu kurung tutup */
                if((Asal == '0' || Asal == '4' || Asal == '5') && cc.compareToIgnoreCase(")") == 0)
                {
                    System.out.println("Masuk Pop");
                    while(StackInput.peek().toString().compareToIgnoreCase("(") != 0)
                    {
                        StackInput.pop();
                    }
                    Asal = '1';
                    cc = "null";
                    NextAndAksi = GetNextStateAndAksi(""+Asal, cc, ""+StackInput.peek());
                }
                else
                {
                    for(String next : NextAndAksi)
                    {
                        System.out.print(next + "\t");
                    }
                    System.out.println("");
                }
                
                if(NextAndAksi[1].compareToIgnoreCase("push") == 0)
                {
                    StackInput.push(cc);
                }
                else if(NextAndAksi[1].compareToIgnoreCase("pop") == 0)
                {
                    StackInput.pop();
                }
                Asal = NextAndAksi[0].charAt(0);
                if(Asal == '6')
                {
                    System.out.println("ketemu = true");
                    ketemu = true;
                }
                else
                {
                    cc = "" + input.charAt(i);
                    System.out.println("Asal : " + Asal + " cc : " + cc);
                    i++;
                }
                
            }
            catch(NullPointerException ex)
            {
                JOptionPane.showMessageDialog(null, "Aturan transisi belum di load");
                break;
            }
                
            
        }
        return ketemu;
    }
    
    private boolean IsNumeric (String s)
    {
        try
        {
            int x = Integer.parseInt(s);
        }
        catch (NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
        
    public String [] GetNextStateAndAksi(String Asal, String input, String TOS)
    {
        boolean ketemu = false;
        int baris = 0;
        String [] hasil = new String[2];
        while (!ketemu && baris < aturan.length)
        {
            if(aturan[baris][0].compareToIgnoreCase(Asal) == 0 && aturan[baris][1].compareToIgnoreCase(input) == 0 && 
                    aturan[baris][2].compareToIgnoreCase(TOS) == 0)
            {
                ketemu = true;
                hasil[0] = aturan[baris][4];
                hasil[1] = aturan[baris][3];
            }
            baris++;
        }
        if(!ketemu)
        {
            return null;
        }
        else
            return hasil;
    }
    
    public void PrintAturan()
    {
        System.out.println("Aturan Edited");
        for (String[] aturan1 : aturan) {
            for (String item : aturan1) {
                System.out.print(item + "\t");
            }
            System.out.println("");
        }
        
        
        System.out.println("Aturan Ori");
        pda.print();
        
    }
    
    public void PrintInput()
    {
        System.out.println(input);
    }
    
    public void PrintFinalState()
    {
        for(String i : FinalState)
        {
            System.out.println(i);
        }
    }
    
    public int Hitung(String input)
    {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        int hasil = 0;
        try {
            JOptionPane.showMessageDialog(null, "Hasil = "+engine.eval(input));
        } catch (ScriptException ex) {
            JOptionPane.showMessageDialog(null, "Input salah");
        }
        return hasil;
    }
    
    public void ConvertToCFG()
    {
        pda.toCFG();
        pda.WriteCFGtoFile();
    }
    
    public void PrintCFG()
    {
        pda.PrintCFG();
    }
}
