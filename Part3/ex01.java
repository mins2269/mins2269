import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Exo1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        st.countTokens();
        String new_id = st.nextToken();
        String answer = solution(new_id);
        System.out.println(answer);
    }
    public static String solution(String new_id){
        String answer = "";
        // 1단계
        String lower = new_id.toLowerCase();
        // 2단계 ^ : not 역할을 함
        lower = lower.replaceAll("[^a-z0-9\\-_.]", "");
        // 3단계
//        StringBuilder sb = new StringBuilder(lower);
//        for(int i = 0; i < lower.length(); i++){
//            char ch = lower.charAt(i);
//            if(ch == '.' && i>0 && lower.charAt(i-1) == '.'){
//                continue;
//            }
//            sb.append(ch);
//        }
        lower = lower.replaceAll("\\.{2,}", ".");
        // 4단계
        if(lower.startsWith(".")){
            lower = lower.substring(1);
        }
        if(lower.endsWith(".")){
            lower = lower.substring(0, lower.length()-1);
        }
        // 5 단계
        if(lower.isBlank()){
            lower = "a";
        }
        // 6 단계
        if(lower.length() >= 15){
            lower = lower.substring(0, 15);
        }
        if(lower.endsWith(".")){
            lower = lower.substring(0, lower.length()-1);
        }
        // 7 단계
        if(lower.length() <= 2){
            StringBuilder sb = new StringBuilder();
            int n = lower.length();
            char end = lower.charAt(lower.length()-1);
            for(int i=0; i<n; i++)
                sb.append(lower.charAt(i));
            for(int i=n; i<3; i++)
                sb.append(end);
            lower = sb.toString();
        }
        answer = lower;
        return answer;
    }
    public static String solution2(String new_id){
        String s = new_id.toLowerCase();
        s = s.replaceAll("[^a-z0-9\\-_.]", "");          // 2
        s = s.replaceAll("\\.{2,}", ".");                // 3
        s = s.replaceAll("^\\.|\\.$", "");               // 4
        s = s.replaceAll("^$", "a");                     // 5
        s = s.replaceAll("^(.{15}).*$", "$1")            // 6
                .replaceAll("\\.$", "");
        s = s.replaceAll("^(.)$", "$1$1$1")     // 1자 → 3자
                .replaceAll("^(.)(.)$", "$1$2$2"); // 2자 → 마지막 문자 반복
        return s;
    }
}
