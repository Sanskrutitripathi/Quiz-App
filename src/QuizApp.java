import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class QuizApp {
    public static void main(String[] args) throws Exception 
    {
        // Load and register driver
        String driverName = "com.mysql.cj.jdbc.Driver";
        Class.forName(driverName);
        System.out.println("Driver loaded sucessfully");

        // Create connection
        String dburl = "jdbc:mysql://localhost:3306/quiz";
        String dbuser = "root";
        String dbpass = "";
        Connection con = DriverManager.getConnection(dburl, dbuser, dbpass);
        if (con != null) 
        {
            System.out.println("Connection successful");
        } 
        else 
        {
            System.out.println("Not connected");
        }

        // Details
        Scanner sc = new Scanner(System.in);
        System.out.println("-----Enter participant's details------");
        System.out.print("Name: ");
        String p_name = sc.next();

        Statement st = con.createStatement();
        int gk_score = 0;
        int sci_score = 0;
        int maths_score = 0;
        int total_score = 0;

        System.out.println();
        System.out.println("-----MENU-----");
        while (true) 
        {
            System.out.println();
            System.out.println("Enter 1 to play General Knowledge based quiz");
            System.out.println("Enter 2 to play Science based quiz");
            System.out.println("Enter 3 to play Mathematics based quiz");
            System.out.println("Enter 4 to view score");
            System.out.println("Enter 5 to exit");
            System.out.print("Enter your choice: "); 
            int ch = sc.nextInt();
            sc.nextLine(); 

            System.out.println();

            switch (ch) 
            {
                case 1:
                    // General Knowledge quiz
                    Map<Question,getOptions> random= new HashMap<>();
                    List<Question> gk = new ArrayList<>();
                    try (BufferedReader br = new BufferedReader(new FileReader("gk.txt"))) 
                    {
                        String line;
                        Question currentQuestion = null;
                        while ((line = br.readLine()) != null) 
                        {
                            if (line.startsWith("Question: ")) 
                            {
                                currentQuestion = new Question(line.substring("Question: ".length()));
                            } 
                            else if (line.startsWith("Options: ")) 
                            {
                                currentQuestion.setOptions(line.substring("Options: ".length()).split(", "));
                            } 
                            else if (line.startsWith("Answer: ")) 
                            {
                                currentQuestion.setAnswer(line.substring("Answer: ".length()));
                                gk.add(currentQuestion);
                                gk.put(currentQuestion,setOptions);
                            }
                        }
                    } 
                    catch (Exception e) 
                    {
                        e.printStackTrace();
                    }

                    if (gk.isEmpty()) 
                    {
                        System.out.println("General Knowledge quiz file is empty or could not be loaded.");
                        break;
                    }

                    for (Question q1 : gk) 
                    {
                        System.out.println(q1.getQuestion());
                        String[] options = q1.getOptions();
                        for (int i = 0; i < options.length; i++) 
                        {
                            System.out.println(options[i]);
                        }
                        System.out.print("Your answer (a/b/c): ");
                        String userAnswer = sc.nextLine().toLowerCase();

                        if (userAnswer.length() == 1 && ((userAnswer.charAt(0) >= 'a') && (userAnswer.charAt(0) <= 'd'))) 
                        {
                            if (userAnswer.equals(q1.getAnswer())) 
                            {
                                gk_score++;
                            } 
                            else 
                            {
                                System.out.println("Incorrect answer!\n");
                            }
                        } 
                        else 
                        {
                            System.out.println("Invalid input! Please enter a valid character between 'a' and 'd'.");
                            System.out.print("Your answer (a/b/c): ");
                            userAnswer = sc.nextLine().toLowerCase();
                        }
                    }
                    System.out.println("Final Score of General Knowledge quiz: " + gk_score);
                    break;

                case 2:
                    // Science quiz
                    List<Question> sci = new ArrayList<>();
                    try (BufferedReader br = new BufferedReader(new FileReader("sci.txt"))) 
                    {
                        String line;
                        Question currentQuestion = null;
                        while ((line = br.readLine()) != null) 
                        {
                            if (line.startsWith("Question: ")) 
                            {
                                currentQuestion = new Question(line.substring("Question: ".length()));
                            } 
                            else if (line.startsWith("Options: ")) 
                            {
                                currentQuestion.setOptions(line.substring("Options: ".length()).split(", "));
                            } 
                            else if (line.startsWith("Answer: ")) 
                            {
                                currentQuestion.setAnswer(line.substring("Answer: ".length()));
                                sci.add(currentQuestion);
                            }
                        }
                    } 
                    catch (Exception e) 
                    {
                        e.printStackTrace();
                    }

                    if (sci.isEmpty()) {
                        System.out.println("Science quiz file is empty or could not be loaded.");
                        break;
                    }

                    for (Question q2 : sci) 
                    {
                        System.out.println(q2.getQuestion());
                        String[] options = q2.getOptions();
                        for (int i = 0; i < options.length; i++) 
                        {
                            System.out.println(options[i]);
                        }
                        System.out.print("Your answer (a/b/c): ");
                        String userAnswer = sc.nextLine().toLowerCase();

                        if (userAnswer.length() == 1 && ((userAnswer.charAt(0) >= 'a') && (userAnswer.charAt(0) <= 'd'))) 
                        {
                            if (userAnswer.equals(q2.getAnswer())) 
                            {
                                sci_score++;
                            } 
                            else 
                            {
                                System.out.println("Incorrect answer!\n");
                            }
                        } 
                        else 
                        {
                            System.out.println("Invalid input! Please enter a valid character between 'a' and 'd'.");
                            System.out.print("Your answer (a/b/c): ");
                            userAnswer = sc.nextLine().toLowerCase();
                        }
                    }
                    System.out.println("Final Score of Science quiz: " + sci_score);
                    break;

                case 3:
                    // Mathematics quiz
                    List<Question> maths = new ArrayList<>();
                    try (BufferedReader br = new BufferedReader(new FileReader("maths.txt"))) 
                    {
                        String line;
                        Question currentQuestion = null;
                        while ((line = br.readLine()) != null) 
                        {
                            if (line.startsWith("Question: ")) 
                            {
                                currentQuestion = new Question(line.substring("Question: ".length()));
                            } 
                            else if (line.startsWith("Options: ")) 
                            {
                                currentQuestion.setOptions(line.substring("Options: ".length()).split(", "));
                            } 
                            else if (line.startsWith("Answer: ")) 
                            {
                                currentQuestion.setAnswer(line.substring("Answer: ".length()));
                                maths.add(currentQuestion);
                            }
                        }
                    } 
                    catch (Exception e) 
                    {
                        e.printStackTrace();
                    }

                    if (maths.isEmpty()) 
                    {
                        System.out.println("Mathematics quiz file is empty or could not be loaded.");
                        break;
                    }

                    for (Question q3 : maths) 
                    {
                        System.out.println(q3.getQuestion());
                        String[] options = q3.getOptions();
                        for (int i = 0; i < options.length; i++) 
                        {
                            System.out.println(options[i]);
                        }
                        System.out.print("Your answer (a/b/c): ");
                        String userAnswer = sc.nextLine().toLowerCase();

                        if (userAnswer.length() == 1 && ((userAnswer.charAt(0) >= 'a') && (userAnswer.charAt(0) <= 'd'))) 
                        {
                            if (userAnswer.equals(q3.getAnswer())) 
                            {
                                maths_score++;
                            } 
                            else 
                            {
                                System.out.println("Incorrect answer!\n");
                            }
                        } 
                        else 
                        {
                            System.out.println("Invalid input! Please enter a valid character between 'a' and 'd'.");
                            System.out.print("Your answer (a/b/c): ");
                            userAnswer = sc.nextLine().toLowerCase();
                        }
                    }
                    System.out.println("Final Score of Maths quiz: " + maths_score);
                    break;

                case 4:
                {
                    System.out.println("Your scores are:");
                    System.out.println("Score of General Knowledge quiz: " + gk_score);
                    System.out.println("Score of Science quiz: " + sci_score);
                    System.out.println("Score of Maths quiz: " + maths_score);
                    break;
                }


                case 5:
                {
                    // Insert the user's data into the database before exiting
                    total_score = gk_score + sci_score + maths_score;
                    insertUserData(con, p_name, gk_score, sci_score, maths_score, total_score);
                    System.exit(0);
                    break;
                }
                    

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }

    static void insertUserData(Connection con, String p_name, int gk_score, int sci_score, int maths_score, int total_score) 
    {
        try 
        {
            // Check if the player with the same name already exists in the database
            String checkPlayerExists = "SELECT COUNT(*) FROM scoreboard WHERE UserName = ?";
            PreparedStatement checkPlayerExistsSt = con.prepareStatement(checkPlayerExists);
            checkPlayerExistsSt.setString(1, p_name);
            ResultSet playerExistsResult = checkPlayerExistsSt.executeQuery();

            if (playerExistsResult.next()) 
            {
                int playerCount = playerExistsResult.getInt(1);
                if (playerCount > 0) 
                {
                    // Player with the same name already exists, update their scores
                    String updateScoreQuery = "UPDATE scoreboard SET gk_score = ?, sci_score = ?, maths_score = ?, total_score = ? WHERE UserName = ?";
                    PreparedStatement updateScoreStmt = con.prepareStatement(updateScoreQuery);
                    updateScoreStmt.setInt(1, gk_score);
                    updateScoreStmt.setInt(2, sci_score);
                    updateScoreStmt.setInt(3, maths_score);
                    updateScoreStmt.setInt(4, total_score);
                    updateScoreStmt.setString(5, p_name);
                    int updatedRows = updateScoreStmt.executeUpdate();

                    if (updatedRows > 0) 
                    {
                        System.out.println("Score updated successfully.");
                    } 
                    else 
                    {
                        System.out.println("Failed to update score.");
                    }
                } 
                else 
                {
                    // Player with the same name doesn't exist, insert a new record
                    String insertScoreQuery = "INSERT INTO scoreboard (UserName, gk_score, sci_score, maths_score, total_score) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement insertScoreStmt = con.prepareStatement(insertScoreQuery);
                    insertScoreStmt.setString(1, p_name);
                    insertScoreStmt.setInt(2, gk_score);
                    insertScoreStmt.setInt(3, sci_score);
                    insertScoreStmt.setInt(4, maths_score);
                    insertScoreStmt.setInt(5, total_score);
                    int insertedRows = insertScoreStmt.executeUpdate();

                    if (insertedRows > 0) 
                    {
                        System.out.println("Score inserted successfully.");
                    } 
                    else 
                    {
                        System.out.println("Failed to insert score.");
                    }
                }
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}

class Question {
    String question;
    String[] options;
    String answer;

    public Question(String question) 
    {
        this.question = question;
    }

    public String getQuestion() 
    {
        return question;
    }

    public String[] getOptions() 
    {
        return options;
    }

    public void setOptions(String[] options) 
    {
        this.options = options;
    }

    public String getAnswer() 
    {
        return answer;
    }

    public void setAnswer(String answer) 
    {
        this.answer = answer;
    }
}
