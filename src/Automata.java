import java.util.Scanner;
import java.util.Stack;

/**
 * TO-DO
 * Cases for begs, differentiate on action.
 * Simplify answers in methods.
 * Etc.
 */

/**
 * Created by blayhem on 06/07/15.
 */
public class Automata {
    public static void main(String[] args) {
        Automata("0", "");
    }
    public static String Automata(String snack, String memory){
        //UTILS
        Scanner sc = new Scanner(System.in);
        Stack<String> stack = new Stack<>();
        //DICTIONARIES
        String[] bads = {"Not", "not", "tired", "Tired", "hungry", "Hungry", "long"};
        String[] goods = {"ine", "ood", "ok", "etter"};
        String[] begs = {"Can you", "Could you", "May you", "for me", "Would you"};
        String [] aboutYou = {"Do you", "Are you"};

        //START CASE
        if(snack.equals("0")){
            if(memory.equals("")){
                System.out.println("Hi, I'm Jarvis, what's your name?");
                memory = sc.nextLine();
                String[] myName = memory.split(" ");
                if(myName[0].compareToIgnoreCase("my")==0 && myName[1].compareToIgnoreCase("name") == 0 && myName[2].compareToIgnoreCase("is") == 0)
                    memory = myName[3];
                if(myName[0].compareToIgnoreCase("i'm") == 0) memory = myName[1];
                if(myName[0].compareToIgnoreCase("i") == 0 && myName[1].compareToIgnoreCase("am") == 0) memory = myName[2];
            }
            System.out.println(Greet(memory));
            memory = sc.nextLine();

            if(memory.contains("you")) return Automata("howAmI", memory);   //if, not switch, because of boolean 'contains'
            if(memory.contains("now")) return Automata("later", memory);
            if(memory.contains("dani") || memory.contains("Dani")) return Automata("creator", memory);
            for (String bad : bads) if (memory.contains(bad)) return Automata("bad", memory);
            for (String good : goods) if (memory.contains(good)) return Automata("good", memory);
            for (String beg : begs) if (memory.contains(beg)) return Automata("do", memory);
            return Automata("notSure", memory);
        }
        System.out.println(Answer(snack));

        System.out.println("Do you want to ask me anything?");
        memory = sc.nextLine();

        if(memory.contains("dani") || memory.contains("Dani")) return Automata("creator", memory);
        for (String anAboutYou : aboutYou) if (memory.contains(anAboutYou)) return Automata("aboutMe",memory);
        for (String beg : begs) if (memory.contains(beg)) return Automata("do",memory);
        if(memory.compareToIgnoreCase("spit") == 0) {
            System.out.println(stack+"\n");
            return null;
        }
        if(memory.compareToIgnoreCase("help") == 0) return Automata("help",memory);
        if(memory.compareToIgnoreCase("no") == 0) return null;
        else{
            stack.push(memory);
            return Automata("think", memory);
        }
    }

    public static String Greet(String name){
        int seed = (int) (Math.random() * 10) % 3;
        switch (seed){
            case 0:
                return "How are you, "+name+"?";
            case 1:
                return "How are you feeling today, "+name+"?";
            case 2:
                return "How are you now, "+name+"?";
            case 3:
                return "How is the day going for you, "+name+"?";
        }
        return null;
    }
    public static String Answer(String snack){
        switch (snack){
            case "good": return "I'm glad to hear that!";
            case "bad": return "I'm sorry to hear that...";
            case "howAmI": return "I really prefer to learn about you! :)";
            case "notSure": return "I think I didn't understand, sorry.";
            case "later": return "Maybe you want to talk later?";
            case "do": return "I'm not able to do that by now, I'm still learning";
            case "aboutMe": return "I don't really know much about me...";
            case "creator": return "Dani is my creator, he's nice";
            case "think": return "Please give me some time to think about that.";
            case "help": return "Remember that I can stop asking you if you say no :)";
        }
        return null;
    }
}
