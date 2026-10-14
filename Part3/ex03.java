import java.util.*;

public class Ex03 {
    public static void main(String[] args) {
        String[] gems1 = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
        String[] gems2 = {"AA", "AB", "AC", "AA", "AC"};
        String[] gems3 = {"XYZ", "XYZ", "XYZ"};
        String[] gems4 = {"ZZZ", "YYY", "NNNN", "YYY", "BBB"};
        int[] answer = solution3(gems1);
        int[] answer2 = solution3(gems2);
        int[] answer3 = solution3(gems3);
        int[] answer4 = solution3(gems4);
        System.out.println(answer[0] + " " + answer[1]);
        System.out.println(answer2[0] + " " + answer2[1]);
        System.out.println(answer3[0] + " " + answer3[1]);
        System.out.println(answer4[0] + " " + answer4[1]);
    }
    public static int[] solution(String[] gems){
        int[] answer = {};
        // 중복 저장을 하지 않고 쥬얼리당 횟수를 map에 저장,
        // TODO 서로 다른 보석이 몇 개인지 모르기에
        Map<String, Integer> map = new LinkedHashMap<>();
        for(String gem : gems){
            map.put(gem, map.getOrDefault(gem, 0) + 1);
        }
        // 서로 다른 보석의 개수
        int gemsCount = map.size();
        // TODO 결국에는 서로 다른 보석을 담을 String 배열이 필요함
        String[] keys = new String[gemsCount];
        int index = 0;
        for(String key : map.keySet()){
            keys[index++] = key;
        }

        // 하나하나 돌며 길이 확인
        int size = gems.length;
        Map<Integer, Integer> map2 = new LinkedHashMap<>();
        for(int i = 0; i < gems.length; i++){
            List<String> list = new ArrayList<>();
            // 훔칠 보석의 종류가 하나일 때 바로 찾고 바로 반환
            list.add(gems[i]);
            if(containsAll(list, keys)){
                answer = new int[] {i+1,i+1};
                return  answer;
            }
            for(int j = i+1; j < gems.length; j++){
                list.add(gems[j]);
                if(containsAll(list, keys)){
                    if(list.size() < size){
                        size = list.size();
                        map2.put(size,i+1);
                    }
                }
            }
        }
        answer = findStartNumber(map2);
        return answer;
    }
    public static boolean containsAll(List<String> list, String[] keys){
        for(int i = 0; i < keys.length; i++){
            if(!list.contains(keys[i])){
                return false;
            }
        }
        return true;
    }
    public static int[] findStartNumber(Map<Integer, Integer> map){
        // 최소 값, 최소 힙
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(Integer key : map.keySet()){
            pq.offer(key);
        }
        // 제일 작은 사이즈
        int key = pq.poll();
        int[] answer = {map.get(key), key + map.get(key) -1};
        return answer;
    }
    public static int[] solution2(String[] gems){
        int need = new HashSet<>(Arrays.asList(gems)).size();
        Map<String, Integer> window = new HashMap<>();

        int left = 0;
        int bestlen = Integer.MAX_VALUE;
        int bestStart = 0;

        for(int right = 0; right < gems.length; right++){
            window.merge(gems[right], 1, Integer::sum);

            while(window.size() == need){
                int len = right - left + 1;
                if(len < bestlen){
                    bestlen = len;
                    bestStart = left + 1;
                }
                //
                String g = gems[left++];
                if(window.merge(g,-1, Integer::sum) == 0){
                    window.remove(g);
                }
            }
        }
        return  new int[]{bestStart, bestlen + bestStart - 1};
    }
    public static int[] solution3(String[] gems){
        int need = new HashSet<>(Arrays.asList(gems)).size();
        Map<String, Integer> window = new HashMap<>();
        int bestlen = Integer.MAX_VALUE;
        // 기준이 되는 window 선언
        int left = 0;
        int bestStart = 0;
        for(int right = left; right <= gems.length-1; right++){
            window.merge(gems[right], 1, Integer::sum);
            while(window.size() == need){
                int len = right - left + 1;
                if(len < bestlen){
                    bestlen = len;
                    bestStart = left + 1;
                }
                String g = gems[left++];
                // 합산 0이면 빼야함, 안 빼면 음수로 계속 더해짐
                if(window.merge(g,-1, Integer::sum) == 0){
                    window.remove(g);
                }
            }
        }
        return  new int[]{bestStart, bestlen + bestStart - 1};
    }
}
