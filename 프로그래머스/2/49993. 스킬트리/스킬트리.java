class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        outer : for (int i = 0; i < skill_trees.length; i++) {
            boolean[] visit = new boolean[skill.length()];
            
            for (int j = 0; j < skill_trees[i].length(); j++) {
                int index = skill.indexOf(skill_trees[i].charAt(j));
                
                if (index == -1) continue;
                
                for (int k = 0; k < index; k++) {
                    if (!visit[k]) continue outer;
                }
                visit[index] = true;
            }
            
            answer++;
        }
        
        return answer;
    }
}