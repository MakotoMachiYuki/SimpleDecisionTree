import java.util.Scanner;

import Tree_Module.LinkedBinaryTree;

public class DecisionTree {
    private LinkedBinaryTree<String> tree;
    Scanner sn = new Scanner(System.in);

    DecisionTree(){
        this.tree = WeatherQuestion();
    }

    public LinkedBinaryTree<String> WeatherQuestion(){
        String q1 = "Temp (celcius) [0 - 50] in celcius degrees";
        String q2 = "Humidity [30 - 90] in %rh";
        String q3 = "Cloud Density: \n1.Not Dense \n2.Dense";
        String q4 = "Wind Speed [0 - 70] in knot";
        String q5 = "Cloud Clearance: \n1.Clear \n2.Dark"; 
        String q6 = "Visibility: \n1.Low \n2.Clear";

        String a1 = "Sunny";
        String a2 = "Windy";
        String a3 = "High Heat";
        String a4 = "Windy";
        String a5 = "Moderate Rain Posibility";
        String a6 = "High Rain Posibility";
        String a7 = "Possible Storm";
        String a8 = "Cold Weather";

        LinkedBinaryTree<String> n2, n3, n4, n4h, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14;
        
        n14 = new LinkedBinaryTree<String>(a1);
        n13 = new LinkedBinaryTree<String>(a2);
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

        n2 = new LinkedBinaryTree<String>(q2, n4, n3);

        n4h = new LinkedBinaryTree<String>(q4, n5, n6);

        tree = new LinkedBinaryTree<String>(q1, n2, n4h);
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

    public void weatherPrediction(){   
        LinkedBinaryTree<String> current = tree;
        System.out.println("Weather Prediction!");

        boolean enter = false;
        boolean restarted = false;
        boolean exit = false;
        boolean finished = false;
        while(finished == false){
            while(current.size() > 1){      
                if(enter == false){

                    System.out.println("Input the word:\n\"restart\" or \"res\" to start the decision tree from the root again!");
                    System.out.println("\"exit\" or \"end\" to exit the decision tree");
                    System.out.println("\"back\" or \"prev\" to return to the previous question");
                    System.out.println("Press any button! to start the decision tree!");
                    System.out.println();
                    sn.nextLine();
                    enter = true;
                }

                if(restarted == true){
                    System.out.println("\nThe Decision Tree is restarted!");
                } 
                if(exit == true){   
                    break;
                }

                String question = current.getRoot();
                String answer = null;

                while(answer == null){   
                    System.out.println(question);
                    answer = sn.nextLine();

                    if(answer.equals("restart") || answer.equals("res")){
                        current = tree;
                        restarted = true;
                        break;
                    } else if(answer.equals("back") || answer.equals("prev")){
                        System.out.println("\nBack to the previous question!");
                        current = current.getParent();
                        break;
                    } else if(answer.equals("exit")|| answer.equals("end")){
                        exit = true;
                        break;
                    }

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

                    if(answer == null){   
                        System.out.println("Invalid Input! Try Again!\n");
                    }
                }

                if(answer.equals("N")){
                    current = current.getRight();
                } else if (answer.equals("Y")){
                    current = current.getLeft();
                }
            }

            if(exit == false){
                System.out.println("\nPrediction is finished!");
                System.out.println("The result: " + current.getRoot() + " !");
            } else if (exit == true){
                finished = true;
            }
        
        
            System.out.println("\nDo you want to test it again?");
            System.out.println("Input:\n1.Yes \n2.\"Back\" to last question\n3.No or any other input to exit");
            String doItAgain = sn.nextLine();
            doItAgain = doItAgain.toLowerCase();

            if(doItAgain.equals("1") || doItAgain.equals("yes")){
                finished = false;
                current = tree;
                System.out.println();
            } else if(doItAgain.equals("2") || doItAgain.equals("back")){
                finished = false;
                current = current.getParent();
            } else{
                finished = true;
                System.out.println("Exited the Decison Tree");
            }
        }
    }   

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

    private String windSpeed(String answer){        
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

    public void levelOrder(){
        System.out.println(tree.levelOrder());
    }

}
