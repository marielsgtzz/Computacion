/**
 * @author Emilio
 * Mariel Guiterrez Zapien
 * Camilo Palma
 * Diego Arellano
 */
import java.util.StringTokenizer;

public class FuncionalidadCalculadora {
   public static boolean verificarSiDiceError(String expresion) {
        return expresion.equals("Error");
    }
    
    public static void verificarExpresion(String expresion) {
        StringTokenizer tokenizer = new StringTokenizer(expresion);
        String token1 = tokenizer.nextToken();
        String token2 = tokenizer.nextToken();
        boolean b = verificarParentesis(expresion);
        if(esOperador(token1))
            b = false;
        
        while(b && tokenizer.hasMoreTokens()) {
            if(esOperador(token1) && esOperador(token2))
                b = false;
            else if(esOperando(token1) && esOperando(token2))
                b = false;
            
            token1 = token2;
            if(tokenizer.hasMoreTokens())
                token2 = tokenizer.nextToken();
            else if(esOperador(token1))
                b = false;
        }
        
        if(!b)
            throw new RuntimeException("Error");
    }
    
    private static boolean verificarParentesis(String cadena) {
        int i = 0;
        boolean b = true;
        PilaADT<Character> pila = new PilaA();
        while(i < cadena.length() && b) {
            if(cadena.charAt(i) == '(')
                pila.push('(');
            else  if(cadena.charAt(i) == ')' && pila.peek().equals( '('))
                    if(pila.isEmpty())
                        b = false;
                    else 
                        pila.pop();
            i++;
        }
        return b && pila.isEmpty();
    }
    
    private static boolean esOperador(String cad) {
        return cad.equals("+") || cad.equals("-") || cad.equals("*") || cad.equals("/");
    }
    
    private static Double operacion(Double num1, Double num2, String op) {
        Double res = 0.0;
        switch(op){
            case("+"):
                res = num1 + num2;
                break;
            case("-"):
                res = num1 - num2;
                break;
            case("*"):
                res = num1 * num2;
                break;
            case("/"):
                 res = num1 / num2;
                 if(res.isInfinite() || res.isNaN())
                     throw new RuntimeException("Error");
                break;
        }
        return res;
    }
    
    private static int jerarquiaOperadores(String cad) {
        int jer = 0;
        if(cad.equals("+") || cad.equals("-"))
            jer = 1;
        else if(cad.equals("*") || cad.equals("/"))
            jer = 2;
        return jer;
    }
    
    private static boolean esOperando(String cad) {
        boolean b = true;
        try {
            Double.parseDouble(cad);
        } catch(NumberFormatException e) {
            b = false;
        }
        return b;
    }
    
    public static String infijaAPostfija(String expresion) {
        StringBuilder cad = new StringBuilder();
        PilaADT<String> operadores = new PilaA();
        StringTokenizer tokenizer = new StringTokenizer(expresion);
        String token;
        
        while(tokenizer.hasMoreTokens()) {
            token = tokenizer.nextToken();
            if(esOperando(token))
                cad.append(token + " ");
            else if(token.equals("("))
                operadores.push(token);
            else if(token.equals(")")) {
                while(!operadores.isEmpty() && !operadores.peek().equals("("))
                    cad.append(operadores.pop() + " ");
                try {
                operadores.pop();
                } catch(RuntimeException e) {
                    throw new RuntimeException("Error");
                }
            } else if(esOperador(token)){
                while(!operadores.isEmpty() && !operadores.peek().equals("(") && jerarquiaOperadores(token) <= jerarquiaOperadores(operadores.peek()))
                    cad.append(operadores.pop() + " ");
                operadores.push(token);
            }
        }
        while(!operadores.isEmpty())
            cad.append(operadores.pop() + " ");
        return cad.toString();
    }
    
    public static Double evaluarPostfija(String expresion) {
        Double num1, num2, res;
        PilaADT<Double> operandos = new PilaA();
        StringTokenizer tokenizer = new StringTokenizer(expresion);
        String token;
        
        while(tokenizer.hasMoreTokens()) {
            token = tokenizer.nextToken();
            if(esOperador(token)) {
                num2 = operandos.pop();
                num1 = operandos.pop();
                res = operacion(num1, num2, token);
                operandos.push(res);
            } else {
                operandos.push(Double.parseDouble(token));
            }
        }
        
        return operandos.pop();
    }
}
