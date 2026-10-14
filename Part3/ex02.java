import java.util.*;

public class BetterSolution {
    static Map<String, List<Integer>> map = new HashMap<>();

    public static int[] solution(String[] info, String[] query) {
        // 1. 전처리: 모든 조합 키 생성
        for (String s : info) {
            String[] p = s.split(" ");
            makeKeys(p, 0, "", Integer.parseInt(p[4]));
        }

        // 2. 점수 목록 정렬
        for (List<Integer> list : map.values()) {
            Collections.sort(list);
        }

        // 3. 쿼리 처리
        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            String[] q = query[i].replace(" and ", " ").split(" ");
            String key = q[0] + q[1] + q[2] + q[3];
            int score = Integer.parseInt(q[4]);

            List<Integer> scores = map.get(key);
            if (scores == null) {
                answer[i] = 0;
            } else {
                answer[i] = scores.size() - lowerBound(scores, score);
            }
        }
        return answer;
    }

    // 4개 조건 각각을 "원래 값" 또는 "-"로 선택해 재귀로 16개 키 생성
    static void makeKeys(String[] p, int depth, String key, int score) {
        if (depth == 4) {
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(score);
            return;
        }
        makeKeys(p, depth + 1, key + p[depth], score);
        makeKeys(p, depth + 1, key + "-", score);
    }

    // score 이상이 처음 나오는 위치
    static int lowerBound(List<Integer> list, int score) {
        int lo = 0, hi = list.size();
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (list.get(mid) < score) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
}
