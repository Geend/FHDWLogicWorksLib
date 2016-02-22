import java.io.Serializable;

/**
 * Created by torben on 20.02.16.
 */
public class AluCode implements Serializable{

    private Boolean m;

    private Boolean s3;
    private Boolean s2;
    private Boolean s1;
    private Boolean s0;

    public AluCode(Boolean m, Boolean s3, Boolean s2, Boolean s1, Boolean s0) {
        this.m = m;
        this.s3 = s3;
        this.s2 = s2;
        this.s1 = s1;
        this.s0 = s0;
    }

    public Boolean getM() {
        return m;
    }

    public void setM(Boolean m) {
        this.m = m;
    }

    public Boolean getS3() {
        return s3;
    }

    public void setS3(Boolean s3) {
        this.s3 = s3;
    }

    public Boolean getS2() {
        return s2;
    }

    public void setS2(Boolean s2) {
        this.s2 = s2;
    }

    public Boolean getS1() {
        return s1;
    }

    public void setS1(Boolean s1) {
        this.s1 = s1;
    }

    public Boolean getS0() {
        return s0;
    }

    public void setS0(Boolean s0) {
        this.s0 = s0;
    }

    @Override
    public String toString() {
        String math;
        if(m){
            math = "M";
        }
        else
        {
            math = "L";
        }

        return math + boolToChar(s3) + boolToChar(s2) + boolToChar(s1) + boolToChar(s0);
    }

    public static AluCode parseString(String string){

        String math = string.substring(0,1);

        Boolean s3 = charToBool(string.substring(1,2).charAt(0));
        Boolean s2 = charToBool(string.substring(2,3).charAt(0));
        Boolean s1 = charToBool(string.substring(3,4).charAt(0));
        Boolean s0 = charToBool(string.substring(4,5).charAt(0));


        if("M".equals(math)) {
            return new AluCode(true, s3, s2, s1, s0);
        }
        else if("L".equals(math)){
            return new AluCode(false, s3, s2, s1, s0);
        }
        else{
            throw new RuntimeException("Wrong format");
        }
    }



    private static Character boolToChar(Boolean bool){
        if(bool)
            return '1';
        else
            return '0';
    }
    private static Boolean charToBool(Character character){
        if(character == '1')
            return true;
        else
            return false;
    }

}
