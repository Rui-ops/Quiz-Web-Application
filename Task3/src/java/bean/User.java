package bean;

public class User {
    
    private String Username;
    private String Password;
    private String Email;
    private int Score;
    
    public User(){
        this.Score = 0;
    }
    
    public void setUsername(String Username){
        this.Username = Username;
    }
    
    public void setPassword(String Password){
        this.Password = Password;
    }
    
    public void setEmail(String Email){
        this.Email = Email;
    }
    
    public void setScore(int Score){
        this.Score = Score;
    }
    
    public String getUsername(){
        return this.Username;
    }
    
    public String getPassword(){
        return this.Password;
    }
    
    public String getEmail(){
        return this.Email;
    }
    
    public int getScore(){
        return this.Score;
    }
    
}