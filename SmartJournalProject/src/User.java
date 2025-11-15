import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class User {

    private String email, password, displayName;
    public static Scanner input = new Scanner(System.in);



    boolean register() {
        
        try {
            PrintWriter outputStream = new PrintWriter(new FileOutputStream("UserData.txt",true));
            Scanner inputStream = new Scanner(new FileInputStream("UserData.txt"));
            System.out.print("Enter email: ");
            email = input.nextLine();

            if (!email.contains("@")) {
                System.out.println("Invaild input.");
                return false;
            }
            // 检查是否冲突
            while (inputStream.hasNextLine()) {
                String currentLine = inputStream.nextLine();
                if (currentLine.equals(email)) {
                    System.out.println("You have already registered. Please use log in.");
                    outputStream.close();
                    return false;
                }
            }

            outputStream.println(email);
            System.out.print("Enter display name: ");
            displayName = input.nextLine();
            outputStream.println(displayName);
            System.out.print("Enter password: ");
            String password = input.nextLine();
            outputStream.println(password);
            inputStream.close();
            outputStream.close();
        } 
        catch (IOException e) {
            System.out.println("Problem with file output"); 
            return false;
        }
        System.out.println("You have successfully registered!");
        return true;
    }

    boolean login() {
        try {
            Scanner inputStream = new Scanner(new FileInputStream("UserData.txt"));
            System.out.print("Enter email: ");
            email = input.nextLine();
            System.out.print("Enter password: ");
            String password = input.nextLine();
            int lineNumber = 0;
            // 查找邮箱是否存在
            while (inputStream.hasNextLine()) {
                lineNumber++;
                String currentLine = inputStream.nextLine();
                if (lineNumber % 3 == 1) {
                    if (currentLine.equals(email)) {
                        // 获取显示名称，并检查密码
                        displayName = inputStream.nextLine();
                        if (password.equals(inputStream.nextLine())) {
                            inputStream.close();
                            System.out.println("Login successful!");
                            return true;
                        }
                    }
                }
            }
            inputStream.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File was not found");
            return false;
        }
        System.out.println("Email or password incorrect!");
        return false;
    }

    String getDisplayName() {
        return displayName;
    }
    
    String getEmail() {
        return email;
    }
}
