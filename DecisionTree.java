import java.util.Scanner;

import Tree_Module.LinkedBinaryTree;

public class DecisionTree {
    //inisialiasi variable tree LinkedBinaryTree bertipe String 
    private LinkedBinaryTree<String> tree;
    //insialisasi variable scanner untuk meminta user's input
    Scanner sn = new Scanner(System.in);

    //constructor untuk decision tree untuk membangun tree langsung
    DecisionTree(){
        this.tree = WeatherQuestion();
    }


    /**
     * Fungsi untuk membangun decision tree dengan LinkedBinaryTree dengan pertanyaan-pertanyaan yang
     * sudah disediakan oleh kelompok kami dan setiap kemungkingan jawaban dari pertanyaan tersebut
     * @return LinkedBinaryTree<String> tree 
     */
    public LinkedBinaryTree<String> WeatherQuestion(){

        //inisialisasi semua pertanyaannya
        String q1 = "Temp (celcius) [0 - 50] in celcius degrees";
        String q2 = "Humidity [30 - 90] in %rh";
        String q3 = "Cloud Density: \n1.Not Dense \n2.Dense";
        String q4 = "Wind Speed [0 - 70] in knot";
        String q5 = "Cloud Clearance: \n1.Clear \n2.Dark"; 
        String q6 = "Visibility: \n1.Low \n2.Clear";

        //inisialisasi semua jawabannya/output
        String a1 = "Sunny";
        String a2 = "Windy";
        String a3 = "High Heat";
        String a4 = "Windy";
        String a5 = "Moderate Rain Posibility";
        String a6 = "High Rain Posibility";
        String a7 = "Possible Storm";
        String a8 = "Cold Weather";

        //inisialisasi semua node yang dibutuhkan
        LinkedBinaryTree<String> n2, n3, n4, n4h, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14;
        
        //memasukan element jawaban kedalam leaf nodenya
        n14 = new LinkedBinaryTree<String>(a1);
        n13 = new LinkedBinaryTree<String>(a2);

        //menghubungkan element question dengan node jawabannya
        n4 = new LinkedBinaryTree<String>(q4, n14, n13);

        n12 = new LinkedBinaryTree<String>(a3);
        n11 = new LinkedBinaryTree<String>(a4);
        n3 = new LinkedBinaryTree<String>(q3, n12, n11);

        n10 = new LinkedBinaryTree<String>(a5);
        n9 = new LinkedBinaryTree<String>(a6);
        n5 = new LinkedBinaryTree<String>(q5, n10, n9);

        n8 = new LinkedBinaryTree<String>(a7);
        n7 = new LinkedBinaryTree<String>(a8);
        n6 = new LinkedBinaryTree<String>(q6, n8, n7);

        //menghubungkan element question di level kedua dengan parent dibawahnyadi level ketiga
        n2 = new LinkedBinaryTree<String>(q2, n4, n3);
        n4h = new LinkedBinaryTree<String>(q4, n5, n6);

        //tree sebagai root awalnya
        tree = new LinkedBinaryTree<String>(q1, n2, n4h);

        //menghubukan semua nodenya dengan parent mereka
        tree.setParent(tree); 
        n4h.setParent(tree);
        n2.setParent(tree);
        n3.setParent(n2);
        n4.setParent(n2);
        n5.setParent(n4h);
        n6.setParent(n4h);
        n7.setParent(n6);
        n8.setParent(n6);
        n9.setParent(n5);
        n10.setParent(n5);
        n11.setParent(n3);
        n12.setParent(n3);
        n13.setParent(n4);
        n14.setParent(n4);


        return tree;
    }
    
    /**
     * Fungsi dimana semua pertanyaan akan diajukan
     * Dan setiap user input akan berpindah antara ke left node atau right node 
     * Dimana user akan berujung di leaf node yang berisi dengan jawaban
     * berdasarkan user input
     */
    public void weatherPrediction(){
        
        //inisialisasi current sebagai tree
        LinkedBinaryTree<String> current = tree;
        System.out.println("Weather Prediction!");


        //inisialisasi variable-variable yang dibutuhkan untuk looping berulang ulang hingga true
        boolean enter = false;
        boolean restarted = false;
        boolean exit = false;
        boolean finished = false;

        //selama user masih mau melanjutkan, maka looping tidak akan berhenti
        while(finished == false){

            //selama ukuran node (jumlah children) lebih dari satu (berati bukan leaf node)
            //maka akan di loop 
            while(current.size() > 1){      

                //message yang dimunculkan untuk pertama kali ketika decision tree pertama kali dijalankan
                if(enter == false){

                    System.out.println("Input the word:\n\"restart\" or \"res\" to start the decision tree from the root again!");
                    System.out.println("\"exit\" or \"end\" to exit the decision tree");
                    System.out.println("\"back\" or \"prev\" to return to the previous question");
                    System.out.println("Press any enter! to start the decision tree!");
                    sn.nextLine();
                    enter = true;
                }


                //message ketika decision tree di restart
                if(restarted == true){
                    System.out.println("\nThe Decision Tree is restarted!");
                } 
                //jika user input exit/prev maka loop untuk current ke left atau right akan berhenti/break
                if(exit == true){   
                    break;
                }

                //mengambil pertanyaan dari element/value dari node current/sekarang
                String question = current.getElement();

                //insialisasi variable answer 
                String answer = null;

                //selama answer bernilai null, loop akan terus berjalan hingga user memberikan
                //input yang benar
                while(answer == null){
                    //mengeprint pertanyaan dari nodenya   
                    System.out.println(question);
                    //meminta user input
                    answer = sn.nextLine();

                    //jika user meminta restart maka node current balik lagi dari root (tree) 
                    //dan break dari loopnya
                    if(answer.equals("restart") || answer.equals("res")){
                        current = tree;
                        restarted = true;
                        break;
                    }
                    //jika user meminta back maka node current akan balik lagi ke parentnya 
                    else if(answer.equals("back") || answer.equals("prev")){
                        System.out.println("\nBack to the previous question!");
                        current = current.getParent();
                        break;
                    } 
                    //jika  user meminta exit maka loop akan berhenti dan exit = true
                    else if(answer.equals("exit")|| answer.equals("end")){
                        exit = true;
                        break;
                    }

                    //memasukan value dari answer kedalam fungsi berdasarkan node pertanyaan yang ditanya
                    else if(question.contains("Temp")){
                        answer = Temp(answer);
                    } else if(question.contains("Humidity")){
                        answer = Humidity(answer);
                    } else if(question.contains("Speed")){
                        answer = windSpeed(answer);
                    } else if(question.contains("Density")){
                        answer = cloudDensity(answer); 
                    } else if(question.contains("Clearance")){
                        answer = cloudClearance(answer);  
                    } else if(question.contains("Visibility")){
                        answer = visibility(answer);
                    }

                    //jika user memasukan input yang salah, maka akan ditanyakan pertanyaan yang sama lagi
                    if(answer == null){   
                        System.out.println("Invalid Input! Try Again!\n");
                    }
                }

                //jika fungsi dari pertanyaan tersebut bernilai N maka current akan ke right node
                if(answer.equals("N")){
                    current = current.getRight();
                } 
                //Y maka current akan ke left node
                else if (answer.equals("Y")){
                    current = current.getLeft();
                }
            }

            //ketika user sudah sampai di leaf node yang berisi jawabannya maka akan mengeprint jawaban yang 
            //ada didalam leaf node tersebut
            if(exit == false){
                System.out.println("\nPrediction is finished!");
                System.out.println("The result: " + current.getElement() + "!");
            } else if (exit == true){
                finished = true;
            }
        
            //meminta user input jika user ingin menjalankan decision tree lagi
            System.out.println("\nDo you want to test it again?");
            System.out.println("Input:\n1.Yes \n2.\"Back\" to last question\n3.No or any other input to exit\n");
            String doItAgain = sn.nextLine();
            doItAgain = doItAgain.toLowerCase();

            //menjalankan decision tree dari awal
            if(doItAgain.equals("1") || doItAgain.equals("yes")){
                finished = false;
                current = tree;
                System.out.println();
            } 
            //menjalankan decision tree dari parent (pertanyaan) terakhir
            else if(doItAgain.equals("2") || doItAgain.equals("back")){
                finished = false;
                current = current.getParent();
            }
            //keluar dari decision tree dan memberhentikan program decision treenya
            else{
                finished = true;
                System.out.println("Exited the Decison Tree\n");
            }
        }
    }   


    /**
     * fungsi untuk pertanyaan temperature
     * @param answer String 
     * @return String
     */
    private String Temp(String answer){   
        try{
            int value = Integer.parseInt(answer);
            
            if(value < 0 || value > 50){
                return null;
            } else if (value >= 0 && value <= 25){
                return "N";
            } else if (value > 25 && value <= 50){
                return "Y";
            } else{
                return null;
            }
        } catch(Exception e){
            return null;
        }
    }

    /**
     * fungsi untuk pertanyaan Humidity
     * @param answer String
     * @return String
     */
    private String Humidity(String answer){       
        try{
            int value = Integer.parseInt(answer);
            
            if(value < 30 || value > 90){
                return null;
            } else if (value >= 30 && value <= 60){
                return "Y";
            } else if (value > 60 && value <= 90){
                return "N";
            } else{
                return null;
            }
        } catch(Exception e){
            return null;
        }
    }

    /**
     * fungsi untuk pertanyaan windSpeed
     * @param answer String
     * @return String
     */
    private String windSpeed(String answer){ 
        
        //jika user input selain angka integer maka akan catch error
        //dan return null dimana akan diterima sebagai invalid input
        try{
            int value = Integer.parseInt(answer);
        
            if(value < 0 || value > 70 ){
                return null;
            } else if (value > 35 && value <= 70){
                return "N";
            } else if (value >= 0 && value <= 35){
                return "Y";
            } else{
                return null;
            }
        } catch(Exception e){
            return null;
        }
    }

    /**
     * fungsi untuk pertanyaan cloudDensity
     * @param answer String
     * @return String
     */
    private String cloudDensity(String answer){
        answer = answer.toLowerCase();

        if(answer.equals("not dense") || answer.equals("1")){
            return "Y";
        } else if(answer.equals("dense") || answer.equals("2")){
            return "N";
        } else{
            return null;
        }
    }

    /**
     * fungsi untuk pertanyaan cloudClearance
     * @param answer String
     * @return String
     */
    private String cloudClearance(String answer){
        answer = answer.toLowerCase();
        
        if(answer.equals("clear") || answer.equals("1")){
            return "Y";
        } else if(answer.equals("dark") || answer.equals("2")){
            return "N";
        } else{
            return null;
        }
    }

    /**
     * fungsi untuk pertanyaan visibility
     * @param answer String
     * @return String
     */
    private String visibility(String answer){
        answer = answer.toLowerCase();

        if(answer.equals("low") || answer.equals("1")){
            return "Y";
        } else if(answer.equals("clear") || answer.equals("2")){
            return "N";
        } else{
            return null;
        }
    }

    /**
     * fungsi untuk mengeprint level order dari decision tree tersebut 
     */
    public void levelOrder(){
        System.out.println(tree.levelOrder());
    }

}
