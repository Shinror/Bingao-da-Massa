package app;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.print("""
                             Bem vindo ao bingao da massa 
                             deseja comecar? [s] [n]
                             =>""");
            String sair =input.nextLine();
            if("n".equals(sair))
                break;

            int[][] cartela = criadorDeCartela();
            
            System.out.println("Sua Cartela");
            mostrar(cartela);
            
            int[][] cartelaPC = criadorDeCartela();
            
            System.out.println("Cartela do adversário");
            mostrar(cartelaPC);
            
           /*
            Professor falou para fazer uma cartela para o pc e sortear os numeros
            e ver quem ganhou assim q completar a tabela inteira.
            */
            //print que comecou o bingo
            //sortear
            System.out.println("O jogo vai começar: ");
            
            int pc =1 ,player =1;//var quem ganhou
            
            boolean[] numgerados = new boolean[75];
            int fim;
            long seed = System.currentTimeMillis();
            Random aleatorio = new Random(seed);
            do{
                do{
                    fim = aleatorio.nextInt(75);
                }while(numgerados[fim]);
                numgerados[fim] = true;
                System.out.print(letra(++fim) +" "+fim+ "-");
                
                if(check(cartelaPC,fim))
                    pc++;
                if(check(cartela,fim))
                    player++;
                
                
                
            }while(player != 25 && pc != 25);
            
             String resultado = (player == 25)? "Você Ganhou!!" : "Você Perdeu :/";
             System.out.println();
             System.out.println(resultado);
        }
    }
    public static boolean check(int[][]cartela,int val){
        //primeira coluna [0] num <=15
        for(int ver : cartela[coluna(val)]){
            if(ver == val){
                return true;
            }
        }
        return false;
    }
    
    public static char letra(int val){
        if(val>60)
            return 'O';
        if(val>45)
            return 'G';
        if(val>30)
            return 'N';
        if (val>15)
            return 'I';
        
        return 'B';
    }
    
    public static int coluna(int val){
        if(val>60)
            return 4;
        if(val>45)
            return 3;
        if(val>30)
            return 2;
        if (val>15)
            return 1;
        
        return 0;
    }
    
    
    public static void mostrar(int[][] matriz){
         System.out.print("B\tI\tN\tG\tO\n");
            for(int i = 0; i < matriz.length; i++){
                for(int j = 0; j < matriz[i].length; j++){
                    System.out.print(matriz[j][i] + "\t");
                }
                System.out.println();
            }
        
    }
    
    public static int[][] criadorDeCartela() {
        int[][] cartela = new int[5][5];
        long seed = System.currentTimeMillis();
        Random aleatorio = new Random(seed);

        boolean[] numerosgerados = new boolean[15];//numeros encontrados

        for (int i = 0; i < cartela.length; i++) {
            for (int j = 0; j < cartela[i].length; j++) {
                int gerado;//temp
                int max = 15 * i;
                do {
                    gerado = aleatorio.nextInt(15);//numeros de 0 a 14
                } while (numerosgerados[gerado]);

                numerosgerados[gerado] = true;
                cartela[i][j] = max + gerado + 1;
            }//for linha
            numerosgerados = new boolean[15];
        }//for coluna
        cartela[2][2] = 0;
        return cartela;
    }

}
